// $(document).ready(function () {
//     $("#searchOrder").keyup(function () {
//         _this = this;
//
//         $.each($("#orderListTable tbody tr"), function () {
//             if ($(this).text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1) {
//                 $(this).hide();
//             } else {
//                 $(this).show();
//             }
//         });
//     });
//
// });

$(function () {

    $(document).on('show.bs.modal', '#deleteProductModal', function (event) {
        var referrerButton = $(event.relatedTarget); // Button that triggered the modal

        var productId = referrerButton.data('product-id');
        var productName = referrerButton.data('product-name');// Extract productId from data-* attributes

        var deleteProductLink = location.origin + "/admin/products/delete?id=" + productId;

        var modal = $(this);
        modal.find('#deleteProductNameModalLabel').text(productName);
        modal.find('#deleteProductLink').attr("href", deleteProductLink)
    });

    $(document).on('click', '.product-search-res-item', function (e) {
        var prodId = $(this).data('prodid');
        window.location = (location.origin + "/admin/products/editProduct?id=" + prodId);
    });

    $(document).mouseup(function (e) {
        var searcResOrdList = $("#searchProductResult");
        if (searcResOrdList.has(e.target).length === 0) {
            searcResOrdList.empty();
        }
    });

    $(document).on('keyup', '#productNameSearchInput', function (e) {
        var searchList = $(this).siblings("#searchProductResult");
        searchList.html('');
        var searchField = $(this).val();

        if (searchField.length > 1) {
            $.getJSON(location.origin + "/admin/products/getProductsByNonFullName?search_S=" + searchField, function (data) {
                $.each(data, function (key, value) {

                    searchList.append('<li class="list-group-item product-search-res-item" data-prodid = "' + value.id + '"><div class="row"' +
                        '><div class="col-4"><img src="' + value.imageUrls[0] + '" height="60" width="80" class="img-thumbnail"></div>' +
                        '<div class="col-8"> <p style="overflow: hidden; text-overflow: ellipsis;">' + value.name + '</p> </div>' +
                        '</div></li>');

                });

            });
        }

    });
});



