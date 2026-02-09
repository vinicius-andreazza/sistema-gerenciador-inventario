const token = getCookie("tokenJWT");

fetch("http://localhost:8080/", {
    headers: {
        "Authorization": `Bearer ${token}`
    },
    credentials: "include"
});

async function refreshToken() {
    const refreshToken = getCookie("refreshToken");

    const response = await fetch(`${API_URL}/refresh`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        credentials: "include",
        body: JSON.stringify({ refreshToken })
    });

    if (response.ok) {
        const data = await response.json();
        setCookie("token", data.tokenJWT, 1);
    }
}

function logout() {
    deleteCookie("token");
    deleteCookie("refreshToken");
    window.location.href = "index.html";
}

