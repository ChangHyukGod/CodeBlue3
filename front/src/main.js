import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// BootstrapVue3
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import BootstrapVue3 from "bootstrap-vue-3";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

// Bootstrap Icons
import "bootstrap-icons/font/bootstrap-icons.css";

const app = createApp(App);
app.use(router);
app.use(store);
app.use(BootstrapVue3);
app.mount("#app");