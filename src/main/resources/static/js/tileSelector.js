var coorSeparator = "_";
var currentShipId = -1;
var lastCommandIndex = 5;
//var weaponTypes = ['Gun', 'Aircraft', 'Torpedo', 'Depth', 'Mine'];

var currentLocationsInRange = [];

function tileElementID(location) {
    let coors = location.split('_');
    location = { x: coors[0], y: coors[1] };
    let id = tileId(location);
    return id;
}

function changeTileContent(locationsInRange, content) {
    let length = locationsInRange.length;
    for (let i = 0; i < length; i++) {
        let location = locationsInRange[i];
        let id = tileElementID(location);
        $(id).text(content);
        $(id).parent().addClass('tileInRange');
    }
}

function restoreTileContent() {
    let length = currentLocationsInRange.length;
    console.log('restore tile content');
    $('.tileInRange').removeClass('tileInRange');
    $('.targetInRange').removeClass('targetInRange');
    for (let i = 0; i < length; i++) {
        let locationAndContent = currentLocationsInRange[i];
        let location = locationAndContent.location;
        let id = tileElementID(location);
        $(id).text(locationAndContent.content);
    }
}

function saveCurrentLocationsInRange(locationsInRange) {
    currentLocationsInRange = [];
    for (let i = 0; i < locationsInRange.length; i++) {
        let location = locationsInRange[i];
        let id = tileElementID(location);
        let content = $(id).text();
        $(id).parent().addClass('tileInRange');
        let locationAndContent = { location: location, content: content };
        currentLocationsInRange[i] = locationAndContent;
    }
}

function clicked(x, y, hasShip, shipId, shipType, initiative, weaponType, weaponRange, direction, locationsInRange){
    x = parseInt(x);
    y = parseInt(y);
    let $clickedTile = $(tileId({ "x": x, "y": y} )).parent();
    let isLocationInRange = $clickedTile.hasClass('tileInRange');
    if(targetingEnabled && isLocationInRange){
        return;
    } else {
        targetingEnabled = false;
    }
    $.ajax({
        url : 'tileClick',
        method : 'GET',
        data: { x: x, y: y},
        async : true,
        complete : function(data) {
            //console.log(data.responseText);
        }
    });
    $('#selectedTile').text(x + " " + y);
    if(hasShip === "true"){
        $('#hasShip').text("Ship");
        $('#commandButtons').css("display", "flex");
        $('.target').removeClass('target');
        currentShipId = parseInt(shipId);
        let commandFound = false;
        for(const shipCommand of shipCommands){
            if(shipCommand.shipID === currentShipId) {
                layoutSelectedCommand(shipCommand.commandIndex);
                if (shipCommand.commandIndex === lastCommandIndex) {
                    for (const shipTarget of shipTargets) {
                        if (shipTarget.shipID === currentShipId) {
                            let existingTargetID = '#' + shipTarget.targetID;
                            $(existingTargetID).parent().addClass('target');
                            break;
                        }
                    }
                }
                commandFound = true;
                break;
            }
        }
        if(commandFound === false){
            layoutSelectedCommand(0);
        }
    } else{
        $('#hasShip').text("Open ocean");
        $('#commandButtons').css("display", "none");
        $('.target').removeClass('target');
        restoreTileContent();
        currentShipId = -1;
    }
    //let commandFound = false;
    /*for(const shipCommand of shipCommands){
        if(shipCommand.shipID === currentShipId) {
            layoutSelectedCommand(shipCommand.commandIndex);
            //commandFound = true;
            break;
        }
    }*/
    /*if(commandFound === false){
        layoutSelectedCommand(0);
    }*/
    $('#shipType').text(shipType);
    $('#initiative').text(initiative);
    $('#weaponType').text(weaponType);
    $('#weaponRange').text(weaponRange);
    $('#direction').text(direction);
    if(locationsInRange === null){
        //console.log('locationsNull');
    } else{
        locationsInRange = locationsInRange.split('-');
        restoreTileContent();
        saveCurrentLocationsInRange(locationsInRange);
        changeTileContent(locationsInRange, 'x');
    }
}

function tileId(location){
    let id = "#" + location.x + coorSeparator + location.y;
    return id;
}

/*function shipSelected(locationsInRange){
    let length = locationsInRange.length;
    let tile1 = locationsInRange[0];
    let tile2 = locationsInRange[1];
    console.log('length' + length);
    console.log(tile1  + '_' + tile2);
}*/