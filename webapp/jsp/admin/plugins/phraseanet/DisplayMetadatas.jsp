<%@ page import="fr.paris.lutece.plugins.phraseanet.web.PhraseanetJspBean" %>
<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeaderSessionLess.jsp" />

<%= PhraseanetJspBean.getListMetaDatas( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
