import { fetchWithAuth } from "./script.js";

const userForm = document.getElementById("userForm");
const userTableBody = document.getElementById("userTableBody");
const refreshBtn = document.getElementById("refreshUsersBtn");

const users = [];

const API_URL = "http://localhost:8080";

let editingUserId;

let currentPage = 0;
let totalPages = 0;
const pageSize = 5;

async function renderUsers(page = 0) {
    try {
        const response = await fetchWithAuth(`${API_URL}/users?page=${page}&size=${pageSize}`,{
            method: "GET",
            credentials: "include"
        })

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const data = await response.json();

        currentPage = data.number;
        totalPages = data.totalPages;

        userTableBody.innerHTML = "";

        data.content.forEach(user => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.roles}</td>
                <td class="actions">
                    <button class="edit" onclick="editUser(${user.id}, '${user.username}', '${user.roles}')">✏️</button>
                    <button class="delete" onclick="deleteUser(${user.id})">🗑️</button>
                </td>
            `;

            userTableBody.appendChild(row);
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


userForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("name").value;
    const password = document.getElementById("password").value;
    const roles = document.getElementById("role").value;

    const url = editingUserId
        ? `${API_URL}/users/${editingUserId}`
        : `${API_URL}/users`;

    const method = editingUserId ? "PATCH" : "POST";

    await fetchWithAuth(url, {
        method,
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify({ username, password, roles })
    });

    renderUsers(currentPage);

    resetForm();

});

/* ---------- EDITAR ---------- */
function editUser(id, username, roles) {
    editingUserId = id;
    document.getElementById("name").value = username;
    document.getElementById("role").value = roles;

    document.querySelector(".cardForm h2").textContent = "Editar Usuário";
    document.querySelector(".cardForm button").textContent = "Salvar Alterações";
}

/* ---------- DELETAR ---------- */
async function deleteUser(id) {
    if (!confirm("Deseja realmente excluir este usuário?")) return;

    await fetchWithAuth(`${API_URL}/users/${id}`, {
        method: "DELETE",
        credentials: "include"
    });

    renderUsers(currentPage);
}

/* ---------- RESET ---------- */
function resetForm() {
    editingUserId = null;
    userForm.reset();
    document.querySelector(".cardForm h2").textContent = "Novo Usuário";
    document.querySelector(".cardForm button").textContent = "Cadastrar Usuário";
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


document.addEventListener("DOMContentLoaded", renderUsers(currentPage));
refreshBtn.addEventListener("click", () => renderUsers(currentPage));
