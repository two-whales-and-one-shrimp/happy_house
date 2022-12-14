import { apiInstance } from ".";

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

export { signIn, signUp, checkId, checkEmail, checkCode };
