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
      <trade-search-list
        :tradeList="aptList"
        @changeMapCenterAddress="emitChangeMapCenter"
      ></trade-search-list>
    </v-row>
  </v-container>
</template>

<script>
import { getKeyword } from "@/api/map.js";
import { getAptInfo, kakaoKeywordSearch } from "@/api/apartment";
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
  watch: {
    async keyword(val) {
      this.keywordList = await getKeyword(val);
    },
  },
  methods: {
 // search2 재희 코드, search() 호균 코드
    async search2() {
      const array = await getAptInfo(11110, 201512);
      await this.searchAptListForMap(array);
      this.$emit("setAptList", this.aptList);
      this.emitChangeMapCenter("서울시 종로구");
    },
    async search() {
      this.aptList = await getAptInfo(11110, 201512);
      console.log(this.value);
    },

    async searchAptListForMap(array) {
      const newAptList = [];
      for (const apt of array) {
        const response = await kakaoKeywordSearch(apt.aptName);
        const aptData = response.data.documents[0];
        //keyword 검색 결과가 없으면 aptList에서 삭제
        if (aptData == undefined) {
          continue;
        }

        const fullAddress = aptData["address_name"];

        apt.fullAddress = fullAddress;
        apt.latlngObj = { x: parseFloat(aptData.x), y: parseFloat(aptData.y) };
        apt.toggle = false; //ui toggle을 위한 값
        newAptList.push(apt);
      }
      this.aptList = [...newAptList];
    },
    emitChangeMapCenter(value) {
      this.$emit("changeMapCenterAddress", value);
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
