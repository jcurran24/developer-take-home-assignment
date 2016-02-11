<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="utf-8">
  <title>College Search</title>

  <link href="/styles/styles.css" rel="stylesheet"/>

  <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>



</head>

<body>

<div id="headingTitle" style="width:100%;"><span style="margin-left:300px; text-align:center; color:black; border:1px; font-size:24px;">College Search</span></div>


<script src="/js/search.js" type="text/javascript"></script>
<script type="text/javascript">
    alert($('#headingTitle').val());

     $("#searchBox").on("change keyup paste", function() {
        alert("Change occured");
        console.log("Textbox has changed");
        var searchStr = $('#searchBox').val();

        $.ajax({url: "/college-search?collegeName",
        success: function(data, status) {

                $.each(data, function(i, item) {
                     $("#autoCompleteDropDown").append($("<option></option>")
                                                        .attr("value",i)
                                                        .text(item.name));
                });
                $("#autoCompleteDropDown").show();

        }});
     });
</script>


<br>

<div id="searchContainer" class="cf sb" style="margin-left:300px;">
    <div class="right magnifier"></div>
    <input class="right" type="text" id="searchBox" placeholder="search"/>
    <select id="autoCompleteDropDown" style="display:block">
    </select>
</div>


<c:if test="${resultsFound == true}">

</c:if>

</body>
</html>