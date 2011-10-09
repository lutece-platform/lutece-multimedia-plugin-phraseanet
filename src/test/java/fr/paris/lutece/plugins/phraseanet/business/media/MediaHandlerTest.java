/*
 * Copyright (c) 2002-2009, Mairie de Paris
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

import fr.paris.lutece.test.LuteceTestCase;


public class MediaHandlerTest extends LuteceTestCase
{
    private final static int ID1 = 1;
    private final static int ID2 = 2;
    private final static String NAME1 = "Name1";
    private final static String NAME2 = "Name2";
    private final static String DESCRIPTION1 = "Description1";
    private final static String DESCRIPTION2 = "Description2";
    private final static String ICONURL1 = "IconUrl1";
    private final static String ICONURL2 = "IconUrl2";
    private final static String INSERTTEMPLATE1 = "InsertTemplate1";
    private final static String INSERTTEMPLATE2 = "InsertTemplate2";
    private final static String MEDIATYPE1 = "MediaType1";
    private final static String MEDIATYPE2 = "MediaType2";
    private final static String BASES1 = "Bases1";
    private final static String BASES2 = "Bases2";
    private final static int DEFAULTWIDTH1 = 1;
    private final static int DEFAULTWIDTH2 = 2;
    private final static int DEFAULTHEIGHT1 = 1;
    private final static int DEFAULTHEIGHT2 = 2;

    public void testBusiness(  )
    {
        // Initialize an object
        MediaHandler mediaHandler = new MediaHandler(  );
        mediaHandler.setId( ID1 );
        mediaHandler.setName( NAME1 );
        mediaHandler.setDescription( DESCRIPTION1 );
        mediaHandler.setIconUrl( ICONURL1 );
        mediaHandler.setInsertTemplate( INSERTTEMPLATE1 );
        mediaHandler.setMediaType( MEDIATYPE1 );
        mediaHandler.setBases( BASES1 );
        mediaHandler.setDefaultWidth( DEFAULTWIDTH1 );
        mediaHandler.setDefaultHeight( DEFAULTHEIGHT1 );

        // Create test
        MediaHandlerHome.create( mediaHandler );

        MediaHandler mediaHandlerStored = MediaHandlerHome.findByPrimaryKey( mediaHandler.getId(  ) );
        assertEquals( mediaHandlerStored.getId(  ), mediaHandler.getId(  ) );
        assertEquals( mediaHandlerStored.getName(  ), mediaHandler.getName(  ) );
        assertEquals( mediaHandlerStored.getDescription(  ), mediaHandler.getDescription(  ) );
        assertEquals( mediaHandlerStored.getIconUrl(  ), mediaHandler.getIconUrl(  ) );
        assertEquals( mediaHandlerStored.getInsertTemplate(  ), mediaHandler.getInsertTemplate(  ) );
        assertEquals( mediaHandlerStored.getMediaType(  ), mediaHandler.getMediaType(  ) );
        assertEquals( mediaHandlerStored.getBases(  ), mediaHandler.getBases(  ) );
        assertEquals( mediaHandlerStored.getDefaultWidth(  ), mediaHandler.getDefaultWidth(  ) );
        assertEquals( mediaHandlerStored.getDefaultHeight(  ), mediaHandler.getDefaultHeight(  ) );

        // Update test
        mediaHandler.setId( ID2 );
        mediaHandler.setName( NAME2 );
        mediaHandler.setDescription( DESCRIPTION2 );
        mediaHandler.setIconUrl( ICONURL2 );
        mediaHandler.setInsertTemplate( INSERTTEMPLATE2 );
        mediaHandler.setMediaType( MEDIATYPE2 );
        mediaHandler.setBases( BASES2 );
        mediaHandler.setDefaultWidth( DEFAULTWIDTH2 );
        mediaHandler.setDefaultHeight( DEFAULTHEIGHT2 );
        MediaHandlerHome.update( mediaHandler );
        mediaHandlerStored = MediaHandlerHome.findByPrimaryKey( mediaHandler.getId(  ) );
        assertEquals( mediaHandlerStored.getId(  ), mediaHandler.getId(  ) );
        assertEquals( mediaHandlerStored.getName(  ), mediaHandler.getName(  ) );
        assertEquals( mediaHandlerStored.getDescription(  ), mediaHandler.getDescription(  ) );
        assertEquals( mediaHandlerStored.getIconUrl(  ), mediaHandler.getIconUrl(  ) );
        assertEquals( mediaHandlerStored.getInsertTemplate(  ), mediaHandler.getInsertTemplate(  ) );
        assertEquals( mediaHandlerStored.getMediaType(  ), mediaHandler.getMediaType(  ) );
        assertEquals( mediaHandlerStored.getBases(  ), mediaHandler.getBases(  ) );
        assertEquals( mediaHandlerStored.getDefaultWidth(  ), mediaHandler.getDefaultWidth(  ) );
        assertEquals( mediaHandlerStored.getDefaultHeight(  ), mediaHandler.getDefaultHeight(  ) );

        // List test
        MediaHandlerHome.findAll(  );

        // Delete test
        MediaHandlerHome.remove( mediaHandler.getId(  ) );
        mediaHandlerStored = MediaHandlerHome.findByPrimaryKey( mediaHandler.getId(  ) );
        assertNull( mediaHandlerStored );
    }
}
