function loadPostMsg(name){
    let url = "/action/fruitstore?name=" + name.value;

    fetch (url, {method: 'POST'})
        .then(x => x.text())
        .then(y => makeSmoothie(y));
}

function makeSmoothie(jsonResponse){
    let response = JSON.parse(jsonResponse);
    let smoothieArticle = document.createElement("article");
    let divFruits = document.createElement("div");
    smoothieArticle.classList.add("smoothie-details");
    divFruits.classList.add("fruits");
    // for each fruit in response.fruits
    console.log(response);
    response.fruits.forEach(fruit => {
        console.log(fruit);
        let fruitDiv = document.createElement("div");
        fruitDiv.classList.add("fruit-details");
        fruitDiv.innerHTML = `
            <img src="./images/${fruit}.png" alt="${fruit}" width="25px" height="25px">
            <p>${fruit}</p>
        `;
        divFruits.appendChild(fruitDiv);
    });
    // add to article fruit div and title and price
    smoothieArticle.innerHTML += `
        <img class="smoothie-image" src="./images/smoothie.jpg" alt="Smoothie">
        <h2 class="smoothie-title">${response.name}</h2>
        <p class="smoothie-price">Price: ${response.price} COP</p>
    `;
    smoothieArticle.appendChild(divFruits);
    // add article to DOM
    document.getElementById("postrespmsg").innerHTML = "";
    document.getElementById("postrespmsg").appendChild(smoothieArticle);

}