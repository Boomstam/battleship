var coorSeparator = "_";

function clicked(x, y, hasShip, shipType, initiative, weaponType, weaponRange, direction){
    x = parseInt(x);
    y = parseInt(y);
    $.ajax({
        url : 'tileClick',
        method : 'GET',
        data: { x: x, y: y},
        async : true,
        complete : function(data) {
            console.log(data.responseText);
        }
    });
    $('#selectedTile').text(x + " " + y);
    if(hasShip === "true"){
        $('#hasShip').text("Ship");
    } else{
        $('#hasShip').text("Open ocean");
    }
    $('#shipType').text(shipType);
    $('#initiative').text(initiative);
    $('#weaponType').text(weaponType);
    $('#weaponRange').text(weaponRange);
    $('#direction').text(direction);
    id = tileId(x, y);
    console.log(id);
    $(id).text('o');
}

function tileId(x, y){
    id = "#" + x + coorSeparator + y;
    return id;
}