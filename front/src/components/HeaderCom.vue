<template>
  <div>
    <nav class="navbar navbar-expand-lg bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Simple</a>
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
        <!-- 대메뉴 -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <!-- 대메뉴(왼쪽) -->
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="/">Home</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                예제
              </a>
              <!-- TODO: 부서 -->
              <ul class="dropdown-menu">
                <!-- <li><a class="dropdown-item" href="/dept">부서</a></li>
                <li><a class="dropdown-item" href="/add-dept">부서추가</a></li>
                <li><a class="dropdown-item" href="/emp">사원</a></li>
                <li><a class="dropdown-item" href="/add-emp">사원추가</a></li>
                <li><a class="dropdown-item" href="/fileDb">fileDb</a></li>
                <li>
                  <a class="dropdown-item" href="/add-fileDb">fileDb 추가</a>
                </li>
                <li><a class="dropdown-item" href="/gallery">gallery</a></li>
                <li>
                  <a class="dropdown-item" href="/add-gallery">gallery 추가</a>
                </li> -->
              </ul>
            </li>
          </ul>

          <!-- TODO: 로그인 메뉴(오른쪽) -->
          <ul class="navbar-nav">
            <!-- 회원가입 버튼 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == false">
              <a
                class="nav-link btn btn-outline-primary rounded-pill px-4 py-2 m-2"
                href="/register"
                >회원가입</a
              >
            </li>

            <!-- 로그인 버튼 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == false">
              <a
                class="nav-link btn btn-outline-primary rounded-pill px-4 py-2 m-2"
                href="/login"
              >로그인
              </a
              >
            </li>

            <!-- 로그아웃 버튼 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == true">
              <a
                class="nav-link btn btn-outline-danger rounded-pill px-4 py-2 m-2"
                href
                @click="logout"
              >로그아웃
              </a>
            </li>
          </ul>
        </div>
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

<style></style>
