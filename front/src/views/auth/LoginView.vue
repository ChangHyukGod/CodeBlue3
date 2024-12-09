<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-xl-6 col-lg-8 col-md-10">
        <div class="card custom-card mt-5">
          <div class="card-body p-0">
            <div class="row">
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 mb-4">로그인</h1>
                  </div>
                  <div class="user">
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control form-control-user mb-3"
                        placeholder="이메일을 입력하세요."
                        v-model="user.email"
                      />
                    </div>
                    <div class="form-group">
                      <input
                        type="password"
                        class="form-control form-control-user mb-3"
                        placeholder="비밀번호를 입력하세요."
                        v-model="user.password"
                      />
                    </div>
                    <button class="btn btn-custom w-100 mb-3" @click="login">
                      Login
                    </button>
                    <div
                      v-if="errorMessage"
                      class="alert alert-danger text-center mt-3"
                      role="alert"
                      v-html="errorMessage"
                    ></div>
                  </div>
                  <hr />
                  <div class="text-center">
                    <div class="d-flex justify-content-center">
                      <a class="small mx-2" href="/register">회원가입</a>
                      <span>|</span>
                      <a
                        class="small mx-2"
                        href="#"
                        @click="showFindEmailModal = true"
                      >
                        아이디 찾기
                      </a>
                      <span>|</span>
                      <a
                        class="small mx-2"
                        href="#"
                        @click="showUpdatePasswordModal = true"
                      >
                        비밀번호 변경
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

    <!-- 이메일 찾기 모달 -->
    <div
      v-if="showFindEmailModal"
      class="modal fade show"
      tabindex="-1"
      aria-labelledby="findEmailModalLabel"
      aria-hidden="true"
      style="display: block"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="findEmailModalLabel">아이디 찾기</h5>
            <button
              type="button"
              class="btn-close"
              @click="showFindEmailModal = false"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="form-group mb-3">
              <label for="name">이름</label>
              <input
                type="text"
                id="findEmail"
                class="form-control"
                v-model="findEmailData.name"
                placeholder="이름을 입력하세요."
              />
            </div>
            <div class="form-group mb-3">
              <label for="name">주민등록번호</label>
              <input
                type="password"
                id="findEmail"
                class="form-control"
                v-model="findEmailData.ssn"
                placeholder="주민등록번호를 입력하세요."
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              @click="showFindEmailModal = false"
            >
              닫기
            </button>
            <button type="button" class="btn btn-primary" @click="findEmail">
              확인
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 비밀번호 변경 모달 -->
    <div
      v-if="showUpdatePasswordModal"
      class="modal fade show"
      tabindex="-1"
      aria-labelledby="findPasswordModalLabel"
      aria-hidden="true"
      style="display: block"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="findPasswordModalLabel">
              비밀번호 변경
            </h5>
            <button
              type="button"
              class="btn-close"
              @click="showUpdatePasswordModal = false"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="form-group mb-3">
              <label for="email">이메일</label>
              <input
                type="text"
                id="updatePassword"
                class="form-control"
                v-model="updatePasswordData.email"
                placeholder="이메일을 입력하세요."
              />
            </div>
            <div class="form-group mb-3">
              <label for="name">주민등록번호</label>
              <input
                type="password"
                id="updatePassword"
                class="form-control"
                v-model="updatePasswordData.ssn"
                placeholder="주민등록번호를 입력하세요."
              />
            </div>
            <div class="form-group mb-3">
              <label for="updatePassword">변경할 비밀번호</label>
              <input
                type="password"
                id="updatePassword"
                class="form-control"
                v-model="updatePasswordData.password"
                placeholder="변경할 비밀번호를 입력하세요."
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              @click="showUpdatePasswordModal = false"
            >
              닫기
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="updatePassword"
            >
              변경
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MemberService from "@/services/auth/MemberService";
import CartService from "@/services/cart/CartService";

export default {
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
      errorMessage: "", // 에러 메시지 상태 관리
      findEmailData: {
        name: "", // 이름
        ssn: "", // 주민번호
      },
      updatePasswordData: {
        email: "", // 이메일
        ssn: "", // 주민등록번호
        password: "", // 변경할 비밀번호
      },
      showFindEmailModal: false, // 이메일 찾기 모달의 표시 상태
      showUpdatePasswordModal: false, // 비밀번호 변경 모달의 표시 상태
    };
  },

  methods: {
    async login() {
      try {
        let response = await MemberService.login(this.user);



        const { email, accessToken, tokenType } = response.data; // 서버에서 받은 데이터 구조 분해

// 이메일과 토큰을 Local Storage에 저장
localStorage.setItem("userEmail", email);
localStorage.setItem("authToken", `${tokenType} ${accessToken}`);


        this.$store.state.loggedIn = true;
        localStorage.setItem("user", JSON.stringify(response.data));

        // 로컬스토리지에서 user 객체를 가져오고, 그것을 출력
        const user = JSON.parse(localStorage.getItem("user"));

        let cartResponse = await CartService.countCartItems(user.email);

        localStorage.setItem("cartCount", cartResponse.data || 0);

        this.errorMessage = ""; // 에러 메시지 초기화
        this.$router.push("/"); // 로그인 성공 후 홈으로 리다이렉트
      } catch (error) {
        this.$store.state.loggedIn = false;
        this.errorMessage = `로그인에 실패했습니다.<br />
    아이디 또는 비밀번호를 맞게 입력해 주세요.`;
        console.log(error);
      }
    },

    async findEmail() {
      try {
        let response = await MemberService.findEmail(this.findEmailData);
        let emailList = "아이디 목록:\n";
        response.data.forEach((email) => {
          emailList += `${email}\n`;
        });

        // 알림창 표시 후 모달 닫기
        alert(emailList);

        // 모달 닫고, 리다이렉트
        this.showFindEmailModal = false;

        // 0.5초 후 홈 페이지로 리다이렉트
        setTimeout(() => {
          this.$router.push("/login");
        }, 500);
      } catch (error) {
        console.error(error);
        alert("아이디를 찾을 수 없습니다.");
      }
    },

    async updatePassword() {
      try {
        let response = await MemberService.updatePassword(
          this.updatePasswordData
        );
        if (response.status === 200) {
          alert("비밀번호 변경이 성공했습니다.");
          this.showUpdatePasswordModal = false; // 모달 닫기
          setTimeout(() => {
            this.$router.push("/login");
          }, 500); // alert 후 0.5초 뒤에 리다이렉트
        } else {
          alert(response.data || "비밀번호를 변경할 수 없습니다.");
        }
      } catch (error) {
        console.error(error);
        alert("비밀번호를 변경할 수 없습니다. 잠시 후 다시 시도해 주세요.");
      }
    },
  },

  mounted() {
    if (this.$store.state.loggedIn === true) {
      this.$router.push("/"); // 이미 로그인 상태라면 홈 페이지로 리다이렉트
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

/* 로그인 버튼 디자인 시작 */
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
/* 로그인 버튼 디자인 끝 */

/* 카드의 테두리 색상을 흰색으로 설정 */
.custom-card {
  background-color: #f8f9fa; /* 배경색: 라이트그레이 */
  border: 1px solid white; /* 테두리 흰색 */
  border-radius: 30px; /* 둥근 모서리 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 은은한 그림자 */
}

/* 모달 디자인 시작 */
.modal-content {
  border-radius: 15px;
  padding: 20px;
}
.modal-header {
  border-bottom: none;
}
.modal-footer {
  border-top: none;
}
/* 모달 디자인 끝 */
</style>
