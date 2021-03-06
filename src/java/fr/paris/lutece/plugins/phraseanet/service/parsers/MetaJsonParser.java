/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.phraseanet.service.parsers;

import fr.paris.lutece.plugins.phraseanet.business.response.Meta;
import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


/**
 * MetaJsonParser
 */
public final class MetaJsonParser
{
    private static Logger _logger = Logger.getLogger( Constants.LOGGER );
    /** private constructor */
    private MetaJsonParser(  )
    {
    }

    /**
     * Parse meta
     * @param jsonMeta The meta as JSONObject
     * @return The meta
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static Meta parse( JSONObject jsonMeta ) throws PhraseanetApiCallException
    {
        _logger.debug( "MetaJsonParser" );
        try
        {
            Meta meta = new Meta(  );
            meta.setApiVersion( jsonMeta.getString( "api_version" ) );
            meta.setRequest( jsonMeta.getString( "request" ) );
            meta.setResponseTime( jsonMeta.getString( "response_time" ) );
            meta.setHttpCode( jsonMeta.getInt( "http_code" ) );
            meta.setErrorMessage( jsonMeta.getString( "error_message" ) );
            meta.setErrorDetails( jsonMeta.getString( "error_details" ) );
            meta.setCharset( jsonMeta.getString( "charset" ) );

            return meta;
        }
        catch ( JSONException e )
        {
            throw new PhraseanetApiCallException( "Error parsing meta : " + e.getMessage(  ) + " - JSON : " +
                jsonMeta.toString( 4 ) );
        }
    }
}
