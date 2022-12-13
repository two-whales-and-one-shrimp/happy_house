<template>
  <div class="d-flex flex-row mx-8 my-5 align-center">
    <logo />
    <v-spacer></v-spacer>
    <span class="mx-2">
      <v-btn exact to="/map" text x-large class="mr-5"> 지도 </v-btn>
      <span v-if="userId == null">
        <v-btn exact to="/user/sign-in" text x-large class="mr-5">로그인</v-btn>
        <v-btn exact to="/user/sign-up" text x-large class="mr-5">
          회원가입
        </v-btn>
      </span>
      <span v-else>
        <avartar @signOut="signOut" :userId="userId" />
      </span>
    </span>
  </div>
</template>

<script>
import logo from "@/components/header/Logo";
import avartar from "@/components/header/Avartar";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "TheHeader",
  data() {
    return {};
  },
  components: {
    logo: logo,
    avartar: avartar,
  },
  computed: {
    ...mapState(userStore, ["userId"]),
  },
  methods: {
    signOut() {},
  },
};
</script>

<style scoped></style>
