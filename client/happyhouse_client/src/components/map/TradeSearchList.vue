<template>
  <v-list accordion class="overflow-auto full-width" max-height="80vh">
    <v-list-item v-for="(tradeItem, index) in tradeList" :key="index">
      <v-card elevation="0" class="card full-width">
        <v-card-title
          class="text-lg-h6 class=pb-0 can-click"
          @click="
            () => {
              tradeItem.toggle = !tradeItem.toggle;
              if (tradeItem.toggle) {
                //panel이 열릴 경우만 지도 중앙 바꿈
                clickApt({
                  x: tradeItem.latlngObj.x,
                  y: tradeItem.latlngObj.y,
                });
              }
            }
          "
        >
          {{ tradeItem.aptName }}
          <v-spacer></v-spacer>
          <span><v-icon>mdi-chevron-down</v-icon></span>
        </v-card-title>
        <v-card-subtitle v-if="tradeItem.toggle">
          <v-container>
            <v-row>
              <v-col>거래일자</v-col>
              <v-col>{{ tradeItem.tradeDate }}</v-col>
            </v-row>
            <v-row>
              <v-col>면적</v-col>
              <v-col>
                <span>{{ tradeItem.area }} </span>m<sup>2</sup></v-col
              >
            </v-row>
            <v-row>
              <v-col>거래금액</v-col>
              <v-col>{{ tradeItem.tradePrice }}</v-col>
            </v-row>
            <v-row>
              <v-col>주소</v-col>
              <v-col>{{ tradeItem.address }}</v-col>
            </v-row>
          </v-container>
        </v-card-subtitle>
      </v-card>
    </v-list-item>
  </v-list>
</template>

<script>
export default {
  props: {
    tradeList: Array,
  },
  data() {
    return {};
  },
  methods: {
    clickApt(latlng) {
      this.$emit("changeMapCenterAddress", latlng);
    },
  },
};
</script>

<style scoped>
.card {
  border-bottom: 1px solid #9e9e9e;
}
.full-width {
  width: 100%;
}

.can-click:hover {
  cursor: pointer;
}
</style>
