<template>
  <div class="container">
    <v-container class="width">
      <v-row>
        <v-col>
          <v-select
            v-model="selectedYear"
            :items="yearList"
            label="년도 선택"
            solo
            @change="selectYear"
          ></v-select>
        </v-col>
        <v-col>
          <v-select
            v-model="selectedMonth"
            :items="monthList"
            label="월 선택"
            solo
          ></v-select>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      yearList: [],
      selectedYear: null,
      monthList: [1, 2, 3, 4, 5, 6, 7, 8, 9],
      selectedMonth: null,
    };
  },
  created() {
    const date = new Date();
    this.nowYear = date.getFullYear();
    this.yearList = Array.from({ length: 20 }, (_, i) => {
      return this.nowYear - i;
    });
  },
  methods: {
    selectYear() {},
    selectMonth() {},
  },
};
</script>

<style scoped>
.container {
  position: fixed;
  z-index: 10;
}
.width {
  width: 20%;
}
.inline {
  display: inline;
}
</style>
