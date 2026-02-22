import { fetchWithAuth } from "./script.js";

const API_URL = "http://localhost:8080";

// Tratamento de erro robusto para evitar "Unexpected token F"
async function fetchMetrics(url) {
    try {
        const response = await fetchWithAuth(url, {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {
            console.warn(`Servidor retornou erro ${response.status} para ${url}`);
            return 0;
        }

        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            return await response.json();
        } else {
            const text = await response.text();
            return isNaN(text) ? 0 : parseFloat(text);
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        return 0;
    }
}

let mockData = [
    { name: "Parafuso Sextavado", type: "Materia-Prima", location: "Corredor A", quantity: 12, min: 50, status: "CRÍTICO" },
    { name: "Painel Solar 300W", type: "Produto", location: "Setor B", quantity: 85, min: 20, status: "OK" },
    { name: "Tinta Epóxi", type: "Materia-Prima", location: "Almoxarifado", quantity: 5, min: 10, status: "CRÍTICO" }
];

async function initDashboard() {
    await loadMetrics();
    renderAlerts();
    renderTable();
}

async function loadMetrics() {
    const [total, low, value] = await Promise.all([
        fetchMetrics(`${API_URL}/products/total`),
        fetchMetrics(`${API_URL}/items/lowStock`),
        fetchMetrics(`${API_URL}/items/totalValue`),
    ]);

    document.getElementById("totalProducts").innerText = total;
    document.getElementById("lowStock").innerText = low;
    document.getElementById("totalValue").innerText = formatCurrency(value);
    document.getElementById("activeSuppliers").innerText = "---"; // Ajuste conforme seu endpoint de fornecedores
}

function renderAlerts() {
    const container = document.getElementById("alertsList");
    const criticalItems = mockData.filter(i => i.quantity < i.min);
    
    if (criticalItems.length === 0) {
        container.innerHTML = `<p style="color: #666; padding: 10px;">✅ Todos os itens estão com estoque normal.</p>`;
        return;
    }

    container.innerHTML = criticalItems.map(item => `
        <div class="alert-item">
            <span>⚠️ <strong>${item.name}</strong> (${item.location})</span>
            <span>Estoque: <b style="color: var(--danger)">${item.quantity}</b> / Min: ${item.min}</span>
        </div>
    `).join('');
}

function renderTable() {
    const table = document.getElementById("recentTable");
    table.innerHTML = mockData.map(p => `
        <tr>
            <td><strong>${p.name}</strong></td>
            <td>${p.type}</td>
            <td>${p.location}</td>
            <td>${p.quantity}</td>
            <td><span class="status-badge ${p.status.toLowerCase()}" 
                style="padding: 4px 8px; border-radius: 4px; font-size: 12px; background: ${p.status === 'OK' ? '#dcfce7' : '#fee2e2'}; color: ${p.status === 'OK' ? '#166534' : '#991b1b'}">
                ${p.status}</span></td>
        </tr>
    `).join('');
}

function goTo(page) {
    const routes = {
        'products': "/productDashboard",
        'materiaPrima': "/rawMaterialDashboard",
        'fornecedores': "/supplierDashboard"
    };
    if (routes[page]) window.location.href = routes[page];
}

function formatCurrency(value) {
    return (value || 0).toLocaleString("pt-BR", {
        style: "currency",
        currency: "BRL"
    });
}

// Inicialização
initDashboard();
window.goTo = goTo;