<template>
  <!-- 최상단 메뉴 -->
  <div>
    <b-nav class="upper_menu">
      <div class="upper_menu_link">
        <b-nav-item v-if="!this.$store.state.loggedIn" href="/login">로그인</b-nav-item>
        <b-nav-item v-if="!this.$store.state.loggedIn">|</b-nav-item>

        <b-nav-item v-if="!this.$store.state.loggedIn" href="/register">회원가입</b-nav-item>
        <b-nav-item v-if="!this.$store.state.loggedIn">|</b-nav-item>

        <b-nav-item v-if="this.$store.state.loggedIn" href @click="logout">로그아웃</b-nav-item>
        <b-nav-item v-if="this.$store.state.loggedIn">|</b-nav-item>

        <b-nav-item>예약확인</b-nav-item>
        <b-nav-item>|</b-nav-item>

        <b-nav-item href="/faq">고객센터</b-nav-item>
        <b-nav-item>|</b-nav-item>

        <b-nav-item href="/add-main" v-if="userRole === 'ROLE_ADMIN'">추가</b-nav-item>
        <b-nav-item>|</b-nav-item>

        <b-nav-item href="/cart">
          <i class="bi bi-cart"></i>
          <span class="badge bg-danger cart-count">{{ cartCount }}</span>
        </b-nav-item>
        <b-nav-item v-if="userRole === 'ROLE_ADMIN'" href="/mainadmin">관리자 페이지</b-nav-item>
      </div>
    </b-nav>
  </div>

  <div class="full-head">
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <img
          src="@/assets/logo.jpg"
          class="gpt-icon"
          href="/"
          onclick="location.href='/'"
        />
        &nbsp;&nbsp;
        <a class="navbar-brand" href="/"></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <!-- 검색창 -->
        <div class="search_box">
          <form class="d-flex search_bar" method="get" action="">
            <input
              class="form-control me-2 search_text"
              name="searchKeyword"
              type="search"
              placeholder="여행의 모든 것"
              aria-label="Search"
              v-model="searchKeyword"
            />
            <button class="btn btn-outline-warning search_glass" type="submit" @click="getAll">
              <i class="bi bi-search"></i>
            </button>
          </form>
        </div>

        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse second_menu_box" id="navbarSupportedContent">
          <router-link to="/coupon" class="nav-menu">
            <a class="nav-link" aria-current="page">
              <i class="bi bi-ticket"></i> 쿠폰
            </a>
          </router-link>
          &nbsp;|&nbsp;
          <router-link to="/recommendgoogleview" class="nav-menu">
            <a class="nav-link" aria-current="page">
              <i class="bi bi-geo-alt"></i> 여행지검색
            </a>
          </router-link>
          &nbsp;|&nbsp;
          <router-link to="/recommend" class="nav-menu"> <!-- 수정된 링크 -->
            <a class="nav-link" aria-current="page">
              <i class="bi bi-star"></i> 추천여행지 <!-- 추천 여행지 아이콘 변경 -->
            </a>
          </router-link>
          &nbsp;|&nbsp;
          <router-link to="/review" class="nav-menu">
            <a class="nav-link" aria-current="page">
              <i class="bi bi-pencil"></i> 리뷰게시판
            </a>
          </router-link>
          &nbsp;|&nbsp;
          <router-link to="/mypage" class="nav-menu">
            <a class="nav-link" aria-current="page">
              <i class="bi bi-person-plus" id="person-icon"></i>
            </a>
          </router-link>
        </div>
      </div>
    </nav>
  </div>
</template>

<script scoped>
import MemberService from "@/services/auth/MemberService";
export default {
  data() {
    return {
      cartCount: 0,
      userRole: "", // 유저 권한
    };
  },
  methods: {
    logout() {
      MemberService.logout();
      this.$store.state.loggedIn = false;
      localStorage.removeItem("cartCount");
      window.location.href = "/login";
    },
  },
  mounted() {
    this.cartCount = parseInt(localStorage.getItem("cartCount")) || 0;
    this.intervalId = setInterval(() => {
      const updatedCount = parseInt(localStorage.getItem("cartCount")) || 0;
      if (this.cartCount !== updatedCount) {
        this.cartCount = updatedCount;
      }
    }, 200);
    const user = localStorage.getItem("user");
    if (user) {
      const parsedUser = JSON.parse(user);
      this.userRole = parsedUser.codeName;
    } else {
      console.error("No user data found in localStorage.");
    }
  },
  beforeUnmount() {
    clearInterval(this.intervalId);
  },
};
</script>

<style>
/* 기존 스타일 유지 */
.second_menu_box {
  font-size: 25px;
  font-family: hanna;
  padding-left: 23%;
  position: relative;
}

.nav-link {
  text-decoration: none !important;
  color: inherit !important;
}

.nav-link:hover {
  text-decoration: none !important;
  color: #ffeb33 !important;
}

.nav-menu {
  text-decoration: none !important;
  color: inherit !important;
}

#person-icon {
  font-size: 30px;
}

/* 최상단 메뉴 */
.upper_menu {
  background-color: #000000;
}

.upper_menu_link {
  display: inline-flex;
  font-size: 11px;
  margin-left: 67%;
}

.upper_menu_link .nav-link {
  color: #ffffff !important;
}

.upper_menu_link .nav-link:hover {
  color: #ffeb33 !important;
}

.upper_menu_link .nav-item {
  margin-right: -25px;
}

.navbar-brand {
  font-size: 50px;
  color: #d9cab6;
  display: inline-block;
  transform: scaleY(1.8);
  font-family: euljiro;
}

.search_bar {
  width: 135%;
  height: 45px;
}

.search_glass {
  right: 1px;
}

.search_bar .search_text {
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  border: 2.5px solid #ffc100;
}

.search_bar .search_glass {
  border-top-right-radius: 50px;
  border-bottom-right-radius: 50px;
  border: 2.5px solid #ffc100;
}

.badge {
  font-size: 1rem;
  padding: 0.4rem;
}

.cart-count {
  font-size: 0.9rem;
}
</style>
