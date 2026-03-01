
const API_URL = "http://localhost:8080";


function logout() {
    fetch(`${API_URL}/logout`,{
        credentials: "include"
})
    window.location.href = "/";
}

export async function fetchWithAuth(url, options = {}) {

  options.headers = {
    ...options.headers,
    credentials: "include"
  };

  let response = await fetch(url, options);


  if (response.status === 401) {
    let response2 = await fetch(url, options)
    if(response2.status === 401){
        logout();
    };
  }
  if(response.status == 500){
    const json = await response.json();
    console.log(json.message)
    alert(json.message)
  }

  return response;
}

