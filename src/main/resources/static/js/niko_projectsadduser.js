/*!
 * Niko JavaScript
 * Adding user to project
 */

function hidePopover(projectId) {
    $("#popoverOpenButton" + projectId).popover('hide');
}

function popoverOpen(projectId) {
    $("#projectInfo" + projectId).text("");

    $.ajax({
        url: "/project/" + projectId + "/getavailableusers",
        dataType: "json",
        success: function(data) { popoverOpenLoad(projectId, data) }
    });
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

        var button = $('<button type="button" class="btn btn-warning btn-block">Add</button>')
            .bind("click", function() { popoverOpenSend(projectId, $("#popoverContentSelect" + projectId).val()) });

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

    $("#projectInfo" + projectId).text(data);
}

function popoverOpenSendFailed(projectId, data) {
    hidePopover(projectId);

    $("#projectInfo" + projectId).text("Error has occured.");
}