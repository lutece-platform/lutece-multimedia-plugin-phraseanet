/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
import fr.paris.lutece.plugins.phraseanet.business.record.Record;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallService;
import fr.paris.lutece.plugins.phraseanet.service.parsers.CollectionsJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.DataboxesJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.EmbedJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.MetadatasJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.RecordJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.SearchResultsJsonParser;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


import fr.paris.lutece.plugins.phraseanet.business.account.Account;
import fr.paris.lutece.plugins.phraseanet.business.embed.Embed;
import fr.paris.lutece.plugins.phraseanet.business.record.Metadata;
import fr.paris.lutece.plugins.phraseanet.business.search.SearchResults;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



/**
 * PhraseanetService
 */
public final class PhraseanetService
{
    private static final String PROPERTY_ITEMS_PER_PAGE_VALUES = "phraseanet.itemsPerPageValues";
    private static final String PROPERTY_MEDIA_TYPE_VALUES = "phraseanet.mediaTypeValues";
    private static final String PATH_GET_RECORD = "/api/v1/records/{0}/{1}/";
    private static final String PATH_GET_DATABOXE_METADATAS = "/api/v1/databoxes/{0}/metadatas/";
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
    private static Logger _logger = Logger.getLogger( Constants.LOGGER );
    
    /** private constructor */
    private PhraseanetService()
    {
        
    }
    

    /**
     * Gets a record
     * @param nDataboxId The databox id
     * @param nRecordId The record id
     * @param account the user phraseanet account
     * @return The record
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static Record getRecord( int nDataboxId, int nRecordId, Account account )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ), Integer.toString( nRecordId ) };
        String url = account.getAccessURL(  ) + MessageFormat.format( PATH_GET_RECORD, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( url, account );
        JSONObject jsonRecord = jsonResponse.getJSONObject( "record" );

        return RecordJsonParser.parse( jsonRecord );
    }
    
    /**
     * Get metadatas for a given databoxe
     * @param nDataboxId The databox id
     * @param account the user phraseanet account
     * @return Metadatas
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Metadata> getDataboxeMetadatas( int nDataboxId, Account account )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ) };
        String url = account.getAccessURL(  ) + MessageFormat.format( PATH_GET_DATABOXE_METADATAS, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( url, account );

        return MetadatasJsonParser.parseByDataboxe( jsonResponse );
    }

    /**
     * Get metadatas for a given record
     * @param nDataboxId The databox id
     * @param nRecordId The record id
     * @param account the user phraseanet account
     * @return Metadatas
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Metadata> getRecordMetadatas( int nDataboxId, int nRecordId, Account account )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ), Integer.toString( nRecordId ) };
        String url = account.getAccessURL(  ) + MessageFormat.format( PATH_GET_RECORD_METADATAS, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( url, account );

        return MetadatasJsonParser.parse( jsonResponse );
    }

    /**
     * Searc results
     * @param strQuery Query terms
     * @param nPage Page number
     * @param nPerPage Number of items per page
     * @param criterias Criterias
     * @param account the user phraseanet account
     * @return search results
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static SearchResults search( String strQuery, int nPage, int nPerPage, SearchCriterias criterias, Account account )
        throws PhraseanetApiCallException
    {
        String strUrl = account.getAccessURL(  ) + PATH_SEARCH ;
        _logger.debug("URL de la reqette API : " + strUrl);
        Map<String, List<String>> mapParameters = new HashMap<String, List<String>>(  );
        putParameter( mapParameters, PARAMETER_QUERY, strQuery );
        putParameter( mapParameters, PARAMETER_PAGE, String.valueOf( nPage ) );
        putParameter( mapParameters, PARAMETER_PER_PAGE, String.valueOf( nPerPage ) );
        _logger.debug("Parametres de la requette : " + mapParameters);

        if ( ( criterias.getRecordType(  ) != null ) || !criterias.getRecordType(  ).equals( SEARCH_ALL ) )
        {
            putParameter( mapParameters, PARAMETER_RECORD_TYPE, criterias.getRecordType(  ) );
        }

        mapParameters.put( PARAMETER_BASES, criterias.getBases(  ) );

        // TODO add other criterias
        JSONObject jsonResponse = PhraseanetApiCallService.getPostResponse( strUrl, mapParameters, account );

        return SearchResultsJsonParser.parse( jsonResponse );
    }

    /**
     * Get databoxes
     * @param account the user phraseanet account
     * @return The lis of databoxes
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Databox> getDataboxes( Account account ) throws PhraseanetApiCallException
    {
        String strUrl = account.getAccessURL(  ) + PATH_DATABOXES;
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( strUrl, account );
        JSONArray jsonDataboxesList = jsonResponse.getJSONArray( "databoxes" );
        JSONObject jsonDataboxes = jsonDataboxesList.toJSONObject( jsonDataboxesList ) ;

        return DataboxesJsonParser.parse( jsonDataboxes );
    }

    /**
     * Get all collections of databox
     * @param nDataboxId The databox id
     * @param account the user phraseanet account
     * @return a collection list
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static List<Collection> getColletions( int nDataboxId, Account account )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { nDataboxId };
        String strUrl = account.getAccessURL(  ) + MessageFormat.format( PATH_COLLECTIONS, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( strUrl, account );

        return CollectionsJsonParser.parse( jsonResponse );
    }

    /**
     * Get embed data of a record
     * @param nDataboxId The databox id
     * @param nRecordId The record id
     * @param account the user phraseanet account
     * @return embed data
     * @throws PhraseanetApiCallException if an error occurs
     */
    public static Embed getEmbed( int nDataboxId, int nRecordId, Account account )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ), Integer.toString( nRecordId ) };
        String url = account.getAccessURL(  ) + MessageFormat.format( PATH_EMBED, arguments );
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse( url, account );
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
    
    /**
     * Get embed data of a record
     * @param nDataboxId The databox id
     * @param nRecordId The record id
     * @return embed data
     * @throws PhraseanetApiCallException if an error occurs
     */
    /**
    public static Record getBasesRecords( List<Integer> listIdBases, Account account )
        throws PhraseanetApiCallException
    {
        Object[] arguments = { Integer.toString( nDataboxId ), Integer.toString( nRecordId ) };
        String url = account.getAccessURL(  ) + PATH_SEARCH ;
        Map<String, List<String>> mapParameters = new HashMap<String, List<String>>(  );
        putParameter( mapParameters, PARAMETER_QUERY, strQuery );
        JSONObject jsonResponse = PhraseanetApiCallService.getPostResponse( strUrl, mapParameters, account );
        
        return EmbedJsonParser.parse( jsonEmbed );
    }
    */
}
