(function () {

    function loadPartial () {
        $.ajax({
            url: '/page',
            success: function (response) {
                console.log('yay');
                $('body').append(response);
            }
        });
    }

    $(document).ready(function () {
        loadPartial();
    });
})();