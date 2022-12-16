<template>
  <div>
    <v-container class="pa-16 outlined">
      <h1 class="pb-8">내 정보</h1>
      <v-divider class="pb-8" />

      <v-row>
        <v-col cols="2" class="d-flex flex-row mb-6">
          <span class="title">아이디</span>
        </v-col>
        <v-col class="d-flex flex-row align-left">
          <span>
            {{ user.id }}
          </span>
        </v-col>
        <v-col cols="3"> </v-col>
      </v-row>
      <v-row class="my-1">
        <v-col class="d-flex flex-row align-center" cols="2">
          <span class="title">비밀번호</span>
        </v-col>
        <v-col class="d-flex flex-row align-left">
          <v-text-field v-if="mode.passwordModify" class="title">
          </v-text-field>
        </v-col>
        <v-col class="d-flex justify-end flex-row align-center" cols="3">
          <v-btn
            v-if="!mode.passwordModify"
            text
            x-large
            @click="changePasswordModifyMode(true)"
          >
            수정
          </v-btn>
          <v-btn v-else text x-large @click="requireChangePassword">
            저장
          </v-btn>
        </v-col>
      </v-row>
      <v-row class="my-1">
        <v-col class="d-flex flex-row align-center" cols="2">
          <span class="title">이메일</span>
        </v-col>
        <v-col class="d-flex flex-row align-left">
          <v-text-field v-if="mode.emailModify" class="title"> </v-text-field>
          <span v-else class="title">{{ user.email }}</span>
        </v-col>
        <v-col class="d-flex justify-end flex-row align-center" cols="3">
          <v-btn
            v-if="!mode.emailModify"
            text
            x-large
            @click="changeEmailModifyMode(true)"
          >
            수정
          </v-btn>
          <v-btn v-else @click="certificationTimer" text x-large v-on="on">
            인증 번호 요청
          </v-btn>
          <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            v-model="mode.emailModify"
          >
            <v-card>
              <v-toolbar color="black" class="title white--text">
                이메일 인증
              </v-toolbar>
              <v-card-text class="py-10">
                <div class="text-md-subtitle-1 mb-3">
                  입력하신 이메일로 인증코드가 발송되었습니다.
                </div>
                <div class="text-xl-h4 text-center">5 : 00</div>
                <v-text-field class="px-16" v-model="certificationCode">
                </v-text-field>
              </v-card-text>
              <v-card-actions class="justify-end">
                <v-btn
                  x-large
                  color="black"
                  class="black white--text"
                  @click="
                    () => {
                      mode.emailModify = false;
                      this.sendCertificationCode();
                    }
                  "
                >
                  인증
                </v-btn>
                <v-btn
                  x-large
                  text
                  @click="
                    () => {
                      dialog.value = false;
                      mode.emailModify = false;
                    }
                  "
                >
                  취소
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-col>
      </v-row>
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
      certificationCode: "",
      certificationValidated: false,
      mode: {
        passwordModify: false,
        emailModify: false,
        emailCertification: false,
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
    changePasswordModifyMode(value) {
      this.mode.passwordModify = value;
    },
    requireChangePassword() {
      this.changePasswordModifyMode(false);
      //비밀번호 변경 요청
    },
    changeEmailModifyMode(value) {
      this.mode.emailModify = value;
    },
    requireCertifiactionCode() {
      if (this.mode.emailCertification) {
        //서버에 이메일 인증 번호 요청
      }
      this.mode.emailCertification = !this.mode.emailCertification;
    },
    sendCertificationCode() {
      this.mode.emailCertification = false;
      //이메일 정보 수정 요청
    },
    certificationTimer() {
      this.certificationValidated = true;

      // const time = 1000 * 60 * 5;
      const time = 2000;
      setTimeout(() => {
        this.mode.emailModify = false;
        this.certificationValidated = false;
      }, time); //5분
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
