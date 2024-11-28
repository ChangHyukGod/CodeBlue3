<template>
  <div class="container d-flex justify-content-center align-items-center">
    <!-- 버튼 그룹 박스 -->
    <div class="mt-3" id="main_button_group">
      <b-button-group size="lg" class="custom-button-group">
        <b-button variant="outline-dark" class="custom-button">
          <i class="bi bi-chat-square-dots custom-icon"></i> <br />1:1 문의
        </b-button>
        <b-button variant="outline-dark" class="custom-button">
          <i class="bi bi-receipt-cutoff custom-icon"></i><br />견적 문의
        </b-button>
        <b-button variant="outline-dark" class="custom-button">
          <i class="bi bi-cash-coin custom-icon"></i><br />결제 방법
        </b-button>
        <b-button variant="outline-dark" class="custom-button">
          <i class="bi bi-ticket-perforated custom-icon"></i><br />쿠폰 안내
        </b-button>
        <b-button
          variant="outline-dark"
          class="custom-button"
          href="/announcement"
        >
          <i class="bi bi-megaphone custom-icon"></i><br />공지사항
        </b-button>
      </b-button-group>
    </div>
  </div>
  <!-- 검색창 박스 -->
  <div class="search_box">
    <form class="d-flex search_bar" method="get" action="/faqlogin">
      <input
        class="form-control me-2 search_text_main"
        name="searchKeyword"
        type="search"
        placeholder="궁금한 것을 물어보세요"
        aria-label="Search"
      />
      <button class="btn btn-outline-warning search_glass_main" type="submit">
        <i class="bi bi-search"></i>
      </button>
    </form>
  </div>
  <!-- 한 줄 도움말 목록 -->
  <br />
  <hr />
  <br />
  <div class="one-line-box">
    <h1 class="body-title">자주 찾는 질문</h1>
    <form name="oneLineHelp">
      <div
        id="carouselExampleControls"
        class="carousel slide"
        data-bs-ride="carousel"
      >
        <!-- 해시태그 버튼-->
        <div class="hashtag-nav" id="category">
          <button
            v-for="(item, index) in cards"
            :key="item.id"
            class="hashtag-button"
            :data-bs-target="'#carouselExampleControls'"
            :data-bs-slide-to="index"
          >
            <i :class="item.icon" id="hashtag-icon"></i> {{ item.name }}
          </button>
        </div>

        <!-- 해시태그 카드 모음 -->
        <div class="carousel-inner">
          <div
            class="carousel-item"
            v-for="(item, index) in cards"
            :key="item.id"
            :class="{ active: index === 0 }"
            data-bs-interval="999999"
          >
            <div class="grid-container">
              <div
                class="card"
                v-for="(question, qIndex) in item.questions"
                :key="`question-${item.id}-${qIndex}`"
              >
                <b-card>
                  <!-- 제목 -->
                  <b-card-title class="card-title">
                    <i :class="question.icon"></i> {{ question.title }}
                  </b-card-title>
                  <hr />
                  <!-- 질문 -->
                  <p
                    v-for="(text, tIndex) in question.texts"
                    :key="`text-${item.id}-${qIndex}-${tIndex}`"
                    class="card-body"
                    data-bs-toggle="modal"
                    :data-bs-target="`#mo-${tIndex + 1}`"
                  >
                    - {{ text }}
                  </p>
                  <hr />
                  <b-button variant="primary" class="card-button"
                    >자세히 보기</b-button
                  >
                </b-card>
              </div>
            </div>
          </div>
        </div>

        <div>
          <!-- 모달 -->
          <div>
            <div
              v-for="modal in modals"
              :key="modal.id"
              class="modal fade"
              :id="modal.id"
              tabindex="-1"
              aria-labelledby="exampleModalLabel"
              aria-hidden="true"
            >
              <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">
                      <i :class="modal.icon"></i> {{ modal.title }}
                    </h1>
                    <button
                      type="button"
                      class="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div class="modal-body">
                    {{ modal.body }}
                  </div>
                  <a
                    :href="modal.link.url"
                    class="btn btn-outline-success"
                    id="card_button"
                  >
                    {{ modal.link.label }}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cards: [
        {
          id: "reservation",
          name: "예약 문의",
          icon: "bi bi-building-exclamation",
          questions: [
            {
              title: "예약 변경",
              icon: "bi bi-journal-richtext",
              texts: [
                "예약 변경은 출발 3일 전까지 가능합니다.",
                "단순 변심 변경은 7일 전까지 가능합니다.",
                "같은 날짜 다발성 예약은 불가합니다.",
              ],
            },
            {
              title: "취소 규정",
              icon: "bi bi-clipboard-check",
              texts: [
                "취소는 출발 5일 전까지 가능합니다.",
                "취소 후 재예약은 제한될 수 있습니다.",
                "수수료가 부과될 수 있습니다.",
              ],
            },
            {
              title: "객실 이용 시간",
              icon: "bi bi-clock",
              texts: [
                "체크인은 오후 3시부터 가능합니다.",
                "체크아웃은 오전 11시까지 완료해야 합니다.",
                "조기 체크인은 요청 가능합니다.",
              ],
            },
            {
              title: "예약 확인 방법",
              icon: "bi bi-eye",
              texts: [
                "예약 확인은 이메일로 안내됩니다.",
                "예약 정보는 마이페이지에서 확인 가능합니다.",
                "문의 시 예약번호를 준비해주세요.",
              ],
            },
          ],
        },
        {
          id: "overseas",
          name: "해외 문의",
          icon: "bi bi-globe-americas",
          questions: [
            {
              title: "비자 발급",
              icon: "bi bi-flag",
              texts: [
                "비자 발급 조건은 국가별로 다릅니다.",
                "비자 발급 소요시간은 2주입니다.",
                "필요한 서류 목록을 확인하세요.",
              ],
            },
            {
              title: "항공권 예약",
              icon: "bi bi-airplane",
              texts: [
                "저렴한 항공권 예약은 3개월 전에 가능합니다.",
                "환불 가능한 항공권을 선택하세요.",
                "좌석 업그레이드는 항공사에 문의하세요.",
              ],
            },
            {
              title: "여행자 보험",
              icon: "bi bi-shield",
              texts: [
                "여행자 보험은 필수로 가입하는 것을 권장합니다.",
                "보험료는 여행일정에 따라 달라집니다.",
                "보장 내용을 꼭 확인하세요.",
              ],
            },
          ],
        },
        {
          id: "payment-refund",
          name: "결제/환불",
          icon: "bi bi-credit-card",
          questions: [
            {
              title: "결제 수단",
              icon: "bi bi-wallet",
              texts: [
                "어떤 결제 수단을 사용할 수 있나요?",
                "결제 후 변경 가능한가요?",
                "해외 결제는 지원되나요?",
              ],
            },
            {
              title: "환불 요청",
              icon: "bi bi-arrow-return-left",
              texts: [
                "환불 요청은 어떻게 하나요?",
                "환불 처리 기간은 얼마나 걸리나요?",
                "부분 환불은 가능한가요?",
              ],
            },
            {
              title: "결제 오류",
              icon: "bi bi-exclamation-circle",
              texts: [
                "결제 오류 시 어떻게 해결하나요?",
                "결제 실패 후 다시 시도하려면?",
                "결제 기록은 어디서 확인하나요?",
              ],
            },
          ],
        },
        {
          id: "account-management",
          name: "계정 관리",
          icon: "bi bi-person",
          questions: [
            {
              title: "회원가입",
              icon: "bi bi-person-plus",
              texts: [
                "회원가입 절차는 어떻게 되나요?",
                "회원가입 시 제공 혜택은 무엇인가요?",
                "회원 정보는 안전하게 보호되나요?",
              ],
            },
            {
              title: "비밀번호 재설정",
              icon: "bi bi-shield-lock",
              texts: [
                "비밀번호를 잊었을 때 어떻게 하나요?",
                "재설정 링크가 오지 않을 경우?",
                "보안 절차는 어떻게 진행되나요?",
              ],
            },
            {
              title: "계정 탈퇴",
              icon: "bi bi-person-x",
              texts: [
                "계정 탈퇴는 어떻게 진행되나요?",
                "탈퇴 후 복구가 가능한가요?",
                "회원 데이터는 완전히 삭제되나요?",
              ],
            },
          ],
        },
      ],
      modals: [
        {
          id: "mo-1",
          title: "예약변경",
          body: "02-555-5000",
          icon: "bi bi-quora",
          link: {
            url: "/faqlogin?searchKeyword=계정관리",
            label: "#계정관리",
          },
        },
        {
          id: "mo-2",
          title: "예약 변경 안내",
          body: "예약 변경은 출발 3일 전까지 가능합니다. 변경 시 일부 수수료가 발생할 수 있습니다.",
          icon: "bi bi-calendar-event",
          link: {
            url: "/faqlogin?searchKeyword=예약변경",
            label: "#예약변경",
          },
        },
        {
          id: "mo-3",
          title: "다발성예약",
          body: "지속적인 다중 예약은 적발시 기능이 제한될 수 있습니다.",
          icon: "bi bi-calendar-event",
          link: {
            url: "/faqlogin?searchKeyword=깃",
            label: "#능",
          },
        },
        // 추가 모달 데이터
      ],
    };
  },
};
</script>

