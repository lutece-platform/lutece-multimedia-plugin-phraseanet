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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for Account objects
 */
public final class TemplateDAO implements ITemplateDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_template ) FROM phraseanet_template";
    private static final String SQL_QUERY_SELECT = "SELECT id_template, name, default_template, media_type FROM phraseanet_template WHERE media_type = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO phraseanet_template ( id_template, name, default_template, media_type ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM phraseanet_template WHERE id_template = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE phraseanet_template SET name = ?, default_template = ? WHERE id_template = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_template, name, default_template, media_type FROM phraseanet_template";

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
     * @param template instance of the Template object to insert
     * @param plugin The plugin
     */
    public void insert( Template template, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        template.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, template.getId(  ) );
        daoUtil.setString( 2, template.getName(  ) );
        daoUtil.setString( 3, template.getTemplate(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Load the data of the Template from the table
     * @param strMediaType The media type
     * @param plugin The plugin
     * @return the instance of the Template
     */
    public Template load( String strMediaType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setString( 1, strMediaType );
        daoUtil.executeQuery(  );

        Template template = null;

        if ( daoUtil.next(  ) )
        {
            template = new Template(  );

            template.setId( daoUtil.getInt( 1 ) );
            template.setName( daoUtil.getString( 2 ) );
            template.setTemplate( daoUtil.getString( 3 ) );
            template.setMediaType( daoUtil.getString( 4 ) );
        }

        daoUtil.free(  );

        return template;
    }

    /**
     * Delete a record from the table
     * @param nTemplateId The identifier of the Template
     * @param plugin The plugin
     */
    public void delete( int nTemplateId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nTemplateId );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Update the record in the table
     * @param template The reference of the Template
     * @param plugin The plugin
     */
    public void store( Template template, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setString( 1, template.getName(  ) );
        daoUtil.setString( 2, template.getTemplate(  ) );
        daoUtil.setInt( 3, template.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Load the data of all the Templates and returns them as a List
     * @param plugin The plugin
     * @return The List which contains the data of all the Templates
     */
    public List<Template> selectTemplatesList( Plugin plugin )
    {
        List<Template> templateList = new ArrayList<Template>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Template template = new Template(  );

            template.setId( daoUtil.getInt( 1 ) );
            template.setName( daoUtil.getString( 2 ) );
            template.setTemplate( daoUtil.getString( 3 ) );

            templateList.add( template );
        }

        daoUtil.free(  );

        return templateList;
    }
}
