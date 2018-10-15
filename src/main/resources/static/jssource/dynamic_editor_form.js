


$(function () {

    $('#inputPhoneNumber').intlTelInput({

    });

//     $(document).ready(function () {
//
//         $('#inputPhoneNumber').intlTelInput({
// //         // whether or not to allow the dropdown
// //         allowDropdown: true,
// //
// // // if there is just a dial code in the input: remove it on blur, and re-add it on focus
// //         autoHideDialCode: true,
// //
// // // add a placeholder in the input with an example number for the selected country
// //         autoPlaceholder: "polite",
// //
// // // modify the auto placeholder
// //         customPlaceholder: null,
// //
// // // append menu to specified element
// //         dropdownContainer: null,
// //
// // // don't display these countries
// //         excludeCountries: [],
// //
// // // format the input value during initialisation and on setNumber
// //         formatOnDisplay: true,
// //
// // // geoIp lookup function
// //         geoIpLookup: null,
// //
// // // inject a hidden input with this name, and on submit, populate it with the result of getNumber
// //         hiddenInput: "",
// //
// // // initial country
// //         initialCountry: "",
// //
// // // localized country names e.g. { 'de': 'Deutschland' }
// //         localizedCountries: null,
// //
// // // don't insert international dial codes
// //         nationalMode: true,
// //
// // // display only these countries
// //         onlyCountries: [],
// //
// // // number type to use for placeholders
// //         placeholderNumberType: "MOBILE",
// //
// // // the countries at the top of the list. defaults to united states and united kingdom
// //         preferredCountries: [ "us", "gb" ],
// //
// // // display the country dial code next to the selected flag so it's not part of the typed number
// //         separateDialCode: false
//         });
//
//     });

    $(document).on('click', '.btn-add', function (e) {
        e.preventDefault();

        $("#searchProductResult").empty();

        var controlForm = $('.controls-1:first'),
            currentEntry = $(this).parents('.entry:first'),
            newEntry = $(currentEntry.clone()).appendTo(controlForm);

        newEntry.find('input').val('');
        newEntry.find('input').attr("class" , "form-control" );
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
       searchInput.attr('class', 'form-control is-valid');

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
        }

    });

    $(document).on('focusout' , '#inputOrderLineProductName', function(e) {

        var searchField = $(this).val();
        var inputField = $(this);

        $.getJSON(location.origin + "/editOrder/getProductByName?search_S=" + searchField, function (d) {
            }).done(function () {
            inputField.attr('class', 'form-control is-valid');
        }).fail( function () {
            inputField.attr('class', 'form-control is-invalid');
        });
        });

    $(document).on('paste' , '#inputPhoneNumber' , function (event) {

        event.preventDefault();

        var validNumb = window.event.clipboardData.getData('text').replace(/\D+/g, "");

        $(this).val("+" + validNumb);
    });
    
    $('#inputPhoneNumber')

        .keydown(function (e) {
            var key = e.which || e.charCode || e.keyCode || 0;
            $phone = $(this);

            // Don't let them remove the starting '('
            if ($phone.val().length === 1 && (key === 8 || key === 46)) {
                $phone.val('+');
                return false;
            }
            // Reset if they highlight and type over first char.
            else if ($phone.val().charAt(0) !== '+') {
                $phone.val('+'+$phone.val());
            }

            // Auto-format- do not expose the mask as the user begins to type
            if (key !== 8 && key !== 9) {
                if ($phone.val().length === 4) {
                    $phone.val($phone.val() + ' ');
                }
                if ($phone.val().length === 5) {
                    $phone.val($phone.val() + ' ');
                }
                if ($phone.val().length === 8) {
                    $phone.val($phone.val() + ' ');
                }
                if ($phone.val().length === 12) {
                    $phone.val($phone.val() + ' ');
                }
            }

            // Allow numeric (and tab, backspace, delete) keys only
            return (key == 8 ||
                key == 9 ||
                key == 46 ||
                (key >= 48 && key <= 57) ||
                (key >= 96 && key <= 105) ||
                (key == 67 && e.ctrlKey == true) ||
                (key == 86 && e.ctrlKey == true));
        })

        .bind('focus click', function () {
            $phone = $(this);

            if ($phone.val().length === 0) {
                $phone.val('+');
            }
            else {
                var val = $phone.val();
                $phone.val('').val(val); // Ensure cursor remains at the end
            }
        })

        .blur(function () {
            $phone = $(this);

            if ($phone.val() === '+') {
                $phone.val('');
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