$(document).on('click', '.btn-add', function (e) {
    e.preventDefault();

    var controlForm = $('.controls-1:first'),
        currentEntry = $(this).parents('.entry:first'),
        newEntry = $(currentEntry.clone()).prependTo(controlForm);

    newEntry.find('input').val('');

    controlForm.find('.entry:not(:last) .btn-add')
        .removeClass('btn-add').addClass('btn-remove')
        .removeClass('btn-success').addClass('btn-danger')
        .html('<span class="glyphicon glyphicon-minus"></span>');
}).on('click', '.btn-remove', function (e) {
    var entryToRemove = $(this).parents('.entry:first');


    entryToRemove.remove();


    e.preventDefault();
    return false;
});