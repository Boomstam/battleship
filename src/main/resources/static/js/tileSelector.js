const coorSeparator = "_";
const lastCommandIndex = 5;
let currentShipId = -1;
let currentLocationsInRange = [];

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
        console.log("targeting location in range");
        setTarget($clickedTile);
        return;
    } else {
        targetingEnabled = false;
    }
    $('#selectedTile').text(x + " " + y);
    if(hasShip === "true"){
        shipId = parseInt(shipId);
        if(currentShipId === shipId){
            return;
        }
        shipClicked(shipId);

        $('.shipDetails').show();

        $('#shipType').text(shipType);
        $('#initiative').text(initiative);
        $('#weaponType').text(weaponType);
        $('#weaponRange').text(weaponRange);
        $('#direction').text(direction);
    } else{
        $('#hasShip').text("Open ocean");
        $('#commandButtons').css("display", "none");
        $('.target').removeClass('target');
        $('.shipDetails').hide();
        restoreTileContent();
        currentShipId = -1;
    }
    if(locationsInRange === null){
        //console.log('locationsNull');
    } else{
        locationsInRange = locationsInRange.split('-');
        restoreTileContent();
        saveCurrentLocationsInRange(locationsInRange);
        changeTileContent(locationsInRange, 'x');
        setTargetsInRange();
    }
}

function shipClicked(shipId) {
    $('#hasShip').text("Ship");
    $('#commandButtons').css("display", "flex");
    $('.target').removeClass('target');
    currentShipId = shipId;
    let commandFound = false;
    for (const shipCommand of shipCommands) {
        if (shipCommand.shipID === currentShipId) {
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
    if (commandFound === false) {
        layoutSelectedCommand(0);
    }
}

function setTargetsInRange(){
    for (const shipCommand of shipCommands) {
        if (shipCommand.shipID === currentShipId) {
            if (shipCommand.commandIndex === lastCommandIndex) {
                $('.tileInRange').addClass('targetInRange');
                return;
            }
        }
    }
}

function tileId(location){
    let id = "#" + location.x + coorSeparator + location.y;
    return id;
}