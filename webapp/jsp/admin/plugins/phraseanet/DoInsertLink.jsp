<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:useBean id="phraseanet" scope="session" class="fr.paris.lutece.plugins.phraseanet.web.PhraseanetLinkService" />

<% 
     response.sendRedirect( phraseanet.doInsertLink( request ) );
%>
