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
      <trade-search-list :tradeList="dummyList"></trade-search-list>
    </v-row>
  </v-container>
</template>

<script>
import TradeSearchList from "./TradeSearchList";
export default {
  data() {
    return {
      dummyList: [
        {
          aptName: "apt1",
          tradeDate: "2022년 00월 00일",
          area: "00",
          tradePrice: "----원",
          address: "00시 00구 00동",
          addressCode: "00000000",
        },
        {
          aptName: "apt2",
          tradeDate: "2022년 00월 00일",
          area: "00",
          tradePrice: "----원",
          address: "00시 00구 00동",
          addressCode: "00000000",
        },
        {
          aptName: "apt3",
          tradeDate: "2022년 00월 00일",
          area: "00",
          tradePrice: "----원",
          address: "00시 00구 00동",
          addressCode: "00000000",
        },
        {
          aptName: "apt4",
          tradeDate: "2022년 00월 00일",
          area: "00",
          tradePrice: "----원",
          address: "00시 00구 00동",
          addressCode: "00000000",
        },
      ],
      keywordList: [],
      keyword: "",
    };
  },
  components: {
    TradeSearchList,
  },
  methods: {
    search() {
      if (this.keyword !== "") {
        this.keywordList.push(this.keyword);
      }
    },
    addKeyword() {
      this.keywordList.push(this.keyword);
      this.keyword = "";
    },
    deleteKeyword(index) {
      this.keywordList.splice(index, 1);
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
