import { fetchWithAuth } from "./script.js";

const supplierForm = document.getElementById("supplierForm");
const supplierTableBody = document.getElementById("supplierTableBody");
const refreshBtn = document.getElementById("refreshButton");

const suppliers = [];

const API_URL = "http://localhost:8080";

let editingSupplierId;

let currentPage = 0;
let totalPages = 0;
const pageSize = 5;

async function renderSuppliers(page = 0) {
    try {
        const response = await fetchWithAuth(`${API_URL}/suppliers?page=${page}&size=${pageSize}`, {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const data = await response.json();

        currentPage = data.number;
        totalPages = data.totalPages;

        supplierTableBody.innerHTML = "";

        data.content.forEach(supplier => {
            const row = document.createElement("tr");

            row.innerHTML = `
                    <td>${supplier.id}</td>
                    <td>${supplier.name}</td>
                    <td>${supplier.phone}</td>
                    <td>${supplier.email}</td>
                    <td class="actions">
                        <button class="edit" onclick="editSupplier(${supplier.id}, '${supplier.name}', '${supplier.phone}', '${supplier.email}')">✏️</button>
                        <button class="delete" onclick="deleteSupplier(${supplier.id})">🗑️</button>
                    </td>
                `;

            supplierTableBody.appendChild(row);
        });
        updatePaginationUI();
    } catch (error) {
        console.error(error);
    }
}

function updatePaginationUI() {
    document.getElementById("pageInfo").textContent =
        `Página ${currentPage + 1} de ${totalPages}`;

    document.getElementById("prevPageBtn").disabled = currentPage === 0;
    document.getElementById("nextPageBtn").disabled = currentPage >= totalPages - 1;
}


supplierForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;

    const url = editingSupplierId
        ? `${API_URL}/suppliers/${editingSupplierId}`
        : `${API_URL}/suppliers`;

    const method = editingSupplierId ? "PATCH" : "POST";

    await fetchWithAuth(url, {
        method,
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify({ name, email, phone})
    });

    renderSuppliers(currentPage);

    resetForm();

});

/* ---------- EDITAR ---------- */
function editSupplier(id, name, phone, email) {
    editingSupplierId = id;
    document.getElementById("name").value = name;
    document.getElementById("phone").value = phone;
    document.getElementById("email").value = email;

    document.querySelector(".card-form h2").textContent = "Editar Fornecedor";
    document.querySelector(".card-form button").textContent = "Salvar Alterações";
}

/* ---------- DELETAR ---------- */
async function deleteSupplier(id) {
    if (!confirm("Deseja realmente excluir este usuário?")) return;

    await fetchWithAuth(`${API_URL}/suppliers/${id}`, {
        method: "DELETE",
        credentials: "include"
    });

    renderSuppliers(currentPage);
}

/* ---------- RESET ---------- */
function resetForm() {
    editingSupplierId = null;
    supplierForm.reset();
    document.querySelector(".card-form h2").textContent = "Novo Fornecedor";
    document.querySelector(".card-form button").textContent = "Cadastrar Fornecedor";
}

document.getElementById("prevPageBtn").addEventListener("click", () => {
    if (currentPage > 0) {
        renderUsers(currentPage - 1);
    }
});

document.getElementById("nextPageBtn").addEventListener("click", () => {
    if (currentPage < totalPages - 1) {
        renderUsers(currentPage + 1);
    }
});


document.addEventListener("DOMContentLoaded",() => renderSuppliers(currentPage));
refreshBtn.addEventListener("click",() => renderSuppliers(currentPage));
