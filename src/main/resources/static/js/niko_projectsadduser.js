/*!
 * Niko JavaScript
 * Adding user to project
 */

function hidePopover(projectId) {
    $("#popoverOpenButton" + projectId).popover('hide');
}

function popoverOpen(projectId) {
    $.ajax({
        url: "/project/" + projectId + "/getavailableusers",
        dataType: "json",
        error: function(data) { popoverOpenLoadFailed(projectId, data) },
        success: function(data) { popoverOpenLoad(projectId, data) }
    });
}

function popoverOpenLoadFailed(projectId, data) {
    $("#popoverContent" + projectId).text('Something went wrong :(');
}

function popoverOpenLoad(projectId, data) {
    if(!jQuery.isEmptyObject(data)) {

        $("#popoverContent" + projectId).html('<select id="popoverContentSelect' + projectId + '" class="form-control"></select>');

        for (var key in data) {
          if (data.hasOwnProperty(key)) {
            var option = $('<option></option>').attr({ 'value' : key }).text(data[key]);
            $("#popoverContentSelect" + projectId).append(option);
          }
        }

        $("#popoverContent" + projectId).append('<br/>');

        var button = $('<button type="button" onclick="popoverOpenSend(' + projectId + ', ' + $("#popoverContentSelect" + projectId).val() + ')" class="btn btn-default btn-block">Add  <span class="glyphicon glyphicon-plus"></span>');

        $("#popoverContent" + projectId).append(button);

    } else {
        $("#popoverContent" + projectId).text('No users available.');
    }
}

function popoverOpenSend(projectId, userId) {
    $.ajax({
        url: "/project/" + projectId + "/addusertoproject/" + userId,
        error: function(data) { popoverOpenSendFailed(projectId, data) },
        success: function(data) { popoverOpenSendDone(projectId, data) }
    });
}

function popoverOpenSendDone(projectId, data) {
    hidePopover(projectId);

    if(!jQuery.isEmptyObject(data)) {

        if (data.hasOwnProperty("success")) {
            addMessageSuccess(data["success"]);
        }

        if (data.hasOwnProperty("error")) {
            addMessageError(data["error"]);
        }

    } else {
        addMessageError("An error has occured :(");
    }
}

function popoverOpenSendFailed(projectId, data) {
    hidePopover(projectId);

    addMessageError("An error has occured :(");
}

function addMessageSuccess(data) {
    $("#messageContainer").append(`
<div id="alert" class="alert alert-block alert-success alert-dismissible fade in" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
  <strong>Success!</strong> ` + data +
`</div>
`);

    setTimeout(function() { $("#alert").alert('close'); }, 7500);
}

function addMessageError(data) {
    $("#messageContainer").append(`
<div id="alert" class="alert alert-block alert-danger alert-dismissible fade in" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
  <strong>Error!</strong> ` + data +
`</div>
`);

    setTimeout(function() { $("#alert").alert('close'); }, 7500);
}