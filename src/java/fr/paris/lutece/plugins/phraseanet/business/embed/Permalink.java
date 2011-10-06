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
 * Permalink
 */
public class Permalink
{
    // Variables declarations 
    private String _strCreatedOn;
    private int _nId;
    private boolean _IsActivated;
    private String _strLabel;
    private String _strLastModified;
    private String _strPageURL;
    private String _strURL;
    
    
       /**
        * Returns the CreatedOn
        * @return The CreatedOn
        */ 
    public String getCreatedOn()
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
        * Returns the Id
        * @return The Id
        */ 
    public int getId()
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
        * Returns the IsActivated
        * @return The IsActivated
        */ 
    public boolean getIsActivated()
    {
        return _IsActivated;
    }
    
       /**
        * Sets the IsActivated
        * @param IsActivated The IsActivated
        */ 
    public void setIsActivated( boolean IsActivated )
    {
        _IsActivated = IsActivated;
    }
    
       /**
        * Returns the Label
        * @return The Label
        */ 
    public String getLabel()
    {
        return _strLabel;
    }
    
       /**
        * Sets the Label
        * @param strLabel The Label
        */ 
    public void setLabel( String strLabel )
    {
        _strLabel = strLabel;
    }
    
       /**
        * Returns the LastModified
        * @return The LastModified
        */ 
    public String getLastModified()
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
        * Returns the PageURL
        * @return The PageURL
        */ 
    public String getPageURL()
    {
        return _strPageURL;
    }
    
       /**
        * Sets the PageURL
        * @param strPageURL The PageURL
        */ 
    public void setPageURL( String strPageURL )
    {
        _strPageURL = strPageURL;
    }
    
       /**
        * Returns the URL
        * @return The URL
        */ 
    public String getURL()
    {
        return _strURL;
    }
    
       /**
        * Sets the URL
        * @param strURL The URL
        */ 
    public void setURL( String strURL )
    {
        _strURL = strURL;
    }
     
}
