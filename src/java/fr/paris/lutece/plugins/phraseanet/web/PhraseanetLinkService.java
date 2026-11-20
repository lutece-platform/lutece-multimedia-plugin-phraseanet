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
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.insert.InsertServiceJspBean;
import fr.paris.lutece.portal.web.insert.InsertServiceSelectionBean;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.string.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
    private static final String MARK_INPUT = "input_search";
    private static final String MARK_QUERY = "query";
    private static final String MARK_RESULTS = "results";
    private static final String MARK_SERVER = "server";
    private static final String MARK_ITEMS_PER_PAGE_VALUES = "items_per_page_values";
    private static final String MARK_ITEMS_PER_PAGE = "items_per_page_selected";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_EMBED = "embed";
    private static final String MARK_METADATAS = "metadatas";
    private static final String MARK_ERROR = "error";
    private static final String MARK_PAGINATOR = "paginator";
    private static final String MARK_NB_ITEMS_PER_PAGE = "nb_items_per_page";
    private static final String PARAMETER_RECORD = "record";
    private static final String PARAMETER_QUERY = "query";
    private static final String PARAMETER_MEDIA_HANDLER = "media_handler";
    private static final String PARAMETER_INPUT = "input_search";
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
		try {
			HashMap<String, Object> model = getModel(request);
			
	        // Gets the locale of the user
	        Locale locale = AdminUserService.getLocale( request );

	        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_SEARCH_FORM, locale, model );

	        return template.getHtml(  );
        }
        catch ( PhraseanetApiCallException ex )
        {
            return ex.getMessage(  );
        }
    }

    /**
     * Gets the search page
     * @param request The HTTP request
     * @return The search page
     */
    public String getSearch( HttpServletRequest request )
    {
        try
        {
            HashMap<String, Object> model = getModel(request);

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


	private HashMap<String, Object> getModel(HttpServletRequest request) throws PhraseanetApiCallException
	{
	    HashMap<String, Object> model = new HashMap<>();
	
	    String strInput = request.getParameter(PARAMETER_INPUT);
	    String strMediaHandler = request.getParameter(PARAMETER_MEDIA_HANDLER);
	    String strQuery = StringUtils.defaultString(request.getParameter(PARAMETER_QUERY));
	    String strCurrentPage = request.getParameter(PARAMETER_CURRENT_PAGE);
	    String strItemsPerPage = request.getParameter(PARAMETER_ITEMS_PER_PAGE);
	
	    // Media handler
	    int nMediaHandlerId = Integer.parseInt(strMediaHandler);
	    MediaHandler mh = MediaHandlerHome.findByPrimaryKey(nMediaHandlerId);
	
	    if (mh == null)
	    {
	        throw new PhraseanetApiCallException("MediaHandler not found: " + nMediaHandlerId);
	    }
	
	    // Pagination parameters
	    int nCurrentPage = StringUtils.isNumeric(strCurrentPage)
	            ? Math.max(1, Integer.parseInt(strCurrentPage))
	            : 1;
	
	    int nPerPage = StringUtils.isNumeric(strItemsPerPage)
	            ? Integer.parseInt(strItemsPerPage)
	            : AppPropertiesService.getPropertyInt(PROPERTY_ITEMS_PER_PAGE_DEFAULT, 10);
	
	    nPerPage = Math.max(1, nPerPage);
	
	    strCurrentPage = String.valueOf(nCurrentPage);
	    strItemsPerPage = String.valueOf(nPerPage);
	
	    int nOffsetStart = (nCurrentPage - 1) * nPerPage;
	
	    // Account
	    Account account = AccountHome.findByPrimaryKey(mh.getIdAccount());
	
	    // Search criterias
	    SearchCriterias criterias = new SearchCriterias();
	    criterias.setRecordType(mh.getMediaType());
	
	    // Phraseanet search
	    SearchResults results = PhraseanetService.search(
	            StringUtil.replaceAccent(strQuery),
	            nOffsetStart,
	            nPerPage,
	            criterias,
	            account
	    );
	
	    // Paginator
	    List<Integer> listPages = IntStream.range(0, results.getTotalResults())
	            .boxed()
	            .collect(Collectors.toList());
	
	    String strUrl = getUrl(request, strInput, strQuery, strItemsPerPage, nMediaHandlerId);
	
	    Paginator<Integer> paginator = new Paginator<>(
	            listPages,
	            nPerPage,
	            strUrl,
	            PARAMETER_CURRENT_PAGE,
	            strCurrentPage
	    );
	
	    // Model
	    model.put(MARK_INPUT, strInput);
	    model.put(MARK_MEDIA_HANDLER, strMediaHandler);
	    model.put(MARK_PAGINATOR, paginator);
	    model.put(MARK_QUERY, strQuery);
	    model.put(MARK_RESULTS, results);
	    model.put(MARK_NB_ITEMS_PER_PAGE, strItemsPerPage);
	    model.put(MARK_SERVER, account.getAccessURL());
	    model.put(MARK_ITEMS_PER_PAGE_VALUES, PhraseanetService.getItemsPerPageValues());
	    model.put(MARK_ITEMS_PER_PAGE, strItemsPerPage);
	
	    return model;
	}

	/**
	 * Get Phraseanet paginator URL
	 * @param request
	 * @param strInput
	 * @param strQuery
	 * @param strItemsPerPage
	 * @param nMediaHandlerId
	 * @return string url
	 */
	private String getUrl(HttpServletRequest request, String strInput, String strQuery, String strItemsPerPage, int nMediaHandlerId) {
		try {
			return request.getRequestURI()
			        + "?input_search=" + strInput
			        + "&media_handler=" + nMediaHandlerId
			        + "&items_per_page=" + strItemsPerPage
			        + "&query=" + URLEncoder.encode(strQuery, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			AppLogService.error("An error occurred while generating the Phraseanet paginator URL. ",e.getMessage());
		}
		return StringUtils.EMPTY;
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
            //MetadataValue metadata = PhraseanetService.getMetadatas(nDataboxId, nRecordId, account);
            //_logger.debug( "Metadata : " + metadata );
            _logger.debug( "embed.getEmbedItem(\"preview\").getPlayerType() = " + embed.getEmbedItem("preview").getPlayerType() );
            

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
