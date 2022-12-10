<template>
  <span class="logo cursor-pointer" @click="changePage"> HappyHouse </span>
</template>

<script>
export default {
  methods: {
    changePage() {
      const nowRoute = this.$route.path;
      if (nowRoute != "/") {
        this.$router.push("/");
      }
    },
  },
};
</script>

<style scoped>
.logo {
  font-size: 2rem;
  font-weight: 800;
}
</style>
