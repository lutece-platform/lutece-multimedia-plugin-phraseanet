<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="phraseanet" scope="session" class="fr.paris.lutece.plugins.phraseanet.web.PhraseanetJspBean" />

<% phraseanet.init( request, phraseanet.RIGHT_MANAGE_PHRASEANET ); %>
<%= phraseanet.getCreateMediaHandlerStep2 ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
