<template>
  <div class="margin-top">
    <div class="text-lg-h5 mb-5">부동산 관련 뉴스</div>
    <div class="d-flex flex-row justify-space-between">
      <template>
        <div v-for="(news, index) in newsList" :key="index">
          <img :src="news.imgSrc" />
          <div>{{ news.title }}</div>
        </div>
      </template>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewsList",
  data() {
    return {
      newsList: [
        {
          imgSrc: "https://via.placeholder.com/150",
          title: "title1",
        },
        {
          imgSrc: "https://via.placeholder.com/150",
          title: "title2",
        },
        {
          imgSrc: "https://via.placeholder.com/150",
          title: "title3",
        },
        {
          imgSrc: "https://via.placeholder.com/150",
          title: "title4",
        },
      ],
    };
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
</style>
