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
  console.log(token);
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "X-ACCESS-TOKEN": `${token}`,
    },
  });
  console.log(instance);
  return instance;
}

export { apiInstance, apiTokenInstance };
