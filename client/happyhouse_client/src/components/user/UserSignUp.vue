<template>
  <v-container class="pa-16 outlined">
    <h1 class="pb-8">회원정보 입력</h1>
    <v-divider class="pb-8" />
    <h4 class="pb-12">
      HappyHouse 서비스 이용을 위해 아래 정보를 입력해 주세요.
    </h4>
    <form>
      <h4 class="py-2">아이디</h4>
      <v-text-field label="아이디 입력" required single-line outlined>
        아이디
      </v-text-field>

      <h4 class="py-2">비밀번호</h4>
      <v-text-field
        label="비밀번호 입력"
        required
        single-line
        outlined
      ></v-text-field>
      <v-text-field
        label="비밀번호 확인"
        required
        single-line
        outlined
      ></v-text-field>

      <h4 class="py-2">이메일</h4>
      <v-row>
        <v-col cols="9" class="pb-0">
          <v-text-field
            label="이메일 주소 입력"
            required
            single-line
            outlined
          ></v-text-field>
        </v-col>
        <v-col cols="3" class="pb-0 pl-0">
          <v-btn block tile x-large elevation="0" @click="isShow = true">
            인증
          </v-btn>
        </v-col>
      </v-row>
      <v-row v-show="isShow">
        <v-col cols="9" class="py-0">
          <v-text-field
            label="인증번호 입력"
            required
            single-line
            outlined
          ></v-text-field>
        </v-col>
        <v-col cols="3" class="py-0 pl-0">
          <v-btn block tile x-large elevation="0">확인</v-btn>
        </v-col>
      </v-row>

      <v-btn class="mt-4" block tile x-large elevation="0">확인</v-btn>
    </form>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      isShow: false,
    };
  },
};
</script>
<style scoped></style>
