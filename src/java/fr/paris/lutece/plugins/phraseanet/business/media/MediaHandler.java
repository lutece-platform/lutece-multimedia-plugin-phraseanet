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
package fr.paris.lutece.plugins.phraseanet.business.media;


/**
 * MediaHandler
 */
public class MediaHandler
{
    private int _nId;
    private String _strName;
    private String _strDescription;
    private String _strIconUrl;
    private String _strInsertTemplate;
    private String _strMediaType;
    private String _strBases;
    private int _nDefaultWidth;
    private int _nDefaultHeight;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId(  )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the Name
     * @return The Name
     */
    public String getName(  )
    {
        return _strName;
    }

    /**
     * Sets the Name
     * @param strName The Name
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Returns the Description
     * @return The Description
     */
    public String getDescription(  )
    {
        return _strDescription;
    }

    /**
     * Sets the Description
     * @param strDescription The Description
     */
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
    }

    /**
     * Returns the IconUrl
     * @return The IconUrl
     */
    public String getIconUrl(  )
    {
        return _strIconUrl;
    }

    /**
     * Sets the IconUrl
     * @param strIconUrl The IconUrl
     */
    public void setIconUrl( String strIconUrl )
    {
        _strIconUrl = strIconUrl;
    }

    /**
     * Returns the InsertTemplate
     * @return The InsertTemplate
     */
    public String getInsertTemplate(  )
    {
        return _strInsertTemplate;
    }

    /**
     * Sets the InsertTemplate
     * @param strInsertTemplate The InsertTemplate
     */
    public void setInsertTemplate( String strInsertTemplate )
    {
        _strInsertTemplate = strInsertTemplate;
    }

    /**
    * Returns the MediaType
    * @return The MediaType
    */
    public String getMediaType(  )
    {
        return _strMediaType;
    }

    /**
     * Sets the MediaType
     * @param strMediaType The MediaType
     */
    public void setMediaType( String strMediaType )
    {
        _strMediaType = strMediaType;
    }

    /**
     * Returns the Bases
     * @return The Bases
     */
    public String getBases(  )
    {
        return _strBases;
    }

    /**
     * Sets the Bases
     * @param strBases The Bases
     */
    public void setBases( String strBases )
    {
        _strBases = strBases;
    }

    /**
     * Returns the DefaultWidth
     * @return The DefaultWidth
     */
    public int getDefaultWidth(  )
    {
        return _nDefaultWidth;
    }

    /**
     * Sets the DefaultWidth
     * @param nDefaultWidth The DefaultWidth
     */
    public void setDefaultWidth( int nDefaultWidth )
    {
        _nDefaultWidth = nDefaultWidth;
    }

    /**
     * Returns the DefaultHeight
     * @return The DefaultHeight
     */
    public int getDefaultHeight(  )
    {
        return _nDefaultHeight;
    }

    /**
     * Sets the DefaultHeight
     * @param nDefaultHeight The DefaultHeight
     */
    public void setDefaultHeight( int nDefaultHeight )
    {
        _nDefaultHeight = nDefaultHeight;
    }
}
