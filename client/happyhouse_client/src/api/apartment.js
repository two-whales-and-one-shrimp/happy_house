import axios from "axios";

async function getAptInfo(dongCode, date) {
  let data = (
    await axios.get(
      `http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?serviceKey=${process.env.VUE_APP_SERVICE_KEY}&pageNo=1&numOfRows=100&LAWD_CD=${dongCode}&DEAL_YMD=${date}`
    )
  ).data.response.body.items;
  console.log(data);
  let aptList = [];
  data.item.forEach((item) => {
    let apt = {};
    apt.aptName = item.아파트;
    apt.tradeDate = `${item.년}년 ${item.월}월 ${item.일}일`;
    apt.area = item.전용면적;
    apt.tradePrice = item.거래금액 + "원";
    apt.address = item.법정동;
    apt.addressCode = `${item.법정동시구군코드}${item.법정동읍면동코드}`;
    aptList.push(apt);
  });

  return aptList;
}

async function kakaoKeywordSearch(aptName) {
  const url = "https://dapi.kakao.com/v2/local/search/keyword.json";
  const params = `page=1
  &size=1
  &sort=accuracy
  &query=${aptName}`;
  const response = await axios.get(`${url}?${params}`, {
    headers: {
      Authorization: `${process.env.VUE_APP_KAKAP_KEYWORD_SEARCH_REST_KEY}`,
    },
  });
  return response;
}

export { getAptInfo, kakaoKeywordSearch };
