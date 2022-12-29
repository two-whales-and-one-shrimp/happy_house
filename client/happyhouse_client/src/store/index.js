import Vue from "vue";
import Vuex from "vuex";
import userStore from "./modules/user-store";
import searchStore from "./modules/search-store";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { userStore, searchStore },
});
