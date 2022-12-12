import Vue from "vue";
import VueRouter from "vue-router";

import HomeView from "../views/HomeView.vue";
import MapView from "@/views/MapView";
import UserView from "@/views/UserView";
import AdminView from "@/views/AdminView";

import UserInfo from "@/components/user/UserInfo";
import UserSignIn from "@/components/user/UserSignIn";
import UserSignUp from "@/components/user/UserSignUp";

Vue.use(VueRouter);

const routes = [
  {
    path: "",
    name: "home",
    component: HomeView,
  },
  {
    path: "map",
    name: "map",
    component: MapView,
  },
  {
    path: "user",
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
    path: "admin",
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
