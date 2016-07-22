/*!
 * Niko JavaScript
 * Adding user to project
 */

function hidePopover(projectId) {
    $("#popoverOpenButton" + projectId).popover('hide');
}

function repositionPopover(projectId) {
    var popover_button = $("#popoverOpenButton" + projectId);
    var popover_content = $("#popoverContent" + projectId).parent('div').parent('div');

    popover_content.css("left", popover_button.offset().left - popover_content.width());
    popover_content.css("top", popover_button.offset().top - popover_content.height()/2 + popover_button.height()/2);
}

function popoverOpen(projectId) {
    repositionPopover(projectId);

    $.ajax({
        url: "/project/" + projectId + "/getavailableusers",
        dataType: "json",
        error: function(data) { popoverOpenLoadFailed(projectId, data) },
        success: function(data) { popoverOpenLoad(projectId, data) }
    });
}

function popoverOpenLoadFailed(projectId, data) {
    $("#popoverContent" + projectId).text('Something went wrong :(');

    repositionPopover(projectId);
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

        var button = $('<button type="button" class="btn btn-default btn-block">Add  <span class="glyphicon glyphicon-plus"></span>')
            .bind("click", function() { popoverOpenSend(projectId, $("#popoverContentSelect" + projectId).val()) });


        $("#popoverContent" + projectId).append(button);

    } else {
        $("#popoverContent" + projectId).text('No users available.');
    }

    repositionPopover(projectId);
}

function popoverOpenSend(projectId, userId) {
    $.ajax({
        url: "/project/" + projectId + "/addusertoproject/" + userId,
        error: function(data) { popoverOpenSendFailed(projectId, data) },
        success: function(data) { popoverOpenSendDone(projectId, data) }
    });

    hidePopover(projectId);
}

function popoverOpenSendDone(projectId, data) {
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
    addMessageError("An error has occured :(");
}

function addMessageSuccess(data) {
    $("#messageContainer").html(`
<div id="alert" class="alert alert-block alert-success alert-dismissible fade in" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
  <strong>Success!</strong> ` + data +
`</div>
`);
}

function addMessageError(data) {
    $("#messageContainer").html(`
<div id="alert" class="alert alert-block alert-danger alert-dismissible fade in" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
  <strong>Error!</strong> ` + data +
`</div>
`);
}

function repositionPopoverOnResize() {
    var popover_buttons = $("[id^=popoverOpenButton]");
    var popover_contents = $("[id^=popoverContent]").parent('div').parent('div');

    popover_contents.first().css("left", popover_buttons.first().offset().left - popover_contents.first().width());
}

window.addEventListener('resize', function() {
        setTimeout(function () {
            repositionPopoverOnResize();

            setTimeout(function () {
                repositionPopoverOnResize();

                setTimeout(function () {
                    repositionPopoverOnResize();
                }, 200);
            }, 200);
        }, 200);
    }, true);