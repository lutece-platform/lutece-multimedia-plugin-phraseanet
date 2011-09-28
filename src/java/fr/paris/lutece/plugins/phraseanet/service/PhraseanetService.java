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

import fr.paris.lutece.plugins.phraseanet.business.record.Record;
import fr.paris.lutece.plugins.phraseanet.business.search.SearchResults;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallService;
import fr.paris.lutece.plugins.phraseanet.service.parsers.RecordJsonParser;
import fr.paris.lutece.plugins.phraseanet.service.parsers.SearchResultsJsonParser;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.url.UrlItem;
import java.text.MessageFormat;
import net.sf.json.JSONObject;

/**
 * PhraseanetService
 */
public class PhraseanetService
{
    private static final String PROPERTY_SERVER = "phraseanet.server";
    private static final String SERVER = AppPropertiesService.getProperty(PROPERTY_SERVER);
    private static final String PATH_GET_RECORD = "/api/v1/records/{0}/{1}/";
    private static final String PATH_SEARCH = "/api/v1/records/search/?";

    private static final String PARAMETER_QUERY = "query";
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_PER_PAGE = "per_page";
    
    
    public static Record getRecord( int nDataboxId, int nRecordId ) throws PhraseanetApiCallException
    {
        Object[] arguments = { nDataboxId , nRecordId };
        String url = SERVER + MessageFormat.format(PATH_GET_RECORD, arguments);
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse(url);
        JSONObject jsonRecord = jsonResponse.getJSONObject("record");
        return RecordJsonParser.parse( jsonRecord );
    }
    
    public static SearchResults search( String strQuery , int nPage , int nPerPage , SearchCriterias criterias ) throws PhraseanetApiCallException
    {
        UrlItem url = new UrlItem( SERVER + PATH_SEARCH );
        url.addParameter(PARAMETER_QUERY , strQuery );
        url.addParameter(PARAMETER_PAGE, String.valueOf(nPage));
        url.addParameter(PARAMETER_PER_PAGE, String.valueOf(nPerPage));
        // TODO add other criterias
        JSONObject jsonResponse = PhraseanetApiCallService.getResponse(url.getUrl());
        return SearchResultsJsonParser.parse(jsonResponse);
    }
}
