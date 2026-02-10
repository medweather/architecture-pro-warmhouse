const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/telemetry'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/home-monitoring-data', (telemetry) => {
        showHomeMonitoring(JSON.parse(telemetry.body).content);
    });
    stompClient.subscribe('/topic/temperature-data', (telemetry) => {
        showTemperature(JSON.parse(telemetry.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#telemetry").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function showHomeMonitoring(message) {
    $("#home").append("<tr><td>" + message + "</td></tr>");
}

function showTemperature(message) {
    $("#temperature").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
});