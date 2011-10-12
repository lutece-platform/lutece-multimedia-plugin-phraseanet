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

import fr.paris.lutece.plugins.phraseanet.business.media.MediaHandler;
import fr.paris.lutece.plugins.phraseanet.business.media.MediaHandlerHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage  media handlers
 * ( manage, create, modify, remove )
 */
public class PhraseanetJspBean extends PluginAdminPageJspBean
{
    // Right
    public static final String RIGHT_MANAGE_PHRASEANET = "PHRASEANET_MANAGEMENT";

    // Parameters
    private static final String PARAMETER_ID_MEDIA_HANDLER = "id_media_handler";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_DESCRIPTION = "description";
    private static final String PARAMETER_URL_ICON = "url_icon";
    private static final String PARAMETER_INSERT_TEMPLATE = "insert_template";
    private static final String PARAMETER_MEDIA_TYPE = "media_type";
    private static final String PARAMETER_BASES = "bases";
    private static final String PARAMETER_PAGE_INDEX = "page_index";
    private static final String PARAMETER_WIDTH = "width";
    private static final String PARAMETER_HEIGHT = "height";

    // templates
    private static final String TEMPLATE_MANAGE_MEDIA_HANDLERS = "/admin/plugins/phraseanet/manage_media_handlers.html";
    private static final String TEMPLATE_CREATE_MEDIA_HANDLER = "/admin/plugins/phraseanet/create_media_handler.html";
    private static final String TEMPLATE_MODIFY_MEDIA_HANDLER = "/admin/plugins/phraseanet/modify_media_handler.html";

    // Properties
    private static final String PROPERTY_PAGE_TITLE_MANAGE_MEDIA_HANDLERS = "phraseanet.manage_media_handlers.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_MEDIA_HANDLER = "phraseanet.modify_media_handler.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_MEDIA_HANDLER = "phraseanet.create_media_handler.pageTitle";
    private static final String PROPERTY_DEFAULT_LIST_MEDIA_HANDLER_PER_PAGE = "phraseanet.listMediaHandlers.itemsPerPage";

    // Markers
    private static final String MARK_MEDIA_HANDLER_LIST = "media_handler_list";
    private static final String MARK_MEDIA_HANDLER = "media_handler";
    private static final String MARK_PAGINATOR = "paginator";
    private static final String MARK_NB_ITEMS_PER_PAGE = "nb_items_per_page";

    // Jsp Definition
    private static final String JSP_URL_DO_REMOVE_MEDIA_HANDLER = "jsp/admin/plugins/phraseanet/DoRemoveMediaHandler.jsp";
    private static final String JSP_URL_MANAGE_MEDIA_HANDLERS = "jsp/admin/plugins/phraseanet/ManageMediaHandlers.jsp";
    private static final String JSP_REDIRECT_TO_MANAGE_MEDIA_HANDLERS = "ManageMediaHandlers.jsp";

    // Messages
    private static final String MESSAGE_CONFIRM_REMOVE_MEDIA_HANDLER = "phraseanet.message.confirmRemoveMediaHandler";
    private static final String MESSAGE_ERROR = "phraseanet.message.error";

    // Variables
    private int _nDefaultItemsPerPage;
    private String _strCurrentPageIndex;
    private int _nItemsPerPage;

    /**
     * Returns the list of media_handler
     *
     * @param request The Http request
     * @return the media_handlers list
     */
    public String getManageMediaHandlers( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_MANAGE_MEDIA_HANDLERS );

        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_MEDIA_HANDLER_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( JSP_URL_MANAGE_MEDIA_HANDLERS );
        String strUrl = url.getUrl(  );
        Collection<MediaHandler> listMediaHandlers = MediaHandlerHome.findAll(  );

        LocalizedPaginator paginator = new LocalizedPaginator( (List<MediaHandler>) listMediaHandlers, _nItemsPerPage,
                strUrl, PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( MARK_NB_ITEMS_PER_PAGE, Integer.toString( _nItemsPerPage ) );
        model.put( MARK_PAGINATOR, paginator );
        model.put( MARK_MEDIA_HANDLER_LIST, paginator.getPageItems(  ) );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MANAGE_MEDIA_HANDLERS, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
     * Returns the form to create a media_handler
     *
     * @param request The Http request
     * @return the html code of the media_handler form
     */
    public String getCreateMediaHandler( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_CREATE_MEDIA_HANDLER );

        Map<String, Object> model = new HashMap<String, Object>(  );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_MEDIA_HANDLER, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }

