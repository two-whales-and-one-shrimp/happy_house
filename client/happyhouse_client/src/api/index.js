import axios from "axios";

function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function apiTokenInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "X-ACCESS-TOKEN": `${localStorage.getItem("accessToken")}`,
    },
  });
  return instance;
}

function apiRefreshTokenInstance(atoken, rtoken) {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      Origin: "http://localhost:8080",
      "Content-Type": "application/json;charset=utf-8",
      "X-ACCESS-TOKEN": `${atoken}`,
      "X-REFRESH-TOKEN": `${rtoken}`,
    },
  });
  return instance;
}

export { apiInstance, apiTokenInstance, apiRefreshTokenInstance };
