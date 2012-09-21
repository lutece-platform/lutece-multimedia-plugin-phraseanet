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
package fr.paris.lutece.plugins.phraseanet.business.template;

import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.List;


/**
* IAccountDAO Interface
*/
public interface ITemplateDAO
{
    /**
     * Insert a new record in the table.
     * @param template instance of the Template object to insert
     * @param plugin the Plugin
     */
    void insert( Template template, Plugin plugin );

    /**
    * Update the record in the table
    * @param template the reference of the Template
    * @param plugin the Plugin
    */
    void store( Template template, Plugin plugin );

    /**
     * Delete a record from the table
     * @param nIdTemplate int identifier of the Template to delete
     * @param plugin the Plugin
     */
    void delete( int nIdTemplate, Plugin plugin );

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Load the data from the table
     * @param strMediaType The type of the Template
     * @param plugin the Plugin
     * @return The instance of the Template
     */
    Template load( String strMediaType, Plugin plugin );

    /**
    * Load the data of all the Template objects and returns them as a List
    * @param plugin the Plugin
    * @return The List which contains the data of all the Template objects
    */
    List<Template> selectTemplatesList( Plugin plugin );
}