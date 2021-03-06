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
package fr.paris.lutece.plugins.phraseanet.business.account;

import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 * This class provides instances management methods (create, find, ...) for Account objects
 */
public final class AccountHome
{
    private static IAccountDAO _dao = (IAccountDAO) SpringContextService.getBean( 
            "phraseanet.accountDAO" );
    private static Plugin _plugin = PluginService.getPlugin( Constants.PLUGIN_NAME );

    /**
     * Private constructor - this class need not be instantiated
     */
    private AccountHome(  )
    {
    }

    /**
     * Create an instance of the Account class
     * @param account The instance of the Account which contains the informations to store
     * @return The  instance of Account which has been created with its primary key.
     */
    public static Account create( Account account )
    {
        _dao.insert( account, _plugin );

        return account;
    }

    /**
     * Update of the Account data specified in parameter
     * @param account The instance of the Account which contains the data to store
     * @return The instance of the  Account which has been updated
     */
    public static Account update( Account account )
    {
        _dao.store( account, _plugin );

        return account;
    }

    /**
     * Remove the Account whose identifier is specified in parameter
     * @param nAccountId The Account Id
     */
    public static void remove( int nAccountId )
    {
        _dao.delete( nAccountId, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a Account whose identifier is specified in parameter
     * @param nKey The Account primary key
     * @return an instance of Account
     */
    public static Account findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the Account objects and returns them in form of a collection
     * @return the list which contains the data of all the Account objects
     */
    public static List<Account> findAll(  )
    {
        return _dao.selectAccountsList( _plugin );
    }
}
