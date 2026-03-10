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

import fr.paris.lutece.plugins.phraseanet.business.embed.Permalink;
import fr.paris.lutece.plugins.phraseanet.business.record.Record;
import fr.paris.lutece.plugins.phraseanet.business.record.Thumbnail;
import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;


/**
 * Record JSON parser
 */
public final class RecordJsonParser
{
    private static final String FIELD_THUMBNAIL_NAME = "thumbnail";
    private static final String FIELD_MINE_TYPE = "mime_type";
    private static final String FIELD_THUMBNAIL_HEIGHT = "height";
    private static final String FIELD_THUMBNAIL_WIDTH = "width";
    private static final String FIELD_THUMBNAIL_SIZE = "filesize";
    private static final String FIELD_THUMBNAIL_PLAYER = "player_type";
                    
    private static final String PATH_DEFAULT_THUMBNAIL = "images/admin/skin/plugins/phraseanet/none.jpg";
    private static final String TYPE_DEFAULT_THUMBNAIL = "IMAGE" ;
    private static final String MINE_DEFAULT_THUMBNAIL = "image/jpeg" ;
    private static final int HEIGHT_DEFAULT_THUMBNAIL =  80 ;
    private static final int WIDTH_DEFAULT_THUMBNAIL = 160 ;
    private static final int SIZE_DEFAULT_THUMBNAIL = 0 ;
    
    private static Logger _logger = Logger.getLogger( Constants.LOGGER );
    /** private constructor */
    private RecordJsonParser(  )
    {
        _logger.debug( "RecordJsonParser" );
    }

    /**
     * Parse a record
     * @param jsonRecord The record as JSONObject
     * @return The record
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static Record parse( JsonNode jsonRecord )
        throws PhraseanetApiCallException
    {
        
        try
        {
            Record record = new Record(  );
            int record_id = jsonRecord.path( "record_id" ).asInt( );
            record.setRecordId( record_id );
            record.setDataboxId( jsonRecord.path( "databox_id" ).asInt( ) );
            record.setMimeType( jsonRecord.path( FIELD_MINE_TYPE ).asText( ) );
            String title = jsonRecord.path( "title" ).asText( );
            record.setTitle( title );
            record.setOriginalName( jsonRecord.path( "original_name" ).asText( ) );
            record.setLastModified( jsonRecord.path( "updated_on" ).asText( ) );
            record.setCreatedOn( jsonRecord.path( "created_on" ).asText( ) );
            record.setCollectionId( jsonRecord.path( "collection_id" ).asInt( ) );
            record.setPhraseaType( jsonRecord.path( "phrasea_type" ).asText( ) );
            record.setUuid( jsonRecord.path( "uuid" ).asText( ) );
            record.setSha256( jsonRecord.path( "sha256" ).asText( ) );

            if (null == jsonRecord.get(FIELD_THUMBNAIL_NAME))
            {
                    _logger.debug("Pas de thumbnail pour le media " + title + "(id:" + record_id +")");
                    Thumbnail thumbnail = new Thumbnail(  );
                    Permalink permalink = new Permalink();
                    permalink.setUrl(PATH_DEFAULT_THUMBNAIL);
                    thumbnail.setPermalink( permalink );
                    thumbnail.setMimeType( MINE_DEFAULT_THUMBNAIL );
                    thumbnail.setHeight( HEIGHT_DEFAULT_THUMBNAIL );
                    thumbnail.setWidth( WIDTH_DEFAULT_THUMBNAIL );
                    thumbnail.setFilesize( SIZE_DEFAULT_THUMBNAIL );
                    thumbnail.setPlayerType( TYPE_DEFAULT_THUMBNAIL );
                    record.setThumbnail( thumbnail );
                }
            else
                {
                    _logger.debug("Thumbnail OK :-)");
                    JsonNode jsonThumbnail = jsonRecord.get( FIELD_THUMBNAIL_NAME );
                    Thumbnail thumbnail = new Thumbnail(  );
                    Permalink permalink = EmbedJsonParser.getPermalink( jsonThumbnail.get( "permalink" ) );
                    thumbnail.setPermalink( permalink );
                    thumbnail.setMimeType( jsonThumbnail.get( FIELD_MINE_TYPE ).asText( ) );
                    thumbnail.setHeight( jsonThumbnail.get( FIELD_THUMBNAIL_HEIGHT ).asInt( ) );
                    thumbnail.setWidth( jsonThumbnail.get( FIELD_THUMBNAIL_WIDTH ).asInt( ) );
                    thumbnail.setFilesize( jsonThumbnail.get( FIELD_THUMBNAIL_SIZE ).asInt( ) );
                    thumbnail.setPlayerType( jsonThumbnail.get( FIELD_THUMBNAIL_PLAYER ).asText( ) );
                    record.setThumbnail( thumbnail );
                }

            return record;
        }
        catch ( Exception e )
        {
            throw new PhraseanetApiCallException( "Error parsing record : " + e.getMessage( ) + " - JSON : " + jsonRecord.toString( ) );
        }
    }
}
