// Simulação de dados
let products = [
    { name: "Parafuso", quantity: 120, value: 1.5, status: "ATIVO", minimumQuantity: 50 },
    { name: "Tinta Azul", quantity: 10, value: 80, status: "ATIVO", minimumQuantity: 20 },
    { name: "Madeira MDF", quantity: 30, value: 50, status: "ATIVO", minimumQuantity: 15 }
];

const API_URL = "http://localhost:8080";

async function fetchTotalProdutcs() {
    const response = await fetch(`${API_URL}/products/total`,{
        method: "GET",
        credentials: "include"
    })

    const data = await response.json();
    console.log(data);
    return await data;
}

async function fetchLowStock() {
    const response = await fetch(`${API_URL}/items/lowStock`,{
        method: "GET",
        credentials: "include"
    })

    const data = await response.json();
    console.log(data);
    return await data;
}

async function fetchTotalValue() {
    const response = await fetch(`${API_URL}/items/totalValue`,{
        method: "GET",
        credentials: "include"
    })

    const data = await response.json();
    console.log(data);
    return await data;
}

// MÉTRICAS
async function loadMetrics() {
    const totalProduct = await fetchTotalProdutcs();
    document.getElementById("totalProducts").innerText = totalProduct;

    const low = await fetchLowStock();
    document.getElementById("lowStock").innerText = low;

    const totalValue = await fetchTotalValue();
    document.getElementById("totalValue").innerText = formatCurrency(totalValue);

    const active = products.filter(p => p.status === "ATIVO").length;
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