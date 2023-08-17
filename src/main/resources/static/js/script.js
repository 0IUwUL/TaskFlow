$('.edit').on('click', function(){
    let id = $(this).data('id')
    let name = $(this).data('name')
    let email = $(this).data('email')

    $('#Modal .modal-dialog').addClass('border border-warning-subtle rounded')
    $('#Modal .modal-header').addClass('bg-warning text-dark')
    $('#Modal .modal-header h1').text("Edit Details")

    $.ajax({
        url: "/edit/"+id,
        type:'get',
        data: {
            id : id,
        },
        success: function(result){
            $('#Modal .modal-body').html(result)
            $('#Modal form #username').val(name)
            $('#Modal form #email').val(email)
            $('#Modal form #id').val(id)
            $('#Modal form button[type="submit"]').text('Edit')
            $("#Modal form").attr('action', '/edit/'+id);
        }
    });
})

