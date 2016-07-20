/*!
 * Niko JavaScript
 * Adding user to project
 */

function clickOnButton(projectId) {
    $("#popoverOpenButton" + projectId).click();
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
}

function popoverOpenSend(projectId, userId) {
    $.ajax({
        url: "/project/" + projectId + "/addusertoproject/" + userId,
        success: function(data) { popoverOpenSendDone(projectId, data) }
    });
}

function popoverOpenSendDone(projectId, data) {
    clickOnButton(projectId);

    $("#projectInfo" + projectId).text(data);
}