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
package fr.paris.lutece.plugins.phraseanet.business.account;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for Account objects
 */
public final class AccountDAO implements IAccountDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_account ) FROM phraseanet_account";
    private static final String SQL_QUERY_SELECT = "SELECT id_account, name, description, access_url, customer_id, customer_secret, autthorize_end_point, access_end_point, phraseanet_id, password, token FROM phraseanet_account WHERE id_account = ?";
    private static final String SQL_QUERY_SELECT_TOKEN = "SELECT token FROM phraseanet_account WHERE id_account = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO phraseanet_account ( id_account, name, description, access_url, customer_id, customer_secret, autthorize_end_point, access_end_point, phraseanet_id, password, token ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM phraseanet_account WHERE id_account = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE phraseanet_account SET name = ?, description = ?, access_url = ?, customer_id = ?, customer_secret = ?, autthorize_end_point = ?, access_end_point = ?, phraseanet_id = ?, password = ?, token = ? WHERE id_account = ?";
    private static final String SQL_QUERY_UPDATE_TOKEN = "UPDATE phraseanet_account SET token = ? WHERE id_account = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_account, name, description, access_url, customer_id, customer_secret, autthorize_end_point, access_end_point, phraseanet_id, password, token FROM phraseanet_account";

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
     * @param account instance of the Account object to insert
     * @param plugin The plugin
     */
    public void insert( Account account, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        account.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, account.getId(  ) );
        daoUtil.setString( 2, account.getName(  ) );
        daoUtil.setString( 3, account.getDescription(  ) );
        daoUtil.setString( 4, account.getAccessURL(  ) );
        daoUtil.setString( 5, account.getCustomerId(  ) );
        daoUtil.setString( 6, account.getCustomerSecret(  ) );
        daoUtil.setString( 7, account.getAuthorizeEndPoint(  ) );
        daoUtil.setString( 8, account.getAccessEndPoint(  ) );
        daoUtil.setString( 9, account.getPhraseanetId(  ) );
        daoUtil.setString( 10, account.getPassword(  ) );
        daoUtil.setString( 11, account.getToken(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Load the data of the Account from the table
     * @param nId The identifier of the Account
     * @param plugin The plugin
     * @return the instance of the Account
     */
    public Account load( int nId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nId );
        daoUtil.executeQuery(  );

        Account account = null;

        if ( daoUtil.next(  ) )
        {
            account = new Account(  );

            account.setId( daoUtil.getInt( 1 ) );
            account.setName( daoUtil.getString( 2 ) );
            account.setDescription( daoUtil.getString( 3 ) );
            account.setAccessURL( daoUtil.getString( 4 ) );
            account.setCustomerId( daoUtil.getString( 5 ) );
            account.setCustomerSecret( daoUtil.getString( 6 ) );
            account.setAuthorizeEndPoint( daoUtil.getString( 7 ) );
            account.setAccessEndPoint( daoUtil.getString( 8 ) );
            account.setPhraseanetId( daoUtil.getString( 9 ) );
            account.setPassword( daoUtil.getString( 10 ) );
            account.setToken( daoUtil.getString( 11 ) );
        }

        daoUtil.free(  );

        return account;
    }

    /**
     * Delete a record from the table
     * @param nAccountId The identifier of the Account
     * @param plugin The plugin
     */
    public void delete( int nAccountId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nAccountId );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Update the account record in the table
     * @param account The reference of the Account
     * @param plugin The plugin
     */
    public void store( Account account, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setString( 1, account.getName(  ) );
        daoUtil.setString( 2, account.getDescription(  ) );
        daoUtil.setString( 3, account.getAccessURL(  ) );
        daoUtil.setString( 4, account.getCustomerId(  ) );
        daoUtil.setString( 5, account.getCustomerSecret(  ) );
        daoUtil.setString( 6, account.getAuthorizeEndPoint(  ) );
        daoUtil.setString( 7, account.getAccessEndPoint(  ) );
        daoUtil.setString( 8, account.getPhraseanetId(  ) );
        daoUtil.setString( 9, account.getPassword(  ) );
        daoUtil.setString( 10, account.getToken(  ) );
        daoUtil.setInt( 11, account.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

     /**
     * Update the token record in the table
     * @param account The reference of the Account
     * @param plugin The plugin
     */
    public void updateToken( Account account, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE_TOKEN, plugin );

        daoUtil.setString( 1, account.getToken(  ) );
        daoUtil.setInt( 2, account.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
    
    /**
     * Load the data of all the Accounts and returns them as a List
     * @param plugin The plugin
     * @return The List which contains the data of all the Accounts
     */
    public List<Account> selectAccountsList( Plugin plugin )
    {
        List<Account> accountList = new ArrayList<Account>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Account account = new Account(  );

            account.setId( daoUtil.getInt( 1 ) );
            account.setName( daoUtil.getString( 2 ) );
            account.setDescription( daoUtil.getString( 3 ) );
            account.setAccessURL( daoUtil.getString( 4 ) );
            account.setCustomerId( daoUtil.getString( 5 ) );
            account.setCustomerSecret( daoUtil.getString( 6 ) );
            account.setAuthorizeEndPoint( daoUtil.getString( 7 ) );
            account.setAccessEndPoint( daoUtil.getString( 8 ) );
            account.setPhraseanetId( daoUtil.getString( 9 ) );
            account.setPassword( daoUtil.getString( 10 ) );
            account.setToken( daoUtil.getString( 11 ) );

            accountList.add( account );
        }

        daoUtil.free(  );

        return accountList;
    }
}
