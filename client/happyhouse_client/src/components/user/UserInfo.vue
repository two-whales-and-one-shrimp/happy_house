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
          <v-text-field
            v-if="mode.passwordModify"
            class="title"
            v-model="user.password"
          >
          </v-text-field>
        </v-col>
        <v-col class="d-flex justify-end flex-row align-center" cols="3">
          <v-btn
            v-if="!mode.passwordModify"
            text
            x-large
            @click="changeMode('password', true)"
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
            @click="changeMode('email', true)"
          >
            수정
          </v-btn>
          <v-btn v-else @click="requireCertifiactionCode" text x-large>
            인증 번호 요청
          </v-btn>
          <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            v-model="mode.emailCertification"
          >
            <v-card>
              <v-toolbar color="black" class="title white--text">
                이메일 인증
              </v-toolbar>
              <v-card-text class="py-10">
                <div class="text-md-subtitle-1 mb-3">
                  입력하신 이메일로 인증코드가 발송되었습니다.
                </div>
                <div class="text-xl-h4 text-center">{{ this.timerText }}</div>
                <v-text-field class="px-16" v-model="certificationCode">
                </v-text-field>
              </v-card-text>
              <v-card-actions class="justify-end pb-5">
                <v-btn
                  v-if="!timeOut"
                  x-large
                  color="black"
                  class="black white--text"
                  @click="sendCertificationCode"
                >
                  인증
                </v-btn>
                <v-btn x-large text @click="cancelCertification"> 취소 </v-btn>
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
import { deleteUser, updateUserPassword } from "@/api/user";
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
      timeOut: false,
      mode: {
        passwordModify: false,
        emailModify: false,
        emailCertification: false,
      },
      timerText: "",
      timerTime: 1000 * 60 * 5,
      timeInterval: null,
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
        deleteUser(
          this.getUserId,
          () => {
            this.REMOVE_USER_INFO();
            localStorage.removeItem("accessToken");
            localStorage.removeItem("refreshToken");
          },
          (e) => {
            console.log(e);
          }
        );

        this.$router.push("/");
      }
    },
    changeMode(type, value) {
      if (type === "password") {
        this.mode.passwordModify = value;
      } else if (type === "email") {
        this.mode.emailModify = value;
      } else if (type === "dialog") {
        this.mode.emailCertification = value;
      }
    },
    showAlert(message) {
      alert(message);
    },
    requireChangePassword() {
      //비밀번호 변경 요청
      updateUserPassword(
        this.getUserId,
        this.user.password,
        () => {
          this.showAlert("변경 완료되었습니다");
        },
        () => {
          this.showAlert("서버 오류로 요청하신 작업을 완료할 수 없습니다.");
        }
      );
      this.changeMode("password", false);
    },
    requireCertifiactionCode() {
      this.changeMode("dialog", true);
      //서버에 이메일 인증 번호 요청
      this.certificationTimer();
    },
    printTime(time) {
      const minutes = Math.floor(time / (60 * 1000));
      const seconds = `${(time % (60 * 1000)) / 1000}`.padStart(2, "0");

      this.timerText = `${minutes} : ${seconds}`;
    },
    certificationTimer() {
      this.resetTimeOut();
      this.printTime(this.timerTime);

      let i = 1;
      this.timeInterval = setInterval(() => {
        this.printTime(this.timerTime - 1000 * i);
        i++;
      }, 1000);

      setTimeout(() => {
        clearInterval(this.timeInterval);

        this.certificationValidated = false;
        this.timeOut = true;
        this.timerText = "시간이 초과되었습니다";
      }, this.timerTime); //5분
    },
    resetTimeOut() {
      this.certificationValidated = false;
      this.timeOut = false;
    },
    closeDialog() {
      this.mode.emailCertification = false;
      clearInterval(this.timeInterval);
      this.resetTimeOut();
    },
    sendCertificationCode() {
      //이메일 정보 수정 요청
      this.closeDialog();
    },
    cancelCertification() {
      this.closeDialog();
      this.mode.emailModify = false;
    },
  },
  created() {
    this.user.id = this.getUserId;
  },
  watch: {
    "mode.emailCertification"(newValue) {
      if (!newValue) {
        this.cancelCertification();
      }
    },
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
