<template>
  <v-menu offset-y>
    <template v-slot:activator="{ on, attrs }">
      <v-avatar
        class="not-drag"
        size="56"
        color="grey"
        v-bind="attrs"
        v-on="on"
      >
        {{ userId }}
      </v-avatar>
    </template>
    <v-list color="grey">
      <v-list-item
        v-if="!isAdmin"
        @click="changePage('/user/info')"
        class="cursor-pointer"
      >
        내 정보
      </v-list-item>
      <v-list-item v-else @click="changePage('/admin')" class="cursor-pointer">
        회원 관리
      </v-list-item>
      <v-list-item @click="emitSignOut" class="cursor-pointer">
        로그아웃
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  props: {
    userId: String,
  },
  data() {
    return {
      isAdmin: true,
    };
  },
  created() {
    this.isAdmin = this.getIsAdmin;
  },
  computed: {
    ...mapGetters("userStore", ["getIsAdmin"]),
  },
  methods: {
    changePage(path) {
      this.$router.push(path);
    },
    emitSignOut() {
      this.$emit("signOut");
    },
  },
};
</script>

<style scoped>
.not-drag {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
</style>
