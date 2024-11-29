<template>
  <!-- 최상단 메뉴 -->
   
  <div>
    <b-nav class="upper_menu">
      <div class="upper_menu_link">
<<<<<<< HEAD

=======
>>>>>>> 610bbf4e094c3bc550131048533a7506482ade81
        <b-nav-item v-if="!this.$store.state.loggedIn" href="/login"
          >로그인
        </b-nav-item>

        <b-nav-item v-if="!this.$store.state.loggedIn">|</b-nav-item>

        <b-nav-item v-if="!this.$store.state.loggedIn" href="/register"
          >회원가입</b-nav-item
        >

        <b-nav-item v-if="!this.$store.state.loggedIn">|</b-nav-item>

        <b-nav-item v-if="this.$store.state.loggedIn" href @click="logout"
          >로그아웃</b-nav-item
        >

        <b-nav-item v-if="this.$store.state.loggedIn">|</b-nav-item>

<<<<<<< HEAD

        <b-nav-item href="/login">로그인</b-nav-item>
        <b-nav-item>|</b-nav-item>
        <b-nav-item href="/register">회원가입</b-nav-item>
        <b-nav-item>|</b-nav-item>

=======
>>>>>>> 610bbf4e094c3bc550131048533a7506482ade81
        <b-nav-item>예약확인</b-nav-item>
        <b-nav-item>|</b-nav-item>
        <b-nav-item href="/faq">고객센터</b-nav-item>
        <b-nav-item>|</b-nav-item>
        <!-- 메인화면 카드 데이터 추가(임시 위치) -->
        <b-nav-item href="/add-main">추가</b-nav-item>
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
            />
            <button class="btn btn-outline-warning search_glass" type="submit">
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
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item second-menu">
              <a class="nav-link active" aria-current="page" href="#">메뉴1</a>
              <a class="nav-link active" aria-current="page" href="#">메뉴2</a>
              <a class="nav-link active" aria-current="page" href="#">메뉴3</a>
              <a class="nav-link active" aria-current="page" href="#">메뉴4</a>
            </li>
          </ul>
        </div>
        <!-- 개인정보 링크 아이콘 -->
        <i class="bi bi-person-plus" id="person-icon"></i>
      </div>
    </nav>
  </div>
</template>

<script>
import MemberService from "@/services/auth/MemberService";
export default {
  methods: {
    logout() {
      MemberService.logout();

      // 공유변수
      this.$store.state.loggedIn = false;

      // 로그인 페이지 강제이동
      this.$router.push("/login");
    },
  },
};
</script>

<style>
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
/* 배너 옆 메뉴 */
.second-menu {
  display: inline-flex;
  font-size: 25px;
  font-family: hanna;
}
.navbar-collapse {
  margin-left: 20%;
}
#person-icon {
  font-size: 40px;
  color: black;
  margin-right: 18%;
}
</style>
