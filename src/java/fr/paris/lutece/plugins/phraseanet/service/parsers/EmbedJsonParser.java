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
package fr.paris.lutece.plugins.phraseanet.service.parsers;

import fr.paris.lutece.plugins.phraseanet.business.embed.Embed;
import fr.paris.lutece.plugins.phraseanet.business.embed.EmbedItem;
import fr.paris.lutece.plugins.phraseanet.business.embed.Permalink;
import fr.paris.lutece.plugins.phraseanet.service.Constants;
import fr.paris.lutece.plugins.phraseanet.service.api.PhraseanetApiCallException;
import java.util.Iterator;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


/**
 * EmbedJsonParser
 */
public final class EmbedJsonParser
{
     private static Logger _logger = Logger.getLogger( Constants.LOGGER );
    /** private constructor */
    private EmbedJsonParser(  )
    {
        _logger.debug( "EmbedJsonParser" );
    }

    /**
     * Parse an Embed object
     * @param jsonEmbed The embed as JSONObject
     * @return The embed
     * @throws PhraseanetApiCallException if an error occurs
    */
    public static Embed parse( JSONObject jsonEmbed ) throws PhraseanetApiCallException
    {
        try
        {
            Embed embed = new Embed(  );
            _logger.debug("parce jsonEmbed : " + jsonEmbed );
            Iterator i = jsonEmbed.keys(  );
            while( i.hasNext(  ) )
            {
                String key = ( String ) i.next(  );
                if (jsonEmbed.getJSONObject(key).isNullObject())
                {
                    _logger.error( "Item manquant");
                }
                else
                {
                    EmbedItem ei = getEmbedItem( jsonEmbed.getJSONObject( key ) );
                    String name = ei.getItemName();
                    _logger.debug( "ItemName : " + name );
                    embed.addEmbedItem( name,  getEmbedItem( jsonEmbed.getJSONObject( key ) ) );
                }
            }

            return embed;
        }
        catch ( JSONException e )
        {
            throw new PhraseanetApiCallException( "Error parsing embed : " + e.getMessage(  ) + " - JSON : " +
                jsonEmbed.toString( 4 ) );
        }
    }

    /**
     * Get embed item
     * @param jsonEmbedItem The embed item as JSON object
     * @return The embed item object
     */
    public static EmbedItem getEmbedItem( JSONObject jsonEmbedItem )
    {
        //_logger.debug( "getEmbedItem : " + jsonEmbedItem );
        EmbedItem ei = new EmbedItem(  );
        //_logger.debug( "EmbedItemName : " + jsonEmbedItem.getString("name") );
        ei.setItemName( jsonEmbedItem.getString("name") );
        JSONObject permalink = jsonEmbedItem.getJSONObject( "permalink" );
        if( ! permalink.isNullObject(  ) )
        {
            ei.setPermalink( getPermalink( jsonEmbedItem.getJSONObject( "permalink" ) ) );
        }
        ei.setWidth( jsonEmbedItem.getInt( "width" ) );
        ei.setHeight( jsonEmbedItem.getInt( "height" ) );
        ei.setFilesize( jsonEmbedItem.getInt( "filesize" ) );
        ei.setPlayerType( jsonEmbedItem.getString( "player_type" ) );
        ei.setMimeType( jsonEmbedItem.getString( "mime_type" ) );
        //_logger.debug( "EmbedItem : ei=" + ei );
        return ei;
    }

    /**
     * Get Permalink
     * @param jsonPermalink The permalink as JSON object
     * @return  The permalink object
     */
    public static Permalink getPermalink( JSONObject jsonPermalink )
    {
        _logger.debug( "getPermalink : " + jsonPermalink);
        Permalink p = null;
        
        if( jsonPermalink != null )
        {
            p = new Permalink(  );
            p.setId( jsonPermalink.getInt( "id" ) );
            p.setCreatedOn( jsonPermalink.getString( "created_on" ) );
            p.setLastModified( jsonPermalink.getString( "updated_on" ) );
            p.setActivated( jsonPermalink.getBoolean( "is_activated" ) );
            p.setLabel( jsonPermalink.getString( "label" ) );
            _logger.debug( "label : " + jsonPermalink.getString( "label" ) );
            p.setPageUrl( jsonPermalink.getString( "page_url" ) );
            p.setUrl( jsonPermalink.getString( "url" ) );
            _logger.debug( "url : " + jsonPermalink.getString( "url" ) );
        }
        return p;
    }
}
