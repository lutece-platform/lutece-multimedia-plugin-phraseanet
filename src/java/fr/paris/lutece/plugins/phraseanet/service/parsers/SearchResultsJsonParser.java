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

import fr.paris.lutece.plugins.phraseanet.business.record.Record;
import fr.paris.lutece.plugins.phraseanet.business.search.SearchResults;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author pierre
 */
public class SearchResultsJsonParser
{
    /** private constructor */
    private SearchResultsJsonParser(  )
    {
    }

    /**
     * Parse for search results
     * @param jsonResponse The response as JSONObject
     * @return The search results
     */
    public static SearchResults parse( JSONObject jsonResponse )
        throws PhraseanetApiCallException
    {
        try
        {
            SearchResults results = new SearchResults(  );
            results.setTotalPages( jsonResponse.getInt( "total_pages" ) );
            results.setCurrentPage( jsonResponse.getInt( "current_page" ) );
            results.setAvailableResults( jsonResponse.getInt( "available_results" ) );
            results.setTotalResults( jsonResponse.getInt( "total_results" ) );
            results.setError( jsonResponse.getString( "error" ) );
            results.setWarning( jsonResponse.getString( "warning" ) );
            results.setQueryTime( jsonResponse.getString( "query_time" ) );
            results.setSearchIndexes( jsonResponse.getString( "search_indexes" ) );
            results.setQuery( jsonResponse.getString( "query" ) );

            JSONArray jsonResults = jsonResponse.getJSONArray( "results" );
            List<Record> listResults = new ArrayList<Record>(  );
            Iterator i = jsonResults.iterator(  );

            while ( i.hasNext(  ) )
            {
                JSONObject jsonResult = (JSONObject) i.next(  );
                Record record = RecordJsonParser.parse( jsonResult );
                listResults.add( record );
            }

            results.setResults( listResults );

            // TODO suggestions
            return results;
        }
        catch ( JSONException e )
        {
            throw new PhraseanetApiCallException( "Error parsing databoxes : " + e.getMessage(  ) + " - JSON : " +
                jsonResponse.toString( 4 ) );
        }
    }
}
