/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import fr.paris.lutece.util.url.UrlItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * PhraseaApiAuthentication
 */
public final class PhraseanetApiAuthentication
{   
    //parameters
    private static final String PARAMETER_CLIENT_ID = "client_id";
    private static final String PARAMETER_REDIRECT_URI = "redirect_uri";
    private static final String PARAMETER_REDIRECT_URI_VALUE = "urn:ietf:wg:oauth:2.0:oob";
    private static final String PARAMETER_RESPONSE_TYPE = "response_type";
    private static final String PARAMETER_RESPONSE_TYPE_VALUE = "code";
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_ACTION_LOGIN = "action_login";
    private static final String PARAMETER_ACTION_LOGIN_VALUE = "ok";
    private static final String PARAMETER_ACCESS_TOKEN = "access_token";
    
    private static Logger _logger = Logger.getLogger( Constants.LOGGER );

    /** private constructor */
    private PhraseanetApiAuthentication(  )
    {
    	
    }   

    
    /**
     * Get Access Token with an account
     * @param account API Phraseanet Account
     * @return the _strAccessToken
     * @throws PhraseanetApiCallException PhraseanetApiCallException
     */
    public static String getAccessToken( Account account )throws PhraseanetApiCallException
    {
        /**
    	String strAccessToken = StringUtils.EMPTY;
    	try
        {
			UrlItem url = new UrlItem( account.getAuthorizeEndPoint(  ) );
			url.addParameter( PARAMETER_CLIENT_ID, account.getCustomerId(  ) );
			url.addParameter( PARAMETER_REDIRECT_URI, PARAMETER_REDIRECT_URI_VALUE );
			url.addParameter( PARAMETER_RESPONSE_TYPE, PARAMETER_RESPONSE_TYPE_VALUE );
			url.addParameter( PARAMETER_LOGIN, account.getPhraseanetId(  ) );
			url.addParameter( PARAMETER_PASSWORD, account.getPassword(  ) );
			url.addParameter( PARAMETER_ACTION_LOGIN, PARAMETER_ACTION_LOGIN_VALUE );
			HttpAccess httpClient = new HttpAccess(  );
            String strResponse = httpClient.doGet( url.getUrl(  ) );
            if( strResponse.contains( PARAMETER_ACCESS_TOKEN ) )
            {
            	String[] tab = strResponse.substring( strResponse.indexOf( "value=" ) ).split( "\"" ) ;
                strAccessToken = tab[1];
            }       
        }
        catch ( HttpAccessException ex )
        {
            throw new PhraseanetApiCallException( ex.getMessage(  ) );
        }
       
        return strAccessToken;
        */
       
      return account.getToken(  );
    }
}
