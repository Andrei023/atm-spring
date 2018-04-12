$(document).ready(function () {
    $("#startMonitor").click(function () {
        $("#hiddenIrsForm").val("startMonitor");
    });
});

$(document).ready(function () {
    $("#stopMonitor").click(function () {
        $("#hiddenIrsForm").val("stopMonitor");
    });
});

$(document).ready(function () {
    $("#displayCache").click(function () {
        $("#hiddenIrsForm").val("displayCache");
    });
});