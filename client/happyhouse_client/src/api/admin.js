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

async function deleteUser(userId, success, fail) {
  try {
    const response = await api.delete(`/admin/${userId}`);
    if (response.status == 200) {
      success(userId);
    } else throw Error;
  } catch (e) {
    fail(e);
  }
}

async function upgradeUserToAdmin(userId, success, fail) {
  try {
    const response = await api.put(`/admin/${userId}`);
    if (response.status == 200) {
      success(userId);
    } else throw Error;
  } catch (e) {
    fail(e);
  }
}

export { getUserList, deleteUser, upgradeUserToAdmin };
