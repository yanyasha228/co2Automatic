$(function () {
    String.prototype.replaceAll = function (search, replace) {
        return this.split(search).join(replace);
    };

    var orderLinesMap = new Map();

    var appSettings;

    var client = {
        clientStatus : "USUAL"
    };


    var discount = 0;
    var sumPrice = 0;

    $(document).ready(function () {
        var clientDomId = $('#clientId').val();
        $.getJSON(location.origin + "/restApi/AppSettings/getAppSettings", function (settingsData) {
            appSettings = settingsData;
        });

        $.getJSON(location.origin + "/restApi/clients/getClientById?search_Id=" + clientDomId).done( function (clientData) {
            client = clientData;
        });

    });

    $('#inputPhoneNumber').intlTelInput({
        preferredCountries: ['ua', 'ru', 'ml'],
        autoHideDialCode: true,
        utilsScript: "static/htmlhelpers/intl-tel-input/build/js/utils.js"
    });


    $(document).on('click', '.btn-add', function (e) {
        e.preventDefault();

        $("#searchProductResult").empty();

        var controlForm = $('.controls-1:first'),
            currentEntry = $(this).parents('.entry:first'),
            newEntry = $(currentEntry.clone()).prependTo(controlForm);

        newEntry.find('input').val('');
        newEntry.find("p[id='sumOrderLinePrice']").text("0.0");
        newEntry.find('input').attr("class", "form-control");

        controlForm.find('.entry:not(:last) .btn-add')
            .removeClass('btn-add').addClass('btn-remove')
            .removeClass('btn-success').addClass('btn-danger')
            .html('<span class="glyphicon glyphicon-minus"></span>');
    }).on('click', '.btn-remove', function (e) {
        var entryToRemove = $(this).parents('.entry:first');
        var entryProdId = entryToRemove.find('#prodOrderLineIdInput');

        var strValInp = entryProdId.val();
        if (strValInp !== "") {
            if (!orderLinesMap.delete(Number(strValInp))) {
                alert("Перезагрузи страницу");
            }
        }
        entryToRemove.remove();

        validateSumPrice();
        e.preventDefault();
        return false;
    });

    $('#orderForm').on('keyup keypress', function (e) {
        var keyCode = e.keyCode || e.which;
        if (keyCode === 13) {
            e.preventDefault();
            return false;
        }
    });

    // $('#orderDiscount').on('keyup keypress', function (e) {
    //     var keyCode = e.keyCode || e.which;
    //     if (keyCode === 13) {
    //         e.preventDefault();
    //         return false;
    //     }
    // });


    $(document).on('click', '#orderLineItem', function (e) {
        var searchList = $(this).parent();
        var searchInput = $(this).closest('.form-group').find('#inputOrderLineProductName');

        validateAndCloseOrderLineList(searchList, searchInput, $(this));

    });


    $(document).mouseup(function (e) {
        var searcResOrdList = $("#searchOrderLineProductsList");
        if (e.target != searcResOrdList[0] && !searcResOrdList.has(e.target).length) {
            searcResOrdList.empty();
        }
    });


    $(document).on('keydown', '#inputOrderLineProductName', function (e) {

        var searchList = $(this).siblings("#searchOrderLineProductsList");

        var hui = searchList.children("#searchProductResult");

        var searchField = $(this).val();

        searchField = encodeRequestReservedSymbols(searchField);

        if (e.keyCode == 13) {
            e.preventDefault();
            var activeItem = searchList.children('.active:first');
            validateAndCloseOrderLineList(searchList, $(this), activeItem);
            return;

        }

        if (e.keyCode == 40 || e.keyCode == 38) {
            arrowActiveItemHandling(e, searchList, $(this));

        } else {
            searchList.html('');
//////Validirovat searchSend !!!!!!!!!!!!!!!!
            if (searchField.length > 1) {
                $.getJSON(location.origin + "/restApi/products/getProductsByNonFullName?search_S=" + searchField, function (data) {
                    $.each(data, function (key, value) {
                        validateProductForView(value);
                        searchList.append('<li class="list-group-item product-search-editor-res-item" tabindex ="' + key + '" id="orderLineItem" data-prodid = "' + value.id + '"><div class="row"' +
                            '><div class="col-4"><img src="' + value.imageUrls[0] + '" height="60" width="80" class="img-thumbnail"></div>' +
                            '<div class="col-8"> <p id="prodNamePar" style="overflow: hidden; text-overflow: ellipsis;">' + value.name + '</p> </div>' +
                            '</div></li>');


                    });

                });
            }
        }

    });

//FOCUSOUT_EVENT
    $(document).on('focusout', '#inputOrderLineProductName', function (e) {

        var searchField = $(this).val();
        var inputField = $(this);

        searchField = encodeRequestReservedSymbols(searchField);

        $.getJSON(location.origin + "/restApi/products/getProductByName?search_S=" + searchField, function (d) {
        }).done(function () {
            inputField.attr('class', 'form-control is-valid');
        }).fail(function () {
            inputField.attr('class', 'form-control is-invalid');

        });
    });

    $(document).on('focusout' , '#orderDiscount' , function (e) {

        discount = $(this).val();
        syncModelAndViewOrderLinesPrices();
    });

    $(document).on('focusout', '#inputOrderLineProductQua', function (eve) {


        var inputQuaField = $(this);
        var inputQuaFieldValue = inputQuaField.val();

        var productOrderLineIdInputItem = inputQuaField.closest("div.form-row").find("input[id='prodOrderLineIdInput']");
        var productOrderLineIdInputItemVal = productOrderLineIdInputItem.val();

        if (orderLinesMap.has(Number(productOrderLineIdInputItemVal))) {
            var her = orderLinesMap.get(Number(productOrderLineIdInputItemVal));
            her.productAmount = inputQuaFieldValue;
            syncModelAndViewOrderLinesPrices();
        }

    });


    $(document).on('paste', '#inputPhoneNumber', function (event) {

        event.preventDefault();

        var validNumb = window.event.clipboardData.getData('text').replace(/\D+/g, "");

        $(this).val("+" + validNumb);
    });

    $(document).on('click', '#clientItem', function (e) {

        var searchClientsList = $('#searchClientsResult');

        var activeItem = searchClientsList.children('.active:first');

        validateAndCloseClientList(searchClientsList, $(this), activeItem)



    });

    $(document).mouseup(function (e) {
        var searchResClientList = $('#searchClientsResult');
        if (searchResClientList.has(e.target).length === 0) {
            searchResClientList.empty();
        }
    });


    $('#inputPhoneNumber')
        .keydown(function (e) {
            var key = e.which || e.charCode || e.keyCode || 0;
            $phone = $(this);

            var searchClientsList = $('#searchClientsResult');

            var tI = "+" + $(this).intlTelInput("getSelectedCountryData").dialCode;

            // Don't let them remove the starting '('
            if ($phone.val().length === tI.length && (key === 8 || key === 46)) {
                $phone.val(tI);
                return false;
            }
            // Reset if they highlight and type over first char.
            else if ($phone.val().indexOf(tI) !== 0) {
                $phone.val(tI + $phone.val());
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

            if (key == 40 || key == 38) {
                arrowActiveItemHandling(e, searchClientsList, $(this));
            }

            if (key == 13) {
                e.preventDefault();
                var activeItem = searchClientsList.children('.active:first');
                validateAndCloseClientList(searchClientsList, $(this), activeItem);
                $(this).blur();
                return;

            }
            // Allow numeric (and tab, backspace, delete) keys only
            if(!(key == 8 ||
                key == 40 || key == 38 ||
                key == 13||
                key == 9 ||
                key == 46 ||
                key == 40 ||
                key == 38 ||
                (key >= 48 && key <= 57) ||
                (key >= 96 && key <= 105) ||
                (key == 67 && e.ctrlKey == true) ||
                (key == 86 && e.ctrlKey == true))){

                return false;
            }
        })

        .bind('focus click', function () {
            $phone = $(this);

            var tI = "+" + $(this).intlTelInput("getSelectedCountryData").dialCode;

            if ($phone.val().length === 0) {
                $phone.val(tI);
            } else {
                var val = $phone.val();
                $phone.val('').val(val); // Ensure cursor remains at the end
            }
        })

        .blur(function () {
            $phone = $(this);

            var tI = "+" + $phone.intlTelInput("getSelectedCountryData").dialCode;

            if ($phone.val().trim() === tI) {
                $phone.val('');
            } else {
                if ($.trim($phone.val())) {
                    if ($phone.intlTelInput("isValidNumber")) {
                        $phone.attr('class', 'form-control is-valid');
                        $('#copyNumberButton').attr('class', 'btn btn-success');
                    } else {
                        $phone.attr('class', 'form-control is-invalid');
                        $('#copyNumberButton').attr('class', 'btn btn-danger');
                    }
                }
            }
        }).keyup(function (event) {

        if (!(event.keyCode == 40 || event.keyCode == 38)) {

            $phone = $(this);

            var DCode = "+" + $phone.intlTelInput("getSelectedCountryData").dialCode;

            var searchList = $('#searchClientsResult');

            searchList.html('');

            var dataForSending;

            if ($phone.val().replace(DCode, '').length !== 0) {

                dataForSending = $phone.val().replace(DCode, '').replace(/\s/g, '');

                $.getJSON(location.origin + "/restApi/clients/getClientsByNoNFullPhoneNumber?search_S=" + dataForSending, function (data) {
                    $.each(data, function (key, value) {
                        searchList.append('<li class="list-group-item clients-search-editor-res-item" tabindex ="' + key + '" id="clientItem" data-clientid = "' + value.id + '">' +
                            '<div class="form-row">' +
                            '<div class="form-group col-md-8">' +
                            '<div class="form-row">' + value.phoneNumber +
                            '</div>' +
                            '<div class="form-row">' + '<p id="prodNamePar" style="overflow: hidden; text-overflow: ellipsis;">' + value.name + ' ' + value.lastName + '</p> ' +
                            '</div>' +
                            '</div>' +
                            '<div class="form-group col-md-4">' +
                            '<div class="form-row"> ' + '<p id="prodNamePar" style="overflow: hidden; text-overflow: ellipsis;">' + value.clientStatus + '</p> </div>' +
                            '</div></div></li>');

                    });

                });
            }

        }
    });

// function sumDouble(input) {
//
//
//     })
//
// }
    function encodeRequestReservedSymbols(strToValidation) {
        return strToValidation.replaceAll("%", "%25")
            .replaceAll("+", "%2B")
            .replaceAll("&", "%26")
            .trim();
    }

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
            } else {
                // Обеждаемся, что это цифра, и останавливаем событие keypress
                if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105)) {
                    event.preventDefault();
                }
            }
        });

    };

    ///////!!!!!!!!!!!!!!!!!!!!!!!!!!!Watch this!!!!!!!!!!!!!!!!!!!!!!!
    function validateSumPrice() {
        var sumPriceOrderLInes = 0;
        // var orderLineIdsM = orderLinesMap.values();
        var sumPricePageFieldP = $('#orderSumPrice');
        orderLinesMap.forEach(function (value, key, map) {
            var orderLineSumPrice = value.productValidPrice * value.productAmount;
            sumPriceOrderLInes = sumPriceOrderLInes + orderLineSumPrice;

        });

        sumPrice = sumPriceOrderLInes - discount;

        sumPricePageFieldP.text(sumPrice);

    };

    function validateAndCloseClientList(searchList, searchInput, selectedItem) {
        var clientItemId = selectedItem.data('clientid');

        $.getJSON(location.origin + "/restApi/clients/getClientById?search_Id=" + clientItemId).done( function (clientData) {
            client = clientData;
            syncModelClientWithView();
            syncModelAndViewOrderLinesPrices();
            searchInput.attr('class', 'form-control is-valid');
        }).fail(function () {
            searchInput.attr('class', 'form-control is-invalid');
        });

        searchList.empty();
    }

    function syncModelClientWithView() {
        $('#inputPhoneNumber').val(client.phoneNumber);
        $('#inputEmail').val(client.email);
        $('#inputName').val(client.name);
        $('#inputLastName').val(client.lastName);
        $('#inputMiddleName').val(client.middleName);
        $('#inputCity').val(client.usualDeliveryPlace);
        $('#inputWarehouseNumber').val(client.usualWarehouseNumber);
    }

    function validateAndCloseOrderLineList(searchList, searchInput, selectedItem) {

        var prodName = selectedItem.find('#prodNamePar').text();
        var productId = selectedItem.data('prodid');
        var entryClass = searchInput.parents('.entry:first');
        var productOrderLineIdInput = selectedItem.closest("div.form-row").find("input[id='prodOrderLineIdInput']");
        var previousObjectId = productOrderLineIdInput.val();
        ///Deleting previous object from model
        orderLinesMap.delete(Number(previousObjectId));
        var productOrderLineQua = selectedItem.closest("div.form-row").find("input[id='inputOrderLineProductQua']");
        $.getJSON(location.origin + "/restApi/products/getProductById?search_Id=" + productId).done(function (data) {
            validateProductForView(data);
            if (addOrderLineInModel(data, entryClass)) {

                searchInput.attr('class', 'form-control is-valid');
                syncModelAndViewOrderLinesPrices();
            } else {
                searchInput.attr('class', 'form-control is-invalid');
                searchInput.val('');
            }
        }).fail(function () {
            searchInput.attr('class', 'form-control is-invalid');
        });


        searchList.empty();

    }

    function addOrderLineInModel(oLineProductData, domEntryClass) {
        if (!orderLinesMap.has(oLineProductData.id)) {
            var orderLineMapItem = {};
            orderLineMapItem.productAmount = 1;
            orderLineMapItem.productValidPrice = 0;
            orderLineMapItem.sumOrderLinePrice = 0;
            orderLineMapItem.orderLineProductDOMItem = domEntryClass;
            orderLineMapItem.orderLineProductDataItem = oLineProductData;
            validateOrderLineMapItem(orderLineMapItem);
            orderLinesMap.set(oLineProductData.id, orderLineMapItem);
            return true;
        } else return false;

    }

    function validateOrderLineMap() {

        orderLinesMap.forEach(function (value, key, map) {
            validateOrderLineMapItem(value);
        });

    }

    function syncModelAndViewOrderLinesPrices() {

        validateOrderLineMap();


        orderLinesMap.forEach(function (val, k, m) {
            val.orderLineProductDOMItem.find("p[id='sumOrderLinePrice']").text(val.sumOrderLinePrice);
            val.orderLineProductDOMItem.find("input[id='inputOrderLineProductName']").val(encodeRequestReservedSymbols(val.orderLineProductDataItem.name));
            val.orderLineProductDOMItem.find("input[id='inputOrderLineProductQua']").val(val.productAmount);
            val.orderLineProductDOMItem.find("input[id='prodOrderLineIdInput']").val(k);
        });

        validateSumPrice();

    }

    function validateOrderLineMapItem(ordLineMItem) {
        if (client.clientStatus == "WHOLESALER") {
            ordLineMItem.productValidPrice = ordLineMItem.orderLineProductDataItem.wholeSalePrice;
        } else {
            ordLineMItem.productValidPrice = ordLineMItem.orderLineProductDataItem.price;
        }

        if (ordLineMItem.orderLineProductDataItem.currency == "USD") {
            ordLineMItem.productValidPrice = ordLineMItem.productValidPrice * appSettings.usd_currency;
        } else if (ordLineMItem.orderLineProductDataItem.currency == "EUR") {
            ordLineMItem.productValidPrice = ordLineMItem.productValidPrice * appSettings.eur_currency;
        }

        ordLineMItem.sumOrderLinePrice = ordLineMItem.productValidPrice * ordLineMItem.productAmount;

    }


    function validateProductForView(productToValidation) {
        validateProductPricePermissions(productToValidation);
        validateProductPricesCurrency(productToValidation);
    }

    function validateProductPricePermissions(prodToVal) {
        if (client.clientStatus == "WHOLESALER") {
            prodToVal.price = prodToVal.wholeSalePrice;
        }
    }

    function validateProductPricesCurrency(prod) {
        if (prod.currency == "USD") {
            prod.price = prod.price * appSettings.usd_currency;
        } else if (prod.currency == "EUR") {
            prod.price = prod.price * appSettings.eur_currency;
        }

    }


    function arrowActiveItemHandling(e, htmlItemList, searchInputField) {
        var searchListLiElFirst = htmlItemList.children('.list-group-item:first');
        var searchListLiElLast = htmlItemList.children('.list-group-item:last');
        var searchListLiElActive = htmlItemList.children('.active:first');
        var activeLiIsFirst = searchListLiElFirst.hasClass("active");
        var activeLiIsLast = searchListLiElLast.hasClass("active");

        if (searchListLiElActive.length !== 0) {

            if (e.keyCode == 38 && activeLiIsFirst ||
                e.keyCode == 40 && activeLiIsLast) {

                if (e.keyCode == 38 && activeLiIsFirst) {
                    searchListLiElFirst.removeClass("active");
                    searchListLiElLast.addClass("active");
                    searchListLiElLast.focus();
                    searchInputField.focus();
                }

                if (e.keyCode == 40 && activeLiIsLast) {
                    searchListLiElLast.removeClass("active");
                    searchListLiElFirst.addClass("active");
                    searchListLiElFirst.focus();
                    searchInputField.focus();
                }

            } else {

                if (e.keyCode == 40) {
                    var nextActiveLi = searchListLiElActive.next();
                    nextActiveLi.addClass("active");
                    nextActiveLi.focus();
                    searchInputField.focus();
                    searchListLiElActive.removeClass("active");

                }

                if (e.keyCode == 38) {
                    var prevActiveLi = searchListLiElActive.prev();
                    prevActiveLi.addClass("active");
                    prevActiveLi.focus();
                    searchInputField.focus();
                    searchListLiElActive.removeClass("active");

                }

            }

        } else {
            searchListLiElFirst.addClass("active");
        }

    }

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
            } else {
                // Обеждаемся, что это цифра, и останавливаем событие keypress
                if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105)) {
                    event.preventDefault();
                }
            }
        });
    };
});