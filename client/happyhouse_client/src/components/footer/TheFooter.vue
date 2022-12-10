<template>
  <div
    v-if="this.$route.path !== '/map'"
    class="black pb-8 margin-top footer-height"
  >
    <div class="py-4 margin-left">
      <div class="mt-5 mb-1 white--text title">SSAFY 8기</div>
      <div class="white--text subtitle-1">김호균, 박재희, 장예은</div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.footer-height {
  height: 20vh;
}
.margin-top {
  margin-top: 15rem !important;
}
.margin-left {
  padding-left: 27.5rem;
}
</style>
