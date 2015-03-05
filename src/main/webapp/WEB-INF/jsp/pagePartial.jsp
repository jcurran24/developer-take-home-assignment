<%
    String pageNumber = request.getParameter("page");

    pageContext.setAttribute("pageNumber", pageNumber);
%>

<div>This is page ${pageNumber}</div>