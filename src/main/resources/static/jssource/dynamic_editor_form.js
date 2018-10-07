$(function () {
    $(document).on('click', '.btn-add', function (e) {
        e.preventDefault();


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

        // $(document).on('click', 'btn-primary', function (e) {
        //     $(this).parents('.entry:first').remove();
        // });
        // $('.delParamQue #delParamQue').modal();
        $(this).parents('.entry:first').remove();

        e.preventDefault();
        return false;
    });

    $(document).on('keyup', '#productNameInput', function (e) {
        var searchList = $(this).siblings("#searchProductResult");
        searchList.html('');
        var searchField = $(this).val();

        if(searchField.length>1) {
            $.getJSON(location.origin + "/editOrder/getProductsByNonFullName?str=" + searchField, function (data) {
                $.each(data, function (key, value) {

                    searchList.append('<li class="list-group-item"><span>' + value.name + '</span></li>');

                });

            });
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