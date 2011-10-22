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

import fr.paris.lutece.portal.service.util.AppLogService;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * SearchCriterias
 */
public class SearchCriterias
{
    private static final String DELIMITER = ",";

    // Variables declarations 
    private String _strOrd;
    private String _strSort;
    private String _strRecordType;
    private List<String> _listBases = new ArrayList<String>(  );

    /**
     * Returns the Ord
     * @return The Ord
     */
    public String getOrd(  )
    {
        return _strOrd;
    }

    /**
     * Sets the Ord
     * @param strOrd The Ord
     */
    public void setOrd( String strOrd )
    {
        _strOrd = strOrd;
    }

    /**
     * Returns the Sort
     * @return The Sort
     */
    public String getSort(  )
    {
        return _strSort;
    }

    /**
     * Sets the Sort
     * @param strSort The Sort
     */
    public void setSort( String strSort )
    {
        _strSort = strSort;
    }

    /**
     * Returns the RecordType
     * @return The RecordType
     */
    public String getRecordType(  )
    {
        return _strRecordType;
    }

    /**
     * Sets the RecordType
     * @param strRecordType The RecordType
     */
    public void setRecordType( String strRecordType )
    {
        _strRecordType = strRecordType;
    }

    /**
     * Get bases
     * @return The bases list
     */
    public List<String> getBases(  )
    {
        return _listBases;
    }

    /**
     * Add a base
     * @param strBaseId The base id
     */
    public void addBase( String strBaseId )
    {
        _listBases.add( strBaseId );
    }

    /**
     * Set bases
     * @param strBases The bases id separated by a comma
     */
    public void setBases( String strBases )
    {
        _listBases.clear(  );

        if ( strBases != null )
        {
            StringTokenizer st = new StringTokenizer( strBases, DELIMITER );

            while ( st.hasMoreTokens(  ) )
            {
                String strIdBase = st.nextToken(  ).trim(  );

                try
                {
                    Integer.parseInt( strIdBase );
                    _listBases.add( strIdBase );
                }
                catch ( NumberFormatException e )
                {
                    AppLogService.error( "Phraseanet plugin : Invalid base id in search criteria : " + strBases );
                }
            }
        }
    }
}