<style scoped>
/* 메인 아이콘 스타일 */
.custom-icon {
  font-size: 60px;
  color: #ffeb33;
  margin-bottom: 0.5rem; /* 아이콘과 텍스트 간격 */
}
/* 버튼 그룹 스타일 */
#main_button_group {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  /* flex-direction: row; 버튼을 가로로 배치 */
}
/* 컨테이너 스타일 */
.container {
  display: flex;
  justify-content: center;
  align-items: center;
}
/* 버튼 간격 설정 */
.custom-button-group .btn {
  margin: 0 20px;
  margin-bottom: 20px;
  width: 150px;
  height: 150px;
  text-align: center;
}
/* 버튼의 텍스트 스타일 */
.custom-button {
  font-size: 1.1rem;
  font-weight: bold;
  color: #333;
  border: 2px solid #ccc;
}
/* 버튼 호버 스타일 */
.custom-button:hover {
  background-color: #464444;
  border-color: #ccc;
  color: white;
}
/* 중앙 정렬 */
.custom-button-group {
  display: flex;
  justify-content: center;
  flex-wrap: wrap; /* 화면 크기에 따라 버튼 줄바꿈 */
}
/* 검색창 스타일 */
.search_box {
  justify-content: center;
  margin-top: 30px;
}
/* 검색바 */
.search_bar {
  width: 70%;
  height: 60px;
  margin-left: 17%;
}
/* 검색 창 */
.search_text_main {
  background-color: #ffffffb7;
  font-size: 17px;
  color: black;
  border: 2.5 px solid black;
  width: 89%;
}
.search_bar .search_text_main {
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  border: 2.5px solid black;
}
.search_bar .search_glass_main {
  border-top-right-radius: 50px;
  border-bottom-right-radius: 50px;
  border: 2.5px solid black;
}
/* 검색창 돋보기 */
.search_glass {
  right: -0.5px;
}
/* 자주 찾는 질문 전체 박스 */
#category {
  background-color: white;
  border-radius: 10px;
  padding: 10px;
  margin-top: 20px;
  margin-bottom: 20px;
}
/* 해시태그 버튼 */
.hashtag-button {
  margin-right: 10px;
  padding: 5px 10px;
  border: 1px solid black;
  border-radius: 5px;
  background-color: white;
  color: #333;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 19px;
}
/* 해시태그 아이콘 */
#hashtag-icon {
  color: #ffeb33;
  font-size: 25px;
}
/* 해시태그 호버 */
.hashtag-button:hover {
  background-color: black;
  color: white;
}
/* 해시태그 하이라이트 */
.hashtag-button.active {
  background-color: black;
  color: white;
}
/* 카드 그리드 박스 */
.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  margin: 20px 0;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}
/* 카드 스타일 */
.card {
  border: 1px solid black !important;
  border-radius: 10px;
  padding: 5px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  height: 100%; /* 카드 높이를 고정 */
}
.card * {
  border: none !important;
}
/* 카드 타이틀 */
.card-title {
  font-size: 23px;
  font-weight: bolder;
  margin-bottom: 10px;
}
/* 카드 내용 */
.card-body {
  font-size: 15px;
  color: #666;
  text-align: start;
  margin: 5px 0;
  padding: 0;
  line-height: 1.5;
  flex: 1;
}
/* 카드 버튼 */
.card-button {
  align-self: flex-end !important;
  margin-top: auto !important;
}

.carousel-item {
  transition: none !important; /* 애니메이션 효과 제거 */
}
</style>
