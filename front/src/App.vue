<template>
  <!-- 머리말 : HeaderCom -->
  <HeaderCom />

  <!-- 본문 -->
  <div class="container mt-3">
    <router-view />
  </div>

  <FooterCom />
</template>

<script>
import FooterCom from "./components/FooterCom.vue";
import HeaderCom from "./components/HeaderCom.vue";
export default {
  components: {
    HeaderCom,
    FooterCom,
  },
};
</script>

<style>
@import "@/assets/css/main.css";
@font-face {
  font-family: 'dohyeon';
  src: url('/src/assets/font/BMDOHYEON_ttf.ttf') format('truetype');
}
@font-face {
  font-family: 'euljiro';
  src:url('/src/assets/font/BMEULJIROTTF.ttf') format('truetype');
}
@font-face {
  font-family: 'hanna';
  src: url('/src/assets/font/BMHANNA_11yrs_ttf.ttf') format('truetype');
}
</style>
