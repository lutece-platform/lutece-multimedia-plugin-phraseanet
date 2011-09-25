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
package fr.paris.lutece.plugins.phraseanet.service;

import fr.paris.lutece.plugins.phraseanet.business.Record;
import fr.paris.lutece.plugins.phraseanet.business.Thumbnail;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author pierre
 */
public class RecordJsonParser
{
    public Record parse( String strJson )
    {
        JSONObject json = (JSONObject) JSONSerializer.toJSON( strJson );
        JSONObject jsonRecord = json.getJSONObject("record");
        Record record = new Record();
        record.setRecordId( jsonRecord.getInt("record_id"));
        record.setDataboxId(jsonRecord.getInt("databox_id"));
        record.setMimeType(jsonRecord.getString("mime_type"));
        record.setTitle(jsonRecord.getString("title"));
        record.setOriginalName(jsonRecord.getString("original_name"));
        record.setLastModified(jsonRecord.getString("last_modification"));
        record.setCreatedOn(jsonRecord.getString("created_on"));
        record.setCollectionId(jsonRecord.getInt("collection_id"));
        record.setPhraseaType(jsonRecord.getString("phrasea_type"));
        record.setUuid(jsonRecord.getString("uuid"));
        record.setSha256(jsonRecord.getString("sha256"));
        JSONObject jsonThumbnail = jsonRecord.getJSONObject("thumbnail");
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setUrl(jsonThumbnail.getString("url"));
        thumbnail.setMimeType(jsonThumbnail.getString("mime_type"));
        thumbnail.setHeight(jsonThumbnail.getInt("height"));
        thumbnail.setWidth(jsonThumbnail.getInt("width"));
        thumbnail.setFilesize(jsonThumbnail.getInt("filesize"));
        record.setThumbnail(thumbnail);
          
        return record;
    }
    
}
