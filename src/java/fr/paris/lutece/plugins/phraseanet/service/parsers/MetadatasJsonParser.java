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
package fr.paris.lutece.plugins.phraseanet.service.parsers;

import fr.paris.lutece.plugins.phraseanet.business.record.Metadata;
import fr.paris.lutece.plugins.phraseanet.business.record.MetadataValue;
import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import fr.paris.lutece.portal.service.util.AppLogService;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * Metadata Json Parser
 */
public final class MetadatasJsonParser
{  
    private static Logger _logger = Logger.getLogger( Constants.LOGGER );
    
    /** private constructor */
    private MetadatasJsonParser(  )
    {
        _logger.debug( "MetadatasJsonParser" );
    }
    

    /**
     * Parse a list of metadatas
     * @param jsonResponse The response as JSONObject
     * @return The list
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Metadata> parse( JSONObject jsonResponse )
    throws PhraseanetApiCallException
    {   
        try
        {
            List<Metadata> listMetadatas = new ArrayList<Metadata>(  );
            //JSONObject jsonMetadatas = jsonResponse.getJSONObject( "metadatas" );
            JSONArray jsonMetadatasList = jsonResponse.getJSONArray( "record_metadatas" );
            JSONObject jsonMetadatas = jsonMetadatasList.toJSONObject( jsonMetadatasList ) ;
            Iterator i = jsonMetadatas.keys(  );
            while ( i.hasNext(  ) )
            {
                String strKey = (String) i.next(  );
                JSONObject jsonMetadata = jsonMetadatas.getJSONObject( strKey );
                Metadata metadata = new Metadata(  );
                metadata.setMetaId( jsonMetadata.getInt( "meta_id" ) );
                metadata.setMetaStructureId( jsonMetadata.getInt( "meta_structure_id" ) );
                metadata.setName( jsonMetadata.getString( "name" ) );
                metadata.setValue( jsonMetadata.getString( "value" ) );
                listMetadatas.add( metadata );
            }

            return listMetadatas;
        }
        catch ( JSONException e )
        {//throw new PhraseanetApiCallException( "Error parsing metadatas : " + e.getMessage(  ) + " - JSON : " + jsonResponse.toString( 4 ) );
            AppLogService.error( "Error parsing metadatas " + e.getMessage()+ " - JSON : " + jsonResponse.toString( 4 ) );
            return null;
        }     
    }

    /**
     * Parse a list of metadatas
     * @param jsonResponse The response as JSONObject
     * @return The list
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Metadata> parseByDataboxe( JSONObject jsonResponse )
        throws PhraseanetApiCallException
    {
        try
        {
            List<Metadata> listMetadatas = new ArrayList<Metadata>(  );
            JSONArray jsonMetadatasList = jsonResponse.getJSONArray( "document_metadatas" );
            _logger.debug("Liste des metadatas : " + jsonMetadatasList ) ;
            JSONObject jsonMetadatas = jsonMetadatasList.toJSONObject(jsonMetadatasList);
            _logger.debug("JSONObject : " + jsonMetadatas ) ;
            Iterator i = jsonMetadatas.keys(  );

            while ( i.hasNext(  ) )
            {
                String strKey = (String) i.next(  );
                JSONObject jsonMetadata = jsonMetadatas.getJSONObject( strKey );
                Metadata metadata = new Metadata(  );
                metadata.setName( jsonMetadata.getString( "name" ) );
                listMetadatas.add( metadata );
            }


            return listMetadatas;
        }
        catch ( JSONException e )
        {
            throw new PhraseanetApiCallException( "Error parsing metadatas : " + e.getMessage(  ) + " - JSON : " +
                jsonResponse.toString( 4 ) );
        }
    }
}
