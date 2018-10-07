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
    modal.find('#deleteProductLink').attr("href" , deleteProductLink)
});
});



