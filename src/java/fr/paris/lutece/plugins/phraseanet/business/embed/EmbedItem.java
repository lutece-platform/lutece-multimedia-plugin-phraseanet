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
package fr.paris.lutece.plugins.phraseanet.business.embed;


/**
 * Embed Item
 */
public class EmbedItem
{
    // Variables declarations 
    private String _itemName;
    private Permalink _permalink;
    private int _nHeight;
    private int _nWidth;
    private int _nFilesize;
    private String _strPlayerType;
    private String _strMimeType;

    /**
     * Returns the itemName
     * @return The itemName
     */
    public String getItemName(  )
    {
        return _itemName;
    }  
    
    /**
     * Sets the itemName
     * @param itemName The itemName
     */
    public void setItemName( String itemName )
    {
        _itemName = itemName;
    }    
    
    /**
     * Returns the Permalink
     * @return The Permalink
     */
    public Permalink getPermalink(  )
    {
        return _permalink;
    }

    /**
     * Sets the Permalink
     * @param permalink The Permalink
     */
    public void setPermalink( Permalink permalink )
    {
        _permalink = permalink;
    }

    /**
     * Returns the Height
     * @return The Height
     */
    public int getHeight(  )
    {
        return _nHeight;
    }

    /**
     * Sets the Height
     * @param nHeight The Height
     */
    public void setHeight( int nHeight )
    {
        _nHeight = nHeight;
    }

    /**
     * Returns the Width
     * @return The Width
     */
    public int getWidth(  )
    {
        return _nWidth;
    }

    /**
     * Sets the Width
     * @param nWidth The Width
     */
    public void setWidth( int nWidth )
    {
        _nWidth = nWidth;
    }

    /**
     * Returns the Size
     * @return The Size
     */
    public int getFilesize(  )
    {
        return _nFilesize;
    }

    /**
     * Sets the Size
     * @param nSize The Size
     */
    public void setFilesize( int nSize )
    {
        _nFilesize = nSize;
    }

    /**
     * Returns the Type
     * @return The Type
     */
    public String getPlayerType(  )
    {
        return _strPlayerType;
    }

    /**
     * Sets the Type
     * @param strType The Type
     */
    public void setPlayerType( String strType )
    {
        _strPlayerType = strType;
    }

    /**
     * Returns the Mime
     * @return The Mime
     */
    public String getMimeType(  )
    {
        return _strMimeType;
    }

    /**
     * Sets the Mime
     * @param strMime The Mime
     */
    public void setMimeType( String strMime )
    {
        _strMimeType = strMime;
    }
}
