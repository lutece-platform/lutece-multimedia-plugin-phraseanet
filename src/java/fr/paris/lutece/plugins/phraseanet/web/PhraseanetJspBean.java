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
import fr.paris.lutece.plugins.phraseanet.business.databox.Databox;
import fr.paris.lutece.plugins.phraseanet.business.media.MediaHandler;
import fr.paris.lutece.plugins.phraseanet.business.media.MediaHandlerHome;
import fr.paris.lutece.plugins.phraseanet.business.record.Metadata;
import fr.paris.lutece.plugins.phraseanet.business.template.Template;
import fr.paris.lutece.plugins.phraseanet.business.template.TemplateHome;
import fr.paris.lutece.plugins.phraseanet.dto.recordtype.RecordTypeDTO;
import fr.paris.lutece.plugins.phraseanet.service.PhraseanetService;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiAuthentication;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;


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
    private static final String PARAMETER_INSERT_TEMPLATE = "insert_template";
    private static final String PARAMETER_MEDIA_TYPE = "media_type";
    private static final String PARAMETER_PAGE_INDEX = "page_index";
    
    private static final String PARAMETER_ID_ACCOUNT = "id_account";
    private static final String PARAMETER_ACCESS_URL = "accessUrl";
    private static final String PARAMETER_CUSTOMER_ID = "customerId";
    private static final String PARAMETER_CUSTOMER_SECRET = "customerSecret";
    private static final String PARAMETER_AUTHORIZE_END_POINT = "authorizeEndPoint";
    private static final String PARAMETER_ACCESS_END_POINT = "accessEndPoint";
    private static final String PARAMETER_PHRASEANET_ID = "phraseanetId";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_TOKEN = "token";
    
    private static final String PARAMETER_ENREGISTRER = "save";
    private static final String PARAMETER_GET_TOKEN = "get_token";
    private static final String PARAMETER_CHECK_ACCOUNT = "check_account";
    
    private static final String PARAMETER_DEFAULT_TEMPLATE = "default_template";
    
    private static final String PARAMETER_ID_DATABOXE = "id_databoxe";

    // templates
    private static final String TEMPLATE_MANAGE_MEDIA_HANDLERS = "/admin/plugins/phraseanet/manage_media_handlers.html";
    private static final String TEMPLATE_CREATE_MEDIA_HANDLER_STEP1 = "/admin/plugins/phraseanet/create_media_handler_step1.html";
    private static final String TEMPLATE_MODIFY_MEDIA_HANDLER_STEP1 = "/admin/plugins/phraseanet/modify_media_handler_step1.html";
    private static final String TEMPLATE_CREATE_MEDIA_HANDLER_STEP2 = "/admin/plugins/phraseanet/create_media_handler_step2.html";
    private static final String TEMPLATE_MODIFY_MEDIA_HANDLER_STEP2 = "/admin/plugins/phraseanet/modify_media_handler_step2.html";
    
    private static final String TEMPLATE_MANAGE_ACCOUNTS = "/admin/plugins/phraseanet/manage_accounts.html";
    private static final String TEMPLATE_CREATE_ACCOUNT = "/admin/plugins/phraseanet/create_account.html";
    private static final String TEMPLATE_MODIFY_ACCOUNT = "/admin/plugins/phraseanet/modify_account.html";
    
    private static final String TEMPLATE_MANAGE_TEMPLATES = "/admin/plugins/phraseanet/manage_templates.html";
    private static final String TEMPLATE_MODIFY_TEMPLATE = "/admin/plugins/phraseanet/modify_template.html";
    
    private static final String TEMPLATE_DISPLAY_METADATAS = "/admin/plugins/phraseanet/display_metadatas.html";
    private static final String TEMPLATE_DISPLAY_COLLECTIONS = "/admin/plugins/phraseanet/display_collections.html";

    // Properties
    private static final String PROPERTY_PAGE_TITLE_MANAGE_MEDIA_HANDLERS = "phraseanet.manage_media_handlers.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_MEDIA_HANDLER_STEP1 = "phraseanet.modify_media_handler.pageTitle1";
    private static final String PROPERTY_PAGE_TITLE_CREATE_MEDIA_HANDLER_STEP1 = "phraseanet.create_media_handler.pageTitle1";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_MEDIA_HANDLER_STEP2 = "phraseanet.modify_media_handler.pageTitle2";
    private static final String PROPERTY_PAGE_TITLE_CREATE_MEDIA_HANDLER_STEP2 = "phraseanet.create_media_handler.pageTitle2";
    private static final String PROPERTY_DEFAULT_LIST_MEDIA_HANDLER_PER_PAGE = "phraseanet.listMediaHandlers.itemsPerPage";
    
    private static final String PROPERTY_PAGE_TITLE_MANAGE_ACCOUNTS = "phraseanet.manage_accounts.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_ACCOUNT = "phraseanet.modify_account.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_ACCOUNT = "phraseanet.create_account.pageTitle";
    private static final String PROPERTY_DEFAULT_LIST_ACCOUNT_PER_PAGE = "phraseanet.listAccounts.itemsPerPage";    

    private static final String PROPERTY_PAGE_TITLE_MANAGE_TEMPLATES = "phraseanet.manage_templates.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_TEMLPATE = "phraseanet.modify_template.pageTitle";
    
    private static final String PROPERTY_URL_DOCUMENTATION = "phraseanet.url.documentation";
    	
    // Markers
    private static final String MARK_MEDIA_HANDLER_LIST = "media_handler_list";
    private static final String MARK_MEDIA_HANDLER = "media_handler";
    private static final String MARK_ACCOUNT_LIST = "account_list";
    private static final String MARK_ACCOUNT = "account";
    private static final String MARK_PAGINATOR = "paginator";
    private static final String MARK_NB_ITEMS_PER_PAGE = "nb_items_per_page";
    private static final String MARK_RECORD_TYPE_LIST = "record_type_list";
    private static final String MARK_MEDIA_HANDLER_NAME = "media_handler_name";
    private static final String MARK_TEMPLATE = "template";
    private static final String MARK_CHECK_ACCOUNT = "check_account";
    private static final String MARK_DATABOXES_LIST = "databoxes_list";
    private static final String MARK_METADATAS_LIST = "metadatas_list";
    private static final String MARK_COLLECTIONS_LIST = "collections_list";
    private static final String MARK_ID_MEDIA_TYPE = "media_type";
    private static final String MARK_ID_MEDIA_DESCRIPTION = "description";  
    private static final String MARK_ID_MEDIA_HANDLER = "id_media_handler";
    private static final String MARK_URL_DOCUMENTATION = "url_documentation";

    // Jsp Definition
    private static final String JSP_URL_DO_REMOVE_MEDIA_HANDLER = "jsp/admin/plugins/phraseanet/DoRemoveMediaHandler.jsp";
    private static final String JSP_URL_MANAGE_MEDIA_HANDLERS = "jsp/admin/plugins/phraseanet/ManageMediaHandlers.jsp";
    private static final String JSP_REDIRECT_TO_MANAGE_MEDIA_HANDLERS = "ManageMediaHandlers.jsp";
    private static final String JSP_REDIRECT_TO_CREATE_MEDIA_HANDLER_STEP2 = "CreateMediaHandlerStep2.jsp";
    private static final String JSP_REDIRECT_TO_MODIFY_MEDIA_HANDLER_STEP2 = "ModifyMediaHandlerStep2.jsp";
    
    private static final String JSP_URL_DO_REMOVE_ACCOUNT = "jsp/admin/plugins/phraseanet/DoRemoveAccount.jsp";
    private static final String JSP_URL_MANAGE_ACCOUNTS = "jsp/admin/plugins/phraseanet/ManageAccounts.jsp";
    private static final String JSP_URL_CREATE_ACCOUNT = "CreateAccount.jsp";
    private static final String JSP_URL_MODIFY_ACCOUNT = "ModifyAccount.jsp";
    private static final String JSP_REDIRECT_TO_MANAGE_ACCOUNTS = "ManageAccounts.jsp";
    private static final String JSP_REDIRECT_TO_CALLBACK ="Callback.jsp";
    
    private static final String JSP_URL_MANAGE_TEMPLATES = "ManageTemplates.jsp";

    // Messages
    private static final String MESSAGE_CONFIRM_REMOVE_MEDIA_HANDLER = "phraseanet.message.confirmRemoveMediaHandler";
    private static final String MESSAGE_CONFIRM_REMOVE_ACCOUNT = "phraseanet.message.confirmRemoveAccount";
    private static final String MESSAGE_ERROR = "phraseanet.message.error";
    private static final String MESSAGE_ERROR_DELETE_ACCOUNT = "phraseanet.message.error.delete.account";
    
    // Constants
    private static final String CONSTANT_MEDIA_TYPE_VIDEO = "video";
    private static final String CONSTANT_MEDIA_TYPE_AUDIO = "audio";
    private static final String CONSTANT_MEDIA_TYPE_IMAGE = "image";
    private static final String CONSTANT_MEDIA_TYPE_FLASH = "flash";
    private static final String CONSTANT_MEDIA_TYPE_DOCUMENT = "document";
    private static final String CONSTANT_NAME_ICON_PICTURE = "_icon.png";
    private static final String CONSTANT_PATH_ICONE_PICTURE = "images/admin/skin/plugins/phraseanet/";
    private static final String CONSTANT_DEFAULT_URL_DOCUMENTATION = "http://dev.lutece.paris.fr/plugins/plugin-phraseanet/fr/";
    
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
     * Returns the form step1 to create a media_handler
     * @param request The Http request
     * @return the html code of the media_handler form step 1
     */
    public String getCreateMediaHandlerStep1( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_CREATE_MEDIA_HANDLER_STEP1 );
        Map<String, Object> model = new HashMap<String, Object>(  );        
              	
        model.put( MARK_RECORD_TYPE_LIST, getRecordTypeDTOList(  ) );
        
        model.put( MARK_ACCOUNT_LIST, AccountHome.findAll(  ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_MEDIA_HANDLER_STEP1, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }
    
    
    /**
     * Check step1 values
     * @param request the request
     * @return error message or step2 form
     */
    public String doCreateMediaHandlerStep2( HttpServletRequest request )
    {
    	String strUrl = StringUtils.EMPTY;
    	
    	String strMediaHandlerName = request.getParameter( PARAMETER_NAME );
    	String strMediaHandlerDescription = request.getParameter( PARAMETER_DESCRIPTION );
        String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT );        
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );
        
        if( StringUtils.isNotBlank( strMediaHandlerName )
        		&& StringUtils.isNotBlank( strMediaHandlerDescription )
        		&& StringUtils.isNotBlank( strIdAccount )
        		&& StringUtils.isNotBlank( strMediaType ) )
        {
        	UrlItem url = new UrlItem( JSP_REDIRECT_TO_CREATE_MEDIA_HANDLER_STEP2 );
        	url.addParameter( PARAMETER_NAME, strMediaHandlerName );
        	url.addParameter( PARAMETER_DESCRIPTION, strMediaHandlerDescription );
        	url.addParameter( PARAMETER_ID_ACCOUNT, strIdAccount );
        	url.addParameter( PARAMETER_MEDIA_TYPE, strMediaType );
        	strUrl = url.getUrl(  );
        }
        else
        {
        	strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }
        return strUrl;
    }
    
    /**
     * Returns the form step2 to create a media_handler
     * @param request The Http request
     * @return the html code of the media_handler form step 2
     */
    public String getCreateMediaHandlerStep2( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_CREATE_MEDIA_HANDLER_STEP2 );
        Map<String, Object> model = new HashMap<String, Object>(  );
        
        String strMediaHandlerName = request.getParameter( PARAMETER_NAME );
        model.put( MARK_MEDIA_HANDLER_NAME, strMediaHandlerName );
        
        String strMediaHandlerDescription = request.getParameter( PARAMETER_DESCRIPTION );
        String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT );        
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );
        
        Account account = new Account(  );
        if( StringUtils.isNotBlank( strIdAccount ) && StringUtils.isNumeric( strIdAccount ) ) 
        {
        	int nIdAccount = Integer.parseInt( strIdAccount );
        	account = AccountHome.findByPrimaryKey( nIdAccount );
        } 
        model.put( MARK_ACCOUNT , account );
        model.put( MARK_ID_MEDIA_TYPE , strMediaType );
        model.put( MARK_ID_MEDIA_DESCRIPTION , strMediaHandlerDescription ); 
        model.put( MARK_URL_DOCUMENTATION, AppPropertiesService.getProperty( PROPERTY_URL_DOCUMENTATION , CONSTANT_DEFAULT_URL_DOCUMENTATION ) );
        
        if( StringUtils.isNotBlank( strMediaType ) ) 
        {
        	Template defaultTemplate = TemplateHome.findByPrimaryKey( strMediaType );
        	model.put( PARAMETER_DEFAULT_TEMPLATE, defaultTemplate.getTemplate(  ) );
        } 
        
        List<Databox> listDataboxes = new ArrayList<Databox> (  );
        try 
        {
        	listDataboxes = PhraseanetService.getDataboxes( account );
		} 
        catch( PhraseanetApiCallException e )
		{
			AppLogService.error( e );
		}
        model.put( MARK_DATABOXES_LIST, listDataboxes );
        
        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_MEDIA_HANDLER_STEP2, getLocale(  ), model );

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
        String strInsertTemplate = request.getParameter( PARAMETER_INSERT_TEMPLATE );
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );
        String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT ); 
        
        if ( StringUtils.isNotBlank( strMediaHandlerName ) && StringUtils.isNotBlank( strMediaHandlerDescription )
                && StringUtils.isNotBlank( strInsertTemplate ) && StringUtils.isNotBlank( strMediaType ) 
                && StringUtils.isNotBlank( strIdAccount ) && StringUtils.isNumeric( strIdAccount ) )
        {
        	int nIdAccount = Integer.parseInt( strIdAccount );
            MediaHandler mh = new MediaHandler(  );
            mh.setName( strMediaHandlerName );
            mh.setIdAccount( nIdAccount );
            mh.setDescription( strMediaHandlerDescription );
            mh.setIconUrl( CONSTANT_PATH_ICONE_PICTURE + strMediaType + CONSTANT_NAME_ICON_PICTURE );
            mh.setInsertTemplate( strInsertTemplate );
            mh.setMediaType( strMediaType );

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
     * Returns the step 1 form to update info about a media_handler
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    public String getModifyMediaHandlerStep1( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_MODIFY_MEDIA_HANDLER_STEP1 );

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
                model.put( MARK_RECORD_TYPE_LIST, getRecordTypeDTOList(  ) );                
                model.put( MARK_ACCOUNT_LIST, AccountHome.findAll(  ) );
                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_MEDIA_HANDLER_STEP1, getLocale(  ),
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
     * Check step1 values
     * @param request the request
     * @return error message or step2 form
     */
    public String doModifyMediaHandlerStep2( HttpServletRequest request )
    {
    	String strUrl = StringUtils.EMPTY;
    	String strMediaHandlerId = request.getParameter( PARAMETER_ID_MEDIA_HANDLER );
    	String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );
    	String strMediaHandlerName = request.getParameter( PARAMETER_NAME );
    	String strMediaHandlerDescription = request.getParameter( PARAMETER_DESCRIPTION );
        String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT );
        
        if( StringUtils.isNotBlank( strMediaHandlerName ) && StringUtils.isNotBlank( strMediaHandlerId )
        		&& StringUtils.isNotBlank( strMediaHandlerDescription )
        		&& StringUtils.isNotBlank( strIdAccount )
        		&& StringUtils.isNotBlank( strMediaType ) )
        {
        	UrlItem url = new UrlItem( JSP_REDIRECT_TO_MODIFY_MEDIA_HANDLER_STEP2 );
        	url.addParameter( PARAMETER_ID_MEDIA_HANDLER, strMediaHandlerId );
        	url.addParameter( PARAMETER_NAME, strMediaHandlerName );
        	url.addParameter( PARAMETER_DESCRIPTION, strMediaHandlerDescription );
        	url.addParameter( PARAMETER_ID_ACCOUNT, strIdAccount );
        	url.addParameter( PARAMETER_MEDIA_TYPE, strMediaType );
        	strUrl = url.getUrl(  ).replaceAll( " ", "%20" );
        }
        else
        {
        	strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }
        return strUrl;
    }
    
    /**
     * Returns the step 2 form to update info about a media_handler
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    public String getModifyMediaHandlerStep2( HttpServletRequest request )
    {    	
    	setPageTitleProperty( PROPERTY_PAGE_TITLE_MODIFY_MEDIA_HANDLER_STEP2 );
        Map<String, Object> model = new HashMap<String, Object>(  );
        
        String strMediaHandlerName = request.getParameter( PARAMETER_NAME );
        model.put( MARK_MEDIA_HANDLER_NAME, strMediaHandlerName );
        
        String strMediaHandlerId = request.getParameter( PARAMETER_ID_MEDIA_HANDLER );
        String strMediaHandlerDescription = request.getParameter( PARAMETER_DESCRIPTION );
        String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT );        
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );
        
        Account account = new Account(  );
        if( StringUtils.isNotBlank( strIdAccount ) && StringUtils.isNumeric( strIdAccount ) ) 
        {
        	int nIdAccount = Integer.parseInt( strIdAccount );
        	account = AccountHome.findByPrimaryKey( nIdAccount );
        } 
        model.put( MARK_ACCOUNT , account );
        model.put( MARK_ID_MEDIA_TYPE , strMediaType );
        model.put( MARK_ID_MEDIA_DESCRIPTION , strMediaHandlerDescription );  
        model.put( MARK_ID_MEDIA_HANDLER , strMediaHandlerId );  
        model.put( MARK_URL_DOCUMENTATION, AppPropertiesService.getProperty( PROPERTY_URL_DOCUMENTATION , CONSTANT_DEFAULT_URL_DOCUMENTATION ) );
        
        List<Databox> listDataboxes = new ArrayList<Databox> (  );
        try 
        {
        	listDataboxes = PhraseanetService.getDataboxes( account );
		} 
        catch( PhraseanetApiCallException e )
		{
			AppLogService.error( e );
		}
        model.put( MARK_DATABOXES_LIST, listDataboxes );
        
        if ( StringUtils.isNotBlank( strMediaHandlerId ) && StringUtils.isNumeric( strMediaHandlerId ) )
        {
            int nMediaHandlerId = Integer.parseInt( strMediaHandlerId );
            MediaHandler mh = MediaHandlerHome.findByPrimaryKey( nMediaHandlerId );
            if( mh.getMediaType(  ).equals( strMediaType ) )
            {
            	model.put( PARAMETER_DEFAULT_TEMPLATE, mh.getInsertTemplate(  ) );
            }
            else
            {
            	Template defaultTemplate = TemplateHome.findByPrimaryKey( strMediaType );
            	model.put( PARAMETER_DEFAULT_TEMPLATE, defaultTemplate.getTemplate(  ) );
            }
        }        
        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_MEDIA_HANDLER_STEP2, getLocale(  ), model );
        return getAdminPage( template.getHtml(  ) );
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
        String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT ); 
        String strInsertTemplate = request.getParameter( PARAMETER_INSERT_TEMPLATE );
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );

        if ( StringUtils.isNotBlank( strMediaHandlerName ) && StringUtils.isNotBlank( strMediaHandlerDescription ) &&
                StringUtils.isNotBlank( strIdAccount ) && StringUtils.isNumeric( strIdAccount ) 
                && StringUtils.isNotBlank( strInsertTemplate ) && StringUtils.isNotBlank( strMediaType ) )
        {
            int nMediaHandlerId = Integer.parseInt( strMediaHandlerId );
            MediaHandler mh = MediaHandlerHome.findByPrimaryKey( nMediaHandlerId );
            
            int nIdAccount = Integer.parseInt( strIdAccount );
            if ( mh != null )
            {
                mh.setName( strMediaHandlerName );
                mh.setIdAccount( nIdAccount );
                mh.setDescription( strMediaHandlerDescription );
                mh.setIconUrl(  CONSTANT_PATH_ICONE_PICTURE + strMediaType + CONSTANT_NAME_ICON_PICTURE  );
                mh.setInsertTemplate( strInsertTemplate );
                mh.setMediaType( strMediaType );

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
    
    /**
     * Method to display meta datas list by databoxe in popup
     * @param request the request 
     * @return simple template with meta datas list
     */
    public static String getListMetaDatas( HttpServletRequest request )
    {
    	Map<String, Object> model = new HashMap<String, Object>(  );
    	
    	String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT );
    	String strIdDataboxe = request.getParameter( PARAMETER_ID_DATABOXE );
    	
    	if( StringUtils.isNotBlank( strIdAccount ) && StringUtils.isNumeric( strIdAccount ) && StringUtils.isNotBlank( strIdDataboxe ) && StringUtils.isNumeric( strIdDataboxe ) )
    	{
    		int nIdAccount = Integer.parseInt( strIdAccount );
    		int nIdDataboxe = Integer.parseInt( strIdDataboxe );
    		
    		Account account = AccountHome.findByPrimaryKey( nIdAccount );
    		
    		List<Metadata> listMetadatas = new ArrayList<Metadata> (  );
            try 
            {
            	listMetadatas = PhraseanetService.getDataboxeMetadatas( nIdDataboxe, account );
    		} 
            catch( PhraseanetApiCallException e )
    		{
    			AppLogService.error( e );
    		}
            model.put( MARK_METADATAS_LIST, listMetadatas );
    	}
    	// Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );
    	HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_DISPLAY_METADATAS, locale, model );
    	
    	return templateList.getHtml(  );
    }
    
    /**
     * Method to display collection list by databoxe in popup
     * @param request the request 
     * @return simple template with collection list
     */
    public static String getListCollections( HttpServletRequest request )
    {
    	Map<String, Object> model = new HashMap<String, Object>(  );
    	
    	String strIdAccount = request.getParameter( PARAMETER_ID_ACCOUNT );
    	String strIdDataboxe = request.getParameter( PARAMETER_ID_DATABOXE );
    	
    	if( StringUtils.isNotBlank( strIdAccount ) && StringUtils.isNumeric( strIdAccount ) && StringUtils.isNotBlank( strIdDataboxe ) && StringUtils.isNumeric( strIdDataboxe ) )
    	{
    		int nIdAccount = Integer.parseInt( strIdAccount );
    		int nIdDataboxe = Integer.parseInt( strIdDataboxe );
    		
    		Account account = AccountHome.findByPrimaryKey( nIdAccount );
    		
    		List<fr.paris.lutece.plugins.phraseanet.business.databox.Collection> listCollections = new ArrayList<fr.paris.lutece.plugins.phraseanet.business.databox.Collection> (  );
            try 
            {
            	listCollections = PhraseanetService.getColletions( nIdDataboxe, account );
    		} 
            catch( PhraseanetApiCallException e )
    		{
            	AppLogService.error( e );
    		}
            model.put( MARK_COLLECTIONS_LIST, listCollections );
    	}
    	// Gets the locale of the user
        Locale locale = AdminUserService.getLocale( request );
    	HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_DISPLAY_COLLECTIONS, locale, model );
    	
    	return templateList.getHtml(  );
    }
    
    /**
     * Returns the list of accounts
     *
     * @param request The Http request
     * @return the media_handlers list
     */
    public String getManageAccounts( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_MANAGE_ACCOUNTS );

        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_ACCOUNT_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( JSP_URL_MANAGE_ACCOUNTS );
        String strUrl = url.getUrl(  );
        Collection<Account> listAccountlers = AccountHome.findAll(  );

        LocalizedPaginator paginator = new LocalizedPaginator( (List<Account>) listAccountlers, _nItemsPerPage,
                strUrl, PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( MARK_NB_ITEMS_PER_PAGE, Integer.toString( _nItemsPerPage ) );
        model.put( MARK_PAGINATOR, paginator );
        model.put( MARK_ACCOUNT_LIST, paginator.getPageItems(  ) );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MANAGE_ACCOUNTS, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }
    
    /**
     * Returns the form to create a account
     *
     * @param request The Http request
     * @return the html code of the account form
     */
    public String getCreateAccount( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_CREATE_ACCOUNT );

        Map<String, Object> model = new HashMap<String, Object>(  );
        
        if( StringUtils.isNotBlank( request.getParameter( PARAMETER_CHECK_ACCOUNT ) ) )
        {
        	Account account = new Account(  );
        	
        	String strAccountName = request.getParameter( PARAMETER_NAME );
            String strDescription = request.getParameter( PARAMETER_DESCRIPTION );        
            String strAccessUrl = request.getParameter( PARAMETER_ACCESS_URL );
            String strCustomerId = request.getParameter( PARAMETER_CUSTOMER_ID );
            String strCustomerSecret = request.getParameter( PARAMETER_CUSTOMER_SECRET );
            String strAuthorizeEndPoint = request.getParameter( PARAMETER_AUTHORIZE_END_POINT );
            String strAccessEndPoint = request.getParameter( PARAMETER_ACCESS_END_POINT );
            String strPhraseanetId = request.getParameter( PARAMETER_PHRASEANET_ID );
            String strPassword = request.getParameter( PARAMETER_PASSWORD );
            String strToken = request.getParameter( PARAMETER_TOKEN );
            
            account.setName( strAccountName );
            account.setDescription( strDescription );
            account.setAccessURL( strAccessUrl );
            account.setCustomerId( strCustomerId );
            account.setCustomerSecret( strCustomerSecret );
            account.setAuthorizeEndPoint( strAuthorizeEndPoint );
            account.setAccessEndPoint( strAccessEndPoint );
            account.setPhraseanetId( strPhraseanetId );
            account.setPassword( strPassword );
            account.setToken( strToken );
            model.put( MARK_ACCOUNT, account );
            try 
			{					
				if( StringUtils.isNotBlank( PhraseanetApiAuthentication.getAccessToken( account ) ) )
				{
					model.put( MARK_CHECK_ACCOUNT, "true" );
				}
			} 
			catch( PhraseanetApiCallException e ) 
			{
				AppLogService.error( e );
				model.put( MARK_CHECK_ACCOUNT, "false" );
			}  
        }

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_ACCOUNT, getLocale(  ), model );
        return getAdminPage( template.getHtml(  ) );
    }

    /**
     * Process the data capture form of a new account
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    public String doCreateAccount( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        
        String strAccountName = request.getParameter( PARAMETER_NAME );
        String strDescription = request.getParameter( PARAMETER_DESCRIPTION );        
        String strAccessUrl = request.getParameter( PARAMETER_ACCESS_URL );
        String strCustomerId = request.getParameter( PARAMETER_CUSTOMER_ID );
        String strCustomerSecret = request.getParameter( PARAMETER_CUSTOMER_SECRET );
        String strAuthorizeEndPoint = request.getParameter( PARAMETER_AUTHORIZE_END_POINT );
        String strAccessEndPoint = request.getParameter( PARAMETER_ACCESS_END_POINT );
        String strPhraseanetId = request.getParameter( PARAMETER_PHRASEANET_ID );
        String strPassword = request.getParameter( PARAMETER_PASSWORD );
        String strToken = request.getParameter( PARAMETER_TOKEN );

        if ( StringUtils.isNotBlank( strAccountName ) && 
                StringUtils.isNotBlank( strDescription ) &&
                StringUtils.isNotBlank( strAccessUrl ) && 
                StringUtils.isNotBlank( strCustomerId ) &&
                //StringUtils.isNotBlank( strCustomerSecret ) && 
                StringUtils.isNotBlank( strAuthorizeEndPoint ) &&
                StringUtils.isNotBlank( strAccessEndPoint ) && 
                StringUtils.isNotBlank( strPhraseanetId ) &&
                StringUtils.isNotBlank( strPassword ) )
        {
        	
            Account account = new Account(  );
            account.setName( strAccountName );
            account.setDescription( strDescription );
            account.setAccessURL( strAccessUrl );
            account.setCustomerId( strCustomerId );
            account.setCustomerSecret( strCustomerSecret );
            account.setAuthorizeEndPoint( strAuthorizeEndPoint );
            account.setAccessEndPoint( strAccessEndPoint );
            account.setPhraseanetId( strPhraseanetId );
            account.setPassword( strPassword );
            account.setToken( strToken );
            
            if( StringUtils.isNotBlank( request.getParameter( PARAMETER_ENREGISTRER ) ) )
    		{
            	AccountHome.create( account );
                strUrl = JSP_REDIRECT_TO_MANAGE_ACCOUNTS;
    		}
            else
            {           
                strUrl = JSP_REDIRECT_TO_MANAGE_ACCOUNTS;              	
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }    
    
    /**
     * Manages the removal form of a account whose identifier is in the http request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    public String getConfirmRemoveAccount( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strAccountId = request.getParameter( PARAMETER_ID_ACCOUNT );

        if ( StringUtils.isNotBlank( strAccountId ) && StringUtils.isNumeric( strAccountId ) )
        {
            int nId = Integer.parseInt( strAccountId );
            UrlItem url = new UrlItem( JSP_URL_DO_REMOVE_ACCOUNT );
            url.addParameter( PARAMETER_ID_ACCOUNT, nId );

            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_ACCOUNT, url.getUrl(  ),
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
     * @return the jsp URL to display the form to manage accounts
     */
    public String doRemoveAccount( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strAccountId = request.getParameter( PARAMETER_ID_ACCOUNT );
        if ( StringUtils.isNotBlank( strAccountId ) && StringUtils.isNumeric( strAccountId ) )
        {        	
        	int nAccountId = Integer.parseInt( strAccountId );
        	if( MediaHandlerHome.checkMediaHandlerByAccount( nAccountId ) )
        	{
        		strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR_DELETE_ACCOUNT , AdminMessage.TYPE_STOP );
        	}
        	else
        	{
        		AccountHome.remove( nAccountId );
                strUrl = JSP_REDIRECT_TO_MANAGE_ACCOUNTS;
        	}            
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }
        return strUrl;
    }

    /**
     * Returns the form to update info about a account
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    public String getModifyAccount( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_MODIFY_ACCOUNT  );
        Map<String, Object> model = new HashMap<String, Object>(  );
        
        String strUrl = StringUtils.EMPTY;
        String strAccountId = request.getParameter( PARAMETER_ID_ACCOUNT );

        if ( StringUtils.isNotBlank( strAccountId ) && StringUtils.isNumeric( strAccountId ) )
        {
            int nAccountId = Integer.parseInt( strAccountId );
            Account account = new Account(  );
            
            if( StringUtils.isNotBlank( request.getParameter( PARAMETER_CHECK_ACCOUNT ) ) )
            {
            	String strAccountName = request.getParameter( PARAMETER_NAME );
                String strDescription = request.getParameter( PARAMETER_DESCRIPTION );        
                String strAccessUrl = request.getParameter( PARAMETER_ACCESS_URL );
                String strCustomerId = request.getParameter( PARAMETER_CUSTOMER_ID );
                String strCustomerSecret = request.getParameter( PARAMETER_CUSTOMER_SECRET );
                String strAuthorizeEndPoint = request.getParameter( PARAMETER_AUTHORIZE_END_POINT );
                String strAccessEndPoint = request.getParameter( PARAMETER_ACCESS_END_POINT );
                String strPhraseanetId = request.getParameter( PARAMETER_PHRASEANET_ID );
                String strPassword = request.getParameter( PARAMETER_PASSWORD );
                String strToken = request.getParameter( PARAMETER_TOKEN );
                
                account.setId( nAccountId );
                account.setName( strAccountName );
                account.setDescription( strDescription );
                account.setAccessURL( strAccessUrl );
                account.setCustomerId( strCustomerId );
                account.setCustomerSecret( strCustomerSecret );
                account.setAuthorizeEndPoint( strAuthorizeEndPoint );
                account.setAccessEndPoint( strAccessEndPoint );
                account.setPhraseanetId( strPhraseanetId );
                account.setPassword( strPassword );
                account.setToken( strToken );
                
				try 
				{					
					if( StringUtils.isNotBlank( PhraseanetApiAuthentication.getAccessToken( account ) ) )
					{
						model.put( MARK_CHECK_ACCOUNT, "true" );
					}
					else
					{
						model.put( MARK_CHECK_ACCOUNT, "false" );
					}
				} 
				catch( PhraseanetApiCallException e ) 
				{
					AppLogService.error( e );
					model.put( MARK_CHECK_ACCOUNT, "false" );
				}               
            }
            else
            {
            	account = AccountHome.findByPrimaryKey( nAccountId );
            }

            if ( account != null )
            {
                model.put( MARK_ACCOUNT, account );
                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_ACCOUNT, getLocale(  ),
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
     * Process the change form of a account
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    public String doModifyAccount( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        
        String strAccountId = request.getParameter( PARAMETER_ID_ACCOUNT );
        
        String strAccountName = request.getParameter( PARAMETER_NAME );
        String strDescription = request.getParameter( PARAMETER_DESCRIPTION );        
        String strAccessUrl = request.getParameter( PARAMETER_ACCESS_URL );
        String strCustomerId = request.getParameter( PARAMETER_CUSTOMER_ID );
        String strCustomerSecret = request.getParameter( PARAMETER_CUSTOMER_SECRET );
        String strAuthorizeEndPoint = request.getParameter( PARAMETER_AUTHORIZE_END_POINT );
        String strAccessEndPoint = request.getParameter( PARAMETER_ACCESS_END_POINT );
        String strPhraseanetId = request.getParameter( PARAMETER_PHRASEANET_ID );
        String strPassword = request.getParameter( PARAMETER_PASSWORD );
        String strToken = request.getParameter( PARAMETER_TOKEN );

        if ( StringUtils.isNotBlank( strAccountName ) && 
                StringUtils.isNotBlank( strDescription ) &&
                StringUtils.isNotBlank( strAccessUrl ) && 
                StringUtils.isNotBlank( strCustomerId ) &&
                //StringUtils.isNotBlank( strCustomerSecret ) && 
                StringUtils.isNotBlank( strAuthorizeEndPoint ) &&
                StringUtils.isNotBlank( strAccessEndPoint ) && 
                StringUtils.isNotBlank( strPhraseanetId ) &&
                StringUtils.isNotBlank( strPassword ) )
        {
            int nAccountId = Integer.parseInt( strAccountId );
            Account account = AccountHome.findByPrimaryKey( nAccountId );

            if ( account != null )
            {
                account.setName( strAccountName );
                account.setDescription( strDescription );
                account.setAccessURL( strAccessUrl );
                account.setCustomerId( strCustomerId );
                account.setCustomerSecret( strCustomerSecret );
                account.setAuthorizeEndPoint( strAuthorizeEndPoint );
                account.setAccessEndPoint( strAccessEndPoint );
                account.setPhraseanetId( strPhraseanetId );
                account.setPassword( strPassword );
                account.setToken( strToken );
                
                if( StringUtils.isNotBlank( request.getParameter( PARAMETER_ENREGISTRER ) ) )
        		{
                	AccountHome.update( account );
                    UrlItem url = new UrlItem( JSP_URL_MODIFY_ACCOUNT );
                	url.addParameter( PARAMETER_ID_ACCOUNT, strAccountId );
                    strUrl = url.getUrl(  );
                    
        		}
                else if(StringUtils.isNotBlank( request.getParameter( PARAMETER_GET_TOKEN ) ))
                {
                    AccountHome.update( account );
                    UrlItem url = new UrlItem(JSP_REDIRECT_TO_CALLBACK);
                	url.addParameter( PARAMETER_ID_ACCOUNT, strAccountId );
                	//url.addParameter( PARAMETER_NAME, strAccountName ); 
                	//url.addParameter( PARAMETER_DESCRIPTION, strDescription );        
                	//url.addParameter( PARAMETER_ACCESS_URL, strAccessUrl );
                	url.addParameter( PARAMETER_CUSTOMER_ID, strCustomerId );
                	//url.addParameter( PARAMETER_CUSTOMER_SECRET, strCustomerSecret );
                	url.addParameter( PARAMETER_AUTHORIZE_END_POINT, strAuthorizeEndPoint );
                	url.addParameter( PARAMETER_ACCESS_END_POINT, strAccessEndPoint );
                	url.addParameter( PARAMETER_PHRASEANET_ID, strPhraseanetId );
                	url.addParameter( PARAMETER_PASSWORD, strPassword );
                	//url.addParameter( PARAMETER_CHECK_ACCOUNT, PARAMETER_CHECK_ACCOUNT );
                    strUrl = url.getUrl(  ).replaceAll( " ", "%20" );
                }
                else
                {           
                    strUrl = JSP_REDIRECT_TO_MANAGE_ACCOUNTS;              	
                }                
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
    
    /**
     * Returns the list of default templates
     *
     * @param request The Http request
     * @return the templates list
     */
    public String getManageTemplates( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_MANAGE_TEMPLATES );
        Map<String, Object> model = new HashMap<String, Object>(  );
        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MANAGE_TEMPLATES, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }
    
    /**
     * Returns the form to update info about a template
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    public String getModifyTemplate( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_PAGE_TITLE_MODIFY_TEMLPATE  );
        Map<String, Object> model = new HashMap<String, Object>(  );
        
        String strUrl = StringUtils.EMPTY;
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );

        if ( StringUtils.isNotBlank( strMediaType ) )
        {
            Template defaultTemplate = TemplateHome.findByPrimaryKey( strMediaType );
            
            if ( defaultTemplate != null )
            {
                model.put( MARK_TEMPLATE, defaultTemplate );

                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_TEMPLATE, getLocale(  ),
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
     * Process the change form of a template
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    public String doModifyTemplate( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        
        String strDefaultTemplate = request.getParameter( PARAMETER_DEFAULT_TEMPLATE );
        String strMediaType = request.getParameter( PARAMETER_MEDIA_TYPE );

        if ( StringUtils.isNotBlank( strDefaultTemplate ) && StringUtils.isNotBlank( strMediaType ) )
        {
            Template defaultTemplate = TemplateHome.findByPrimaryKey( strMediaType );

            if ( defaultTemplate != null )
            {
            	defaultTemplate.setTemplate( strDefaultTemplate );
            	TemplateHome.update( defaultTemplate );
	    		UrlItem url = new UrlItem( JSP_URL_MANAGE_TEMPLATES );
	    		strUrl = url.getUrl(  );
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
    
    /**
     * Method to get record type list 
     * @return type record type list 
     */
    private List<RecordTypeDTO> getRecordTypeDTOList(  )
    {
    	List<RecordTypeDTO> listRecordTypeDTO = new ArrayList<RecordTypeDTO>(  );
    	if( PhraseanetService.getMediaTypeValues(  ) != null )
        {  
	    	for( String strRecordType : PhraseanetService.getMediaTypeValues(  ) )
	    	{
	    		RecordTypeDTO recordTypeDTO = new RecordTypeDTO(  );
	    		recordTypeDTO.setName( strRecordType );
	    		if( strRecordType.contains( CONSTANT_MEDIA_TYPE_VIDEO ) )
	    		{
	    			recordTypeDTO.setId( 1 );
	    			recordTypeDTO.setIcon( CONSTANT_MEDIA_TYPE_VIDEO + CONSTANT_NAME_ICON_PICTURE );
	    			
	    		}
	    		else if( strRecordType.contains( CONSTANT_MEDIA_TYPE_AUDIO ) ) 
	    		{
	    			recordTypeDTO.setId( 2 );
	    			recordTypeDTO.setIcon( CONSTANT_MEDIA_TYPE_AUDIO + CONSTANT_NAME_ICON_PICTURE );
	    		}
	    		else if( strRecordType.contains( CONSTANT_MEDIA_TYPE_IMAGE ) ) 
	    		{
	    			recordTypeDTO.setId( 3 );
	    			recordTypeDTO.setIcon( CONSTANT_MEDIA_TYPE_IMAGE + CONSTANT_NAME_ICON_PICTURE );
	    		}
	    		else if( strRecordType.contains( CONSTANT_MEDIA_TYPE_FLASH ) ) 
	    		{
	    			recordTypeDTO.setId( 4 );
	    			recordTypeDTO.setIcon( CONSTANT_MEDIA_TYPE_FLASH + CONSTANT_NAME_ICON_PICTURE );
	    		}
	    		else
	    		{
	    			recordTypeDTO.setId( 5 );
	    			recordTypeDTO.setIcon( CONSTANT_MEDIA_TYPE_DOCUMENT + CONSTANT_NAME_ICON_PICTURE );
	    		}
	    		listRecordTypeDTO.add( recordTypeDTO );
	    	}
        }
    	return listRecordTypeDTO;
    }

}
    
