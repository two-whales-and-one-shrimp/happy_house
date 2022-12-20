import { apiTokenInstance } from "./index.js";

const apiToken = apiTokenInstance();

async function getUserList(success, fail) {
  try {
    const response = await apiToken.get("/admin");
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
    const response = await apiToken.delete(`/admin/${userId}`);
    if (response.status == 200) {
      success(userId);
    } else throw Error;
  } catch (e) {
    fail(e);
  }
}

async function upgradeUserToAdmin(userId, success, fail) {
  try {
    const response = await apiToken.put(`/admin/${userId}`);
    if (response.status == 200) {
      success(userId);
    } else throw Error;
  } catch (e) {
    fail(e);
  }
}

export { getUserList, deleteUser, upgradeUserToAdmin };
