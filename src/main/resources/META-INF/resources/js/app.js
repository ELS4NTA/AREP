const app = (function () {

    const baseUrl = "http://localhost:8080";

    function init() {
        document.getElementById("postform").addEventListener("submit", function(event) {
            event.preventDefault();
        });
        getPosts();
    }

    function initLogin() {
        document.getElementById("loginform").addEventListener("submit", function(event) {
            event.preventDefault();
        });
    }

    function getPosts() {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            loadPosts(JSON.parse(this.responseText));
        };
        xhttp.open("GET", `${baseUrl}/api/v1/stream`);
        xhttp.send();
    }

    function createPost() {
        let content = document.getElementById("content").value;
        document.getElementById("content").value = "";
        let username = sessionStorage.getItem("username");
        fetch(`${baseUrl}/api/v1/stream`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, content }),
        })
        .then((response) => response.json())
        .then((post) => {
            addPostToStream(post);
        });
    }

    function login() {
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

        fetch(`${baseUrl}/secured/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Login failed");
            }
            return response.json();
        })
        .then((data) => {
            sessionStorage.setItem("username", username);
            sessionStorage.setItem("token", data.token);
            window.location.href = "stream.html";
        })
        .catch((error) => {
            alert(error);
        });
    }

    function logout() {
        sessionStorage.clear();
        window.location.href = "index.html";
    }

    return {
        init: init,
        initLogin: initLogin,
        createPost: createPost,
        login: login,
        logout: logout
    }
})();
