<template>
  <div>
    <!-- 머리말 : HeaderCom -->
    <HeaderCom />

    <!-- 본문 -->
    <div class="container mt-3">
      <router-view />
    </div>

    <!-- CartView 페이지에서만 FooterCom을 숨기기 위한 조건부 렌더링 -->
    <FooterCom v-if="showFooter" />
  </div>
</template>

<script>
import FooterCom from "./components/FooterCom.vue";
import HeaderCom from "./components/HeaderCom.vue";
export default {
  components: {
    HeaderCom,
    FooterCom,
  },
  computed: {
    // 현재 경로가 '/cart' 또는 '/TotalPayment'와 일치하면 Footer를 숨기기
    showFooter() {
      return (
        this.$route.path !== "/cart" && this.$route.path !== "/TotalPayment"
      ); // Cart와 TotalPayment 페이지에서 footer 숨기기
    },
  },
};
</script>

<style>
@import "@/assets/css/main.css";
@font-face {
  font-family: "dohyeon";
  src: url("/src/assets/font/BMDOHYEON_ttf.ttf") format("truetype");
}
@font-face {
  font-family: "euljiro";
  src: url("/src/assets/font/BMEULJIROTTF.ttf") format("truetype");
}
@font-face {
  font-family: "hanna";
  src: url("/src/assets/font/BMHANNA_11yrs_ttf.ttf") format("truetype");
}
</style>
