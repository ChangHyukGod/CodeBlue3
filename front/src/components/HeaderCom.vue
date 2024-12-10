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

        <!-- 메인화면 카드 데이터 추가(관리자 로그인시 보임) -->
        <b-nav-item href="/add-main" v-if="userRole === 'ROLE_ADMIN'">추가</b-nav-item>
        <b-nav-item>|</b-nav-item>

        <b-nav-item href="/cart">
          <i class="bi bi-cart"></i>
          <span class="badge bg-danger cart-count">{{ cartCount }}</span>
        </b-nav-item>
        <!-- 메인화면 카드 데이터 추가(임시 위치) -->
        <b-nav-item v-if="userRole === 'ROLE_ADMIN'" href="/mainadmin">관리자 페이지</b-nav-item>
      </div>
    </b-nav>
  </div>

  <div class="full-head">
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <img
          src="@/assets/icon.png"
          class="gpt-icon"
          href="/"
          onclick="location.href='/'"
        />
        &nbsp;&nbsp;
        <a class="navbar-brand" href="/"> 지조결 L.L.A</a>
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

        <!-- TODO: 대메뉴 -->
        <div
          class="collapse navbar-collapse second_menu_box"
          id="navbarSupportedContent"
        >
          <router-link to="/coupon" class="nav-menu">
            <a class="nav-link" aria-current="page">쿠폰</a>
          </router-link>
          &nbsp;|&nbsp;
          <router-link to="/recommendgoogleview" class="nav-menu">
            <a class="nav-link" aria-current="page">여행지검색</a> </router-link
          >&nbsp;|&nbsp;
          <router-link to="/review" class="nav-menu">
            <a class="nav-link" aria-current="page">리뷰게시판</a> </router-link
          >&nbsp;|&nbsp;
          <router-link to="/mypage" class="nav-menu">
            <a class="nav-link" aria-current="page">
              <i class="bi bi-person-plus" id="person-icon"></i>
            </a>
          </router-link>
        </div>
        <!-- 개인정보 링크 아이콘 -->
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

      // 공유변수
      this.$store.state.loggedIn = false;

      localStorage.removeItem("cartCount"); // 로컬스토리지에서 cartCount 삭제

      // 로그인 페이지로 이동
      window.location.href = "/login"; // 페이지 강제 이동 (새로고침 포함) => **라우터로 하면 로그인 페이지로 잘 안 넘어갈 때가 있음**
    },
  },
  mounted() {
    // 로컬스토리지에서 cartCount 값을 가져와 초기화
    // parseInt로 문자열을 숫자로 변환하고, 값이 없으면 기본값으로 0을 설정
    this.cartCount = parseInt(localStorage.getItem("cartCount")) || 0;

    // 0.5초마다 로컬스토리지의 cartCount 값을 확인하는 setInterval 설정
    this.intervalId = setInterval(() => {
      // 로컬스토리지에서 현재 장바구니 개수를 다시 가져옴
      const updatedCount = parseInt(localStorage.getItem("cartCount")) || 0;

      // 현재 데이터 (this.cartCount)와 로컬스토리지 값 (updatedCount)을 비교
      if (this.cartCount !== updatedCount) {
        // 두 값이 다르면 로컬스토리지 값을 업데이트하여 UI 반영
        this.cartCount = updatedCount;
      }
    }, 200); // 200ms (0.2초) 간격으로 반복 실행
    // 관리자 권한 : 로컬스토리지에서 토큰 정보 가져오기
    const user = localStorage.getItem("user"); // 저장된 사용자 정보 가져오기
    if (user) {
      const parsedUser = JSON.parse(user); // JSON 문자열을 객체로 파싱
      this.userRole = parsedUser.codeName; // 권한 정보 저장
    } else {
      console.error("No user data found in localStorage.");
    }
  },
  beforeUnmount() {
    // 이 Vue 컴포넌트가 화면에서 제거될 때, 불필요한 interval을 정리하여 메모리 누수 방지
    clearInterval(this.intervalId);
  },
};
</script>

<style>
.second_menu_box {
  font-size: 25px;
  font-family: hanna;
  padding-left: 23%;
  position: relative;
}

.nav-link {
  text-decoration: none !important; /* 기본 밑줄 강제 제거 */
  color: inherit !important; /* 색상 강제 적용 */
}

.nav-link:hover {
  text-decoration: none !important; /* hover 시 밑줄 강제 제거 */
  color: #ffeb33 !important; /* hover 시 색상 변경 */
}

.nav-menu {
  text-decoration: none !important; /* 활성 상태 강제 밑줄 제거 */
  color: inherit !important;
}
#person-icon {
  font-size: 30px;
}
/* 최상단 메뉴 */
.upper_menu {
  background-color: #000000;
}
/* 최상단 메뉴 링크 */
.upper_menu_link {
  display: inline-flex;
  font-size: 11px;
  margin-left: 67%;
}
.upper_menu_link .nav-link {
  color: #ffffff !important; /* 원하는 색상 (주황색) */
}
/* 링크 색상 변경 - hover 상태 */
.upper_menu_link .nav-link:hover {
  color: #ffeb33 !important; /* hover 시 색상 (빨간색) */
}
.upper_menu_link .nav-item {
  margin-right: -25px; /* 아이템 간 오른쪽 간격 */
}

/* 메인 아이콘 */
.gpt-icon {
  width: 3.5%;
  height: 3.5%;
  margin-left: 16.5%;
}
/* 사이트 타이틀 */
.navbar-brand {
  font-size: 50px;
  color: #d9cab6;
  display: inline-block;
  transform: scaleY(1.8);
  font-family: euljiro;
}
/* 검색창 */
.search_bar {
  width: 135%;
  height: 45px;
}
/* 돋보기 */
.search_glass {
  right: 1px;
}
.search_bar .search_text {
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  border: 2.5px solid #ffeb33;
}
.search_bar .search_glass {
  border-top-right-radius: 50px;
  border-bottom-right-radius: 50px;
  border: 2.5px solid #ffeb33;
}
.badge {
  font-size: 1rem; /* 글자 크기 */
  padding: 0.4rem; /* 여백 */
}
.cart-count {
  font-size: 0.9rem;
}
</style>
