<%@page import="utils.HibernateUtils"%>
<%
    HibernateUtils h = new HibernateUtils();
    response.sendRedirect("home.jsf") ; %>