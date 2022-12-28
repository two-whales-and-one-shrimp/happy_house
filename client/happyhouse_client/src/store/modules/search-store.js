const searchStore = {
  namespaced: true,
  state: {
    gugunCode: null,
    selectedYear: null,
    selectedMonth: null,
  },
  getters: {
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
    SET_GUGUN_CODE: (state, code) => {
      state.gugunCode = code;
    },
    SET_SELECTED_YEAR: (state, year) => {
      state.selectedYear = year;
    },
    SET_SELECTED_MONTH: (state, month) => {
      state.selectedMonth = month;
    },
  },
};

export default searchStore;
