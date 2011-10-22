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
package fr.paris.lutece.plugins.phraseanet.service.parsers;

import fr.paris.lutece.plugins.phraseanet.business.response.Meta;

import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;


/**
 * MetaJsonParserTest
 */
public class MetaJsonParserTest
{
    /**
     * Test of parse method, of class RecordJsonParser.
     */
    @Test
    public void testParse(  ) throws IOException, PhraseanetApiCallException
    {
        System.out.println( "parse" );

        String strJson = new Utils(  ).getJson( "meta.json" );
        JSONObject jsonMeta = (JSONObject) JSONSerializer.toJSON( strJson );
        Meta meta = MetaJsonParser.parse( jsonMeta );
        assertEquals( meta.getApiVersion(  ), "1.0" );
        assertEquals( meta.getRequest(  ), "GET /api/v1/feeds/288/content/" );
        assertEquals( meta.getResponseTime(  ), "2011-07-27T15:52:04+02:00" );
        assertEquals( meta.getHttpCode(  ), 200 );
        assertEquals( meta.getCharset(  ), "UTF-8" );
    }
}
