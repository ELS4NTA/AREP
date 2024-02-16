function loadGetMsg() {
    let nameVar = document.getElementById("name").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let data = JSON.parse(this.responseText);
        if (data.Response !== "False") {
            // Crear un nuevo elemento <article>
            let movieArticle = document.createElement("article");
    
            // Agregar clases a <article>
            movieArticle.classList.add("movie-details");
    
            // Crear y agregar elementos con estilos y clases
            movieArticle.innerHTML = `
                <img class="poster" src="${data.Poster}" alt="${data.Title} Poster">
                <div>
                    <h2 class="title">${data.Title}</h2>
                    <p class="director">Director: ${data.Director}</p>
                    <p class="released">Released: ${data.Released}</p>
                    <p class="actors">Actors: ${data.Actors}</p>
                    <p class="language">Language: ${data.Language}</p>
                    <p class="plot">Plot: ${data.Plot}</p>
                </div>
            `;
            // Agregar el <article> al DOM
            document.getElementById("getrespmsg").innerHTML = "";
            document.getElementById("getrespmsg").appendChild(movieArticle);
        }
    };
    xhttp.open("GET", "/action/movie?name=" + nameVar);
    xhttp.send();
}