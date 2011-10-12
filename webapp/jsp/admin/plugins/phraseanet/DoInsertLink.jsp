<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:useBean id="phraseanetLinkService" scope="session" class="fr.paris.lutece.plugins.phraseanet.web.PhraseanetLinkService" />

<% 
     response.sendRedirect( phraseanetLinkService.doInsertLink( request ) );
%>
