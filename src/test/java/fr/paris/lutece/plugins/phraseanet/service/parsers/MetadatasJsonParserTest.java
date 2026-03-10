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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.paris.lutece.plugins.phraseanet.business.record.Metadata;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;

/**
 * Metadatas Json Parser Test
 */
public class MetadatasJsonParserTest
{
    /**
     * Test of parse method, of class MetadatasJsonParser.
     */
    @Test
    public void testRecordMetadatasParse( ) throws IOException, PhraseanetApiCallException
    {
        System.out.println( "parse" );

        String strJson = new Utils( ).getJson( "metadatas_records.json" );
        ObjectMapper mapper = new ObjectMapper( );
        JsonNode json = mapper.readTree( strJson );
        List<Metadata> list = MetadatasJsonParser.parse( json );
        assertEquals( list.size( ), 12 );
    }

    /**
     * Test of parseByDataboxe method, of class MetadatasJsonParser.
     */
    @Test
    public void testDataboxeMetadatasParse( ) throws IOException, PhraseanetApiCallException
    {
        System.out.println( "parseByDataboxe" );

        String strJson = new Utils( ).getJson( "metadatas_databoxe.json" );
        ObjectMapper mapper = new ObjectMapper( );
        JsonNode json = mapper.readTree( strJson );
        List<Metadata> list = MetadatasJsonParser.parseByDataboxe( json );
        assertEquals( list.size( ), 2 );
    }
}
