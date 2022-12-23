<template>
  <v-expansion-panels accordion>
    <v-expansion-panel v-for="(tradeItem, index) in tradeList" :key="index">
      <v-expansion-panel-header class="text-lg-h6">
        {{ tradeItem.aptName }}
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-container>
          <v-row>
            <v-col>거래일자</v-col>
            <v-col>{{ tradeItem.tradeDate }}</v-col>
          </v-row>
          <v-row>
            <v-col>면적</v-col>
            <v-col
              ><span>{{ tradeItem.area }}</span
              >m<sup>2</sup></v-col
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
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
export default {
  props: {
    tradeList: Array,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
