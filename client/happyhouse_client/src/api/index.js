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

function apiTokenInstance(token) {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "X-ACCESS-TOKEN": `${token}`,
    },
  });
  return instance;
}

function apiRefreshTokenInstance(atoken, rtoken) {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "X-ACCESS-TOKEN": `${atoken}`,
      "X-REFRESH-TOKEN": `${rtoken}`,
    },
  });
  return instance;
}

export { apiInstance, apiTokenInstance, apiRefreshTokenInstance };
