import * as cheerio from "cheerio";

import axios from "axios";

async function getNews() {
  let data = (await axios.get("https://land.naver.com/news/headline.naver"))
    .data;
  let $ = cheerio.load(data);
  let list = $("ul.headline_list li");
  let newsList = [];
  list.each(function () {
    let news = {};
    news.imgSrc = $(this).find("dt.photo a img").attr("src");
    news.title = $(this).find("dt a").text();
    // 뉴스 설명
    //console.log($(this).find("dd").text());
    // 뉴스 링크
    // console.log(`https://land.naver.com${$(this).find("dt a").attr("href")}`);
    newsList.push(news);
  });

  return newsList;
}

export { getNews };
