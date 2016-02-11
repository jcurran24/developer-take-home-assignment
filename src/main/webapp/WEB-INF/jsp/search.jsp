<!DOCTYPE html>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="utf-8">
  <title>College Search</title>

  <link href="/styles/styles.css" rel="stylesheet"/>
  <script type="text/javascript">
     var results = ${searchResults};
  </script>
</head>

<body>

Hello, this is the search page.

<script src="/js/jquery-2.1.3.min.js" type="text/javascript"></script>
<script src="/js/search.js" type="text/javascript"></script>


<br>

<c:if test="${resultsFound == true}">
    var results = ${searchResults};
</c:if>

</body>
</html>