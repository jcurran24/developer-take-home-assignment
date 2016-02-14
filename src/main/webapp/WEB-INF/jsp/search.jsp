<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="utf-8">
  <title>College Search</title>

  <link href="/styles/styles.css" rel="stylesheet"/>

  <script src="/js/jquery-2.1.3.min.js" type="text/javascript"></script>



</head>

<body>

<div id="headingTitleDiv" style="width:100%;"><span id="headingTitle" style="margin-left:300px; text-align:center; color:black; border:1px; font-size:24px;">College Search</span></div>
<br>

<div id="searchContainer" class="cf sb" style="margin-left:300px;">
    <div class="right magnifier"></div>
    <input class="right" type="text" id="searchBox" placeholder="search"/>
    <select id="autoCompleteDropDown" style="display:none">
    </select>
</div>

<script type="text/javascript">
     $("#searchBox").on("change keyup paste", function() {
        console.log("Textbox has changed");
        var searchStr = $('#searchBox').val();

        if($("#searchBox").val().length >= 2) {
            $.ajax({url: "/execute-search?collegeName=" + searchStr,
            success: function(data, status) {
                console.log("Retrieved data in ajax call: " + data);

                $.each(data, function(i, item) {
                     $("#autoCompleteDropDown").append($("<option></option>")
                                                        .attr("value",i)
                                                        .text(item.name));
                });
                $("#autoCompleteDropDown").show();

            }});
        } else if($("#searchBox").val().length < 2) {
            $("#autoCompleteDropDown").empty();
            $("#autoCompleteDropDown").hide();
        }
     });
</script>

<script src="/js/search.js" type="text/javascript"></script>


</body>
</html>