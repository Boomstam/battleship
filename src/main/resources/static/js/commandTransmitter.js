var shipCommands = [];
var shipTargets = [];
var targetingEnabled = false;

function transmitCommand(commandIndex){
    commandIndex = parseInt(commandIndex);
    let commandExists = false;
    for(const shipCommand of shipCommands){
        if(shipCommand.shipID === currentShipId){
            shipCommand.commandIndex = commandIndex;
            commandExists = true;
            break;
        }
    }
    if(commandExists === false){
        shipCommands.push( { "shipID": currentShipId, "commandIndex": commandIndex } );
    }
    console.log("click transmit");
    $.ajax({
        url : 'handleCommand',
        method : 'GET',
        data: { shipId: currentShipId, commandIndex: commandIndex},
        async : true,
        complete : function(data) {
            //console.log(data.responseText);
        }
    });
    layoutSelectedCommand(commandIndex);
}

function layoutSelectedCommand(commandIndex){
    console.log("click layout");
    let $commands = $('.command');
    console.log("commands length_" + $commands.length);
    let numCommands = $commands.length;
    $('.targetInfoContainer').hide();
    for (let i = 0; i < numCommands; i++){
        let $command = $commands[i];
        $($command).removeClass('selectedCommand');
        if(i === commandIndex){
            $($command).addClass('selectedCommand');
            if(i === numCommands - 1){
                $('.targetInfoContainer').css('display', 'flex');
                    enableTargeting();
            } else {
                disableTargeting();
                $('.target').removeClass('target');
                for(let i = 0; i < shipTargets.length; i++){
                    let shipTarget = shipTargets[i];
                    if(shipTarget.shipID === currentShipId){
                        shipTargets.splice(i);
                        break;
                    }
                }
            }
        }
    }
}

function disableTargeting(){
    targetingEnabled = false;
    $('.targetInRange').removeClass('targetInRange');
    unbindTargetClicks();
}

function enableTargeting(){
    targetingEnabled = true;
    $('.tileInRange').addClass('targetInRange');
    bindTargetClicks();
}

function bindTargetClicks(){
    $('.tileInRange').click(function (){
        console.log('click target');
        setTarget($(this));
    });
}

function unbindTargetClicks(){
    //$('.tileInRange').prop("onclick", null).off("click");
    $('.tileInRange').attr("onclick", "").unbind("click");
    /*$('.tileInRange').click(function (){
    });*/
}

function setTarget($targetElement){
    if(targetingEnabled == false){
        return;
    }
    $targetElement.addClass('target');
    let id = $targetElement.find('.tileContent').attr('id');
    let targetExists = false;
    for(const shipTarget of shipTargets){
        if(shipTarget.shipID === currentShipId){
            let existingTargetID = '#' + shipTarget.targetID;
            $(existingTargetID).parent().removeClass('target');

            shipTarget.targetID = id;
            targetExists = true;
            break;
        }
    }
    if(targetExists === false){
        shipTargets.push( { "shipID": currentShipId, "targetID": id } );
    }
    sendTargetCommand(id);
}

function sendTargetCommand(id){
    $.ajax({
        url : 'handleCommand',
        method : 'GET',
        data: { shipId: currentShipId, commandIndex: lastCommandIndex, targetID: id},
        async : true,
        complete : function(data) {
            //console.log(data.responseText);
        }
    });
}