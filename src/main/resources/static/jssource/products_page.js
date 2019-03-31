$(function () {

    var currentItemToDelete;

    $(document).on('show.bs.modal', '#deleteProductModal', function (event) {
        var referrerButton = $(event.relatedTarget); // Button that triggered the modal

        currentItemToDelete = referrerButton.closest('tr');
        var productId = referrerButton.data('product-id');
        var productName = referrerButton.data('product-name');// Extract productId from data-* attributes

        // var deleteProductLink = location.origin + "/restApi/products/delete?id=" + productId;;

        var modal = $(this);
        modal.find('#deleteProductNameModalLabel').text(productName);
        modal.find('#deleteProductLink').attr('data-prod-id', productId)
    });

    $(document).on('hide.bs.modal', '#deleteProductModal', function (event) {
        currentItemToDelete = null;
    });

    $(document).on('click', '#deleteProductLink', function (ev) {

        var productId = $(this).data('prod-id');

        $.ajax({
            url: location.origin + '/restApi/products/' + productId,
            type: 'DELETE',
            success : function (result) {
                currentItemToDelete.remove();
                currentItemToDelete = null;
                $('#deleteProductModal').modal('hide');
                location.reload();
            }
        });
        // $.post(location.origin + '/restApi/products/delete?id=' + productId).done(function (data) {
        //     currentItemToDelete.remove();
        //     currentItemToDelete = null;
        //     $('#deleteProductModal').modal('hide');
        // })
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



