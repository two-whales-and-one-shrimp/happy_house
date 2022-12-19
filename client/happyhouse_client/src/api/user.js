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

async function signOut(userId) {
  let response;
  try {
    response = await apiTokenInstance(localStorage.getItem("accessToken")).get(
      `/user/signout/${userId}`
    );
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

export { signIn, signOut, getNewAccessToken };
