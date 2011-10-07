<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../insert/InsertServiceHeader.jsp" />

<jsp:useBean id="phraseanet" scope="session" class="fr.paris.lutece.plugins.phraseanet.web.PhraseanetLinkService" />

<%= phraseanet.getSearchForm( request ) %>
