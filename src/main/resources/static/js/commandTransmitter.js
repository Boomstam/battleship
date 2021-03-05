function transmitCommand(commandIndex){
    commandIndex = parseInt(commandIndex);
    $.ajax({
        url : 'handleCommand',
        method : 'GET',
        data: { shipId: currentShipId, commandIndex: commandIndex},
        async : true,
        complete : function(data) {
            console.log(data.responseText);
        }
    });
}