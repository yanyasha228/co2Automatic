$(function () {
    $(document).on('click', '.btn-add', function (e) {
        e.preventDefault();

        $("#searchProductResult").empty();

        var controlForm = $('.controls-1:first'),
            currentEntry = $(this).parents('.entry:first'),
            newEntry = $(currentEntry.clone()).appendTo(controlForm);

        newEntry.find('input').val('');
        newEntry.find('input')
        controlForm.find('.entry:not(:last) .btn-add')
            .removeClass('btn-add').addClass('btn-remove')
            .removeClass('btn-success').addClass('btn-danger')
            .html('<span class="glyphicon glyphicon-minus"></span>');
    }).on('click', '.btn-remove', function (e) {

        $(this).parents('.entry:first').remove();

        e.preventDefault();
        return false;
    });

    $(document).on('click' ,'#orderLineItem' , function (e) {
        var serchList = $(this).closest('.product-search-editor-res');
       var searchInput = $(this).closest('.form-group').find('#inputOrderLineProductName');
       var prodName = $(this).find('#prodNamePar').text();
       searchInput.val(prodName);
       serchList.empty();

    } );

    $(document).mouseup(function (e) {
        var searcResOrdList = $("#searchProductResult");
        if (searcResOrdList.has(e.target).length === 0) {
            searcResOrdList.empty();
        }
    });

    $(document).on('keyup', '#inputOrderLineProductName', function (e) {
        var searchList = $(this).siblings("#searchProductResult");
        searchList.html('');
        var searchField = $(this).val();

        if(searchField.length>1) {
            $.getJSON(location.origin + "/editOrder/getProductsByNonFullName?search_S=" + searchField, function (data) {
                $.each(data, function (key, value) {

                    searchList.append('<li class="list-group-item product-search-editor-res-item" id="orderLineItem" data-prodid = "'+ value.id +'"><div class="row"' +
                        '><div class="col-4"><img src="'+ value.imageUrls[0] +'" height="60" width="80" class="img-thumbnail"></div>'  +
                        '<div class="col-8"> <p id="prodNamePar" style="overflow: hidden; text-overflow: ellipsis;">' + value.name + '</p> </div>' +
                        '</div></li>');

                });

            });

            $.getJSON(location.origin + "/editOrder/getProductsByName?search_S=" + searchField, function (data) {
                if(!data){
                    $(this).attr('class','form-control is-invalid');
                }
            })
        }

    });
});


// function sumDouble(input) {
//
//
//     })
//
// }
function numValidDouble(input) {
    $(input).keydown(function (event) {
        // Разрешаем: backspace, delete, tab, escape , "."
        if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 ||
            event.keyCode == 110 || event.keyCode == 190 ||
            // Разрешаем: Ctrl+A
            (event.keyCode == 65 && event.ctrlKey === true) ||
            // Разрешаем: home, end, влево, вправо
            (event.keyCode >= 35 && event.keyCode <= 39)) {
            // Ничего не делаем

            return;
        }
        else {
            // Обеждаемся, что это цифра, и останавливаем событие keypress
            if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105)) {
                event.preventDefault();
            }
        }
    });

};

function numValid(input) {
    $(input).keydown(function (event) {
        // Разрешаем: backspace, delete, tab, escape ,
        if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 ||
            // Разрешаем: Ctrl+A
            (event.keyCode == 65 && event.ctrlKey === true) ||
            // Разрешаем: home, end, влево, вправо
            (event.keyCode >= 35 && event.keyCode <= 39)) {
            // Ничего не делаем
            return;
        }
        else {
            // Обеждаемся, что это цифра, и останавливаем событие keypress
            if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105)) {
                event.preventDefault();
            }
        }
    });
};