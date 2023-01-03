<template>
  <div id="map"></div>
</template>

<script>
/* global kakao */
/* eslint no-undef: "off" */
export default {
  props: {
    aptList: Array,
    centerAddress: [String, Object],
  },
  data() {
    return {
      markerList: [],
      infoList: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      return;
    }
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(this.initMap);
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_KEY}&libraries=services`;
    document.head.appendChild(script);
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);

      const zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.TOPRIGHT);
    },
    makeMarkers() {
      //기존 마커 다 지우고 시작
      this.deleteAllMarkers();

      //dummydata
      for (const apt of this.aptList) {
        const markerPosition = new kakao.maps.LatLng(
          apt.latlngObj.y,
          apt.latlngObj.x
        );
        const marker = new kakao.maps.Marker({
          position: markerPosition,
        });
        var iwContent = `<div style="padding:3px; text-align:center">${apt.aptName}</div>`;
        const infowindow = new kakao.maps.InfoWindow({
          position: markerPosition,
          content: iwContent,
        });

        marker.setMap(this.map);
        infowindow.open(this.map, marker);

        this.markerList.push(marker);
        this.infoList.push(infowindow);
      }
    },
    deleteAllMarkers() {
      for (const marker of this.markerList) {
        marker.setMap(null);
      }
      for (const infowindow of this.infoList) {
        infowindow.close();
      }
    },
    //지도 중심 바꾸기
    async changeMapCenter() {
      let geocoder = new kakao.maps.services.Geocoder();
      let coords = null;

      if (this.centerAddress.x == undefined) {
        console.log(this.centerAddress);
        geocoder.addressSearch(this.centerAddress, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            //지도 중심 이동
            this.map.setCenter(coords);
            this.map.setLevel(7);
          }
        });
      } else {
        console.log(this.centerAddress);
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
  beforeUpdate() {
    this.deleteAllMarkers();
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
  width: 78.3vw;
  height: 90vh;
  border-left: 1px solid #9e9e9e;
  border-top: 1px solid #9e9e9e;
}
</style>
