<template>
  <div class="margin-top">
    <left-bar></left-bar>
    <the-kakao-map></the-kakao-map>
  </div>
</template>

<script>
import LeftBar from "@/components/map/LeftBar";
import TheKakaoMap from "@/components/map/TheKakaoMap";

export default {
  components: {
    LeftBar,
    TheKakaoMap,
  },
};
</script>

<style scoped>
.margin-top {
  margin-top: -5.5rem;
}
</style>
