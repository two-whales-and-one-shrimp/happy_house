<template>
  <div class="d-flex flex-column">
    <div class="display-1 mb-10 align-self-center">회원 목록</div>
    <v-simple-table>
      <thead>
        <tr height="50px">
          <td class="text-lg-h6 font-weight-bold text-center" width="20%">
            회원 아이디
          </td>
          <td class="text-lg-h6 font-weight-bold text-left"></td>
          <td width="15%"></td>
          <td width="15%"></td>
        </tr>
      </thead>
      <tbody>
        <tr height="70px" v-for="user in userList" :key="user.userId">
          <td class="text-lg-body-1">
            <div class="text-center">{{ user.userId }}</div>
          </td>
          <td class="text-left">
            <v-btn
              color="primary"
              class="text--white float-left text-lg-body-1"
              v-if="user.admin"
              large
              rounded
            >
              관리자
            </v-btn>
          </td>
          <td>
            <v-btn
              @click="upgradeUser(user.userId)"
              text
              large
              :disabled="user.admin"
              class="text-lg-body-1"
            >
              승격
            </v-btn>
          </td>
          <td>
            <v-btn
              @click="deleteUser(user.userId)"
              text
              large
              class="text-lg-body-1"
            >
              삭제
            </v-btn>
          </td>
        </tr>
      </tbody>
    </v-simple-table>
  </div>
</template>

<script>
import { getUserList, deleteUser, upgradeUserToAdmin } from "@/api/admin.js";

export default {
  data() {
    return {
      userList: [],
    };
  },
  created() {
    getUserList(
      (list) => {
        this.userList = [...list];
      },
      () => {
        console.log("error");
        // eslint-disable-next-line prettier/prettier
      },
    );
  },
  methods: {
    updateUserToAdmin(userId) {
      const index = this.userList.findIndex((user) => user.userId == userId);
      this.userList[index].admin = true;
    },
    upgradeUser(userId) {
      const select = confirm("관리자로 승격 하시겠습니까?");
      if (select) {
        upgradeUserToAdmin(userId, this.updateUserToAdmin, (error) => {
          console.log(error);
        });
      }
    },
    deleteUserByList(userId) {
      const index = this.userList.findIndex((user) => user.userId === userId);
      console.log(index);
      this.userList.splice(index, 1);
    },
    deleteUser(userId) {
      const select = confirm("회원을 삭제하시겠습니까?");
      if (select) {
        deleteUser(userId, this.deleteUserByList, (error) => {
          console.log(error);
        });
      }
    },
  },
};
</script>
