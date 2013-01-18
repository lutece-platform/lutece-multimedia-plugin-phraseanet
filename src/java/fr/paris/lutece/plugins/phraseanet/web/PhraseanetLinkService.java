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
package fr.paris.lutece.plugins.phraseanet.web;

import fr.paris.lutece.plugins.phraseanet.business.account.Account;
import fr.paris.lutece.plugins.phraseanet.business.account.AccountHome;
import fr.paris.lutece.plugins.phraseanet.business.embed.Embed;
import fr.paris.lutece.plugins.phraseanet.business.media.MediaHandler;
import fr.paris.lutece.plugins.phraseanet.business.media.MediaHandlerHome;
import fr.paris.lutece.plugins.phraseanet.business.record.Metadata;
import fr.paris.lutece.plugins.phraseanet.business.search.SearchResults;
import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.plugins.phraseanet.service.PhraseanetService;
import fr.paris.lutece.plugins.phraseanet.service.SearchCriterias;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.insert.InsertServiceJspBean;
import fr.paris.lutece.portal.web.insert.InsertServiceSelectionBean;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.string.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;


/**
 * This class provides the user interface to manage PageLibrary features
 */
public class PhraseanetLinkService extends InsertServiceJspBean implements InsertServiceSelectionBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants
    private static final String TEMPLATE_CHOOSE_MEDIA = "admin/plugins/phraseanet/choose_media.html";
    private static final String TEMPLATE_SEARCH_FORM = "admin/plugins/phraseanet/search_form.html";
    private static final String TEMPLATE_SEARCH_RESULTS = "admin/plugins/phraseanet/search_results.html";
    private static final String TEMPLATE_ERROR = "admin/plugins/phraseanet/error.html";
    private static final String MARK_MEDIA_HANDLERS = "media_handlers_list";
    private static final String MARK_MEDIA_HANDLER = "media_handler";
    private static final String MARK_INPUT = "input";
    private static final String MARK_QUERY = "query";
    private static final String MARK_RESULTS = "results";
    private static final String MARK_SERVER = "server";
    private static final String MARK_ITEMS_PER_PAGE_VALUES = "items_per_page_values";
    private static final String MARK_ITEMS_PER_PAGE = "items_per_page_selected";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_EMBED = "embed";
    private static final String MARK_METADATAS = "metadatas";
    private static final String MARK_ERROR = "error";
    private static final String PARAMETER_RECORD = "record";
    private static final String PARAMETER_SEARCH = "search";
    private static final String PARAMETER_MEDIA_HANDLER = "media_handler";
    private static final String PARAMETER_INPUT = "input";
    private static final String PARAMETER_DATABOX = "databox";
    private static final String PARAMETER_CURRENT_PAGE = "current_page";
    private static final String PARAMETER_ITEMS_PER_PAGE = "items_per_page";
    private static final String PROPERTY_ITEMS_PER_PAGE_DEFAULT = "phraseanet.itemsPerPageDefault";
    private static final String URL_JSP_ERROR = "PhraseanetError.jsp";
    private static Logger _logger = Logger.getLogger( Constants.LOGGER );
    private String _strError;

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
        return getChooseMedia( request );
    }

    /**
     * Get choose media page
     * @param request The HTTP request
     * @return The choose media page
     */
    public String getChooseMedia( HttpServletRequest request )
    {
        String strInput = request.getParameter( PARAMETER_INPUT );
        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( MARK_INPUT, strInput );
        model.put( MARK_MEDIA_HANDLERS, MediaHandlerHome.findAll(  ) );

        // Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CHOOSE_MEDIA, locale, model );

        return template.getHtml(  );
    }

    /**
     * Get the search form
     * @param request The HTTP request
     * @return The search form
     */
    public String getSearchForm( HttpServletRequest request )
    {
        _logger.debug( "getSearchForm" );
        String strInput = request.getParameter( PARAMETER_INPUT );
        String strMediaHandler = request.getParameter( PARAMETER_MEDIA_HANDLER );

        String strDefaultItemsPerPage = AppPropertiesService.getProperty( PROPERTY_ITEMS_PER_PAGE_DEFAULT );

        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( MARK_INPUT, strInput );
        model.put( MARK_MEDIA_HANDLER, strMediaHandler );
        model.put( MARK_ITEMS_PER_PAGE_VALUES, PhraseanetService.getItemsPerPageValues(  ) );
        model.put( MARK_ITEMS_PER_PAGE, strDefaultItemsPerPage );

        // Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_SEARCH_FORM, locale, model );

        return template.getHtml(  );
    }

    /**
     * Gets the search page
     * @param request The HTTP request
     * @return The search page
     */
    public String getSearch( HttpServletRequest request )
    {
        String strInput = request.getParameter( PARAMETER_INPUT );
        String strMediaHandler = request.getParameter( PARAMETER_MEDIA_HANDLER );
        String strQuery =  request.getParameter( PARAMETER_SEARCH );
        String strCurrentPage = request.getParameter( PARAMETER_CURRENT_PAGE );
        String strItemsPerPage = request.getParameter( PARAMETER_ITEMS_PER_PAGE );
        
        _logger.debug( "Query search : " + strQuery );


        int nMediaHandlerId = Integer.parseInt( strMediaHandler );
        MediaHandler mh = MediaHandlerHome.findByPrimaryKey( nMediaHandlerId );
        int nPage = ( strCurrentPage != null ) ? Integer.parseInt( strCurrentPage ) : 1;
        int nPerPage = ( strItemsPerPage != null ) ? Integer.parseInt( strItemsPerPage ) : 10;

        try
        {
            SearchCriterias criterias = new SearchCriterias(  );
            criterias.setRecordType( mh.getMediaType(  ) );
            
        	Account account = AccountHome.findByPrimaryKey( mh.getIdAccount(  ) );
        	
            SearchResults results = PhraseanetService.search( StringUtil.replaceAccent( strQuery ), nPage, nPerPage, criterias, account );

            HashMap<String, Object> model = new HashMap<String, Object>(  );

            model.put( MARK_INPUT, strInput );
            model.put( MARK_MEDIA_HANDLER, strMediaHandler );

            model.put( MARK_QUERY, ( strQuery != null ) ? strQuery : "" );
            model.put( MARK_RESULTS, results );
            model.put( MARK_SERVER, account.getAccessURL(  ) );

            model.put( MARK_ITEMS_PER_PAGE_VALUES, PhraseanetService.getItemsPerPageValues(  ) );
            model.put( MARK_ITEMS_PER_PAGE, strItemsPerPage );

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
        _logger.debug( "doInsertLink" );

        String strInput = request.getParameter( PARAMETER_INPUT );
        String strMediaHandler = request.getParameter( PARAMETER_MEDIA_HANDLER );
        String strRecordId = request.getParameter( PARAMETER_RECORD );
        String strDataboxId = request.getParameter( PARAMETER_DATABOX );

        // Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );

        int nMediaHandlerId = Integer.parseInt( strMediaHandler );
        MediaHandler mh = MediaHandlerHome.findByPrimaryKey( nMediaHandlerId );
        int nRecordId = Integer.parseInt( strRecordId );
        int nDataboxId = Integer.parseInt( strDataboxId );

        try
        {
        	//FIXME test d'un compte provisoir
        	Account account = AccountHome.findByPrimaryKey( mh.getIdAccount(  ) );
        	
            Embed embed = PhraseanetService.getEmbed( nDataboxId, nRecordId, account );
            _logger.debug( "embed : " + embed );
            List<Metadata> listMetadatas = PhraseanetService.getRecordMetadatas( nDataboxId, nRecordId, account );
            _logger.debug( "listMetadatas : " + listMetadatas );

            Map<String, Object> model = new HashMap<String, Object>(  );
            model.put( MARK_LOCALE, locale );
            model.put( MARK_EMBED, embed );
            model.put( MARK_METADATAS, listMetadatas );
           
            HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( mh.getInsertTemplate(  ), locale, model );
                      
            _logger.debug( "Template : " + mh.getInsertTemplate() );
                      
            String strInsert = t.getHtml(  );
            
            _logger.debug( "INSERT Html : \"" + strInsert + "\"" );

            return insertUrl( request, strInput, strInsert );
        }
        catch ( PhraseanetApiCallException ex )
        {
            _strError = ex.getMessage(  );

            return URL_JSP_ERROR;
        }
    }


    /**
     * Get The Error page
     * @param request The HTTP request
     * @return The error page
     */
    public String getError( HttpServletRequest request )
    {
        // Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );
        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( MARK_ERROR, _strError );

        HtmlTemplate t = AppTemplateService.getTemplate( TEMPLATE_ERROR, locale, model );

        return t.getHtml(  );
    }
}
