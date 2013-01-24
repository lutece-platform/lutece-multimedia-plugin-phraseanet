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
package fr.paris.lutece.plugins.phraseanet.business.record;


/**
 * This is the business class for the object Metadata
 */
public class Metadata
{
    // Variables declarations 
    private int _nMetaId;
    private int _nMetaStructureId;
    private String _strName;
    private String _strValue;

    /**
     * Returns the MetaId
     * @return The MetaId
     */
    public int getMetaId(  )
    {
        return _nMetaId;
    }

    /**
     * Sets the MetaId
     * @param nMetaId The MetaId
     */
    public void setMetaId( int nMetaId )
    {
        _nMetaId = nMetaId;
    }

    /**
     * Returns the MetaStructureId
     * @return The MetaStructureId
     */
    public int getMetaStructureId(  )
    {
        return _nMetaStructureId;
    }

    /**
     * Sets the MetaStructureId
     * @param nMetaStructureId The MetaStructureId
     */
    public void setMetaStructureId( int nMetaStructureId )
    {
        _nMetaStructureId = nMetaStructureId;
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
     * Returns the Value
     * @return The Value
     */
    public String getValue(  )
    {
        return _strValue;
    }

    /**
     * Sets the Value
     * @param strValue The Value
     */
    public void setValue( String strValue )
    {
        _strValue = strValue;
    }

}
