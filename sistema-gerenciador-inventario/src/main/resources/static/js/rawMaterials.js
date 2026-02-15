let rawMaterials = [];
let selectedIndex = null;

const API_URL = "http://localhost:8080/rawMaterials";

/* Render Cards */
function renderCards() {
    const container = document.getElementById("cardsContainer");
    container.innerHTML = "";

    rawMaterials.forEach((p, index) => {
        container.innerHTML += `
            <div class="card" onclick="openModal(${index})">
                <h3>${p.name}</h3>
                <p><strong>Qtd:</strong> ${p.quantity}</p>
                <p><strong>Valor:</strong> R$ ${p.unitValue}</p>
                <p><strong>Status:</strong> ${p.status}</p>
            </div>
        `;
    });
}

/* Abrir Modal Visualização */
function openModal(index) {
    selectedIndex = index;
    const rawMaterial = rawMaterials[index];

    document.getElementById("modalTitle").innerText = rawMaterial.name;
    const body = document.getElementById("modalBody");
    body.innerHTML = "";

    Object.keys(rawMaterial).forEach(key => {
        body.innerHTML += `
            <input value="${rawMaterial[key]}" id="edit_${key}" disabled>
        `;
    });

    document.getElementById("itemModal").style.display = "flex";
}

function closeModal() {
    document.getElementById("itemModal").style.display = "none";
}

/* Editar */
function enableEdit() {
    document.querySelectorAll("#modalBody input")
        .forEach(input => input.disabled = false);
}

async function saveEdit() {
    const rawMaterial = rawMaterials[selectedIndex];

    Object.keys(rawMaterial).forEach(key => {
        rawMaterial[key] = document.getElementById("edit_" + key).value;
    });

     try {
        const response = await fetchWithAuth(`${API_URL}/${selectedIndex}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify({ rawMaterial })
        });

        if (response.ok) {
            console.log("Materia Prima atualizado com sucesso")
        }
    } catch (error) {
        console.log(error)
    }

    renderCards();
    closeModal();
}

/* Excluir */
async function deleteRawMaterial() {
    rawMaterials.splice(selectedIndex, 1);

    try {
        const response = await fetchWithAuth(`${API_URL}/${selectedIndex}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            credentials: "include"
        });

        if (response.ok) {
            console.log("Materia Prima deletado com sucesso")
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

    const rawMaterial = {
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
        batch: c_batch.value,
        unitValue: c_unitValue.value,
        suppliers: c_suppliers.value
    };
    try {
        const response = await fetchWithAuth(`${API_URL}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify({ rawMaterial })
        });

        if (response.ok) {
            console.log("Materia Prima criado com sucesso")
        }
    } catch (error) {
        console.log(error)
    }

    rawMaterials.push(rawMaterial);
    renderCards();
    closeCreateModal();
    this.reset();
});

async function getRawMaterials() {
    try {
        const response = await fetchWithAuth(`${API_URL}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
            credentials: "include"
        });

        const data = await response.json()
        console.log("data:"+data)
        data.forEach(p => {
            p.itemLocal = p.itemLocal.local_id
            rawMaterials.push(p)
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

document.addEventListener("DOMContentLoaded", getRawMaterials());
