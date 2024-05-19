function loadGetMsg() {
    let msgVar = document.getElementById("msg").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        displayLogs(JSON.parse(this.responseText));
    }
    xhttp.open("GET", "/log?message=" + msgVar);
    xhttp.send();
}

function displayLogs(logs) {
    let tableHtml = '<table><tr><th>Message</th><th>Issued At</th></tr>';
    logs.forEach(log => {
        tableHtml += '<tr>';
        tableHtml += `<td>${log.message}</td>`;
        tableHtml += `<td>${log.issuedAt.$date}</td>`;
        tableHtml += '</tr>';
    });
    tableHtml += '</table>';
    document.getElementById("logsTable").innerHTML = tableHtml;
}