$(function () {
    String.prototype.replaceAll = function (search, replace) {
        return this.split(search).join(replace);
    };

    var inputPaymentMethodDom;
    var inputNameDom;
    var inputLastNameDom;
    var inputMiddleNameDom;
    var inputCityDom;
    var inputWarehouseNumberDom;
    var inputOrderCommentDom;
    var inputOrderWeight;

    var inputArrayForValidation = [];

    var orderLinesMap = new Map();

    var appSettings;

    var client = {
        clientStatus: "USUAL"
    };


    var discount = 0;
    var sumPrice = 0;

    $(document).ready(function () {

        var clientDomId = $('#clientId').val();
        $.getJSON(location.origin + "/restApi/AppSettings", function (settingsData) {
            appSettings = settingsData;
        });

        $.getJSON(location.origin + "/restApi/clients/" + clientDomId).done(function (clientData) {
            client = clientData;
        });

        inputPaymentMethodDom = $('#paymentMethod');
        inputNameDom = $('#clientName');
        inputLastNameDom = $('#clientLastName');
        inputMiddleNameDom = $('#clientMiddleName');
        inputCityDom = $('#deliveryPlace');
        inputWarehouseNumberDom = $('#deliveryPlaceWarehouseNumber');
        inputOrderCommentDom = $('#orderComment');
        inputOrderWeight = $('#orderWeight');


        inputArrayForValidation.push(inputNameDom);
        inputArrayForValidation.push(inputLastNameDom);
        inputArrayForValidation.push(inputMiddleNameDom);
        inputArrayForValidation.push(inputCityDom);
        inputArrayForValidation.push(inputWarehouseNumberDom);
        inputArrayForValidation.push(inputOrderWeight);


    });

    $('#clientPhoneNumber').intlTelInput({
        preferredCountries: ['ua', 'ru', 'ml'],
        autoHideDialCode: true,
        utilsScript: "static/htmlhelpers/intl-tel-input/build/js/utils.js"
    });

    $('#clientPhoneNumber').on('change', function (e) {
        if ($(this).val() === "PICKUP") {
            $(inputArrayForValidation).each(function (iK, itemV) {
                itemV.attr('class', 'form-control is-valid');
            });
        } else {
            $(inputArrayForValidation).each(function (iK, itemV) {
                if ($(itemV).val().replace(/\s/g, '') === "") itemV.attr('class', 'form-control is-invalid');

            });
        }
    });

    $(document).on('click', '.btn-add', function (e) {
        e.preventDefault();

        $("#searchProductResult").empty();

        var controlForm = $('.controls-1:first'),
            currentEntry = $(this).parents('.entry:first'),
            newEntry = $(currentEntry.clone()).prependTo(controlForm);

        newEntry.find('input').val('');
        newEntry.find("p[id='sumOrderLinePrice']").text("0.0");
        newEntry.find('input').attr("class", "form-control is-invalid");

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
                alert("Refresh the page");
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

    $('#orderForm').submit(function (e) {
        e.preventDefault();
        if (validateSubmit()) {
            this.submit();
        }

    });


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
                $.getJSON(location.origin + "/restApi/products/?nonFullProductName=" + searchField, function (data) {
                    $.each(data, function (key, value) {
                        validateProductForView(value);
                        searchList.append('<li class="list-group-item product-search-editor-res-item" tabindex ="' + key + '" id="orderLineItem" data-prodid = "' + value.id + '">' +
                            '<div class="form-group col-md-3">' +
                            '<div class="input-row">' +
                            '<div class="col-4">' +
                            '<img src="' + value.images[0].url + '" height="60" width="80" class="img-thumbnail">' +
                            '</div>' +
                            '</div>' +
                            '<div class="input-row">' +
                            '<div class="col-8">' +
                            '<p id="prodNamePar" style="overflow: hidden; text-overflow: ellipsis;">' + value.name + '</p> ' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</li>');


                    });

                });
            }
        }

    });

//FOCUSOUT_EVENT

    $(document).on('focusout', '#clientName', function (e) {

        checkEmptinessInputFieldDependentOnPaymentMethodAndSetValidClassStatus($(this));
    });

    $(document).on('focusout', '#clientLastName', function (e) {
        checkEmptinessInputFieldDependentOnPaymentMethodAndSetValidClassStatus($(this));
    });

    $(document).on('focusout', '#clientMiddleName', function (e) {
        checkEmptinessInputFieldDependentOnPaymentMethodAndSetValidClassStatus($(this));
    });

    $(document).on('focusout', '#deliveryPlace', function (e) {
        checkEmptinessInputFieldDependentOnPaymentMethodAndSetValidClassStatus($(this));
    });

    $(document).on('focusout', '#deliveryPlaceWarehouseNumber', function (e) {
        checkEmptinessInputFieldDependentOnPaymentMethodAndSetValidClassStatus($(this));
    });

    /////////////////////////

    $(document).on('focusout', '#inputOrderLineProductName', function (e) {

        var searchField = $(this).val();
        var inputField = $(this);
        var previousItemDomId = inputField.closest("div.form-row").find("input[id='prodOrderLineIdInput']");

        searchField = encodeRequestReservedSymbols(searchField);

        $.getJSON(location.origin + "/restApi/products?productName=" + searchField).done(function (d) {
            var inputEntryDom = inputField.parents('.entry:first');
            if (addOrderLineInModel(d, inputEntryDom)) {

                inputField.attr('class', 'form-control is-valid');
                syncModelAndViewOrderLinesPrices();
            } else if (previousItemDomId.val() == d.id) {
                inputField.attr('class', 'form-control is-valid');

            } else {
                inputField.attr('class', 'form-control is-invalid');
                inputField.val('');
            }

        }).fail(function () {
            inputField.attr('class', 'form-control is-invalid');
            deleteOrderLineFromModelAndEmptyViewByProductId(Number(previousItemDomId.val()));
            syncModelAndViewOrderLinesPrices();
        });
    });

    $(document).on('focusout', '#discount', function (e) {

        discount = $(this).val();
        syncModelAndViewOrderLinesPrices();
    });

    $(document).on('change', '#inputOrderLineProductQua', function (event) {

        var inputQuaFieldValue = Number($(this).val());
        var productOrderLineIdInputItem = $(this).closest("div.form-row").find("input[id='prodOrderLineIdInput']");
        var productOrderLineIdInputItemVal = productOrderLineIdInputItem.val();

        if (orderLinesMap.has(Number(productOrderLineIdInputItemVal))) {
            var her = orderLinesMap.get(Number(productOrderLineIdInputItemVal));
            if ((Number(her.orderLineProductDataItem.quantity) < inputQuaFieldValue) ||
                (inputQuaFieldValue < 1)) {
                $(this).attr('class', 'form-control is-invalid');
            } else {
                $(this).attr('class', 'form-control is-valid');
                her.productAmount = inputQuaFieldValue;
                syncModelAndViewOrderLinesPrices();
            }

        }

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


    $(document).on('paste', '#clientPhoneNumber', function (event) {

        event.preventDefault();

        var validNumb = window.event.clipboardData.getData('text').replace(/\D+/g, "");

        $(this).val("+" + validNumb);
    });

    $(document).on('click', '#clientItem', function (e) {

        var searchClientsList = $('#searchClientsResult');

        var searchPhoneInput = $('#clientPhoneNumber');

        var activeItem = $(this);

        validateAndCloseClientList(searchClientsList, searchPhoneInput, activeItem)


    });

    $(document).mouseup(function (e) {
        var searchResClientList = $('#searchClientsResult');
        if (searchResClientList.has(e.target).length === 0) {
            searchResClientList.empty();
        }
    });


    $('#clientPhoneNumber')
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
            if (!(key == 8 ||
                key == 40 || key == 38 ||
                key == 13 ||
                key == 9 ||
                key == 46 ||
                key == 40 ||
                key == 38 ||
                (key >= 48 && key <= 57) ||
                (key >= 96 && key <= 105) ||
                (key == 67 && e.ctrlKey == true) ||
                (key == 86 && e.ctrlKey == true))) {

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

                $.getJSON(location.origin + "/restApi/clients?notFullClientPhoneNumber=" + dataForSending, function (data) {
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



    ///////!!!!!!!!!!!!!!!!!!!!!!!!!!!Watch this!!!!!!!!!!!!!!!!!!!!!!!
    function validateSumPrice() {
        var sumPriceOrderLInes = 0;
        // var orderLineIdsM = orderLinesMap.values();
        var sumPricePageFieldP = $('#summaryPrice');
        orderLinesMap.forEach(function (value, key, map) {
            var orderLineSumPrice = value.productValidPrice * value.productAmount;
            sumPriceOrderLInes = sumPriceOrderLInes + orderLineSumPrice;

        });

        sumPrice = sumPriceOrderLInes - discount;

        sumPricePageFieldP.text(sumPrice);

    };

    function validateAndCloseClientList(searchList, searchInput, selectedItem) {
        var clientItemId = selectedItem.data('clientid');

        $.getJSON(location.origin + "/restApi/clients/" + clientItemId).done(function (clientData) {
            client = clientData;
            syncModelClientWithView();
            syncModelAndViewOrderLinesPrices();
            searchInput.attr('class', 'form-control is-valid');
            validateRequiredFieldsAfterAutoInserting();
        }).fail(function () {
            searchInput.attr('class', 'form-control is-invalid');
        });

        searchList.empty();
    }

    function syncModelClientWithView() {
        $('#clientId').val(client.id);
        $('#clientPhoneNumber').val(client.phoneNumber);
        $('#clientEmail').val(client.email);
        $('#clientName').val(client.name);
        $('#clientLastName').val(client.lastName);
        $('#clientMiddleName').val(client.middleName);
        $('#deliveryPlace').val(client.usualDeliveryPlace);
        $('#deliveryPlaceWarehouseNumber').val(client.usualWarehouseNumber);
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
            val.orderLineProductDOMItem.find("input[id='inputOrderLineProductName']").val(val.orderLineProductDataItem.name);
            val.orderLineProductDOMItem.find("input[id='inputOrderLineProductQua']").val(val.productAmount);
            val.orderLineProductDOMItem.find("input[id='prodOrderLineIdInput']").val(k);
        });

        validateSumPrice();

    }

    function deleteOrderLineFromModelAndEmptyViewByProductId(ordLineId) {
        var itemToRemove = orderLinesMap.get(ordLineId);

        if (itemToRemove !== undefined) {
            itemToRemove.orderLineProductDOMItem.find("p[id='sumOrderLinePrice']").text(0.00);
            itemToRemove.orderLineProductDOMItem.find("input[id='inputOrderLineProductQua']").val(0);
            itemToRemove.orderLineProductDOMItem.find("input[id='prodOrderLineIdInput']").val(0);
            orderLinesMap.delete(ordLineId);
        }

    }

    //validating price depending on userStatus and currency
    function validateOrderLineMapItem(ordLineMItem) {
        if (client.clientStatus == "WHOLESALER") {
            ordLineMItem.productValidPrice = ordLineMItem.orderLineProductDataItem.wholeSalePrice;
        } else {
            ordLineMItem.productValidPrice = ordLineMItem.orderLineProductDataItem.price;
        }

        if (ordLineMItem.orderLineProductDataItem.currency == "USD") {
            ordLineMItem.productValidPrice = (ordLineMItem.productValidPrice * appSettings.usd_currency).toFixed(2);
        } else if (ordLineMItem.orderLineProductDataItem.currency == "EUR") {
            ordLineMItem.productValidPrice = (ordLineMItem.productValidPrice * appSettings.eur_currency).toFixed(2);
        }

        ordLineMItem.sumOrderLinePrice = (ordLineMItem.productValidPrice * ordLineMItem.productAmount).toFixed(2);

    }


    function validateProductForView(productToValidation) {
        validateProductPricePermissions(productToValidation);
        validateProductPricesCurrency(productToValidation);
    }

    //Validate main price for subsequent calculation depending on clientStatus
    function validateProductPricePermissions(prodToVal) {
        if (client.clientStatus == "WHOLESALER") {
            prodToVal.price = prodToVal.wholeSalePrice;
        }
    }

    function validateProductPricesCurrency(prod) {
        if (prod.currency == "USD") {
            prod.price = (prod.price * appSettings.usd_currency).toFixed(2);
        } else if (prod.currency == "EUR") {
            prod.price = (prod.price * appSettings.eur_currency).toFixed(2);
        }

    }

    ///Method check if field has empty value when PaymentMethod isn't PICKUP amd set appendedClass "is-valid" when hasn't
    //and vice versa
    function checkEmptinessInputFieldDependentOnPaymentMethodAndSetValidClassStatus(inputFieldForValidation) {
        if (inputFieldForValidation.val().replace(/\s/g, '') === "" && !($(inputPaymentMethod).val() === "PICKUP")) {
            inputFieldForValidation.attr('class', 'form-control is-invalid');
        } else {
            inputFieldForValidation.attr('class', 'form-control is-valid');
        }
    }



    function validateRequiredFieldsAfterAutoInserting() {
        $(inputArrayForValidation).each(function (iK, itemV) {
            if ($(itemV).val().replace(/\s/g, '') !== "") itemV.attr('class', 'form-control is-valid');

        });
    }

    function validateSubmit() {

        var formIsValid = true;

        var entryDomItems = $('#orderLines').find('.entry');

        entryDomItems.each(function (i, item) {
            if ($(item).find('#inputOrderLineProductName:first').hasClass("is-invalid") ||
                $(item).find('#inputOrderLineProductQua:first').hasClass("is-invalid")) {

                formIsValid = false;

            }

        });

        if (!($(inputPaymentMethod).val() === "PICKUP")) {
            $(inputArrayForValidation).each(function (iK, itemV) {
                if ($(itemV).hasClass("is-invalid")) formIsValid = false;

            });
        }

        return formIsValid;

    }


    $(document).on('show.bs.modal', '#changeOrderStatusModal', function (event) {
        var referrerItem = $(event.relatedTarget); // Button that triggered the modal

        var productId = referrerItem.data('product-id');
        var productName = referrerItem.data('product-name');// Extract productId from data-* attributes

        var deleteProductLink = location.origin + "/admin/products/delete?id=" + productId;

        var modal = $(this);
        modal.find('#deleteProductNameModalLabel').text(productName);
        modal.find('#deleteProductLink').attr("data-prodIdToDelete", productId)
    });
});