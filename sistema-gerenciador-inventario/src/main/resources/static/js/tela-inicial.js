import { fetchWithAuth } from "./script.js";
// Simulação de dados
let products = [
    { name: "Parafuso", quantity: 120, value: 1.5, status: "ATIVO", minimumQuantity: 50 },
    { name: "Tinta Azul", quantity: 10, value: 80, status: "ATIVO", minimumQuantity: 20 },
    { name: "Madeira MDF", quantity: 30, value: 50, status: "ATIVO", minimumQuantity: 15 }
];

const API_URL = "http://localhost:8080";

async function fetchMetrics(url) {
    const response = await fetchWithAuth(url,{
        method: "GET",
        credentials: "include"
    })

    const data = await response.json();
    console.log(data);
    return await data;
}

// MÉTRICAS
async function loadMetrics() {
    const totalProduct = await fetchMetrics(`${API_URL}/products/total`);
    document.getElementById("totalProducts").innerText = totalProduct;

    const low = await fetchMetrics(`${API_URL}/items/lowStock`);
    document.getElementById("lowStock").innerText = low;

    const totalValue = await fetchMetrics(`${API_URL}/items/totalValue`);
    document.getElementById("totalValue").innerText = formatCurrency(totalValue);

    const active = await fetchMetrics(`${API_URL}/items/activeItems`);
    document.getElementById("activeItems").innerText = active;
}

// TABELA RECENTES
function loadRecent() {
    const table = document.getElementById("recentTable");
    table.innerHTML = "";

    products.slice(-5).forEach(p => {
        table.innerHTML += `
            <tr>
                <td>${p.name}</td>
                <td>${p.quantity}</td>
                <td>R$ ${p.value}</td>
                <td>${p.status}</td>
            </tr>
        `;
    });
}

// AÇÕES RÁPIDAS
function goTo(page) {
    if (page === "products") window.location.href = "/productDashboard";
    if (page === "materiaPrima") window.location.href = "/rawMaterialDashboard";
    if (page === "fornecedores") window.location.href = "/supplierDashboard";
}

function formatCurrency(value) {
    return value.toLocaleString("pt-BR", {
        style: "currency",
        currency: "BRL"
    });
}

// INIT
loadMetrics();
loadRecent();

window.goTo = goTo;