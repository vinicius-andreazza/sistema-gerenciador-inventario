import { fetchWithAuth } from "./script.js";

const API_URL = "http://localhost:8080";

let allItems = [];

async function fetchMetrics(url) {
    try {
        const response = await fetchWithAuth(url, { method: "GET", credentials: "include" });
        if (!response.ok) return 0;
        
        const data = await response.json();
        return data.content !== undefined ? data.content : data;
    } catch (error) {
        console.error("Erro na requisição:", error);
        return 0;
    }
}

async function initDashboard() {
    await loadMetrics();
    await loadLowStockData();
    setupSearch();
}

async function loadLowStockData() {
    const data = await fetchMetrics(`${API_URL}/items/lowStock?size=5&sort=quantity,asc`);
    
    allItems = Array.isArray(data) ? data : [];
    
    renderAlerts(allItems);
    renderTable(allItems);
}

function renderAlerts(items) {
    const container = document.getElementById("alertsList");
    if (!items || items.length === 0) {
        container.innerHTML = `<div class="alert-item" style="border-left-color: var(--success); background: #f0fdf4;">
            <span>✅ Estoque em dia! Nenhum item abaixo do mínimo.</span>
        </div>`;
        return;
    }

    container.innerHTML = items.map(item => `
        <div class="alert-item">
            <span>⚠️ <strong>${item.name}</strong></span>
            <span>Estoque: <b style="color: var(--danger)">${item.quantity}</b> / Min: ${item.minimiumQuantity || '---'}</span>
        </div>
    `).join('');
}

function renderTable(items) {
    const table = document.getElementById("recentTable");
    if (!items || items.length === 0) {
        table.innerHTML = `<tr><td colspan="5" style="text-align:center">Nenhum dado encontrado</td></tr>`;
        return;
    }

    table.innerHTML = items.map(item => `
        <tr>
            <td><strong>${item.name}</strong></td>
            <td>${item.type || 'Item'}</td>
            <td>${item.locationName || 'Geral'}</td>
            <td>${item.quantity}</td>
            <td><span class="status-badge crítico">ABAIXO DO MÍN</span></td>
        </tr>
    `).join('');
}

function setupSearch() {
    const searchInput = document.getElementById("searchInput");
    searchInput.addEventListener("input", (e) => {
        const term = e.target.value.toLowerCase();
        const filtered = allItems.filter(item => 
            item.name.toLowerCase().includes(term) || 
            (item.locationName && item.locationName.toLowerCase().includes(term))
        );
        renderTable(filtered);
    });
}

async function loadMetrics() {
    const [total, low, value, suppliers] = await Promise.all([
        fetchMetrics(`${API_URL}/products/total`),
        fetchMetrics(`${API_URL}/items/quantityInLowStock`),
        fetchMetrics(`${API_URL}/items/totalValue`),
        fetchMetrics(`${API_URL}/suppliers/count`)
    ]);

    document.getElementById("totalProducts").innerText = total;
    document.getElementById("lowStock").innerText = low;
    document.getElementById("totalValue").innerText = formatCurrency(value);
    document.getElementById("activeSuppliers").innerText = suppliers;
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

initDashboard();
window.goTo = goTo;