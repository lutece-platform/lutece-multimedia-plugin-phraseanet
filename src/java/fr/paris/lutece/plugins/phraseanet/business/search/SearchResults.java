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
package fr.paris.lutece.plugins.phraseanet.business.search;

import java.util.List;
import java.util.Map;


/**
 * SearchResults
 */
public class SearchResults
{
    // Variables declarations 
    private int _nTotalPages;
    private int _nCurrentPage;
    private int _nAvailableResults;
    private int _nTotalResults;
    private String _strError;
    private String _strWarning;
    private String _strQueryTime;
    private String _strSearchIndexes;
    private List _listResults;
    private String _strQuery;
    private Map _mapSuggestions;

    /**
     * Returns the TotalPages
     * @return The TotalPages
     */
    public int getTotalPages(  )
    {
        return _nTotalPages;
    }

    /**
     * Sets the TotalPages
     * @param nTotalPages The TotalPages
     */
    public void setTotalPages( int nTotalPages )
    {
        _nTotalPages = nTotalPages;
    }

    /**
     * Returns the CurrentPage
     * @return The CurrentPage
     */
    public int getCurrentPage(  )
    {
        return _nCurrentPage;
    }

    /**
     * Sets the CurrentPage
     * @param nCurrentPage The CurrentPage
     */
    public void setCurrentPage( int nCurrentPage )
    {
        _nCurrentPage = nCurrentPage;
    }

    /**
     * Returns the AvailableResults
     * @return The AvailableResults
     */
    public int getAvailableResults(  )
    {
        return _nAvailableResults;
    }

    /**
     * Sets the AvailableResults
     * @param nAvailableResults The AvailableResults
     */
    public void setAvailableResults( int nAvailableResults )
    {
        _nAvailableResults = nAvailableResults;
    }

    /**
     * Returns the TotalResults
     * @return The TotalResults
     */
    public int getTotalResults(  )
    {
        return _nTotalResults;
    }

    /**
     * Sets the TotalResults
     * @param nTotalResults The TotalResults
     */
    public void setTotalResults( int nTotalResults )
    {
        _nTotalResults = nTotalResults;
    }

    /**
     * Returns the Error
     * @return The Error
     */
    public String getError(  )
    {
        return _strError;
    }

    /**
     * Sets the Error
     * @param strError The Error
     */
    public void setError( String strError )
    {
        _strError = strError;
    }

    /**
     * Returns the Warning
     * @return The Warning
     */
    public String getWarning(  )
    {
        return _strWarning;
    }

    /**
     * Sets the Warning
     * @param strWarning The Warning
     */
    public void setWarning( String strWarning )
    {
        _strWarning = strWarning;
    }

    /**
     * Returns the QueryTime
     * @return The QueryTime
     */
    public String getQueryTime(  )
    {
        return _strQueryTime;
    }

    /**
     * Sets the QueryTime
     * @param strQueryTime The QueryTime
     */
    public void setQueryTime( String strQueryTime )
    {
        _strQueryTime = strQueryTime;
    }

    /**
     * Returns the SearchIndexes
     * @return The SearchIndexes
     */
    public String getSearchIndexes(  )
    {
        return _strSearchIndexes;
    }

    /**
     * Sets the SearchIndexes
     * @param strSearchIndexes The SearchIndexes
     */
    public void setSearchIndexes( String strSearchIndexes )
    {
        _strSearchIndexes = strSearchIndexes;
    }

    /**
     * Returns the Results
     * @return The Results
     */
    public List getResults(  )
    {
        return _listResults;
    }

    /**
     * Sets the Results
     * @param listResults The Results
     */
    public void setResults( List listResults )
    {
        _listResults = listResults;
    }

    /**
     * Returns the Query
     * @return The Query
     */
    public String getQuery(  )
    {
        return _strQuery;
    }

    /**
     * Sets the Query
     * @param strQuery The Query
     */
    public void setQuery( String strQuery )
    {
        _strQuery = strQuery;
    }

    /**
     * Returns the Suggestions
     * @return The Suggestions
     */
    public Map getSuggestions(  )
    {
        return _mapSuggestions;
    }

    /**
     * Sets the Suggestions
     * @param mapSuggestions The Suggestions
     */
    public void setSuggestions( Map mapSuggestions )
    {
        _mapSuggestions = mapSuggestions;
    }
}