    /**
     * Process the data capture form of a new media_handler
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    public String doCreateMediaHandler( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strMediaHandlerName = request.getParameter( PARAMETER_NAME );
        String strMediaHandlerDescription = request.getParameter( PARAMETER_DESCRIPTION );
        String strUrlIcon = request.getParameter( PARAMETER_URL_ICON );
        String strInsertTemplate = request.getParameter( PARAMETER_INSERT_TEMPLATE );
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );
        String strBases = request.getParameter( PARAMETER_BASES );
        String strWidth = request.getParameter( PARAMETER_WIDTH );
        String strHeight = request.getParameter( PARAMETER_HEIGHT );

        if ( StringUtils.isNotBlank( strMediaHandlerName ) && StringUtils.isNotBlank( strMediaHandlerDescription ) &&
                StringUtils.isNotBlank( strUrlIcon ) && StringUtils.isNotBlank( strInsertTemplate ) &&
                StringUtils.isNotBlank( strWidth ) && StringUtils.isNumeric( strWidth ) &&
                StringUtils.isNotBlank( strHeight ) && StringUtils.isNumeric( strHeight ) &&
                StringUtils.isNotBlank( strMediaType ) )
        {
            int nWidth = Integer.parseInt( strWidth );
            int nHeight = Integer.parseInt( strHeight );

            MediaHandler mh = new MediaHandler(  );
            mh.setName( strMediaHandlerName );
            mh.setDescription( strMediaHandlerDescription );
            mh.setIconUrl( strUrlIcon );
            mh.setInsertTemplate( strInsertTemplate );
            mh.setMediaType( strMediaType );
            mh.setBases( strBases );
            mh.setDefaultWidth( nWidth );
            mh.setDefaultHeight( nHeight );

            MediaHandlerHome.create( mh );

            strUrl = JSP_REDIRECT_TO_MANAGE_MEDIA_HANDLERS;
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Manages the removal form of a media_handler whose identifier is in the http request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    public String getConfirmRemoveMediaHandler( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strMediaHandlerId = request.getParameter( PARAMETER_ID_MEDIA_HANDLER );

        if ( StringUtils.isNotBlank( strMediaHandlerId ) && StringUtils.isNumeric( strMediaHandlerId ) )
        {
            int nId = Integer.parseInt( strMediaHandlerId );
            UrlItem url = new UrlItem( JSP_URL_DO_REMOVE_MEDIA_HANDLER );
            url.addParameter( PARAMETER_ID_MEDIA_HANDLER, nId );

            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_MEDIA_HANDLER, url.getUrl(  ),
                    AdminMessage.TYPE_CONFIRMATION );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Handles the removal form of a media handler
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage media handlers
     */
    public String doRemoveMediaHandler( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strMediaHandlerId = request.getParameter( PARAMETER_ID_MEDIA_HANDLER );

        if ( StringUtils.isNotBlank( strMediaHandlerId ) && StringUtils.isNumeric( strMediaHandlerId ) )
        {
            int nMediaHandlerId = Integer.parseInt( strMediaHandlerId );
            MediaHandlerHome.remove( nMediaHandlerId );

            strUrl = JSP_REDIRECT_TO_MANAGE_MEDIA_HANDLERS;
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Returns the form to update info about a media_handler
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    public String getModifyMediaHandler( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_MODIFY_MEDIA_HANDLER );

        String strUrl = StringUtils.EMPTY;
        String strMediaHandlerId = request.getParameter( PARAMETER_ID_MEDIA_HANDLER );

        if ( StringUtils.isNotBlank( strMediaHandlerId ) && StringUtils.isNumeric( strMediaHandlerId ) )
        {
            int nMediaHandlerId = Integer.parseInt( strMediaHandlerId );
            MediaHandler mh = MediaHandlerHome.findByPrimaryKey( nMediaHandlerId );

            if ( mh != null )
            {
                Map<String, Object> model = new HashMap<String, Object>(  );
                model.put( MARK_MEDIA_HANDLER, mh );

                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_MEDIA_HANDLER, getLocale(  ),
                        model );

                strUrl = getAdminPage( template.getHtml(  ) );
            }
            else
            {
                strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_STOP );
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Process the change form of a media_handler
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    public String doModifyMediaHandler( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strMediaHandlerId = request.getParameter( PARAMETER_ID_MEDIA_HANDLER );
        String strMediaHandlerName = request.getParameter( PARAMETER_NAME );
        String strMediaHandlerDescription = request.getParameter( PARAMETER_DESCRIPTION );
        String strUrlIcon = request.getParameter( PARAMETER_URL_ICON );
        String strInsertTemplate = request.getParameter( PARAMETER_INSERT_TEMPLATE );
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );
        String strBases = request.getParameter( PARAMETER_BASES );
        String strWidth = request.getParameter( PARAMETER_WIDTH );
        String strHeight = request.getParameter( PARAMETER_HEIGHT );

        if ( StringUtils.isNotBlank( strMediaHandlerName ) && StringUtils.isNotBlank( strMediaHandlerDescription ) &&
                StringUtils.isNotBlank( strUrlIcon ) && StringUtils.isNotBlank( strInsertTemplate ) &&
                StringUtils.isNotBlank( strWidth ) && StringUtils.isNumeric( strWidth ) &&
                StringUtils.isNotBlank( strHeight ) && StringUtils.isNumeric( strHeight ) &&
                StringUtils.isNotBlank( strMediaType ) )
        {
            int nMediaHandlerId = Integer.parseInt( strMediaHandlerId );
            MediaHandler mh = MediaHandlerHome.findByPrimaryKey( nMediaHandlerId );

            if ( mh != null )
            {
                int nWidth = Integer.parseInt( strWidth );
                int nHeight = Integer.parseInt( strHeight );

                mh.setName( strMediaHandlerName );
                mh.setDescription( strMediaHandlerDescription );
                mh.setIconUrl( strUrlIcon );
                mh.setInsertTemplate( strInsertTemplate );
                mh.setMediaType( strMediaType );
                mh.setBases( strBases );
                mh.setDefaultWidth( nWidth );
                mh.setDefaultHeight( nHeight );

                MediaHandlerHome.update( mh );
                strUrl = JSP_REDIRECT_TO_MANAGE_MEDIA_HANDLERS;
            }
            else
            {
                strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_STOP );
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }
}
