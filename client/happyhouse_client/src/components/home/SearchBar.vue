<template>
  <div>
    <div class="mb-8 text-lg-h5">어느 지역 거래 정보를 찾으시나요?</div>
    <v-combobox
      solo
      class="text-md-body-1"
      height="70"
      placeholder="지역과 년도, 월을 입력하세요."
      v-model="value"
      :items="getKeywordList"
      :search-input.sync="keyword"
      hide-no-data
      return-object
      @keyup.enter="search"
      @keyup.delete="update"
    ></v-combobox>
  </div>
</template>

<script>
import { getKeyword } from "@/api/map.js";
export default {
  name: "SearchBar",
  data() {
    return {
      value: "",
      keyword: null,
      keywordList: [],
      canSearch: false,
    };
  },
  computed: {
    getKeywordList() {
      return this.keywordList;
    },
  },
  watch: {
    async keyword(val) {
      this.keywordList = await getKeyword(val);
    },
    // search() {
    //   console.log("검색");
    // },
  },
  methods: {
    search() {
      if (this.canSearch) {
        console.log("페이지 변경");
      }
      this.canSearch = !this.canSearch;
      console.log(this.value);
    },

    update() {
      this.canSearch = false;
    },
  },
};
</script>

<style scoped></style>
