<template>
  <div>
    <v-container class="pa-16 outlined">
      <h1 class="pb-8">내 정보</h1>
      <v-divider class="pb-8" />

      <h4 class="py-2">아이디</h4>
      <div class="px-3 py-4 mb-8 border">
        {{ user.id }}
      </div>

      <h4 class="py-2">비밀번호</h4>
      <v-text-field
        type="password"
        required
        v-model="user.password"
        single-line
        outlined
      ></v-text-field>

      <h4 class="py-2">이메일</h4>
      <v-text-field
        v-model="user.email"
        type="mail"
        required
        single-line
        outlined
      ></v-text-field>
      <v-divider />
      <v-btn class="mt-4" block tile x-large elevation="0" @click="pageBack">
        뒤로가기
      </v-btn>
      <v-divider class="mt-4 mb-4" />
      <span class="quit" @click="quit">탈퇴하기 > </span>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
export default {
  data() {
    return {
      user: {
        id: "",
        password: "",
        email: "",
      },
    };
  },
  computed: {
    ...mapGetters("userStore", ["getUserId"]),
  },
  methods: {
    ...mapMutations("userStore", ["REMOVE_USER_INFO"]),
    pageBack() {
      this.$router.back();
    },
    quit() {
      const select = confirm("정말 탈퇴하시겠습니까?");
      if (select) {
        //탈퇴
        this.REMOVE_USER_INFO();
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");

        this.$router.push("/");
      }
    },
  },
  created() {
    this.user.id = this.getUserId;
  },
};
</script>

<style scoped>
.border {
  border: 1px solid #9e9e9e;
  border-radius: 4px;
}

.quit {
  color: #f44336;
  margin-top: 16px;
}

.quit:hover {
  cursor: pointer;
}
</style>
