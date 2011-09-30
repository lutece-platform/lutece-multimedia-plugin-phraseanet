/*
 * Copyright (c) 2002-2011, Mairie de Paris
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

import fr.paris.lutece.plugins.phraseanet.business.response.Meta;
import fr.paris.lutece.plugins.phraseanet.service.parsers.MetaJsonParser;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import fr.paris.lutece.util.url.UrlItem;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.util.HashMap;


/**
 * PhraseanetApiCallService
 */
public class PhraseanetApiCallService
{
    private static final String FIELD_META = "meta";
    private static final String FIELD_RESPONSE = "response";
    private static final int SUCCESS = 200;
    private static final String PARAMETER_OAUTH_TOKEN = "oauth_token";

    public static JSONObject getResponse( String strRequest )
        throws PhraseanetApiCallException
    {
        try
        {
            UrlItem url = new UrlItem( strRequest );
            url.addParameter( PARAMETER_OAUTH_TOKEN, PhraseanetApiAuthentication.getAccessToken(  ) );

            HttpAccess httpClient = new HttpAccess(  );
            String strResponse = httpClient.doGet( url.getUrl(  ) );
            System.out.println( strResponse ); // TODO remove me

            return extractResponse( strResponse );
        }
        catch ( HttpAccessException ex )
        {
            throw new PhraseanetApiCallException( ex.getMessage(  ) );
        }
    }

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

    public static JSONObject getPostResponse( String strUrl, HashMap mapParameters )
        throws PhraseanetApiCallException
    {
        try
        {
            UrlItem url = new UrlItem( strUrl );
            mapParameters.put( PARAMETER_OAUTH_TOKEN, PhraseanetApiAuthentication.getAccessToken(  ) );

            HttpAccess httpClient = new HttpAccess(  );
            String strResponse = httpClient.doPost( url.getUrl(  ), mapParameters );
            System.out.println( strResponse ); // TODO remove me

            return extractResponse( strResponse );
        }
        catch ( HttpAccessException ex )
        {
            throw new PhraseanetApiCallException( ex.getMessage(  ) );
        }
    }
}
