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
        <tr height="70px" v-for="user in userList" :key="user.id">
          <td class="text-lg-body-1">
            <div class="text-center">{{ user.id }}</div>
          </td>
          <td class="text-left">
            <v-btn
              color="primary"
              class="text--white float-left text-lg-body-1"
              v-if="user.isAdmin"
              large
              rounded
            >
              관리자
            </v-btn>
          </td>
          <td>
            <v-btn
              @click="makeAdmin(user.id)"
              text
              large
              class="text-lg-body-1"
            >
              승격
            </v-btn>
          </td>
          <td>
            <v-btn
              @click="deleteUser(user.id)"
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
export default {
  data() {
    return {
      userList: [
        { id: "testId", isAdmin: true },
        { id: "userId2", isAdmin: false },
        { id: "userId3", isAdmin: true },
        { id: "userId4", isAdmin: false },
      ],
    };
  },
  methods: {
    makeAdmin(userId) {
      const select = confirm("관리자로 승격 하시겠습니까?");
      if (select) {
        const tmpList = [...this.userList];
        const index = this.userList.findIndex((user) => user.id == userId);

        tmpList[index].isAdmin = true;
        this.userList = [...tmpList];
      }
    },
    deleteUser(userId) {
      const select = confirm("회원을 삭제하시겠습니까?");
      if (select) {
        const index = this.userList.findIndex((user) => user.id == userId);
        this.userList.splice(index, 1);
      }
    },
  },
};
</script>
