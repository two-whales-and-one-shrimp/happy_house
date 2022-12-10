<template>
  <v-app>
    <v-main>
      <header-component />
      <v-container class="margin-top padding-side">
        <router-view />
      </v-container>
      <footer-component />
    </v-main>
  </v-app>
</template>

<script>
import headerComponent from "@/components/header/TheHeader";
import footerComponent from "@/components/footer/TheFooter";

export default {
  name: "App",

  data: () => ({
    //
  }),
  components: {
    headerComponent,
    footerComponent,
  },
};
</script>

<style scoped>
@import "@/assets/css/main.css";
.margin-top {
  margin-top: 7rem;
}

.padding-side {
  padding-left: 20vw;
  padding-right: 20vw;
}
</style>
