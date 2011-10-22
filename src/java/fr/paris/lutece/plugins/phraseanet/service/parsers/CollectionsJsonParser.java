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
package fr.paris.lutece.plugins.phraseanet.service.parsers;

import fr.paris.lutece.plugins.phraseanet.business.databox.Collection;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * CollectionsJsonParser
 */
public final class CollectionsJsonParser
{
    /** private constructor */
    private CollectionsJsonParser()
    {
        
    }
    
    /**
     * Parse a list of collection
     * @param jsonResponse The response as JSONObject
     * @return The list
     */
    public static List<Collection> parse( JSONObject jsonResponse )
    {
        List<Collection> listCollectiones = new ArrayList<Collection>(  );
        JSONObject jsonCollections = jsonResponse.getJSONObject( "collections" );
        Iterator i = jsonCollections.keys(  );

        while ( i.hasNext(  ) )
        {
            String strKey = (String) i.next(  );
            JSONObject jsonCollection = jsonCollections.getJSONObject( strKey );
            Collection Collection = new Collection(  );
            Collection.setBaseId( jsonCollection.getInt( "base_id" ) );
            Collection.setCollId( jsonCollection.getInt( "coll_id" ) );
            Collection.setName( jsonCollection.getString( "name" ) );
            Collection.setRecordAmount( jsonCollection.getInt( "record_amount" ) );
            listCollectiones.add( Collection );
        }

        return listCollectiones;
    }
}
