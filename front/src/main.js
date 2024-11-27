import { createApp } from "vue";
import App from "./App.vue";

import router from "./router";
import store from "./store";
import "bootstrap-icons/font/bootstrap-icons.css";
// 여기
// TODO: bootstrap import
import "bootstrap/dist/js/bootstrap.bundle";
import "bootstrap/dist/css/bootstrap.min.css";
// TODO: bootstartp vue3 import
import BootstrapVue3 from "bootstrap-vue-3";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

createApp(App).use(BootstrapVue3).use(store).use(router).mount("#app");
