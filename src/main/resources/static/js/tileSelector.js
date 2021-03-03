
function clicked(x, y, hasShip){
    $.ajax({
        url : 'tileClick',
        method : 'GET',
        data: { x: x, y: y},
        async : true,
        complete : function(data) {
            console.log(data.responseText);
        }
    });
    //$('#info').text(x + " " + y + " " + board.tiles);
    $('#info').text(x + " " + y + " " + hasShip);

}

//$('#info').load(document.URL +  ' #info');
//$( "#info" ).load(window.location.href + " #info" );