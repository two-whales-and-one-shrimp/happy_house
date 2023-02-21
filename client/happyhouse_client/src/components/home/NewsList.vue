<template>
  <div class="margin-top">
    <div class="text-lg-h5 mb-5">부동산 관련 뉴스</div>
    <div class="d-flex flex-row justify-space-between">
      <template>
        <div
          class="news-container"
          v-for="(news, index) in newsList"
          :key="index"
          @click="changeToNews(news.link)"
        >
          <img :src="news.imgSrc" />
          <div class="d-flex flex-wrap title-box">{{ news.title }}</div>
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import { getNews } from "@/api/news.js";

export default {
  name: "NewsList",
  data() {
    return {
      newsList: [],
    };
  },

  async created() {
    this.newsList = await getNews();
  },

  methods: {
    changeToNews(src) {
      window.open(src, "_blanck");
    },
  },
};
</script>

<style scoped>
.margin-top {
  margin-top: 6rem;
}

img {
  width: 150px;
  height: 150px;
}

.title-box {
  width: 150px;
}

.news-container {
  cursor: pointer;
}
</style>
