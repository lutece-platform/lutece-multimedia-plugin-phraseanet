/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.phraseanet.business.template;

import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 * This class provides instances management methods (create, find, ...) for Template objects
 */
public final class TemplateHome
{
    // Static variable pointed at the DAO instance
    private static ITemplateDAO _dao = (ITemplateDAO) SpringContextService.getBean( 
            "phraseanet.templateDAO" );
    private static Plugin _plugin = PluginService.getPlugin( Constants.PLUGIN_NAME );

    /**
     * Private constructor - this class need not be instantiated
     */
    private TemplateHome(  )
    {
    }

    /**
     * Create an instance of the Template class
     * @param template The instance of the Template which contains the informations to store
     * @return The  instance of Template which has been created with its primary key.
     */
    public static Template create( Template template )
    {
        _dao.insert( template, _plugin );

        return template;
    }

    /**
     * Update of the Template data specified in parameter
     * @param template The instance of the Template which contains the data to store
     * @return The instance of the  Template which has been updated
     */
    public static Template update( Template template )
    {
        _dao.store( template, _plugin );

        return template;
    }

    /**
     * Remove the Template whose identifier is specified in parameter
     * @param nTemplateId The Template Id
     */
    public static void remove( int nTemplateId )
    {
        _dao.delete( nTemplateId, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a Template whose identifier is specified in parameter
     * @param strMediaType The Template media type
     * @return an instance of Template
     */
    public static Template findByPrimaryKey( String strMediaType )
    {
        return _dao.load( strMediaType, _plugin );
    }

    /**
     * Load the data of all the Template objects and returns them in form of a collection
     * @return the list which contains the data of all the Template objects
     */
    public static List<Template> findAll(  )
    {
        return _dao.selectTemplatesList( _plugin );
    }
}
