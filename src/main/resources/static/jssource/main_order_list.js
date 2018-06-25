$(document).ready(function () {
    $("#searchOrder").keyup(function () {
        _this = this;

        $.each($("#orderListTable tbody tr"), function () {
            if ($(this).text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
    });
});

$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});
