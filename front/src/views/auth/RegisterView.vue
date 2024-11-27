<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-xl-6 col-lg-8 col-md-10">
        <div class="card custom-card mt-5">
          <div class="card-body p-0">
            <div class="row">
              <!-- TODO: email/password 입력양식  -->
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">회원가입</h1>
                  </div>
                  <div class="user">
                    <div class="form-group">
                      <input
                        type="email"
                        class="form-control form-control-user mb-3"
                        placeholder="이메일을 입력하세요."
                        v-model="user.email"
                      />
                    </div>
                    <div class="form-group">
                      <input
                        type="password"
                        class="form-control form-control-user mb-3"
                        placeholder="패스워드를 입력하세요."
                        v-model="user.password"
                      />
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-user mb-3"
                          placeholder="이름을 입력하세요."
                          v-model="user.name"
                        />
                      </div>
                      <div class="form-group">
                        <input
                          type="password"
                          class="form-control form-control-user mb-3"
                          placeholder="주민등록번호를 입력하세요."
                          v-model="user.ssn"
                        />
                      </div>
                    </div>
                    <button
                      button
                      class="btn btn-custom w-100 mb-3"
                      @click="register"
                    >
                      Sign Up
                    </button>
                    <!-- 에러 메시지 표시 -->
                    <div
                      v-if="errorMessage"
                      class="alert alert-danger text-center"
                      role="alert"
                      v-html="errorMessage"
                    ></div>
                  </div>
                  <hr />
                  <div class="text-center">
                    <a href="/login">
                      이미 계정이 있으신가요? 로그인 하세요!
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MemberService from "@/services/auth/MemberService";
export default {
  data() {
    return {
      user: {
        email: "",
        password: "",
        name: "",
        ssn: "",
        codeName: "ROLE_USER",
      },

      errorMessage: "", // 에러 메시지 상태 관리
    };
  },
  methods: {
    async register() {
      try {
        let response = await MemberService.insert(this.user);
        console.log(response.data);

        alert("회원가입이 되었습니다.");
        this.$router.push("/login");
      } catch (error) {
        this.errorMessage = `
          이미 가입된 이메일이 있습니다.
        `;

        console.log(error);
      }
    },
  },
  mounted() {
    if (this.$store.state.loggedIn == true) {
      this.$router.push("/");
    }
  },
};
</script>

<style>
/* 에러 메시지 디자인 시작 */
.alert {
  font-size: 0.9rem;
  border-radius: 5px;
  padding: 10px 15px;
}
.alert-danger {
  background-color: #f8d7da;
  color: #842029;
  border: 1px solid #f5c2c7;
}
.alert .bi {
  font-size: 1.2rem;
}
/* 에러 메시지 디자인 끝 */

/* 회원가입 버튼 디자인 시작 */
.btn-custom {
  background-color: black;
  color: white;
  border-radius: 50px;
  padding: 10px 20px;
  font-size: 16px;
  border: none;
}

.btn-custom:hover,
.btn-custom:focus,
.btn-custom:active {
  background-color: black;
  color: white;
  outline: none;
  box-shadow: none;
}
/* 회원가입 버튼 디자인 끝 */

/* 카드의 테두리 색상을 흰색으로 설정 */
.custom-card {
  background-color: #f8f9fa; /* 배경색: 라이트그레이 */
  border: 1px solid white; /* 테두리 흰색 */
  border-radius: 30px; /* 둥근 모서리 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 은은한 그림자 */
}
</style>
