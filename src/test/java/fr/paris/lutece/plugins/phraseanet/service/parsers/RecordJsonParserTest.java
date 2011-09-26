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

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import fr.paris.lutece.plugins.phraseanet.service.parsers.RecordJsonParser;
import fr.paris.lutece.plugins.phraseanet.business.record.Record;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RecordJsonParserTest
 */
public class RecordJsonParserTest
{
    private static final String RECORD_JSON = "{\"record\": {"+
            "\"databox_id\": 1," +
            "\"record_id\": 295," +
            "\"mime_type\": \"image/gif\"," +
            "\"title\": \"Argentina.gif\"," +
            "\"original_name\": \"Argentina.gif\"," +
            "\"last_modification\": \"2011-03-24T12:05:18+01:00\"," +
            "\"created_on\": \"2011-03-24T12:05:04+01:00\"," +
            "\"phrasea_type\": \"image\"," +
            "\"uuid\": \"b65b957f-ed22-4291-9811-35c09a43ba28\"," +
            "\"collection_id\": 1," +
            "\"sha256\": \"669f161400fe81fa3024b074a1c0cfe0d0d7643470a2f450e6b005ce8daf0f8d\"," +
            "\"thumbnail\": {" +
                "\"url\": \"/web//db_trunk/subdefs/2011/03/24/0000/295_thumbnail.jpg\"," +
                "\"mime_type\": \"image/jpeg\"," +
                "\"height\": 48," +
                "\"width\": 48," +
                "\"filesize\": 1017" +
            "}," +
            "\"technical_informations\": []" +
        "}}";

    /**
     * Test of parse method, of class RecordJsonParser.
     */
    @Test
    public void testParse()
    {
        System.out.println("parse");
        JSONObject json = (JSONObject) JSONSerializer.toJSON( RECORD_JSON );
        JSONObject jsonRecord = json.getJSONObject("record");
        Record record = RecordJsonParser.parse( jsonRecord );
        assertEquals( record.getDataboxId() , 1 );
        assertEquals( record.getRecordId() , 295 );
        assertEquals( record.getMimeType() , "image/gif" );
        assertEquals( record.getTitle() , "Argentina.gif" );
        assertEquals( record.getOriginalName() , "Argentina.gif" );
        assertEquals( record.getLastModified() , "2011-03-24T12:05:18+01:00" );
        assertEquals( record.getCreatedOn() , "2011-03-24T12:05:04+01:00" );
        assertEquals( record.getCollectionId() , 1 );
        assertEquals( record.getSha256() , "669f161400fe81fa3024b074a1c0cfe0d0d7643470a2f450e6b005ce8daf0f8d" );
        assertEquals( record.getThumbnail().getUrl() , "/web//db_trunk/subdefs/2011/03/24/0000/295_thumbnail.jpg" );
        assertEquals( record.getThumbnail().getMimeType() , "image/jpeg" );
        assertEquals( record.getThumbnail().getHeight() , 48 );
        assertEquals( record.getThumbnail().getWidth() , 48 );
        assertEquals( record.getThumbnail().getFilesize() , 1017 );
        assertEquals( record.getPhraseaType() , "image" );
        assertEquals( record.getUuid() , "b65b957f-ed22-4291-9811-35c09a43ba28" );

    }
}
