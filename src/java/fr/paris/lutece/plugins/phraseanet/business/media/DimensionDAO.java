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
 * This class provides Data Access methods for Dimension objects
 */

public final class DimensionDAO implements IDimensionDAO
{
	
	// Constants
	
	private static final String SQL_QUERY_NEW_PK = "SELECT max( id ) FROM phraseanet_dimension";
	private static final String SQL_QUERY_SELECT = "SELECT id_dimension, dimension_name, dimension_width, dimension_height FROM phraseanet_dimension WHERE id_dimension = ?";
	private static final String SQL_QUERY_INSERT = "INSERT INTO phraseanet_dimension ( id_dimension, dimension_name, dimension_width, dimension_height ) VALUES ( ?, ?, ?, ? ) ";
	private static final String SQL_QUERY_DELETE = "DELETE FROM phraseanet_dimension WHERE id_dimension = ? ";
	private static final String SQL_QUERY_UPDATE = "UPDATE phraseanet_dimension SET id_dimension = ?, dimension_name = ?, dimension_width = ?, dimension_height = ? WHERE id_dimension = ?";
	private static final String SQL_QUERY_SELECTALL = "SELECT id_dimension, dimension_name, dimension_width, dimension_height FROM phraseanet_dimension";


	
	/**
	 * Generates a new primary key
         * @param plugin The Plugin
	 * @return The new primary key
	 */
    
	public int newPrimaryKey( Plugin plugin)
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
		daoUtil.executeQuery();

		int nKey;

		if( !daoUtil.next() )
		{
			// if the table is empty
			nKey = 1;
		}

		nKey = daoUtil.getInt( 1 ) + 1;
		daoUtil.free();

		return nKey;
	}




	/**
	 * Insert a new record in the table.
	 * @param dimension instance of the Dimension object to insert
         * @param plugin The plugin
	 */

	public void insert( Dimension dimension, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT , plugin );
                
		dimension.setId( newPrimaryKey( plugin ) );
                
                daoUtil.setInt ( 1, dimension.getId ( ) );
                daoUtil.setString ( 2, dimension.getName ( ) );
                daoUtil.setInt ( 3, dimension.getWidth ( ) );
                daoUtil.setInt ( 4, dimension.getHeight ( ) );

		daoUtil.executeUpdate();
		daoUtil.free();
	}


	/**
	 * Load the data of the dimension from the table
	 * @param nId The identifier of the dimension
         * @param plugin The plugin
	 * @return the instance of the Dimension
	 */


        public Dimension load( int nId, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT , plugin );
		daoUtil.setInt( 1 , nId );
		daoUtil.executeQuery();

		Dimension dimension = null;

		if ( daoUtil.next() )
		{
			dimension = new Dimension();

                        dimension.setId( daoUtil.getInt(  1 ) );
                        dimension.setName( daoUtil.getString(  2 ) );
                        dimension.setWidth( daoUtil.getInt(  3 ) );
                        dimension.setHeight( daoUtil.getInt(  4 ) );
		}

		daoUtil.free();
		return dimension;
	}


	/**
	 * Delete a record from the table
         * @param nDimensionId The identifier of the dimension
         * @param plugin The plugin
	 */

	public void delete( int nDimensionId, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE , plugin );
		daoUtil.setInt( 1 , nDimensionId );
		daoUtil.executeUpdate();
		daoUtil.free();
	}


	/**
	 * Update the record in the table
	 * @param dimension The reference of the dimension
         * @param plugin The plugin
	 */

	public void store( Dimension dimension, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE , plugin );
                
        daoUtil.setInt( 1, dimension.getId( ) );
        daoUtil.setString( 2, dimension.getName( ) );
        daoUtil.setInt( 3, dimension.getWidth( ) );
        daoUtil.setInt( 4, dimension.getHeight( ) );
        daoUtil.setInt( 5, dimension.getId( ) );
                
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}



	/**
	 * Load the data of all the dimensions and returns them as a List
         * @param plugin The plugin
	 * @return The List which contains the data of all the dimensions
	 */

        public List<Dimension> selectDimensionsList( Plugin plugin )
	{
		List<Dimension> dimensionList = new ArrayList<Dimension>(  );
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL , plugin );
		daoUtil.executeQuery(  );

		while ( daoUtil.next(  ) )
		{
                Dimension dimension = new Dimension(  );

                    dimension.setId( daoUtil.getInt( 1 ) );
                    dimension.setName( daoUtil.getString( 2 ) );
                    dimension.setWidth( daoUtil.getInt( 3 ) );
                    dimension.setHeight( daoUtil.getInt( 4 ) );

                dimensionList.add( dimension );
		}

		daoUtil.free();
		return dimensionList;
	}

}