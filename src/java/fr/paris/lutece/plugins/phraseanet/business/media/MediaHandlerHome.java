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
package fr.paris.lutece.plugins.phraseanet.business.media;

import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 * This class provides instances management methods (create, find, ...) for MediaHandler objects
 */
public final class MediaHandlerHome
{
    // Static variable pointed at the DAO instance
    private static IMediaHandlerDAO _dao = (IMediaHandlerDAO) SpringContextService.getBean( 
            "phraseanet.mediaHandlerDAO" );
    private static Plugin _plugin = PluginService.getPlugin( Constants.PLUGIN_NAME );

    /**
     * Private constructor - this class need not be instantiated
     */
    private MediaHandlerHome(  )
    {
    }

    /**
     * Create an instance of the mediaHandler class
     * @param mediaHandler The instance of the MediaHandler which contains the informations to store
     * @return The  instance of mediaHandler which has been created with its primary key.
     */
    public static MediaHandler create( MediaHandler mediaHandler )
    {
        _dao.insert( mediaHandler, _plugin );

        return mediaHandler;
    }

    /**
     * Update of the mediaHandler data specified in parameter
     * @param mediaHandler The instance of the MediaHandler which contains the data to store
     * @return The instance of the  mediaHandler which has been updated
     */
    public static MediaHandler update( MediaHandler mediaHandler )
    {
        _dao.store( mediaHandler, _plugin );

        return mediaHandler;
    }

    /**
     * Remove the mediaHandler whose identifier is specified in parameter
     * @param nMediaHandlerId The mediaHandler Id
     */
    public static void remove( int nMediaHandlerId )
    {
        _dao.delete( nMediaHandlerId, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a mediaHandler whose identifier is specified in parameter
     * @param nKey The mediaHandler primary key
     * @return an instance of MediaHandler
     */
    public static MediaHandler findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the mediaHandler objects and returns them in form of a collection
     * @return the list which contains the data of all the mediaHandler objects
     */
    public static List<MediaHandler> findAll(  )
    {
        return _dao.selectMediaHandlersList( _plugin );
    }
    
    /**
     * Check if some Account is used by Media
     * @param nIdAccount the id account to check
     * @return true if the account is used
     */
    public static boolean checkMediaHandlerByAccount( int nIdAccount )
    {
    	return _dao.checkMediaHandlerByAccount( nIdAccount, _plugin );
    }
}
