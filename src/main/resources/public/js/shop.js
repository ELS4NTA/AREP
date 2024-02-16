function loadGetMsg() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let response = JSON.parse(this.responseText);

        // if data object has atributtes delete all children of the div getrespmsg
        if (response.smoothies.length !== 0) {
            document.getElementById("getrespmsg").innerHTML = "";
            document.getElementById("getrespmsg").classList.add("getrespmsg");
            let totalPrice = 0;
            // for each smoothie that has fruits, price and name create an article
            response.smoothies.forEach(smoothie => {
                let smoothieArticle = document.createElement("article");
                let divFruits = document.createElement("div");
                smoothieArticle.classList.add("smoothie-details");
                divFruits.classList.add("fruits");
                // for each fruit in response.fruits
                smoothie.fruits.forEach(fruit => {
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
                    <h2 class="smoothie-title">${smoothie.name}</h2>
                    <p class="smoothie-price">Price: ${smoothie.price} COP</p>
                `;
                totalPrice += smoothie.price;
                smoothieArticle.appendChild(divFruits);
                // add article to DOM
                document.getElementById("getrespmsg").appendChild(smoothieArticle);
            });
            let buttonPrice = document.createElement("button");
            buttonPrice.classList.add("shop-button");
            buttonPrice.innerHTML = `
                <p>Pay COP $ ${totalPrice}</p>
            `;
            buttonPrice.onclick = function() {
                window.location.href = "index.html";
            };
            document.getElementById("hero_section").appendChild(buttonPrice);
        }
        
    };
    xhttp.open("GET", "/component/smoothies");
    xhttp.send();
}