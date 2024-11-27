<template>
  <div class="bigbox">
    <h1 class="title">공지사항</h1>
    <hr />
    <div class="announce_body_box">
      <div class="notice_container">
        <div class="notice_search">
          <div class="notice_tab_area nav">
            <div class="sub_tab_list" role="tablist"></div>
          </div>
          <!-- 검색창 -->
          <form class="search_input">
            <div class="input_box typing form-group search_bar_announce">
              <input
                placeholder="제목, 내용"
                name="searchValue"
                class="input_text form-control"
                value=""
              /><i class="bi bi-search search_glass_announce"></i>
            </div>
          </form>
        </div>
        <div class="notice_content">
          <ul class="notice_table">
            <li class="notice_item">
              <a href="#" class="notice_title"
                ><p class="NoticeTableItem_title__KrN-Y">
                  <span>쇼핑투데이 내 '오늘의 팝업'판이 출시됩니다!</span>
                </p>
                <span class="date">2024.09.20</span></a
              >
            </li>
          </ul>
        </div>
        <div class="notice_paging">
          <ul class="paging pagination">
            <li class="page_arrow prev disabled page-item disabled">
              <span class="page-link" disabled=""
                ><span class="blind">이전 페이지로 이동</span></span
              >
            </li>
            <li class="page-item active">
              <span class="page-link"
                >1<span class="sr-only">(current)</span></span
              >
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">2</a>
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">3</a>
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">4</a>
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">5</a>
            </li>
            <li class="page_arrow next page-item">
              <a class="page-link" role="button" href="#"
                ><span class="blind">다음 페이지로 이동</span></a
              >
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
/* 공지 전체 */
.bigbox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
/* 타이틀 */
.title {
  text-align: center;
  margin: 20px 0px;
  font-weight: bold;
}
/* 부제타이틀 */
.subtitle {
  text-align: start;
  font-weight: bolder;
}
/* 전체 박스 */
.announce_body_box {
  width: 70%;
  border: 2.5px solid black;
  border-radius: 10px;
  padding: 15px;
}
/* 검색창 */
/* 돋보기 아이콘 위치 조정 및 검색창 스타일 개선 */
/* 검색창 */
.search_bar_announce {
  width: 70%; /* 검색창의 너비 */
  margin: 10px auto; /* 검색창을 중앙 정렬 */
  border-radius: 25px; /* 테두리 둥글게 */
  display: flex;
  align-items: end;
  border: 1.5px solid #ccc; /* 테두리 색상 */
  padding: 5px 15px; /* 내부 여백 */
  position: relative; /* 자식 요소의 절대 위치 기준으로 설정 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
  background-color: white;
}

/* 돋보기 아이콘 */
.search_glass_announce {
  position: absolute; /* 검색창 내부에서 절대 위치 설정 */
  right: 10px; /* 검색창 내부에서 오른쪽 여백 */
  top: 50%; /* 검색창의 세로 중앙 정렬 */
  transform: translateY(-50%); /* 세로 중앙 정렬 보정 */
  font-size: 1.2rem; /* 아이콘 크기 */
  color: #999; /* 색상 */
  cursor: pointer; /* 클릭 가능한 상태 */
}

/* 입력 필드 */
.input_text {
  width: 70%; /* 검색창의 너비 */
  margin: 10px auto; /* 검색창을 중앙 정렬 */
  border-radius: 25px; /* 테두리 둥글게 */
  display: flex;
  align-items: end;
  border: 1.5px solid #ccc; /* 테두리 색상 */
  padding: 5px 15px; /* 내부 여백 */
  position: relative; /* 자식 요소의 절대 위치 기준으로 설정 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
  background-color: white;
}

/* 공지사항 리스트 스타일 */
.notice_table {
  list-style: none;
  padding: 0;
  margin: 15px 0;
}

.notice_item {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.notice_title {
  text-decoration: none;
  color: #333;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
}

.notice_title:hover {
  color: #007bff; /* 링크에 마우스를 올렸을 때 색상 변경 */
}

.date {
  font-size: 0.9rem;
  color: #666;
  margin-left: auto;
}

/* 페이징 스타일 */
.notice_paging .pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.page-item {
  margin: 0 5px;
}

.page-link {
  color: #007bff;
  text-decoration: none;
  border: 1px solid #ddd;
  padding: 5px 10px;
  border-radius: 5px;
  transition: all 0.3s;
}

.page-link:hover {
  background-color: #007bff;
  color: white;
  border-color: #007bff;
}

.page-item.active .page-link {
  background-color: #007bff;
  color: white;
  border-color: #007bff;
}
</style>
