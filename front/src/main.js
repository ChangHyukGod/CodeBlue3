import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { createPinia } from "pinia";

// TODO: bootstrap import
import "bootstrap/dist/js/bootstrap.bundle";
import "bootstrap/dist/css/bootstrap.min.css";

// BootstrapVue3
import BootstrapVue3 from "bootstrap-vue-3";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

// Bootstrap Icons
import "bootstrap-icons/font/bootstrap-icons.css";



const app = createApp(App);
const pinia = createPinia();
app.use(router);
app.use(store);
app.use(BootstrapVue3);
app.use(pinia);
app.mount("#app");
