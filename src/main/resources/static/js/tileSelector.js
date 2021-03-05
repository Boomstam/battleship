var coorSeparator = "_";
var currentShipId = -1;

function clicked(x, y, hasShip, shipId, shipType, initiative, weaponType, weaponRange, direction, locationsInRange){
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
        $('#commandButtons').css("display", "inherit");
        currentShipId = parseInt(shipId);
    } else{
        $('#hasShip').text("Open ocean");
        $('#commandButtons').css("display", "none");
        currentShipId = -1;
    }
    $('#shipType').text(shipType);
    $('#initiative').text(initiative);
    $('#weaponType').text(weaponType);
    $('#weaponRange').text(weaponRange);
    $('#direction').text(direction);
    if(locationsInRange === null){
        console.log('locationsNull');
    } else{
        locationsInRange = locationsInRange.split('-');
        let length = locationsInRange.length;
        for(let i = 0; i < length; i++){
            let location = locationsInRange[i];
            let coors = location.split('_');
            location = { x: coors[0], y:coors[1] };
            //console.log(location);
            id = tileId(location);
            //console.log(id);
            $(id).text('o');
        }
        /*let i = 0;
        while(i < 5){
            let location = locationsInRange[i];
            console.log(location);
            i++;
        }*/
        let tile1 = locationsInRange[0];
        let tile2 = locationsInRange[1];

        console.log('length' + length);
        console.log(tile1  + '_' + tile2);
        //shipSelected(locationsInRange);
        //asyncShipsSelected(locationsInRange);
    }
    /*id = tileId(x, y);
    console.log(id);
    $(id).text('o');*/
}

function tileId(location){
    let id = "#" + location.x + coorSeparator + location.y;
    return id;
}

function shipSelected(locationsInRange){
    let length = locationsInRange.length;
    /*for(let i = 0; i < 5; i++){
        location = locationsInRange[i];
    }*/
    let tile1 = locationsInRange[0];
    let tile2 = locationsInRange[1];
    console.log('length' + length);
    console.log(tile1  + '_' + tile2);
    //console.log(locationsInRange.length);
    //console.log(locationsInRange[0]);
    //for(location of locationsInRange){
        //console.log(location.x + "_" + location.y);
        //let id = tileId(location);
        //$(id).text('o');

    //
}

/*const asyncShipsSelected = async (locationsInRange) => {
    for(location of locationsInRange){
        //console.log(location.x + "_" + location.y);
        let id = tileId(location);
        $(id).text('o');
        await delay(1000);
    }
};

const delay = ms => new Promise(res => setTimeout(res, ms));*/