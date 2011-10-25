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


import fr.paris.lutece.plugins.phraseanet.business.databox.Collection;
import fr.paris.lutece.plugins.phraseanet.business.databox.Databox;
import fr.paris.lutece.plugins.phraseanet.business.embed.Embed;
import fr.paris.lutece.plugins.phraseanet.business.record.Metadata;
import fr.paris.lutece.plugins.phraseanet.business.record.Record;
import fr.paris.lutece.plugins.phraseanet.business.search.SearchResults;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallService;
import fr.paris.lutece.plugins.phraseanet.service.parsers.CollectionsJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.DataboxesJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.EmbedJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.MetadatasJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.RecordJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.SearchResultsJsonParser;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import net.sf.json.JSONObject;


/**
 * PhraseanetService
 */
public final class PhraseanetService
{
    private static final String PROPERTY_ITEMS_PER_PAGE_VALUES = "phraseanet.itemsPerPageValues";
    private static final String PROPERTY_MEDIA_TYPE_VALUES = "phraseanet.mediaTypeValues";
    private static final String SERVER = AppPropertiesService.getProperty( Constants.PROPERTY_SERVER );
    private static final String PATH_GET_RECORD = "/api/v1/records/{0}/{1}/";
    private static final String PATH_GET_RECORD_METADATAS = "/api/v1/records/{0}/{1}/metadatas/";
    private static final String PATH_SEARCH = "/api/v1/records/search/?";
    private static final String PATH_DATABOXES = "/api/v1/databoxes/list/";
    private static final String PATH_COLLECTIONS = "/api/v1/databoxes/{0}/collections/";
    private static final String PATH_EMBED = "/api/v1/records/{0}/{1}/embed/";
    private static final String PARAMETER_QUERY = "query";
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_PER_PAGE = "per_page";
    private static final String PARAMETER_RECORD_TYPE = "record_type";
    private static final String PARAMETER_BASES = "bases[]";
    private static final String DELIMITER = ",";
    private static final String SEARCH_ALL = "< All >";
    private static List<String> _listItemsPerPageValues;
    private static List<String> _listMediaTypeValues;
    
    /** private constructor */
    private PhraseanetService()
    {
        
    }
    

    /**
     * Gets a record
     * @param nDataboxId The databox id
     * @param nRecordId The record id
     * @return The record
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static Record getRecord( int nDataboxId, int nRecordId )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ), Integer.toString( nRecordId ) };
        String url = SERVER + MessageFormat.format( PATH_GET_RECORD, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( url );
        JSONObject jsonRecord = jsonResponse.getJSONObject( "record" );

        return RecordJsonParser.parse( jsonRecord );
    }

    /**
     * Get metadatas for a given record
     * @param nDataboxId The databox id
     * @param nRecordId The record id
     * @return Metadatas
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Metadata> getRecordMetadatas( int nDataboxId, int nRecordId )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ), Integer.toString( nRecordId ) };
        String url = SERVER + MessageFormat.format( PATH_GET_RECORD_METADATAS, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( url );

        return MetadatasJsonParser.parse( jsonResponse );
    }

    /**
     * Searc results
     * @param strQuery Query terms
     * @param nPage Page number
     * @param nPerPage Number of items per page
     * @param criterias Criterias
     * @return search results
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static SearchResults search( String strQuery, int nPage, int nPerPage, SearchCriterias criterias )
        throws PhraseanetApiCallException
    {
        String strUrl = SERVER + PATH_SEARCH;
        Map<String, List<String>> mapParameters = new HashMap<String, List<String>>(  );
        putParameter( mapParameters, PARAMETER_QUERY, strQuery );
        putParameter( mapParameters, PARAMETER_PAGE, String.valueOf( nPage ) );
        putParameter( mapParameters, PARAMETER_PER_PAGE, String.valueOf( nPerPage ) );

        if ( ( criterias.getRecordType(  ) != null ) || !criterias.getRecordType(  ).equals( SEARCH_ALL ) )
        {
            putParameter( mapParameters, PARAMETER_RECORD_TYPE, criterias.getRecordType(  ) );
        }

        mapParameters.put( PARAMETER_BASES, criterias.getBases(  ) );

        // TODO add other criterias
        JSONObject jsonResponse = PhraseanetApiCallService.getPostResponse( strUrl, mapParameters );

        return SearchResultsJsonParser.parse( jsonResponse );
    }

    /**
     * Get databoxes
     * @return The lis of databoxes
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Databox> getDataboxes(  ) throws PhraseanetApiCallException
    {
        String strUrl = SERVER + PATH_DATABOXES;
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( strUrl );
        JSONObject jsonDataboxes = jsonResponse.getJSONObject( "databoxes" );

        return DataboxesJsonParser.parse( jsonDataboxes );
    }

    /**
     * Get all collections of databox
     * @param nDataboxId The databox id
     * @return a collection list
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Collection> getColletions( int nDataboxId )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { nDataboxId };
        String strUrl = SERVER + MessageFormat.format( PATH_COLLECTIONS, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( strUrl );

        return CollectionsJsonParser.parse( jsonResponse );
    }

    /**
     * Get embed data of a record
     * @param nDataboxId The databox id
     * @param nRecordId The record id
     * @return embed data
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static Embed getEmbed( int nDataboxId, int nRecordId )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ), Integer.toString( nRecordId ) };
        String url = SERVER + MessageFormat.format( PATH_EMBED, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( url );
        JSONObject jsonEmbed = jsonResponse.getJSONObject( "embed" );

        return EmbedJsonParser.parse( jsonEmbed );
    }

    /**
     * Gets items per page values
     * @return items per page values
     */
    public static synchronized List<String> getItemsPerPageValues(  )
    {
        if ( _listItemsPerPageValues == null )
        {
            _listItemsPerPageValues = new ArrayList<String>(  );

            String strItemsPerPageValues = AppPropertiesService.getProperty( PROPERTY_ITEMS_PER_PAGE_VALUES );
            StringTokenizer st = new StringTokenizer( strItemsPerPageValues, DELIMITER );

            while ( st.hasMoreTokens(  ) )
            {
                String strValue = st.nextToken(  );
                _listItemsPerPageValues.add( strValue.trim(  ) );
            }
        }

        return _listItemsPerPageValues;
    }

    /**
     * Get media types values
     * @return media types values
     */
    public static synchronized List<String> getMediaTypeValues(  )
    {
        if ( _listMediaTypeValues == null )
        {
            _listMediaTypeValues = new ArrayList<String>(  );

            String strMediaTypeValues = AppPropertiesService.getProperty( PROPERTY_MEDIA_TYPE_VALUES );
            StringTokenizer st = new StringTokenizer( strMediaTypeValues, DELIMITER );

            _listMediaTypeValues.add( SEARCH_ALL );

            while ( st.hasMoreTokens(  ) )
            {
                String strValue = st.nextToken(  );
                _listMediaTypeValues.add( strValue.trim(  ) );
            }
        }

        return _listMediaTypeValues;
    }

    /**
     * Add parameters to a map
     * @param map The map
     * @param strKey The parameter key
     * @param strValue The parameter value
     */
    private static void putParameter( Map<String, List<String>> map, String strKey, String strValue )
    {
        List<String> listValue = new ArrayList<String>(  );
        listValue.add( strValue );
        map.put( strKey, listValue );
    }
}
