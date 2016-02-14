// TODO: This file should include all functionality relevant to the College Search page.  Should make use of ExecuteSearchServlet.java

function asyncCallExecuteSearch(searchUrl) {
     $.ajax({url: "/execute-search?collegeName=" + searchUrl,
          success: function(data, status) {
                console.log("Retrieved data in ajax call: " + data);
                var dropDownListSize = 0;
                $.each(data, function(i, item) {
                $("#autoCompleteDropDown").append($("<option></option>").attr("value",i).text(item.name));
                    dropDownListSize = i + 1;
                });
                if(dropDownListSize >= 1) {
                    $("#autoCompleteDropDown").attr("size", dropDownListSize);
                    $("#autoCompleteDropDown").show();
                } else {
                    $("#noSearchResultsContainer").show();
                }
          }
     });
}

function setupChangeEventListenerOnSearchBox() {
    $("#searchBox").on("change keyup paste", function() {
        console.log("Textbox has changed");
        var searchStr = $('#searchBox').val();

        if($("#searchBox").val().length >= 2) {
            $("#autoCompleteDropDown").empty();
            $("#noSearchResultsContainer").hide();
            asyncCallExecuteSearch(searchStr);
        } else if($("#searchBox").val().length < 2) {
            $("#autoCompleteDropDown").empty();
            $("#autoCompleteDropDown").hide();
            $("#noSearchResultsContainer").hide();
        }
    });
}