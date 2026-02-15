const maxRows = 10;

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8087/telemetry'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/home-monitoring-data', (telemetry) => {
        showHomeMonitoring(JSON.parse(telemetry.body));
    });
    stompClient.subscribe('/topic/temperature-data', (telemetry) => {
        showTemperature(JSON.parse(telemetry.body));
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

function showHomeMonitoring(data) {
    $("#home").append(`<tr><td>HomeMonitoringSensorID=${data.sensorId}: ${data.value}</td></tr>`);
    const tableBody = document.getElementById("home");
    while (tableBody.rows.length > maxRows) {
        tableBody.deleteRow(0);
    }
}

function showTemperature(data) {
    $("#temperature").append(`<tr><td>TemperatureSensorID=${data.sensorId}: ${data.value}</td></tr>`);
    const tableBody = document.getElementById("temperature");
    while (tableBody.rows.length > maxRows) {
        tableBody.deleteRow(0);
    }
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
});