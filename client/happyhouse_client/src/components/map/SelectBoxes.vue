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
            @change="selectMonth"
          ></v-select>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import { mapMutations, mapGetters } from "vuex";

const searchStore = "searchStore";

export default {
  data() {
    return {
      yearList: [],
      selectedYear: null,
      monthList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
      selectedMonth: null,
    };
  },
  created() {
    const date = new Date();
    this.nowYear = date.getFullYear();
    this.yearList = Array.from({ length: 20 }, (_, i) => {
      return this.nowYear - i;
    });
    this.selectedYear = this.getSelectedYear;
    this.selectedMonth = this.getSelectedMonth;
  },
  computed: {
    ...mapGetters(searchStore, ["getSelectedYear", "getSelectedMonth"]),
  },
  methods: {
    ...mapMutations(searchStore, ["SET_SELECTED_YEAR", "SET_SELECTED_MONTH"]),
    selectYear() {
      this.SET_SELECTED_YEAR(this.selectedYear);
    },
    selectMonth() {
      this.SET_SELECTED_MONTH(this.selectedMonth);
    },
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
