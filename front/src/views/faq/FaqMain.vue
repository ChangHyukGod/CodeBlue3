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
        <b-button variant="outline-dark" class="custom-button" href="/announcement">
          <i class="bi bi-megaphone custom-icon"></i><br />공지사항
        </b-button>
      </b-button-group>
    </div>
  </div>
  <!-- 검색창 박스 -->
  <div class="search_box">
    <form class="d-flex search_bar" method="get" action="/faqlogin">
      <input
        class="form-control me-2 search_text"
        name="searchKeyword"
        type="search"
        placeholder="궁금한 것을 물어보세요"
        aria-label="Search"
      />
      <button class="btn btn-outline-warning search_glass" type="submit">
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
        <!-- 해시태그 -->
        <div class="hashtag-nav" id="category">
          <button
            class="hashtag-button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide-to="0"
          >
            <i class="bi bi-building-exclamation" id="hashtag-icon"></i> 예약
            문의
          </button>
          <button
            class="hashtag-button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide-to="1"
          >
            <i class="bi bi-globe-americas" id="hashtag-icon"></i> 해외 문의
          </button>
          <button
            class="hashtag-button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide-to="2"
          >
            <i class="bi bi-arrow-clockwise" id="hashtag-icon"></i>
            <i class="bi bi-wallet2" id="hashtag-icon"></i> 결제/환불
          </button>
          <button
            class="hashtag-button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide-to="3"
          >
            <i class="bi bi-person-exclamation" id="hashtag-icon"></i> 계정 관리
          </button>
        </div>
        <!-- 해시태그 카드 모음 -->
        <div class="carousel-inner">
          <!-- 첫번째 페이지 -->
          <div class="carousel-item active" data-bs-interval="999999">
            <div class="grid-container">
              <!-- <h1 class="help-title">계정 관리</h1> -->
              <div class="card">
                <b-card>
                  <b-card-text> 제목 틀 </b-card-text>
                  <b-card-text> 답변 들 </b-card-text>
                  <b-button href="#" variant="primary">Go somewhere</b-button>
                </b-card>

              </div>
            </div>
          </div>
          <!-- 두번째 페이지 -->
          <div class="carousel-item" data-bs-interval="999999">
            <div class="grid-container"></div>
          </div>
          <!-- 세번째 페이지 -->
          <div class="carousel-item" data-bs-interval="999999">
            <div class="grid-container"></div>
          </div>
          <!-- 네번째 페이지 -->
          <div class="carousel-item" data-bs-interval="999999">
            <div class="grid-container"></div>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {};
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
.search_text {
  background-color: #ffffffb7;
  font-size: 17px;
  color: black;
  border: 2.5 px solid #ffeb33;
  width: 89%;
}
/* 검색창 돋보기 */
.search_glass {
  right: -0.5px;
}
/* 자주 찾는 질문 전체 박스 */
#category {
  background-color: white;
  border: 1px solid #ccc;
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
.card {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  margin: 20px;
}
</style>
