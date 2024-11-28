import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/main/MainView.vue";

const routes = [
  {
    path: "/",
    component: MainView,
  },
  {
    path: "/add-main",
    component: () => import("../views/main/MainAdd.vue"),
  },
  {
    path: "/maindetail/:tourId",
    component: () => import("../views/main/MainDetail.vue"),
  },
  {
    path: "/register",
    component: () => import("../views/auth/RegisterView.vue"),
  },
  {
    path: "/login",
    component: () => import("../views/auth/LoginView.vue"),
  },
  {
    path: "/faq",
    component: () => import("../views/FaqMain.vue"),
  },
  {
    path: "/announcement",
    component: () => import("../views/FaqAnnouncement.vue"),
  },
  {
    path: "/review",
    component: () => import("../views/review/ReviewMain.vue"),
  },
  {
    path: "/add-review",
    component: () => import("../views/review/AddReview.vue"),
  },
  {
    path: "/review/:uuid",
    component: () => import("../views/review/ReviewDetail.vue"),
  },
  {
    path: "/coupon",
    component: () => import("../views/coupon/CouponView.vue"),
  },
  {
    path: "/admin",
    component: ()=> import("../views/admin/AddAdminDb.vue"),
  },
  {
    path: "/admindb",
    component: () => import("../views/admin/AdminDbDetail.vue")
  },
  {
    path: "/postlist",
    component : () => import("../views/admin/AdminDbView.vue")
  }
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
