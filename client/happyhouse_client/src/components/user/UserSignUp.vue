<template>
  <v-container class="pa-16 outlined">
    <h1 class="pb-8">회원정보 입력</h1>
    <v-divider class="pb-8" />
    <h4 class="pb-12">
      HappyHouse 서비스 이용을 위해 아래 정보를 입력해 주세요.
    </h4>

    <h4 class="py-2">아이디</h4>
    <v-text-field
      placeholder="아이디 입력"
      id="userid"
      v-model="user.userid"
      :error-messages="check_id"
      :rules="[rules.userid.require, rules.userid.duplicate]"
      required
      single-line
      outlined
    >
      아이디
    </v-text-field>

    <h4 class="py-2">비밀번호</h4>
    <v-text-field
      placeholder="비밀번호 입력"
      v-model="user.userpassword"
      :error-messages="check_password"
      :rules="[rules.userpassword.require]"
      type="password"
      required
      single-line
      outlined
    ></v-text-field>
    <v-text-field
      placeholder="비밀번호 확인"
      v-model="user.userpasswordcheck"
      :error-messages="check_passwordcheck"
      :rules="[
        rules.userpasswordcheck.require,
        rules.userpasswordcheck.coincide,
      ]"
      type="password"
      required
      single-line
      outlined
    ></v-text-field>

    <h4 class="py-2">이메일</h4>
    <v-row>
      <v-col cols="9" class="pb-0">
        <v-text-field
          placeholder="이메일 주소 입력"
          v-model="user.useremail"
          :error-messages="check_email"
          :rules="[rules.useremail.require]"
          type="email"
          required
          single-line
          outlined
        ></v-text-field>
      </v-col>
      <v-col cols="3" class="pb-0 pl-0">
        <v-btn
          block
          tile
          x-large
          elevation="0"
          @click="[showEmail(), checkEmail()]"
        >
          인증
        </v-btn>
      </v-col>
    </v-row>
    <v-row v-show="isShow">
      <v-col cols="9" class="py-0">
        <v-text-field
          placeholder="인증번호 입력"
          id="checkcode"
          v-model="emailcode"
          :error-messages="check_emailcode"
          :rules="[rules.emailcode.require, rules.emailcode.coincide]"
          required
          single-line
          outlined
        ></v-text-field>
      </v-col>
      <v-col cols="3" class="py-0 pl-0">
        <v-btn block tile x-large elevation="0" @click="checkCode">확인</v-btn>
      </v-col>
    </v-row>

    <v-btn class="mt-4" block tile x-large elevation="0" @click="signUp"
      >회원가입</v-btn
    >
  </v-container>
</template>

<script>
import { signUp, checkId, checkEmail, checkCode } from "@/api/user";

export default {
  data() {
    return {
      user: {
        userid: "",
        userpassword: "",
        useremail: "",
      },
      emailcode: "",

      check_id: [],
      check_password: [],
      check_passwordcheck: [],
      check_email: [],
      check_emailcode: [],

      rules: {
        userid: {
          require: (v) => !!v || "아이디를 입력해주세요",
          duplicate: (v) => this.checkId(v),
        },
        userpassword: {
          require: (v) => !!v || "비밀번호를 입력해주세요",
        },
        userpasswordcheck: {
          require: (v) => !!v || "비밀번호를 확인해주세요",
          coincide: (v) =>
            v === this.user.userpassword
              ? []
              : "비밀번호가 다릅니다. 다시 확인해주세요",
        },
        useremail: {
          require: (v) => !!v || "이메일을 입력해주세요",
        },
        emailcode: {
          require: (v) => !!v || "인증번호를 입력해주세요",
        },
      },
      isShow: false,
    };
  },
  methods: {
    signUp() {
      let param = {
        userId: this.user.userid,
        userPassword: this.user.userpassword,
        userEmail: this.user.useremail,
      };
      signUp(param, ({ data }) => {
        if (data === "success") {
          this.$router.push({ name: "signIn" });
        }
      });
    },
    checkId(userid) {
      checkId(userid, ({ data }) => {
        if (data === "success") {
          this.check_id = [];
        } else {
          this.check_id = ["이미 사용중인 아이디입니다."];
        }
      });
    },
    checkEmail() {
      checkEmail(this.user.useremail);
    },
    showEmail() {
      this.isShow = true;
    },
    checkCode() {
      checkCode(this.emailcode, ({ data }) => {
        if (data === "success") {
          this.check_emailcode = [];
        } else {
          this.check_emailcode = ["인증번호가 다릅니다. 다시 확인해주세요"];
        }
      });
    },
  },
};
</script>

<style scoped></style>
