import { fetchWithAuth } from "./script.js";

const stockForm = document.getElementById("stockForm");
const stockTableBody = document.getElementById("stockTableBody");
const refreshBtn = document.getElementById("refreshButton");
const searchInput = document.getElementById("searchInput");

const API_URL = "http://localhost:8080/locals";

let editingStockId = null;
let currentPage = 0;
let totalPages = 0;
const pageSize = 5;

async function renderStock(page = 0, query = "") {
    try {
        const url = `${API_URL}?page=${page}&size=${pageSize}&search=${query}`;
        const response = await fetchWithAuth(url, {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) throw new Error("Erro ao buscar dados de estoque");

        const data = await response.json();
        console.log(data)
        currentPage = data.number;
        totalPages = data.totalPages;

        stockTableBody.innerHTML = "";

        data.content.forEach(item => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${item.id}</td>
                <td>${item.sectorName}</td>
                <td>${item.shelf}</td>
                <td>${item.position}</td>
                <td class="actions">
                    <button class="edit" onclick="editStock(${item.id}, '${item.sectorName}', '${item.shelf}', ${item.position})">✏️</button>
                    <button class="delete" onclick="deleteStock(${item.id})">🗑️</button>
                </td>
            `;
            stockTableBody.appendChild(row);
        });
        updatePaginationUI();
    } catch (error) {
        console.error("Erro:", error);
    }
}

function updatePaginationUI() {
    document.getElementById("pageInfo").textContent =
        `Página ${currentPage + 1} de ${totalPages}`;

    document.getElementById("prevPageBtn").disabled = currentPage === 0;
    document.getElementById("nextPageBtn").disabled = currentPage >= totalPages - 1;
}

searchInput.addEventListener("input", (e) => {
    renderStock(0, e.target.value);
});

stockForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const sector = document.getElementById("sector").value;
    const packageVal = document.getElementById("package").value;
    const capacity = document.getElementById("capacity").value;

    const url = editingStockId ? `${API_URL}/${editingStockId}` : API_URL;
    const method = editingStockId ? "PATCH" : "POST";

    await fetchWithAuth(url, {
        method,
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify({ sector, package: packageVal, capacity })
    });

    renderStock(currentPage);
    resetForm();
});

document.getElementById("prevPageBtn").addEventListener("click", () => {
    if (currentPage > 0) {
        renderStock(currentPage - 1);
    }
});

document.getElementById("nextPageBtn").addEventListener("click", () => {
    if (currentPage < totalPages - 1) {
        renderStock(currentPage + 1);
    }
});

window.editStock = function(id, sector, packageVal, capacity) {
    editingStockId = id;
    document.getElementById("sector").value = sector;
    document.getElementById("package").value = packageVal;
    document.getElementById("capacity").value = capacity;

    document.querySelector(".card-form h2").textContent = "Editar Local";
    document.querySelector(".card-form button").textContent = "Salvar Alterações";
}

window.deleteStock = async function(id) {
    if (!confirm("Excluir este local de estoque?")) return;
    await fetchWithAuth(`${API_URL}/${id}`, { method: "DELETE", credentials: "include" });
    renderStock(currentPage);
}

function resetForm() {
    editingStockId = null;
    stockForm.reset();
    document.querySelector(".card-form h2").textContent = "Novo Local de Estoque";
    document.querySelector(".card-form button").textContent = "Cadastrar Local";
}


document.addEventListener("DOMContentLoaded", () => renderStock(0));
refreshBtn.addEventListener("click", () => renderStock(currentPage));