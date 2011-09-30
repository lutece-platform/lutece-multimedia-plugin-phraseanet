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
package fr.paris.lutece.plugins.phraseanet.web;

import fr.paris.lutece.plugins.phraseanet.business.search.SearchResults;
import fr.paris.lutece.plugins.phraseanet.service.PhraseanetService;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.insert.InsertServiceJspBean;
import fr.paris.lutece.portal.web.insert.InsertServiceSelectionBean;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage PageLibrary features
 */
public class PhraseanetLinkService extends InsertServiceJspBean implements InsertServiceSelectionBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants
    private static final String TEMPLATE_SELECTOR_PAGE = "admin/plugins/phraseanet/linkservice_selector.html";
    private static final String TEMPLATE_SEARCH_RESULTS = "admin/plugins/phraseanet/search_results.html";
    private static final String MARK_SELECTED_TEXT = "selected_text";
    private static final String MARK_INPUT = "input";
    private static final String MARK_QUERY = "query";
    private static final String MARK_RESULTS = "results";
    private static final String MARK_WIDTH = "width";
    private static final String MARK_HEIGHT = "height";
    private static final String MARK_SERVER = "server";
    private static final String MARK_DATABOXES = "databoxes";
    private static final String PARAMETER_SEARCH = "search";
    private static final String PARAMETER_PROVIDER = "provider";
    private static final String PARAMETER_SELECTED_TEXT = "selected_text";
    private static final String PARAMETER_INPUT = "input";
    private static final String PARAMETER_LINK = "link";
    private static final String PARAMETER_WIDTH = "width";
    private static final String PARAMETER_HEIGHT = "height";
    private static final String PROPERTY_WIDTH = "phraseanet.videoPlayer.width";
    private static final String PROPERTY_HEIGHT = "phraseanet.videoPlayer.height";
    private static final String DEFAULT_WIDTH = "260";

    ////////////////////////////////////////////////////////////////////////////
    // Methods
    /**
     * Return the html form for image selection.
     *
     * @param request The Http Request
     * @return The html form.
     */
    public String getInsertServiceSelectorUI( HttpServletRequest request )
    {
        String strSelectedText = request.getParameter( PARAMETER_SELECTED_TEXT );
        String strInput = request.getParameter( PARAMETER_INPUT );

        String strDefaultPlayerWidth = AppPropertiesService.getProperty( PROPERTY_WIDTH, DEFAULT_WIDTH );
        String strDefaultPlayerHeight = AppPropertiesService.getProperty( PROPERTY_HEIGHT, DEFAULT_WIDTH );

        Map<String, Serializable> model = new HashMap<String, Serializable>(  );

        model.put( MARK_SELECTED_TEXT, strSelectedText );
        model.put( MARK_INPUT, strInput );
        model.put( MARK_WIDTH, strDefaultPlayerWidth );
        model.put( MARK_HEIGHT, strDefaultPlayerHeight );

        // Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_SELECTOR_PAGE, locale, model );

        return template.getHtml(  );
    }

    /**
     * Gets the search page
     * @param request The HTTP request
     * @return The search page
     */
    public String getSearch( HttpServletRequest request )
    {
        String strSelectedText = request.getParameter( PARAMETER_SELECTED_TEXT );
        String strInput = request.getParameter( PARAMETER_INPUT );
        String strQuery = request.getParameter( PARAMETER_SEARCH );
        String strWidth = request.getParameter( PARAMETER_WIDTH );
        String strHeight = request.getParameter( PARAMETER_HEIGHT );

        int nPage = 1;
        int nPerPage = 10;

        try
        {
            SearchResults results = PhraseanetService.search( strQuery, nPage, nPerPage, null );

            HashMap<String, Object> model = new HashMap<String, Object>(  );

            model.put( MARK_QUERY, ( strQuery != null ) ? strQuery : "" );
            model.put( MARK_RESULTS, results );
            model.put( MARK_SERVER, AppPropertiesService.getProperty( PhraseanetService.PROPERTY_SERVER ) );

            model.put( MARK_SELECTED_TEXT, strSelectedText );
            model.put( MARK_INPUT, strInput );

            model.put( MARK_WIDTH, strWidth );
            model.put( MARK_HEIGHT, strHeight );

            model.put( MARK_DATABOXES, PhraseanetService.getDataboxes(  ) );

            // Gets the locale of the user
            Locale locale = AdminUserService.getLocale( request );

            HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_SEARCH_RESULTS, locale, model );

            return template.getHtml(  );
        }
        catch ( PhraseanetApiCallException ex )
        {
            return ex.getMessage(  );
        }
    }

    /**
     * Insert the link into the editor
     * @param request The HTTP request
     * @return The code to insert
     */
    public String doInsertLink( HttpServletRequest request )
    {
        String strLink = request.getParameter( PARAMETER_LINK );
        String strInput = request.getParameter( PARAMETER_INPUT );
        String strProvider = request.getParameter( PARAMETER_PROVIDER );
        String strWidth = request.getParameter( PARAMETER_WIDTH );
        String strHeight = request.getParameter( PARAMETER_HEIGHT );

        // Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );

        /*
                FeedProvider videoProvider = FeedsService.getInstance().getProvider(strProvider);
                String strInsert = "no video";

                if (videoProvider instanceof VideoProvider)
                {
                    strInsert = ((VideoProvider) videoProvider).getPlayerHtml(strLink, strWidth, strHeight, locale);
                }
        */
        String strInsert = "video coming soon";

        return insertUrl( request, strInput, strInsert );
    }
}
