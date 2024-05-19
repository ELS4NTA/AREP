function loadGetSin() {
    let nameVar = document.getElementById("sin").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsgsin").innerHTML = "Value of sin("+nameVar+") is: "+
        this.responseText;
    }
    xhttp.open("GET", "/sin?value="+nameVar);
    xhttp.send();
}

function loadGetCos() {
    let nameVar = document.getElementById("cos").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsgcos").innerHTML = "Value of cos("+nameVar+") is: "+
        this.responseText;
    }
    xhttp.open("GET", "/cos?value="+nameVar);
    xhttp.send();
}

function loadGetPalindrome() {
    let nameVar = document.getElementById("palindrome").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.responseText == "true") {
            document.getElementById("getrespmsgpalindrome").innerHTML = "The word is a palindrome";
        } else {
            document.getElementById("getrespmsgpalindrome").innerHTML = "The word is not a palindrome";
        }
    }
    xhttp.open("GET", "/palindrome?value="+nameVar);
    xhttp.send();
}

function loadGetMagnitude() {
    let nameVar = document.getElementById("vector").value;
    let vector = nameVar.split(",");
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsgmagnitude").innerHTML = "The maginude of " + vector + " is: "+
        this.responseText;
    }
    xhttp.open("GET", "/vector?x1="+vector[0]+"&y1="+vector[1]+"&x2="+vector[2]+"&y2="+vector[3]);
    xhttp.send();
}