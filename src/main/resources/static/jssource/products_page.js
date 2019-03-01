
$(function () {

    $(document).on('show.bs.modal', '#deleteProductModal', function (event) {
        var referrerButton = $(event.relatedTarget); // Button that triggered the modal

        var productId = referrerButton.data('product-id');
        var productName = referrerButton.data('product-name');// Extract productId from data-* attributes

        var deleteProductLink = location.origin + "/admin/products/delete?id=" + productId;

        var modal = $(this);
        modal.find('#deleteProductNameModalLabel').text(productName);
        modal.find('#deleteProductLink').attr("data-prodIdToDelete", productId)
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


});



