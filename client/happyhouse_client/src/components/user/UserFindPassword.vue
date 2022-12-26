<template>
  <v-container class="pa-16 outlined">
    <h1 class="pb-8">비밀번호 찾기</h1>
    <v-divider class="pb-8" />
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
    <h4 class="py-2">이메일</h4>
    <v-text-field
      label="이메일 입력"
      required
      single-line
      outlined
      v-model="email"
    ></v-text-field>
    <v-divider />
    <v-row class="float-right pb-3">
      <v-col>
        <v-btn plain text @click="changeToBack">
          <h4>뒤로가기</h4>
        </v-btn>
      </v-col>
    </v-row>
    <v-btn
      class="mt-4"
      block
      tile
      x-large
      elevation="0"
      @click="requestFindPassword"
      :disabled="checkEmpty"
    >
      제출
    </v-btn>
  </v-container>
</template>

<script>
import { findUserPassword } from "@/api/user";

export default {
  data() {
    return {
      id: "",
      email: "",
      isNotEmpty: false,
    };
  },
  computed: {
    checkEmpty() {
      if (this.id === "") return true;
      if (this.email === "") return true;
      return false;
    },
  },
  methods: {
    async requestFindPassword() {
      const response = await findUserPassword(this.id, this.email);
      if (response.status === 200) {
        confirm("이메일이 전송되었습니다");
      } else {
        confirm("아이디와 이메일을 확인해 주세요");
      }
    },
    changeToBack() {
      this.$router.back();
    },
  },
};
</script>
