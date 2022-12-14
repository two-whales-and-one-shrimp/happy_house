import { signIn, signOut } from "@/api/user.js";

const userStore = {
  namespaced: true,
  state: {
    isAdmin: sessionStorage.getItem("isAdmin"),
    userId: sessionStorage.getItem("userId"),
  },
  getters: {
    getUserId: function (state) {
      return state.userId;
    },
  },
  mutations: {
    SET_USER_INFO: (state, userInfo) => {
      state.userId = userInfo.id;
      state.isAdmin = userInfo.isAdmin;
      sessionStorage.setItem("userId", userInfo.id);
      sessionStorage.setItem("isAdmin", userInfo.isAdmin);
    },

    REMOVE_USER_INFO: (state) => {
      state.userId = null;
      state.isAdmin = false;
      sessionStorage.removeItem("userId");
      sessionStorage.removeItem("isAdmin");
    },
  },
  actions: {
    async userSignIn({ commit }, userInfo) {
      let response = await signIn(userInfo.id, userInfo.password);
      if (response.status == 200) {
        commit("SET_USER_INFO", {
          id: response.data.userId,
          isAdmin: response.data.isAdmin,
        });
        localStorage.setItem("accessToken", response.data.accessToken);
        localStorage.setItem("refreshToken", response.data.refreshToken);
        return true;
      } else {
        return false;
      }
    },

    async userSignOut({ state, commit }) {
      let response = await signOut(state.userId);
      if (response.status == 200) {
        commit("REMOVE_USER_INFO");
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");
        return true;
      } else {
        return false;
      }
    },
  },
};

export default userStore;
