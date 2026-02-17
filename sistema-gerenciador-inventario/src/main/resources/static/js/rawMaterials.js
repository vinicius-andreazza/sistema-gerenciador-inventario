import { fetchWithAuth } from "./script.js";

let rawMaterials = [];
let selectedIndex = null;

const API_URL = "http://localhost:8080/rawMaterials";

let currentPage = 0;
let totalPages = 0;
const pageSize = 15;

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
            <div class="field-group">
                <label for="edit_${key}">${formatLabel(key)}</label>
                <input value="${rawMaterial[key]}" id="edit_${key}" disabled>
            </div>
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
        const response = await fetchWithAuth(`${API_URL}/${rawMaterial.id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify(rawMaterial)
        });

        if (response.ok) {
            console.log("Matéria-prima atualizada com sucesso");
        }
    } catch (error) {
        console.log(error);
    }

    getRawMaterials(currentPage);
    closeModal();
}

/* Excluir */
async function deleteRawMaterial() {
    const rawMaterial = rawMaterials[selectedIndex];

    try {
        const response = await fetchWithAuth(`${API_URL}/${rawMaterial.id}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            credentials: "include"
        });

        if (response.ok) {
            console.log("Matéria-prima deletada com sucesso");
        }
    } catch (error) {
        console.log(error);
    }

    getRawMaterials(currentPage);
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
        typeItem: "MATERIA_PRIMA",
        description: c_description.value,
        quantity: c_quantity.value,
        minimumQuantity: c_minimumQuantity.value,
        measure: c_measure.value,
        status: c_status.value,
        userId: 1,
        itemLocalId: c_itemLocal.value,
        batch: c_batch.value,
        unitValue: c_unitValue.value,
        suppliers: c_suppliers.value
    };

    try {
        const response = await fetchWithAuth(`${API_URL}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify(rawMaterial)
        });

        if (response.ok) {
            console.log("Matéria-prima criada com sucesso");
        }
    } catch (error) {
        console.log(error);
    }

    getRawMaterials(currentPage);
    closeCreateModal();
    this.reset();
});

/* GET com paginação */
async function getRawMaterials(page = 0) {
    try {
        const response = await fetchWithAuth(`${API_URL}?page=${page}&size=${pageSize}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
            credentials: "include"
        });

        const data = await response.json();

        rawMaterials = [];

        currentPage = data.number;
        totalPages = data.totalPages;

        data.content.forEach(p => {
            p.itemLocal = p.itemLocal.local_id;
            rawMaterials.push(p);
        });

        renderCards();
        updatePaginationUI();
    } catch (error) {
        console.log(error);
    }
}

function updatePaginationUI() {
    document.getElementById("pageInfo").textContent =
        `Página ${currentPage + 1} de ${totalPages}`;

    document.getElementById("prevPageBtn").disabled = currentPage === 0;
    document.getElementById("nextPageBtn").disabled = currentPage >= totalPages - 1;
}

/* Paginação botões */
document.getElementById("prevPageBtn").addEventListener("click", () => {
    if (currentPage > 0) {
        getRawMaterials(currentPage - 1);
    }
});

document.getElementById("nextPageBtn").addEventListener("click", () => {
    if (currentPage < totalPages - 1) {
        getRawMaterials(currentPage + 1);
    }
});

/* Sidebar */
function toggleSidebar() {
    const sidebar = document.querySelector(".sidebar");
    sidebar.classList.toggle("collapsed");
}

/* Helpers */
function formatLabel(text) {
    return text
        .replace(/([A-Z])/g, ' $1')
        .replace(/^./, str => str.toUpperCase());
}

/* Init */
document.addEventListener("DOMContentLoaded", () => getRawMaterials(0));

/* Expor funções */
window.openCreateModal = openCreateModal;
window.closeCreateModal = closeCreateModal;
window.closeModal = closeModal;
window.openModal = openModal;
window.enableEdit = enableEdit;
window.saveEdit = saveEdit;
window.deleteRawMaterial = deleteRawMaterial;
