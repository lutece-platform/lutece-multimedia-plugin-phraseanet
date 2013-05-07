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
package fr.paris.lutece.plugins.phraseanet.business.record;


/**
 * This is the business class for the object Record
 */
public class Record
{
    // Variables declarations 
    private int _nRecordId;
    private int _nDataboxId;
    private String _strMimeType;
    private String _strTitle;
    private String _strOriginalName;
    private String _strLastModified;
    private String _strCreatedOn;
    private int _nCollectionId;
    private String _strSha256;
    private Thumbnail _thumbnail;
    private TechnicalInformations _technicalInformations;
    private String _strPhraseaType;
    private String _strUuid;

    /**
     * Returns the RecordId
     * @return The RecordId
     */
    public int getRecordId(  )
    {
        return _nRecordId;
    }

    /**
     * Sets the RecordId
     * @param nRecordId The RecordId
     */
    public void setRecordId( int nRecordId )
    {
        _nRecordId = nRecordId;
    }

    /**
     * Returns the DataboxId
     * @return The DataboxId
     */
    public int getDataboxId(  )
    {
        return _nDataboxId;
    }

    /**
     * Sets the DataboxId
     * @param nDataboxId The DataboxId
     */
    public void setDataboxId( int nDataboxId )
    {
        _nDataboxId = nDataboxId;
    }

    /**
     * Returns the MimeType
     * @return The MimeType
     */
    public String getMimeType(  )
    {
        return _strMimeType;
    }

    /**
     * Sets the MimeType
     * @param strMimeType The MimeType
     */
    public void setMimeType( String strMimeType )
    {
        _strMimeType = strMimeType;
    }

    /**
     * Returns the Title
     * @return The Title
     */
    public String getTitle(  )
    {
        return _strTitle;
    }

    /**
     * Sets the Title
     * @param strTitle The Title
     */
    public void setTitle( String strTitle )
    {
        _strTitle = strTitle;
    }

    /**
     * Returns the OriginalName
     * @return The OriginalName
     */
    public String getOriginalName(  )
    {
        return _strOriginalName;
    }

    /**
     * Sets the OriginalName
     * @param strOriginalName The OriginalName
     */
    public void setOriginalName( String strOriginalName )
    {
        _strOriginalName = strOriginalName;
    }

    /**
     * Returns the LastModified
     * @return The LastModified
     */
    public String getLastModified(  )
    {
        return _strLastModified;
    }

    /**
     * Sets the LastModified
     * @param strLastModified The LastModified
     */
    public void setLastModified( String strLastModified )
    {
        _strLastModified = strLastModified;
    }

    /**
     * Returns the CreatedOn
     * @return The CreatedOn
     */
    public String getCreatedOn(  )
    {
        return _strCreatedOn;
    }

    /**
     * Sets the CreatedOn
     * @param strCreatedOn The CreatedOn
     */
    public void setCreatedOn( String strCreatedOn )
    {
        _strCreatedOn = strCreatedOn;
    }

    /**
     * Returns the CollectionId
     * @return The CollectionId
     */
    public int getCollectionId(  )
    {
        return _nCollectionId;
    }

    /**
     * Sets the CollectionId
     * @param nCollectionId The CollectionId
     */
    public void setCollectionId( int nCollectionId )
    {
        _nCollectionId = nCollectionId;
    }

    /**
     * Returns the Sha256
     * @return The Sha256
     */
    public String getSha256(  )
    {
        return _strSha256;
    }

    /**
     * Sets the Sha256
     * @param strSha256 The Sha256
     */
    public void setSha256( String strSha256 )
    {
        _strSha256 = strSha256;
    }

    /**
     * Returns the Thumbnail
     * @return The Thumbnail
     */
    public Thumbnail getThumbnail(  )
    {
        return _thumbnail;
    }

    /**
     * Sets the Thumbnail
     * @param thumbnail The Thumbnail
     */
    public void setThumbnail( Thumbnail thumbnail )
    {
        _thumbnail = thumbnail;
    }

    /**
     * Returns the TechnicalInformation
     * @return The TechnicalInformation
     */
    public TechnicalInformations getTechnicalInformations(  )
    {
        return _technicalInformations;
    }

    /**
     * Sets the TechnicalInformation
     * @param technicalInformations The TechnicalInformation
     */
    public void setTechnicalInformations( TechnicalInformations technicalInformations )
    {
        _technicalInformations = technicalInformations;
    }

    /**
     * Returns the PhraseaType
     * @return The PhraseaType
     */
    public String getPhraseaType(  )
    {
        return _strPhraseaType;
    }

    /**
     * Sets the PhraseaType
     * @param strPhraseaType The PhraseaType
     */
    public void setPhraseaType( String strPhraseaType )
    {
        _strPhraseaType = strPhraseaType;
    }

    /**
     * Returns the Uuid
     * @return The Uuid
     */
    public String getUuid(  )
    {
        return _strUuid;
    }

    /**
     * Sets the Uuid
     * @param strUuid The Uuid
     */
    public void setUuid( String strUuid )
    {
        _strUuid = strUuid;
    }
}
