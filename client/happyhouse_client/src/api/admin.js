import { apiInstance } from "./index.js";

const api = apiInstance();

async function getUserList(success, fail) {
  try {
    const response = await api.get("/admin");
    const list = await response.data;
    if (list) {
      success(list);
    }
  } catch (e) {
    console.log(e);
    fail();
  }
}

export { getUserList };
