/*
 * Copyright (c) 2002-2012, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.phraseanet.service.api;

import fr.paris.lutece.plugins.phraseanet.business.account.Account;
import fr.paris.lutece.plugins.phraseanet.business.response.Meta;
import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.plugins.phraseanet.service.parsers.MetaJsonParser;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import fr.paris.lutece.util.url.UrlItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;


/**
 * PhraseanetApiCallService
 */
public final class PhraseanetApiCallService
{
    private static final String FIELD_META = "meta";
    private static final String FIELD_RESPONSE = "response";
    private static final int SUCCESS = 200;
    private static final String PARAMETER_OAUTH_TOKEN = "oauth_token";
    private static Logger _logger = Logger.getLogger( Constants.LOGGER );

    /** Private constructor */
    private PhraseanetApiCallService(  )
    {
    }

    /**
     * Get a response for a GET request
     * @param strRequest The request
     * @param account the user phraseanet account
     * @return The response as a JSON object
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static JSONObject getResponse( String strRequest, Account account )
        throws PhraseanetApiCallException
    {
        try
        {
            UrlItem url = new UrlItem( strRequest );
            url.addParameter( PARAMETER_OAUTH_TOKEN, PhraseanetApiAuthentication.getAccessToken( account ) );

            HttpAccess httpClient = new HttpAccess(  );
            String strResponse = httpClient.doGet( url.getUrl(  ) );

            _logger.debug( strResponse );

            return extractResponse( strResponse );
        }
        catch ( HttpAccessException ex )
        {
            throw new PhraseanetApiCallException( ex.getMessage(  ) );
        }
    }

    /**
     * Extract response as JSON object
     * @param strResponse The complete response as String
     * @return The response as JSON object
     * @throws PhraseanetApiCallException if an error occurs
     */
    static JSONObject extractResponse( String strResponse )
        throws PhraseanetApiCallException
    {
        JSONObject json = (JSONObject) JSONSerializer.toJSON( strResponse );
        JSONObject jsonMeta = json.getJSONObject( FIELD_META );
        Meta meta = MetaJsonParser.parse( jsonMeta );

        if ( meta.getHttpCode(  ) != SUCCESS )
        {
            throw new PhraseanetApiCallException( meta.getErrorMessage(  ) + " : " + meta.getErrorDetails(  ) );
        }

        JSONObject jsonResponse = json.getJSONObject( FIELD_RESPONSE );

        return jsonResponse;
    }

    /**
     * Get the response of a POST request
     * @param strUrl The URL
     * @param mapParameters The parameters
     * @param account the user account
     * @return The response as a JSON object
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static JSONObject getPostResponse( String strUrl, Map<String, List<String>> mapParameters, Account account )
        throws PhraseanetApiCallException
    {
        try
        {
            List<String> listParam = new ArrayList<String>(  );
            listParam.add( PhraseanetApiAuthentication.getAccessToken( account ) );

            mapParameters.put( PARAMETER_OAUTH_TOKEN, listParam );

            HttpAccess httpClient = new HttpAccess(  );
            String strResponse = httpClient.doPostMultiValues( strUrl, mapParameters );
            _logger.debug( strResponse );
            
            return extractResponse( strResponse );
        }
        catch ( HttpAccessException ex )
        {
            throw new PhraseanetApiCallException( ex.getMessage(  ) );
        }
    }
}
