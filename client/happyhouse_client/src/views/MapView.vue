<template>
  <div class="margin-top">
    <left-bar
      @setAptList="setAptList"
      @changeMapCenterAddress="changeMapCenterAddress"
    ></left-bar>
    <select-boxes></select-boxes>
    <the-kakao-map
      :aptList="aptList"
      :centerAddress="centerAddress"
    ></the-kakao-map>
  </div>
</template>

<script>
import LeftBar from "@/components/map/LeftBar";
import TheKakaoMap from "@/components/map/TheKakaoMap";
import SelectBoxes from "@/components/map/SelectBoxes";

export default {
  components: {
    LeftBar,
    TheKakaoMap,
    SelectBoxes,
  },
  data() {
    return {
      aptList: [],
      centerAddress: "",
    };
  },
  methods: {
    setAptList(list) {
      this.aptList = [...list];
    },
    changeMapCenterAddress(centerAddress) {
      this.centerAddress = centerAddress;
    },
  },
};
</script>

<style scoped>
.margin-top {
  margin-top: -5.5rem;
}
</style>
