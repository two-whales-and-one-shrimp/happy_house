import { getGugunCode } from "@/api/map";

const searchStore = {
  namespaced: true,
  state: {
    keyword: "",
    gugunCode: "",
    selectedYear: null,
    selectedMonth: null,
  },
  getters: {
    getKeyword: (state) => {
      return state.keyword;
    },
    getGugunCode: (state) => {
      return state.gugunCode;
    },
    getSelectedYear: (state) => {
      return state.selectedYear;
    },
    getSelectedMonth: (state) => {
      return state.selectedMonth;
    },
  },
  mutations: {
    SET_KEYWORD: (state, keyword) => {
      state.keyword = keyword;
    },
    SET_GUGUN_CODE: (state, code) => {
      state.gugunCode = code;
      console.log(state.gugunCode);
    },
    SET_SELECTED_YEAR: (state, year) => {
      state.selectedYear = year;
    },
    SET_SELECTED_MONTH: (state, month) => {
      state.selectedMonth = month;
    },
  },

  actions: {
    async findGugunCode({ commit }, keyword) {
      let code = await getGugunCode(keyword);
      commit("SET_KEYWORD", keyword);
      commit("SET_GUGUN_CODE", code);
    },
  },
};

export default searchStore;
