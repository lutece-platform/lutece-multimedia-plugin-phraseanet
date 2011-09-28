/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.phraseanet.service.api;

import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * PhraseanetApiCallService Test
 */
public class PhraseaApiCallServiceTest
{

    private static final String RESPONSE = "{ \"meta\": { "
            + "\"api_version\": \"1.0\","
            + " \"request\": \"GET /api/v1/feeds/288/content/\","
            + "  \"response_time\": \"2011-07-27T15:52:04+02:00\","
            + "  \"http_code\": 200,"
            + "  \"error_message\": null,"
            + "  \"error_details\": null,"
            + "  \"charset\": \"UTF-8\""
            + "},"
            + "\"response\": {"
            + "\"body\":\"response body\""
            + "} }";

    /**
     * Test of getResponse method, of class PhraseanetApiCallService.
     */
    @Test
    public void testGetResponse() throws Exception
    {
        /*
        System.out.println("getResponse");
        String strRequest = "";
        JSONObject expResult = null;
        JSONObject result = PhraseanetApiCallService.getResponse(strRequest);
         */    }

    /**
     * Test of extractResponse method, of class PhraseanetApiCallService.
     */
    @Test
    public void testExtractResponse() throws Exception
    {
        System.out.println("extractResponse");
        JSONObject response = PhraseanetApiCallService.extractResponse(RESPONSE);
        assertEquals(response.getString("body"), "response body");
    }
}
