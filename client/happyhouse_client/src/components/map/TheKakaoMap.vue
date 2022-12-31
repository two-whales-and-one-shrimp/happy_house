<template>
  <div id="map"></div>
</template>

<script>
export default {
  props: {
    aptList: Array,
    centerAddress: [String, Object],
  },
  data() {
    return {
      markerList: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      return;
    }
    const script = document.createElement("script");
    // eslint-disable-next-line no-undef
    script.onload = () => kakao.maps.load(this.initMap);
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_KEY}&libraries=services`;
    document.head.appendChild(script);
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        // eslint-disable-next-line no-undef
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      // eslint-disable-next-line no-undef
      this.map = new kakao.maps.Map(container, options);
    },
    makeMarkers() {
      //기존 마커 다 지우고 시작
      this.deleteAllMarkers();

      //dummydata
      for (const apt of this.aptList) {
        // eslint-disable-next-line no-undef
        const markerPosition = new kakao.maps.LatLng(
          apt.latlngObj.y,
          apt.latlngObj.x
        );
        // eslint-disable-next-line no-undef
        const marker = new kakao.maps.Marker({
          position: markerPosition,
        });
        marker.setMap(this.map);
        this.markerList.push(marker);
      }
    },
    deleteAllMarkers() {
      for (const marker of this.markerList) {
        marker.setMap(null);
      }
    },
    //지도 중심 바꾸기
    async changeMapCenter() {
      // eslint-disable-next-line no-undef
      let geocoder = new kakao.maps.services.Geocoder();
      let coords = null;

      if (this.centerAddress.x == undefined) {
        geocoder.addressSearch(this.centerAddress, (result, status) => {
          // eslint-disable-next-line no-undef
          if (status === kakao.maps.services.Status.OK) {
            // eslint-disable-next-line no-undef
            coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            //지도 중심 이동
            this.map.setCenter(coords);
            this.map.setLevel(7);
          }
        });
      } else {
        // eslint-disable-next-line no-undef
        coords = new kakao.maps.LatLng(
          this.centerAddress.y,
          this.centerAddress.x
        );
      }

      //지도 중심 이동
      this.map.setCenter(coords);
      this.map.setLevel(3);
    },
  },
  watch: {
    aptList: function () {
      this.makeMarkers();
    },
    centerAddress: function () {
      this.changeMapCenter();
    },
  },
};
</script>

<style scoped>
#map {
  position: fixed;
  left: 400px;
  width: 100vw;
  height: 100vh;
  border-left: 1px solid #9e9e9e;
  border-top: 1px solid #9e9e9e;
}
</style>
