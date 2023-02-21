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
    ></v-combobox>
  </div>
</template>

<script>
import { getKeyword } from "@/api/map.js";
import { mapMutations, mapActions } from "vuex";

const searchStore = "searchStore";

export default {
  name: "SearchBar",
  data() {
    return {
      value: "",
      keyword: null,
      keywordList: [],
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
  },
  methods: {
    ...mapMutations(searchStore, [
      "SET_SELECTED_YEAR",
      "SET_SELECTED_MONTH",
      "SET_KEYWORD",
    ]),
    ...mapActions(searchStore, ["findGugunCode"]),

    async search() {
      let now = new Date();
      this.SET_SELECTED_YEAR(now.getFullYear());
      this.SET_SELECTED_MONTH(now.getMonth() + 1);
      await this.findGugunCode(this.value);
      this.$router.push({ name: "map", centerAddress: this.keyword });
    },
  },
};
</script>

<style scoped></style>
