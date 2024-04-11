const app = (function () {

    const baseUrl = "http://localhost:8080";

    function init() {
        document.getElementById("postform").addEventListener("submit", function(event) {
            event.preventDefault();
        });
        getPosts();
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
        let username = sessionStorage.getItem("username");
        console.log("sending post");
        fetch(`${baseUrl}/api/v1/stream`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, content }),
        })
        .then((response) => response.json())
        .then((post) => {
            console.log("Success:", post);
            addPostToStream(post);
        });
    }

    function login() {
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

        fetch(`${baseUrl}/api/v1/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        })
        .then((response) => response.json())
        .then((data) => {
            console.log("Success:", data);
            sessionStorage.setItem("username", username);
            getPosts();
        });
    }

    return {
        init: init,
        createPost: createPost,
    }
})();
