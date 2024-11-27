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
            <div class="input_box typing form-group">
              <input
                placeholder="제목, 내용"
                name="searchValue"
                class="input_text form-control"
                value=""
              /><button type="submit" class="btn_search btn btn-primary">
                <span class="blind">검색</span>
              </button>
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
            <li class="page-item">
              <a class="page-link" role="button" href="#">6</a>
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">7</a>
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">8</a>
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">9</a>
            </li>
            <li class="page-item">
              <a class="page-link" role="button" href="#">10</a>
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
.announce_body_box {
  width: 70%;
  border: 1.5px solid #ffeb33;
  border-radius: 25px;
  padding: 15px;
}

</style>
