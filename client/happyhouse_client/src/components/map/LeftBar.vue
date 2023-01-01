<template>
  <v-container>
    <v-row>
      <div class="search-bar py-4 pl-4 d-flex flex-column">
        <div class="d-flex flex-row flex-wrap">
          <span>
            <v-chip
              v-for="(keyword, index) in keywordList"
              :key="index"
              class="mr-2"
              close
              @click:close="deleteKeyword(index)"
            >
              {{ keyword }}
            </v-chip>
          </span>
          <div class="d-flex flex-row full-width">
            <input
              v-model="keyword"
              class="ml-3 input"
              type="text"
              @keydown.enter="addKeyword"
            />
            <v-btn text class="mr-3" @click="search">
              <v-icon>mdi-magnify</v-icon>
            </v-btn>
          </div>
        </div>
      </div>
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
import { getAptInfo, kakaoKeywordSearch } from "@/api/apartment";
import TradeSearchList from "./TradeSearchList";
export default {
  data() {
    return {
      aptList: [],
      keywordList: [],
      keyword: "",
    };
  },
  components: {
    TradeSearchList,
  },
  methods: {
    async search() {
      const array = await getAptInfo(11110, 201512);
      await this.searchAptListForMap(array);
      this.$emit("setAptList", this.aptList);
      this.emitChangeMapCenter("서울시 종로구");
    },
    addKeyword() {
      this.keywordList.push(this.keyword);
      this.keyword = "";
    },
    deleteKeyword(index) {
      this.keywordList.splice(index, 1);
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
