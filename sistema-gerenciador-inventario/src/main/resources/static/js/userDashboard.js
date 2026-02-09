const userForm = document.getElementById("userForm");
const userTableBody = document.getElementById("userTableBody");
const refreshBtn = document.getElementById("refreshUsersBtn");

const users = [];

const API_URL = "http://localhost:8080";

userForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const name = document.getElementById("name").value;
  const password = document.getElementById("password").value;
  const role = document.getElementById("role").value;

  const user = {
    name,
    password, // futuramente: hash no backend
    role
  };

  users.push(user);
  renderUsers();
  userForm.reset();
});

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
            `;

            userTableBody.appendChild(row);
        });

    } catch (error) {
        console.error(error);
    }
}

document.addEventListener("DOMContentLoaded", renderUsers);

refreshBtn.addEventListener("click", renderUsers);
