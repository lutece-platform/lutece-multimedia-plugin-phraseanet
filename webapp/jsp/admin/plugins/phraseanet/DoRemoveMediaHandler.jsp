<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:useBean id="phraseanet" scope="session" class="fr.paris.lutece.plugins.phraseanet.web.PhraseanetJspBean" />

<% phraseanet.init( request, phraseanet.RIGHT_MANAGE_PHRASEANET ); %>
<% response.sendRedirect( phraseanet.doRemoveMediaHandler( request ) ); %>
