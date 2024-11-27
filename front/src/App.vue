<template>
  <!-- 머리말 : HeaderCom -->
  <HeaderCom />

  <!-- 본문 -->
  <div class="container mt-3">
    <router-view />
  </div>

</template>

<script>

import HeaderCom from "./components/HeaderCom.vue";
export default {
  components: {
    HeaderCom,
  },
};
</script>

<style>
</style>
