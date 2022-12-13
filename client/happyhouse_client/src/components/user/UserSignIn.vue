<template>
  <v-container class="pa-16 outlined">
    <h1 class="pb-8">로그인 정보 입력</h1>
    <v-divider class="pb-8" />
    <v-snackbar top color="error" v-model="isVisible"
      >아이디와 비밀번호를 확인해주세요!</v-snackbar
    >
    <h4 class="pb-12">
      HappyHouse 서비스 이용을 위해 아래 정보를 입력해 주세요.
    </h4>

    <h4 class="py-2">아이디</h4>
    <v-text-field
      label="아이디 입력"
      required
      single-line
      outlined
      v-model="id"
    >
      아이디
    </v-text-field>

    <h4 class="py-2">비밀번호</h4>
    <v-text-field
      label="비밀번호 입력"
      required
      single-line
      outlined
      v-model="password"
    ></v-text-field>
    <v-divider />
    <v-row class="float-right pb-3">
      <v-col
        ><v-btn to="/user/sign-up" plain text><h4>회원 가입</h4></v-btn></v-col
      >
      <v-col
        ><v-btn plain text><h4>비밀번호 찾기</h4></v-btn></v-col
      >
    </v-row>
    <v-btn class="mt-4" block tile x-large elevation="0" @click="signIn"
      >로그인</v-btn
    >
  </v-container>
</template>

<script>
import { mapActions } from "vuex";

const userStore = "userStore";

export default {
  data() {
    return {
      id: "",
      password: "",
      isVisible: false,
    };
  },
  methods: {
    ...mapActions(userStore, ["userSignIn"]),
    async signIn() {
      if (
        await this.userSignIn({
          id: this.id,
          password: this.password,
        })
      ) {
        this.$router.push("/");
      } else {
        this.isVisible = true;
      }
    },
  },
};
</script>
<style scoped></style>
