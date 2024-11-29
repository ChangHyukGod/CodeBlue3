import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// Bootstrap 및 Bootstrap-Vue3 불러오기
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

import BootstrapVue3 from "bootstrap-vue-3";

createApp(App)
  .use(BootstrapVue3) // Bootstrap-Vue3 플러그인 등록
  .use(store)
  .use(router)
  .mount("#app");
