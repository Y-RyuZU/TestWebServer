document.getElementById("reload_button").addEventListener("click", async event => {
    const responce = await fetch("/api/random_data")
    const json_data = await responce.json();
    const container = document.getElementById("results_div");
    const template = document.getElementById("result_template");
    container.innerHTML = "";
    for (const [key, value] of Object.entries(json_data)) {
        const clone = template.content.cloneNode(true);
        clone.querySelector(".key").textContent = key;
        clone.querySelector(".key").textContent = value;
        container.appendChild(clone)
    }
});