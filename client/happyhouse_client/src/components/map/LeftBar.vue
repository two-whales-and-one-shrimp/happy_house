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
import { getAptInfo, kakaoAddressSearch } from "@/api/apartment";
import { mapGetters, mapActions } from "vuex";
import TradeSearchList from "./TradeSearchList";

const searchStore = "searchStore";

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

  async created() {
    this.value = this.getKeyword;
    await this.setList();
  },

  computed: {
    ...mapGetters(searchStore, [
      "getSelectedYear",
      "getSelectedMonth",
      "getGugunCode",
      "getKeyword",
    ]),
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
    ...mapActions(searchStore, ["findGugunCode"]),

    async search() {
      await this.findGugunCode(this.value);
      await this.setList();
    },

    async setList() {
      let date =
        "" + this.getSelectedYear + ("0" + this.getSelectedMonth).slice(-2);
      console.log(this.getGugunCode + " " + date);
      const array = await getAptInfo(this.getGugunCode, date);
      await this.searchAptListForMap(array);
      this.$emit("setAptList", this.aptList);
      this.emitChangeMapCenter(this.getKeyword);
    },

    async searchAptListForMap(array) {
      const newAptList = [];
      for (const apt of array) {
        /*
         * keyword 검색을 하면 같은 아파트 이름의 다른 지역이 나온다.
         * 좌표 검색을 그래서 주소 기반 검색으로 변경했다.
         */
        const response = await kakaoAddressSearch(
          this.getKeyword + apt.address
        );
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
