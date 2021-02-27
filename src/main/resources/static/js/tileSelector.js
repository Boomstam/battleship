
function clicked(x, y){
    $.ajax({
        url : 'start',
        method : 'GET',
        data: { x: x, y: y},
        //https://stackoverflow.com/questions/44488002/js-deprecation-synchronous-xmlhttprequest-on-the-main-thread-is-deprecated-be
        async : true,
        complete : function(data) {
            console.log(data.responseText);
            //location.reload();
        }
    });//this.getAttribute('data-parameter1')
    $('#info').text(x + " " + y);
    //$('#info').load(document.URL +  ' #info');
    //$( "#info" ).load(window.location.href + " #info" );
}