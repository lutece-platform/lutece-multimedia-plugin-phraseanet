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
package fr.paris.lutece.plugins.phraseanet.business.embed;


/**
 * Embed Item
 */
public class EmbedItem
{
    // Variables declarations 
    private Permalink _permalink;
    private int _nHeight;
    private int _nWidth;
    private int _nSize;
    private String _strType;
    private String _strMime;

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
    public int getSize(  )
    {
        return _nSize;
    }

    /**
     * Sets the Size
     * @param nSize The Size
     */
    public void setSize( int nSize )
    {
        _nSize = nSize;
    }

    /**
     * Returns the Type
     * @return The Type
     */
    public String getType(  )
    {
        return _strType;
    }

    /**
     * Sets the Type
     * @param strType The Type
     */
    public void setType( String strType )
    {
        _strType = strType;
    }

    /**
     * Returns the Mime
     * @return The Mime
     */
    public String getMime(  )
    {
        return _strMime;
    }

    /**
     * Sets the Mime
     * @param strMime The Mime
     */
    public void setMime( String strMime )
    {
        _strMime = strMime;
    }
}
