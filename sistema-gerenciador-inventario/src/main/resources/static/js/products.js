let products = [];
let selectedIndex = null;

const API_URL = "http://localhost:8080/products";

/* Render Cards */
function renderCards() {
    const container = document.getElementById("cardsContainer");
    container.innerHTML = "";

    products.forEach((p, index) => {
        container.innerHTML += `
            <div class="card" onclick="openModal(${index})">
                <h3>${p.name}</h3>
                <p><strong>Qtd:</strong> ${p.quantity}</p>
                <p><strong>Valor:</strong> R$ ${p.value}</p>
                <p><strong>Status:</strong> ${p.status}</p>
            </div>
        `;
    });
}

/* Abrir Modal Visualização */
function openModal(index) {
    selectedIndex = index;
    const product = products[index];

    document.getElementById("modalTitle").innerText = product.name;
    const body = document.getElementById("modalBody");
    body.innerHTML = "";

    Object.keys(product).forEach(key => {
        body.innerHTML += `
            <input value="${product[key]}" id="edit_${key}" disabled>
        `;
    });

    document.getElementById("productModal").style.display = "flex";
}

function closeModal() {
    document.getElementById("productModal").style.display = "none";
}

/* Editar */
function enableEdit() {
    document.querySelectorAll("#modalBody input")
        .forEach(input => input.disabled = false);
}

async function saveEdit() {
    const product = products[selectedIndex];

    Object.keys(product).forEach(key => {
        product[key] = document.getElementById("edit_" + key).value;
    });

     try {
        const response = await fetch(`${API_URL}/${selectedIndex}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify({ product })
        });

        if (response.ok) {
            console.log("Produto atualizado com sucesso")
        }
    } catch (error) {
        console.log(error)
    }

    renderCards();
    closeModal();
}

/* Excluir */
async function deleteProduct() {
    products.splice(selectedIndex, 1);

    try {
        const response = await fetch(`${API_URL}/${selectedIndex}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            credentials: "include"
        });

        if (response.ok) {
            console.log("Produto deletado com sucesso")
        }
    } catch (error) {
        console.log(error)
    }

    renderCards();
    closeModal();
}

/* Modal Criar */
function openCreateModal() {
    document.getElementById("createModal").style.display = "flex";
}

function closeCreateModal() {
    document.getElementById("createModal").style.display = "none";
}

document.getElementById("createForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const product = {
        name: c_name.value,
        category: c_category.value,
        typeItem: "PRODUTO",
        description: c_description.value,
        quantity: c_quantity.value,
        minimumQuantity: c_minimumQuantity.value,
        measure: c_measure.value,
        status: c_status.value,
        user: "eike",
        itemLocal: c_itemLocal.value,
        value: c_value.value,
        weight: c_weight.value,
        height: c_height.value,
        length: c_length.value,
        depth: c_depth.value
    };
    try {
        const response = await fetch(`${API_URL}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify({ product })
        });

        if (response.ok) {
            console.log("Produto criado com sucesso")
        }
    } catch (error) {
        console.log(error)
    }

    products.push(product);
    renderCards();
    closeCreateModal();
    this.reset();
});

async function getProducts() {
    try {
        const response = await fetch(`${API_URL}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
            credentials: "include"
        });

        const data = await response.json()
        console.log("data:"+data)
        data.forEach(p => {
            p.itemLocal = p.itemLocal.local_id
            products.push(p)
            console.log(p)
        })
    } catch (error) {
        console.log(error)
    }
    renderCards();
}

/* Sidebar */
function toggleSidebar() {
    const sidebar = document.querySelector(".sidebar");
    sidebar.classList.toggle("collapsed");
}

document.addEventListener("DOMContentLoaded", getProducts());
