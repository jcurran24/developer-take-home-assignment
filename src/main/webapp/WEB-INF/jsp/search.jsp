<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="utf-8">
  <title>College Search</title>

  <link href="/styles/styles.css" rel="stylesheet"/>
  <link href="http://code.jquery.com/ui/1.10.2/themes/blitzer/jquery-ui.css" rel="stylesheet" type="text/css" />

  <script src="/js/jquery-2.1.3.min.js" type="text/javascript"></script>



</head>

<body>

<div id="headingTitleDiv"><span id="headingTitle">College Search</span></div>
<br>

<div id="searchContainer" class="ui-toolbar ui-widget-header ui-helper-clearfix">
    <span style="position: absolute;" class="btnSearch ui-state-default" title="search"><span class="ui-icon ui-icon-search"></span></span>
    <input type="search" class="right" id="searchBox" placeholder="    Search" /><br>
    <select id="autoCompleteDropDown" size="4"></select>
</div>
<br>
<div id="noSearchResultsContainer">
    <span id="noSearchResults">No search results. Please try again.</span>
</div>

<script type="text/javascript">


     $("#searchBox").on("change keyup paste", function() {
        console.log("Textbox has changed");
        var searchStr = $('#searchBox').val();

        if($("#searchBox").val().length >= 2) {
            $("#autoCompleteDropDown").empty();
            $("#noSearchResultsContainer").hide();
            $.ajax({url: "/execute-search?collegeName=" + searchStr,
            success: function(data, status) {
                console.log("Retrieved data in ajax call: " + data);
                var dropDownListSize = 0;
                $.each(data, function(i, item) {
                     $("#autoCompleteDropDown").append($("<option></option>")
                                                        .attr("value",i)
                                                        .text(item.name));
                     dropDownListSize = i + 1;
                });
                if(dropDownListSize >= 1) {
                    $("#autoCompleteDropDown").attr("size", dropDownListSize);
                    $("#autoCompleteDropDown").show();
                } else {
                    $("#noSearchResultsContainer").show();
                }
            }});
        } else if($("#searchBox").val().length < 2) {
            $("#autoCompleteDropDown").empty();
            $("#autoCompleteDropDown").hide();
            $("#noSearchResultsContainer").hide();
        }
     });
</script>

<script src="/js/search.js" type="text/javascript"></script>


</body>
</html>