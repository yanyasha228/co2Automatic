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

//     $("#searchButton").on("click" ,function () {
//         var searchParamInput = $("#searchParamInput").value;
//         if(searchParamInput!=null){
//             var xhr = new XMLHttpRequest();
//             xhr.open('GET', document.URL + "?searchParam=" + searchParamInput, false);
//             xhr.send(true);
//         }
//     });
// });

$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});


