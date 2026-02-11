const userForm = document.getElementById("userForm");
const userTableBody = document.getElementById("userTableBody");
const refreshBtn = document.getElementById("refreshUsersBtn");

const users = [];

const API_URL = "http://localhost:8080";

let editingUserId;

async function renderUsers() {
    try {
        const response = await fetch(`${API_URL}/users`, {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const data = await response.json();

        userTableBody.innerHTML = "";

        data.forEach(user => {
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

    } catch (error) {
        console.error(error);
    }
}

async function updateUsers(params) {
    try {
        let id = 0;
        tr = userTableBody.children
        if(tr.length!=0){
            const lastRow = tr.item(tr.length - 1).innerHTML
            const initial = lastRow.search("<td>") + 4
            const final = lastRow.search("</td>")
            id = lastRow.substring(initial, final);
        }
        id++
        const response = await fetch(`${API_URL}/users/${id}`, {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const data = await response.json();
        const user = data.user;

        const row = document.createElement("tr");

        row.innerHTML = `
                    <td>${data.id}</td>
                    <td>${data.username}</td>
                    <td>${data.roles}</td>
                    <td class="actions">
                        <button class="edit" onclick="editUser(${data.id}, '${data.username}', '${data.roles}')">✏️</button>
                        <button class="delete" onclick="deleteUser(${data.id})">🗑️</button>
                    </td>
            `;

        console.log(row)

        console.log(userTableBody)

        userTableBody.appendChild(row);
    } catch (error) {
        console.error(error);
    }
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

    await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify({ username, password, roles })
    });

    if (method == "POST") {
        updateUsers();
    }
    else {
        renderUsers();
    }

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

    await fetch(`${API_URL}/users/${id}`, {
        method: "DELETE",
        credentials: "include"
    });

    renderUsers();
}

/* ---------- RESET ---------- */
function resetForm() {
    editingUserId = null;
    userForm.reset();
    document.querySelector(".cardForm h2").textContent = "Novo Usuário";
    document.querySelector(".cardForm button").textContent = "Cadastrar Usuário";
}

document.addEventListener("DOMContentLoaded", renderUsers);
refreshBtn.addEventListener("click", renderUsers);
