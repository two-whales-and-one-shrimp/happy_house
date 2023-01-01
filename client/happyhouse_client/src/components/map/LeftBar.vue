<template>
  <v-container>
    <v-row>
      <v-combobox
        outlined
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
    </v-row>
    <v-row>
      <trade-search-list :tradeList="aptList"></trade-search-list>
    </v-row>
  </v-container>
</template>

<script>
import { getKeyword } from "@/api/map.js";
import { getAptInfo } from "@/api/apartment";
import TradeSearchList from "./TradeSearchList";
export default {
  data() {
    return {
      aptList: [],
      value: "",
      keyword: null,
      keywordList: [],
    };
  },
  components: {
    TradeSearchList,
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
    async search() {
      this.aptList = await getAptInfo(11110, 201512);
      console.log(this.value);
    },
  },
};
</script>

<style scoped>
.container {
  position: fixed;
  left: 0;
  width: 400px;
  height: 100vh;
}

.search-bar {
  border: 1px solid #9e9e9e;
  border-left: none;
  border-right: none;
  width: 100%;
}
.input {
  font-size: 1.3rem;
  width: 75%;
  border: none;
}
.input:focus {
  outline: none;
}

.full-width {
  width: 100%;
}
</style>
