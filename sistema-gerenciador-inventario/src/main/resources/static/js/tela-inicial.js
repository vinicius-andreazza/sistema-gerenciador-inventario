// Simulação de dados
let products = [
    { name: "Parafuso", quantity: 120, value: 1.5, status: "ATIVO", minimumQuantity: 50 },
    { name: "Tinta Azul", quantity: 10, value: 80, status: "ATIVO", minimumQuantity: 20 },
    { name: "Madeira MDF", quantity: 30, value: 50, status: "ATIVO", minimumQuantity: 15 }
];

// MÉTRICAS
function loadMetrics() {
    document.getElementById("totalProducts").innerText = products.length;

    const low = products.filter(p => p.quantity <= p.minimumQuantity).length;
    document.getElementById("lowStock").innerText = low;

    const totalValue = products.reduce((sum, p) => sum + (p.quantity * p.value), 0);
    document.getElementById("totalValue").innerText = "R$ " + totalValue.toFixed(2);

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

// INIT
loadMetrics();
loadRecent();