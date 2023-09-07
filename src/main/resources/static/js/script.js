$(document).ready(function(){
    $('.card-text').each(function(){
        var maxLength = 30;
        var text = $(this).text();

        if (text.length > maxLength) {
            var truncatedText = text.substring(0, maxLength) + '...';
            $(this).text(truncatedText);
        }
    })
    $('#date').each(function(){
        var text = $(this).text();
        let parts = text.split("T");
        $(this).text(parts[0]);
    })
})