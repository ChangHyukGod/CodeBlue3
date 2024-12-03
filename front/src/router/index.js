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
    path: "/mainupdate/:tourId",
    component: () => import("../views/main/MainUpdate.vue"),
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
    component: () => import("../views/faq/FaqMain.vue"),
  },
  {
    path: "/announcement",
    component: () => import("../views/faq/FaqAnnouncement.vue"),
  },
  {
    path: "/faq/payment",
    component: () => import("../views/faq/FaqHowtoPay.vue"),
  },
  {
    path: "/faq/list",
    component: () => import("../views/faq/FaqQuestionList.vue"),
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
    path: "/review/:reviewid",
    component: () => import("../views/review/ReviewDetail.vue"),
  },
  {
    path: "/coupon",
    component: () => import("../views/coupon/CouponView.vue"),
  },
  {
    path: "/mypage",
    component: () => import("../views/mypage/MypageView.vue"),
  },

  {
    path: "/recommend",
    component: () => import("../views/recommend/RecommendView.vue"),
  },

  {
    path: "/recommendadd",
    component: () => import("../views/recommend/RecommedAddView.vue"),
  },

  {
    path: "/addadmin",
    component: () => import("../views/admin/AddAdminDb.vue"),
  },
  {
    path: "/adminlist",
    component: () => import("../views/admin/AdminDbDetail.vue"),
  },
  {
    path: "/admindb",
    component: () => import("../views/admin/AdminDbView.vue"),
  },
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
