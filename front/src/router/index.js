import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes = [
  {

    path: "/",
    component: HomeView,
  },
  {
    path: '/register',
    component: () => import('../views/auth/RegisterView.vue')
  },
  {
    path: '/login',
    component: () => import('../views/auth/LoginView.vue')
  },
  {
    path: "/faq",
    component: () => import("../views/FaqMain.vue"),
  },
  {
    path: "/announcement",
    component: () => import("../views/FaqAnnouncement.vue")
  },
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
