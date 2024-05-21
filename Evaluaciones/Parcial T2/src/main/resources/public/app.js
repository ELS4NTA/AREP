function loadGetMsg() {
    let list = document.getElementById("list").value;
    let value = document.getElementById("value").value;
    let isLinear = document.getElementById("searchType").checked;
    let operation = isLinear ? "/linearsearch" : "/binarysearch";
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        makeBeautiful(this.responseText);
    }
    xhttp.open("GET", `${operation}?list=${list}&value=${value}`);
    xhttp.send();
}

function makeBeautiful(response) {
    let json = JSON.parse(response);
    let table = '<table border="1">';
    for (let key in json) {
        table += '<tr>';
        table += '<td>' + key + '</td>';
        table += '<td>' + json[key] + '</td>';
        table += '</tr>';
    }
    table += '</table>';
    document.getElementById('getrespmsg').innerHTML = table;
}