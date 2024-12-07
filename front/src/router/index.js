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
    path: "/announcement/:ano",
    name: "AnnouncementDetail",
    component: () => import("@/views/faq/FaqAnnouncementDetail.vue"),
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
    path: "/faq/talk",
    component: () => import("../views/faq/FaqTalk.vue"),
  },
  {
    path: "/faq/talk/:tno",
    component: () => import("../views/faq/FaqTalkTest.vue"),
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
    path: "/add-recommend",
    component: () => import("../views/recommend/RecommendAdd.vue"),
  },

  {
    path: "/recommendupdate/:tdId",
    component: () => import("../views/recommend/RecommedUpdateView.vue"),
  },

  {
    path: "/recommenddetail/:tdId",
    component: () => import("../views/recommend/RecommendDetail.vue"),
  },

  {
    path: "/recommendmap",
    component: () => import("../views/recommend/RecommendMap.vue"),
  },

  {
    path: "/recommendmap2",
    component: () => import("../views/recommend/RecommendMap2.vue"),
  },

  {
    path: "/recommendmap3",
    component: () => import("../views/recommend/RecommendMap3.vue"),
  },

  {
    path: "/recommendcomadd",
    component: () => import("../views/recommend/RecommendComAdd.vue"),
  },

  {
    path: "/recommendcomupdate/:comId",
    component: () => import("../views/recommend/RecommendComUpdate.vue"),
  },

  {
    path: "/recommendgoogleview",
    component: () => import("../views/recommend/RecommendGoogleView.vue"),
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
    path: "/mainadmin",
    component: () => import("../views/admin/MainAdmin.vue"),
  },
  {
    path: "/mainadmin1",

    component: () => import("../views/admin/AdminFaqTalk.vue")

  },
  {
    path: "/mainadmin2",

    component: () => import("../views/admin/AdminFaqQuestion.vue")

  },
  {
    path: "/mainadmin3",

    component: () => import("../views/admin/AdminHowtoPay.vue")

  },
  {
    path: "/mainadmin4",
    component: () => import("../views/admin/MainAdmin4.vue"),
  },
  {
    path: "/mainadmin5",

    component: () => import("../views/admin/AdminFaqAnnouncement.vue")

  },
  {
    path: "/addRoom/:tourId",
    component: () => import("../views/room/AddRoomView.vue"),
  },
  {
    path: "/editRoom/:roomId",
    component: () => import("../views/room/UpdateRoomView.vue"),
  },
  {
    path: "/addRoomPhoto/:roomId",
    component: () => import("../views/room/addRoomPhoto.vue"),
  },
  {

    path: "/payment/:roomId",
    component: () => import("../views/payment/PaymentView.vue"),
  },
  {
    path: "/cart",
    component: () => import("../views/cart/CartView.vue"),
  },

  {
    path: "/cart/payment/:cartId",
    component: () => import("../views/payment/CartPaymentView.vue"),
  },


];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
