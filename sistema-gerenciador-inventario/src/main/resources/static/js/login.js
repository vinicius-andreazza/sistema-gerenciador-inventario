const form = document.getElementById("loginForm");
const errorMessage = document.getElementById("errorMessage");

const API_URL = "http://localhost:8080";

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    console.log(password)
    if (!username || !password) {
        errorMessage.textContent = "Preencha todos os campos.";
        return;
    }

    try {
        const response = await fetch(`${API_URL}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        if (!response.ok) {
            throw new Error("Usuário ou senha inválidos");
        }

        const data = await response.json();

        setCookie("tokenJWT", data.tokenJWT, 1);
        setCookie("refreshToken", data.refreshToken, 7);

        window.location.href = "dashboard.html";

    } catch (error) {
        errorMessage.textContent = error.message;
    }
});
function setCookie(name, value, days) {
    const expires = new Date();
    expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);

    document.cookie = `${name}=${value}; expires=${expires.toUTCString()}; path=/; SameSite=Lax`;
}

function getCookie(name) {
    const cookies = document.cookie.split("; ");
    for (let cookie of cookies) {
        const [key, value] = cookie.split("=");
        if (key === name) {
            return value;
        }
    }
    return null;
}

function deleteCookie(name) {
    document.cookie = `${name}=; Max-Age=0; path=/`;
}
