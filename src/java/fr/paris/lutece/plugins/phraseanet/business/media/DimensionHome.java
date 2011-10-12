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
package fr.paris.lutece.plugins.phraseanet.business.media;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 * This class provides instances management methods (create, find, ...) for Dimension objects
 */
public final class DimensionHome
{
    // Static variable pointed at the DAO instance

    //    private static IDimensionDAO _dao = ( IDimensionDAO ) SpringContextService.getPluginBean( "phraseanet" , "phraseanet.dimensionDAO" );
    private static IDimensionDAO _dao = new DimensionDAO(  );
    private static Plugin _plugin = PluginService.getPlugin( "phraseanet" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private DimensionHome(  )
    {
    }

    /**
     * Create an instance of the dimension class
     * @param dimension The instance of the Dimension which contains the informations to store
     * @return The  instance of dimension which has been created with its primary key.
     */
    public static Dimension create( Dimension dimension )
    {
        _dao.insert( dimension, _plugin );

        return dimension;
    }

    /**
     * Update of the dimension which is specified in parameter
     * @param dimension The instance of the Dimension which contains the data to store
     * @return The instance of the  dimension which has been updated
     */
    public static Dimension update( Dimension dimension )
    {
        _dao.store( dimension, _plugin );

        return dimension;
    }

    /**
     * Remove the dimension whose identifier is specified in parameter
     * @param nDimensionId The dimension Id
     */
    public static void remove( int nDimensionId )
    {
        _dao.delete( nDimensionId, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a dimension whose identifier is specified in parameter
     * @param nKey The dimension primary key
     * @return an instance of Dimension
     */
    public static Dimension findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the dimension objects and returns them in form of a list
     * @return the list which contains the data of all the dimension objects
     */
    public static List<Dimension> getDimensionsList(  )
    {
        return _dao.selectDimensionsList( _plugin );
    }
}