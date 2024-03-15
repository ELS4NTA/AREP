const app = (function () {
    function init() {
        document.getElementById("loginForm").addEventListener("submit", function (event) {
            event.preventDefault();

            let username = document.getElementById("username").value;
            let password = document.getElementById("password").value;

            sessionStorage.setItem("username", username);

            fetch("/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username, password }),
            })
            .then((response) => response.text())
            .then((data) => {
                console.log("Success:", data);
                if (data.endsWith(".html")) {
                    window.location.href = data;
                } else {
                    document.getElementById("postrespmsg").innerHTML = data;
                }
            });
        });
    }

    function candies() {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            _displayCandies(this.responseText);
        };
        xhttp.open("GET", "/candies?username=" + sessionStorage.getItem("username"));
        xhttp.send();
    }

    function _displayCandies(response) {
        console.log(response);
        let message = response;
        if (message !== "Unauthorized") {
            let candies = JSON.parse(response);
            let tableHtml = '<table><tr><th>Dulce</th><th>Cantidad</th></tr>';
            for (let candy in candies) {
                tableHtml += `<tr><td>${candy}</td><td>${candies[candy]}</td></tr>`;
            }
            tableHtml += "</table>";
            message = tableHtml;
            _logoutButton();
        }
        document.getElementById("getrespmsg").innerHTML = message;
    }

    function _logoutButton() {
        let logoutButton = document.createElement("button");
        logoutButton.innerHTML = "Logout";
        logoutButton.onclick = function () {
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function() {
                window.location.href = this.responseText;
            }
            xhttp.open("GET", "/logout?username=" + sessionStorage.getItem("username"));
            xhttp.send();
            sessionStorage.removeItem("username");
        };
        document.getElementById("getresplogout").appendChild(logoutButton);
    }

    return {
        init: init,
        candies: candies,
    };
})();
