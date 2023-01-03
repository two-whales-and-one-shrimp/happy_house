import { apiInstance } from ".";

const api = apiInstance();

async function getKeyword(keyword) {
  try {
    let response = await api.get(`/map/${keyword}`);
    if (response.status == 200) {
      let data = response.data;
      //console.log(data);
      return data;
    } else if (response.status == 404) {
      return [];
    }
  } catch {
    return [];
  }
}

async function getGugunCode(keyword) {
  try {
    let response = await api.get(`/map/guguncode/${keyword}`);
    if (response.status == 200) {
      let gugunCode = response.data;
      return gugunCode;
    }
  } catch {
    return;
  }
}

export { getKeyword, getGugunCode };
