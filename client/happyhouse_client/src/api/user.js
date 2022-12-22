import { apiInstance, apiTokenInstance, apiRefreshTokenInstance } from ".";

const api = apiInstance();

async function signIn(userId, userPassword) {
  let response;
  try {
    response = await api.post(`/user/signin`, {
      id: userId,
      password: userPassword,
    });
  } catch (e) {
    response = e.response;
  }
  return response;
}

async function signUp(user, success, fail) {
  await api
    .post(`/user/signup`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function checkId(userId, success, fail) {
  await api.get(`/user/${userId}`).then(success).catch(fail);
}

async function checkEmail(userEmail, success, fail) {
  await api.post(`/user/email`, userEmail).then(success).catch(fail);
}

async function checkCode(userCode, success, fail) {
  await api.post(`/user/code`, userCode).then(success).catch(fail);
}

async function signOut(userId) {
  let response;
  try {
    response = await apiTokenInstance().get(`/user/signout/${userId}`);
  } catch (e) {
    response = e.response;
    console.log(e.response);
  }
  return response;
}

async function getNewAccessToken() {
  let response;
  try {
    response = await apiRefreshTokenInstance(
      localStorage.getItem("accessToken"),
      localStorage.getItem("refreshToken")
    ).get(`/user/refresh`);
  } catch (e) {
    response = e.response;
  }
  return response;
}

async function deleteUser(userId, success, fail) {
  let response;
  try {
    response = await apiTokenInstance().delete(`/user/${userId}`);

    if (response.status === 200) {
      success();
    }
  } catch (e) {
    fail(e);
  }
  return response;
}

export {
  signIn,
  signUp,
  checkId,
  checkEmail,
  checkCode,
  signOut,
  deleteUser,
  getNewAccessToken,
};
