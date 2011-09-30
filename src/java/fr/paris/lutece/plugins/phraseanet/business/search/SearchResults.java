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
    private int _nCurrentPages;
    private int _nAvalaibleResults;
    private int _nTotalResults;
    private String _strError;
    private String _strWarning;
    private String _strQueryTime;
    private String _strSearchIndexes;
    private List _Results;
    private String _Query;
    private Map _Suggestions;

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
     * Returns the CurrentPages
     * @return The CurrentPages
     */
    public int getCurrentPages(  )
    {
        return _nCurrentPages;
    }

    /**
     * Sets the CurrentPages
     * @param nCurrentPages The CurrentPages
     */
    public void setCurrentPages( int nCurrentPages )
    {
        _nCurrentPages = nCurrentPages;
    }

    /**
     * Returns the AvalaibleResults
     * @return The AvalaibleResults
     */
    public int getAvalaibleResults(  )
    {
        return _nAvalaibleResults;
    }

    /**
     * Sets the AvalaibleResults
     * @param nAvalaibleResults The AvalaibleResults
     */
    public void setAvalaibleResults( int nAvalaibleResults )
    {
        _nAvalaibleResults = nAvalaibleResults;
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
        return _Results;
    }

    /**
     * Sets the Results
     * @param Results The Results
     */
    public void setResults( List Results )
    {
        _Results = Results;
    }

    /**
     * Returns the Query
     * @return The Query
     */
    public String getQuery(  )
    {
        return _Query;
    }

    /**
     * Sets the Query
     * @param Query The Query
     */
    public void setQuery( String Query )
    {
        _Query = Query;
    }

    /**
     * Returns the Suggestions
     * @return The Suggestions
     */
    public Map getSuggestions(  )
    {
        return _Suggestions;
    }

    /**
     * Sets the Suggestions
     * @param Suggestions The Suggestions
     */
    public void setSuggestions( Map Suggestions )
    {
        _Suggestions = Suggestions;
    }
}
