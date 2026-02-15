const supplierForm = document.getElementById("supplierForm");
const supplierTableBody = document.getElementById("supplierTableBody");
const refreshBtn = document.getElementById("refreshButton");

const suppliers = [];

const API_URL = "http://localhost:8080";

let editingSupplierId;

async function renderSuppliers() {
    try {
        const response = await fetchWithAuth(`${API_URL}/suppliers`, {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const data = await response.json();

        supplierTableBody.innerHTML = "";

        data.forEach(supplier => {
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

    } catch (error) {
        console.error(error);
    }
}

async function updateSuppliers() {
    try {
        let id = 0;
        tr = supplierTableBody.children
        if(tr.length!=0){
            const lastRow = tr.item(tr.length - 1).innerHTML
            const initial = lastRow.search("<td>") + 4
            const final = lastRow.search("</td>")
            id = lastRow.substring(initial, final);
        }
        id++
        const response = await fetchWithAuth(`${API_URL}/suppliers/${id}`, {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const data = await response.json();
        const supplier = data.supplier;

        const row = document.createElement("tr");

        row.innerHTML = `
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.phone}</td>
                    <td>${data.email}</td>
                    <td class="actions">
                        <button class="edit" onclick="editSupplier(${data.id}, '${data.name}', '${data.phone}', '${data.email}')">✏️</button>
                        <button class="delete" onclick="deleteSupplier(${data.id})">🗑️</button>
                    </td>
            `;

        console.log(row)

        console.log(supplierTableBody)

        supplierTableBody.appendChild(row);
    } catch (error) {
        console.error(error);
    }
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

    if (method == "POST") {
        updateSuppliers();
    }
    else {
        renderSuppliers();
    }

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

    renderSuppliers();
}

/* ---------- RESET ---------- */
function resetForm() {
    editingSupplierId = null;
    supplierForm.reset();
    document.querySelector(".card-form h2").textContent = "Novo Fornecedor";
    document.querySelector(".card-form button").textContent = "Cadastrar Fornecedor";
}

document.addEventListener("DOMContentLoaded", renderSuppliers);
refreshBtn.addEventListener("click", renderSuppliers);
