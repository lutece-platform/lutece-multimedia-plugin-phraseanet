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
package fr.paris.lutece.plugins.phraseanet.business.response;


/**
 * This is the business class for the object Meta
 */
public class Meta
{
    // Variables declarations 
    private String _strApiVersion;
    private String _strRequest;
    private String _strResponseTime;
    private int _nHttpCode;
    private String _strErrorMessage;
    private String _strErrorDetails;
    private String _strCharset;

    /**
     * Returns the ApiVersion
     * @return The ApiVersion
     */
    public String getApiVersion(  )
    {
        return _strApiVersion;
    }

    /**
     * Sets the ApiVersion
     * @param strApiVersion The ApiVersion
     */
    public void setApiVersion( String strApiVersion )
    {
        _strApiVersion = strApiVersion;
    }

    /**
     * Returns the Request
     * @return The Request
     */
    public String getRequest(  )
    {
        return _strRequest;
    }

    /**
     * Sets the Request
     * @param strRequest The Request
     */
    public void setRequest( String strRequest )
    {
        _strRequest = strRequest;
    }

    /**
     * Returns the ResponseTime
     * @return The ResponseTime
     */
    public String getResponseTime(  )
    {
        return _strResponseTime;
    }

    /**
     * Sets the ResponseTime
     * @param strResponseTime The ResponseTime
     */
    public void setResponseTime( String strResponseTime )
    {
        _strResponseTime = strResponseTime;
    }

    /**
     * Returns the HttpCode
     * @return The HttpCode
     */
    public int getHttpCode(  )
    {
        return _nHttpCode;
    }

    /**
     * Sets the HttpCode
     * @param nHttpCode The HttpCode
     */
    public void setHttpCode( int nHttpCode )
    {
        _nHttpCode = nHttpCode;
    }

    /**
     * Returns the ErrorMessage
     * @return The ErrorMessage
     */
    public String getErrorMessage(  )
    {
        return _strErrorMessage;
    }

    /**
     * Sets the ErrorMessage
     * @param strErrorMessage The ErrorMessage
     */
    public void setErrorMessage( String strErrorMessage )
    {
        _strErrorMessage = strErrorMessage;
    }

    /**
     * Returns the ErrorDetails
     * @return The ErrorDetails
     */
    public String getErrorDetails(  )
    {
        return _strErrorDetails;
    }

    /**
     * Sets the ErrorDetails
     * @param strErrorDetails The ErrorDetails
     */
    public void setErrorDetails( String strErrorDetails )
    {
        _strErrorDetails = strErrorDetails;
    }

    /**
     * Returns the Charset
     * @return The Charset
     */
    public String getCharset(  )
    {
        return _strCharset;
    }

    /**
     * Sets the Charset
     * @param strCharset The Charset
     */
    public void setCharset( String strCharset )
    {
        _strCharset = strCharset;
    }
}
