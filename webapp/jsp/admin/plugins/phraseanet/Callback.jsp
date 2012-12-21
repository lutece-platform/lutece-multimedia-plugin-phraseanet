<%@ page import="java.io.*,java.util.*,fr.paris.lutece.portal.service.util.*;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">

  <title>Callback Lutece</title>
  </head>
  <body>
  <%
  //page import="java.io.*,java.util.*,fr.paris.lutece.portal.service.util.*;"
  
  //private static final String CALLBACK_URL = "phraseanet.url.callback";
  
  String callback = AppPathService.getBaseUrl( request ) +  AppPropertiesService.getProperty( "phraseanet.url.callback" );
  
/*
  String callback = "http://localhost" ;
  //callback = callback + request.getRemoteHost();
  callback = callback + ":" + request.getServerPort() ;
  callback = callback + request.getRequestURI()  ;  
*/  
  String[] client_ids = request.getParameterValues("customerId") ;
  String client_id = "" ;
  if (client_ids != null) client_id = client_ids[0];
  //else client_id = "ad74d95109c35b892e7d5773b37e940c" ;
   
  String[] codes = request.getParameterValues("code") ;
  String code = "" ;
  if (codes != null) code = codes[0];
  
  String[] authorizeEndPoints = request.getParameterValues("authorizeEndPoint") ;
  String authorizeEndPoint = "" ;
  if (authorizeEndPoints != null) authorizeEndPoint = authorizeEndPoints[0];
  //else authorizeEndPoint = "https://mdp.alchemyasp.com/api/oauthv2/authorize";
  
  String[] accessEndPoints = request.getParameterValues("accessEndPoint") ;
  String accessEndPoint = "" ;
  if (accessEndPoints != null) accessEndPoint = accessEndPoints[0];
  //else accessEndPoint = "https://mdp.alchemyasp.com/api/oauthv2/token";
  
  String[] phraseanetIds = request.getParameterValues("phraseanetId") ;
  String phraseanetId = "" ;
  if (phraseanetIds != null) phraseanetId = phraseanetIds[0];
  
  String[] passwords = request.getParameterValues("password") ;
  String password = "" ;
  if (passwords != null) password = passwords[0];
  
%>
  
<!--

<hr /> <br/>
      
      <form method="get" action="<%= authorizeEndPoint %>">
      client_id : <input type="text" name="client_id" value="<%= client_id %>"><br/>
      response_type : <input type="text" name="response_type" value="code"><br/>
      redirect_uri : <input type="text" name="redirect_uri" value="<%= callback %>"><br/>
      login : <input type="text" name="login" value="<%= phraseanetId %>"><br/>
      password : <input type="password" name="password" value="<%= password %>"><br/> 
      action_login : <input type="text" name="action_login" value="ok"><br/>
      <input type="submit" value="Demande d'identification serveur (GET)">
      </form>


<br />


      <form method="post" action="<%= accessEndPoint %>">
      client_id : <input type="text" name="client_id" value="<%= client_id %>"><br/>
      grant_type : <input type="text" name="grant_type" value="authorization_code"><br/>
      redirect_uri : <input type="text" name="redirect_uri" value="<%= callback %>"><br/>
      code : <input type="text" name="code" value="<%= code %>"><br/>
      <input type="submit" value="Demande de token (POST)">
      </form>
      
<hr /> <br/>

      <form method="get" action="<%= authorizeEndPoint %>">
      client_id : <input type="text" name="client_id" value="<%= client_id %>"><br/>
      response_type : <input type="text" name="response_type" value="token"><br/>
      redirect_uri : <input type="text" name="redirect_uri" value="<%= callback %>"><br/>
      <input type="submit" value="Demande Ajax (GET)">
      </form>



<hr /> <br/>
-->
      <form method="get" action="<%= authorizeEndPoint %>">
      client_id : <input type="text" name="client_id" value="<%= client_id %>"><br/>
      response_type : <input type="text" name="response_type" value="token"><br/>
      redirect_uri : <input type="text" name="redirect_uri" value="<%= callback %>"><br/> 
      login : <input type="text" name="login" value="<%= phraseanetId %>"><br/>
      password : <input type="password" name="password" value="<%= password %>"><br/> 
      action_login : <input type="text" name="action_login" value="ok"><br/>
      <input type="submit" value="Demande pour <%= authorizeEndPoint %>">
      </form>
 <!--    
<hr />      <br/>

      <form method="post" action="<%= authorizeEndPoint %>">
      client_id : <input type="text" name="client_id" value="06c4269321be860bf82800f41afe3b8f"><br/>
      response_type : <input type="text" name="response_type" value="code"><br/>
      redirect_uri : <input type="text" name="redirect_uri" value="urn:ietf:wg:oauth:2.0:oob"><br/>
      <input type="submit" value="Demande Auth Mobil (GET)">
      </form>
      
      
<br />

      <form method="get" action="<%= accessEndPoint %>">
      client_id : <input type="text" name="client_id" value="06c4269321be860bf82800f41afe3b8f"><br/>
      response_type : <input type="text" name="response_type" value="token"><br/>
      redirect_uri : <input type="text" name="redirect_uri" value="urn:ietf:wg:oauth:2.0:oob"><br/>
      <input type="submit" value="Demande token mobile (GET)">
      </form>
-->
  </body>
</html>