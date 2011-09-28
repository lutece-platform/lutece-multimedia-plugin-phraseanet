/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.phraseanet.service.parsers;

import fr.paris.lutece.plugins.phraseanet.business.record.Record;
import fr.paris.lutece.plugins.phraseanet.business.search.SearchResults;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author pierre
 */
public class SearchResultsJsonParser 
{
    public static SearchResults parse( JSONObject jsonResponse )
    {
        SearchResults results = new SearchResults();
        results.setTotalPages( jsonResponse.getInt("total_pages"));
        results.setCurrentPages( jsonResponse.getInt("current_page"));
        results.setAvalaibleResults( jsonResponse.getInt("available_results"));
        results.setTotalResults( jsonResponse.getInt("total_results"));
        results.setError( jsonResponse.getString("error"));
        results.setWarning( jsonResponse.getString("warning"));
        results.setQueryTime( jsonResponse.getString("query_time"));
        results.setSearchIndexes( jsonResponse.getString("search_indexes"));
        results.setQuery( jsonResponse.getString("query"));
        JSONArray jsonResults = jsonResponse.getJSONArray("results");
        List<Record> listResults = new ArrayList<Record>();
        Iterator i = jsonResults.iterator();
        while( i.hasNext() )
        {
            JSONObject jsonResult = (JSONObject) i.next();
            Record record = RecordJsonParser.parse( jsonResult );
            listResults.add(record);
        }
        // TODO suggestions
        return results;
    }
    
}
