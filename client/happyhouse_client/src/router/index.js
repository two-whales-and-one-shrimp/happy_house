import Vue from "vue";
import VueRouter from "vue-router";

import HomeView from "../views/HomeView.vue";
import MapView from "@/views/MapView";
import UserView from "@/views/UserView";
import AdminView from "@/views/AdminView";

import UserInfo from "@/components/user/UserInfo";
import UserSignIn from "@/components/user/UserSignIn";
import UserSignUp from "@/components/user/UserSignUp";

import store from "@/store";

/*
 * 로그인 후 다시 로그인 페이지 요청시 못 가게 한다.
 */
const avoidDuplicateSignIns = async (to, from, next) => {
  const userId = store.getters["userStore/getUserId"];

  if (userId == null) {
    next();
  } else {
    next(from);
  }
};

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/map",
    name: "map",
    component: MapView,
  },
  {
    path: "/user",
    component: UserView,
    children: [
      {
        path: "info",
        name: "userinfo",
        component: UserInfo,
      },
      {
        path: "sign-in",
        name: "signIn",
        beforeEnter: avoidDuplicateSignIns,
        component: UserSignIn,
      },
      {
        path: "sign-up",
        name: "signUp",
        component: UserSignUp,
      },
    ],
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
