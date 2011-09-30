/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.phraseanet.service.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;


/**
 *
 * @author pierre
 */
public class Utils
{
    public String getJson( String strResource ) throws IOException
    {
        String strResourcePath = "/jsonTestFiles/" + strResource;
        InputStreamReader isr = new InputStreamReader( getClass(  ).getResourceAsStream( strResourcePath ) );
        BufferedReader in = new BufferedReader( isr );
        Writer writer = new StringWriter(  );

        if ( in != null )
        {
            char[] buffer = new char[1024];

            try
            {
                int n;

                while ( ( n = in.read( buffer ) ) != -1 )
                {
                    writer.write( buffer, 0, n );
                }
            }
            finally
            {
                isr.close(  );
            }

            return writer.toString(  );
        }
        else
        {
            return "";
        }
    }
}
