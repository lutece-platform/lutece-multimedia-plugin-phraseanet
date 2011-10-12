<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../insert/InsertServiceHeader.jsp" />

<jsp:useBean id="phraseanetLinkService" scope="session" class="fr.paris.lutece.plugins.phraseanet.web.PhraseanetLinkService" />

<%= phraseanetLinkService.getSearch( request ) %>
