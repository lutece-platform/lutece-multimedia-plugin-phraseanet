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

/**
 * Account
 *
 */
public class Account 
{
	
	private int _nId;
    private String _strName;
    private String _strDescription;
    private String _strAccessURL;
    private String _strCustomerId;
    private String _strCustomerSecret;
    private String _strAuthorizeEndPoint;
    private String _strAccessEndPoint;
    private String _strPhraseanetId;
    private String _strPassword;
    private String _strToken;
    
	/**
	 * @return the _nId
	 */
	public int getId(  ) 
	{
		return _nId;
	}
	
	/**
	 * @param nId the _nId to set
	 */
	public void setId( int nId ) 
	{
		_nId = nId;
	}
	
	/**
	 * @return the _strDescription
	 */
	public String getDescription(  ) 
	{
		return _strDescription;
	}
	
	/**
	 * @param strDescription the _strDescription to set
	 */
	public void setDescription( String strDescription ) 
	{
		_strDescription = strDescription;
	}
	
	/**
	 * @return the _strName
	 */
	public String getName(  ) 
	{
		return _strName;
	}
	
	/**
	 * @param strName the _strName to set
	 */
	public void setName( String strName ) 
	{
		_strName = strName;
	}
	
	/**
	 * @return the _strAccessURL
	 */
	public String getAccessURL(  ) 
	{
		return _strAccessURL;
	}
	/**
	 * @param strAccessURL the _strAccessURL to set
	 */
	public void setAccessURL( String strAccessURL ) 
	{
		_strAccessURL = strAccessURL;
	}
	/**
	 * @return the _strCustomerId
	 */
	public String getCustomerId(  ) 
	{
		return _strCustomerId;
	}
	/**
	 * @param strCustomerId the _strCustomerId to set
	 */
	public void setCustomerId( String strCustomerId ) 
	{
		_strCustomerId = strCustomerId;
	}
	/**
	 * @return the _strCustomerSecret
	 */
	public String getCustomerSecret(  ) 
	{
		return _strCustomerSecret;
	}
	/**
	 * @param strCustomerSecret the _strCustomerSecret to set
	 */
	public void setCustomerSecret( String strCustomerSecret ) 
	{
		_strCustomerSecret = strCustomerSecret;
	}
	/**
	 * @return the _strAuthorizeEndPoint
	 */
	public String getAuthorizeEndPoint(  ) 
	{
		return _strAuthorizeEndPoint;
	}
	/**
	 * @param strAuthorizeEndPoint the _strAuthorizeEndPoint to set
	 */
	public void setAuthorizeEndPoint( String strAuthorizeEndPoint ) 
	{
		_strAuthorizeEndPoint = strAuthorizeEndPoint;
	}
	/**
	 * @return the _strAccessEndPoint
	 */
	public String getAccessEndPoint(  ) 
	{
		return _strAccessEndPoint;
	}
	/**
	 * @param strAccessEndPoint the _strAccessEndPoint to set
	 */
	public void setAccessEndPoint( String strAccessEndPoint ) 
	{
		_strAccessEndPoint = strAccessEndPoint;
	}
	/**
	 * @return the _strPhraseanetId
	 */
	public String getPhraseanetId(  ) 
	{
		return _strPhraseanetId;
	}
	/**
	 * @param strPhraseanetId the _strPhraseanetId to set
	 */
	public void setPhraseanetId( String strPhraseanetId ) 
	{
		_strPhraseanetId = strPhraseanetId;
	}
	/**
	 * @return the _strPassword
	 */
	public String getPassword(  ) 
	{
		return _strPassword;
	}
	/**
	 * @param strPassword the _strPassword to set
	 */
	public void setPassword( String strPassword ) 
	{
		_strPassword = strPassword;
	}
	/**
	 *  @return the _strToken to set
	 */
    public String getToken(  )
    {
        return _strToken;
    }
	/**
	 * @param strPassword the _strPassword to set
	 */
	public void setToken( String strToken ) 
	{
		_strToken = strToken;
	}    

    
}
