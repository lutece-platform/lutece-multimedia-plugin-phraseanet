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
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for MediaHandler objects
 */
public final class MediaHandlerDAO implements IMediaHandlerDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_media ) FROM phraseanet_media";
    private static final String SQL_QUERY_SELECT = "SELECT id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height FROM phraseanet_media WHERE id_media = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO phraseanet_media ( id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM phraseanet_media WHERE id_media = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE phraseanet_media SET id_media = ?, media_name = ?, media_description = ?, url_icon = ?, insert_template = ?, media_type = ?, bases = ?, default_width = ?, default_height = ? WHERE id_media = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height FROM phraseanet_media";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery(  );

        int nKey;

        if ( !daoUtil.next(  ) )
        {
            // if the table is empty
            nKey = 1;
        }

        nKey = daoUtil.getInt( 1 ) + 1;
        daoUtil.free(  );

        return nKey;
    }

    /**
     * Insert a new record in the table.
     * @param mediaHandler instance of the MediaHandler object to insert
     * @param plugin The plugin
     */
    public void insert( MediaHandler mediaHandler, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        mediaHandler.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, mediaHandler.getId(  ) );
        daoUtil.setString( 2, mediaHandler.getName(  ) );
        daoUtil.setString( 3, mediaHandler.getDescription(  ) );
        daoUtil.setString( 4, mediaHandler.getIconUrl(  ) );
        daoUtil.setString( 5, mediaHandler.getInsertTemplate(  ) );
        daoUtil.setString( 6, mediaHandler.getMediaType(  ) );
        daoUtil.setString( 7, mediaHandler.getBases(  ) );
        daoUtil.setInt( 8, mediaHandler.getDefaultWidth(  ) );
        daoUtil.setInt( 9, mediaHandler.getDefaultHeight(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Load the data of the mediaHandler from the table
     * @param nId The identifier of the mediaHandler
     * @param plugin The plugin
     * @return the instance of the MediaHandler
     */
    public MediaHandler load( int nId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nId );
        daoUtil.executeQuery(  );

        MediaHandler mediaHandler = null;

        if ( daoUtil.next(  ) )
        {
            mediaHandler = new MediaHandler(  );

            mediaHandler.setId( daoUtil.getInt( 1 ) );
            mediaHandler.setName( daoUtil.getString( 2 ) );
            mediaHandler.setDescription( daoUtil.getString( 3 ) );
            mediaHandler.setIconUrl( daoUtil.getString( 4 ) );
            mediaHandler.setInsertTemplate( daoUtil.getString( 5 ) );
            mediaHandler.setMediaType( daoUtil.getString( 6 ) );
            mediaHandler.setBases( daoUtil.getString( 7 ) );
            mediaHandler.setDefaultWidth( daoUtil.getInt( 8 ) );
            mediaHandler.setDefaultHeight( daoUtil.getInt( 9 ) );
        }

        daoUtil.free(  );

        return mediaHandler;
    }

    /**
     * Delete a record from the table
     * @param nMediaHandlerId The identifier of the mediaHandler
     * @param plugin The plugin
     */
    public void delete( int nMediaHandlerId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nMediaHandlerId );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Update the record in the table
     * @param mediaHandler The reference of the mediaHandler
     * @param plugin The plugin
     */
    public void store( MediaHandler mediaHandler, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, mediaHandler.getId(  ) );
        daoUtil.setString( 2, mediaHandler.getName(  ) );
        daoUtil.setString( 3, mediaHandler.getDescription(  ) );
        daoUtil.setString( 4, mediaHandler.getIconUrl(  ) );
        daoUtil.setString( 5, mediaHandler.getInsertTemplate(  ) );
        daoUtil.setString( 6, mediaHandler.getMediaType(  ) );
        daoUtil.setString( 7, mediaHandler.getBases(  ) );
        daoUtil.setInt( 8, mediaHandler.getDefaultWidth(  ) );
        daoUtil.setInt( 9, mediaHandler.getDefaultHeight(  ) );
        daoUtil.setInt( 10, mediaHandler.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Load the data of all the mediaHandlers and returns them as a List
     * @param plugin The plugin
     * @return The List which contains the data of all the mediaHandlers
     */
    public List<MediaHandler> selectMediaHandlersList( Plugin plugin )
    {
        List<MediaHandler> mediaHandlerList = new ArrayList<MediaHandler>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            MediaHandler mediaHandler = new MediaHandler(  );

            mediaHandler.setId( daoUtil.getInt( 1 ) );
            mediaHandler.setName( daoUtil.getString( 2 ) );
            mediaHandler.setDescription( daoUtil.getString( 3 ) );
            mediaHandler.setIconUrl( daoUtil.getString( 4 ) );
            mediaHandler.setInsertTemplate( daoUtil.getString( 5 ) );
            mediaHandler.setMediaType( daoUtil.getString( 6 ) );
            mediaHandler.setBases( daoUtil.getString( 7 ) );
            mediaHandler.setDefaultWidth( daoUtil.getInt( 8 ) );
            mediaHandler.setDefaultHeight( daoUtil.getInt( 9 ) );

            mediaHandlerList.add( mediaHandler );
        }

        daoUtil.free(  );

        return mediaHandlerList;
    }
}
