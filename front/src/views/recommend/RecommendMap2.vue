<template>
  <div>
    <!-- 지도 표시 영역 -->

    <div
      id="map"
      style="
        width: 100%;
        height: 1000px;
        margin-top: 300px;
        margin-bottom: 300px;
      "
    ></div>
  </div>
</template>

<script>
export default {
  name: "GoogleMapExample",
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      // 함안 좌표
      const location = { lat: 37.1436, lng: 128.2023 };

      // 맵을 렌더링할 div 가져오기
      const mapElement = document.getElementById("map");

      // eslint-disable-next-line no-undef
      const map = new google.maps.Map(mapElement, {
        center: location,
        zoom: 14,
      });

      // eslint-disable-next-line no-undef
      new google.maps.Marker({
        position: location,
        map: map,
        title: "충북 제천 의림지",
      });
    },
  },
};
</script>

<style>
/* 필요하면 추가적인 스타일을 적용하세요 */

/* 구글 맵의 스타일을 개선 */
#map {
  width: 100%;
  height: 400px;
  border-radius: 10px; /* 둥근 모서리 */
  border: 4px solid #ffdd00; /* 노란색 외곽선 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* 부드러운 그림자 */
  transition: all 0.3s ease; /* 마우스 오버 시 부드러운 변화 */
}

#map:hover {
  transform: scale(1.1); /* 마우스 오버 시 약간 확대 */
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.15); /* 확대되면 그림자 더 강조 */
}
</style>
