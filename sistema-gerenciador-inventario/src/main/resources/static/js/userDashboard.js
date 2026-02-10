const userForm = document.getElementById("userForm");
const userTableBody = document.getElementById("userTableBody");
const refreshBtn = document.getElementById("refreshUsersBtn");

const users = [];

const API_URL = "http://localhost:8080";

userForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    const user = {
        name,
        password,
        role
    };

    const response = await fetch(`${API_URL}/users`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        credentials: "include",
        body: JSON.stringify({
            username: user.name,
            password: user.password,
            roles: user.role
        })
    });

    renderUsers()
    userForm.reset();
});

async function renderUsers() {
    try {
        const tr = userTableBody.children
        let id = 0;
        if (tr.length == 0) {
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
                `;

                userTableBody.appendChild(row);
            });
        }
        else {
            const lastRow = tr.item(tr.length - 1).innerHTML
            const initial = lastRow.search("<td>") + 4
            const final = lastRow.search("</td>")
            id = lastRow.substring(initial,final);
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
            `;

            console.log(row)

            console.log(userTableBody)

            userTableBody.appendChild(row);
        }

    } catch (error) {
        console.error(error);
    }
}
document.addEventListener("DOMContentLoaded", renderUsers);

refreshBtn.addEventListener("click", renderUsers);
