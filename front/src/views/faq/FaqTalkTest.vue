<template>
  <div class="bigbox">
    <hr />
    <div class="announce_body_box">
      <div class="notice_container">
        <div class="notice_search">
          <div class="notice_tab_area nav">
            <div class="sub_tab_list" role="tablist"></div>
          </div>
          <br />
        </div>
        <div class="notice_content">
          <p class="talk_big_title">문의 등록 테스트</p>
          <br />
          <div class="talk_list">
            <div
              v-for="(talk, index) in talkList"
              :key="index"
              class="talk_item"
            >
              제목 : {{ talk.title }}
              <hr />
              기본키 : {{ talk.tno }}
              <hr />
              내용 : {{ talk.content }}
              <hr />
              카테고리 : {{ talk.category }}
              <hr />
              사진 : {{ talk.image }}
              <hr />
              답변 : {{ talk.reply }}
              <hr />
              작성일 : {{ talk.createDate }}
            </div>
            <router-link :to="'/faq/talk'">
              <button type="button" class="btn btn-warning goto_button">
                <i class="bi bi-arrow-return-left"></i>
              </button>
            </router-link>
          </div>
        </div>
        <br />
      </div>
    </div>
  </div>
</template>

<script>
import TalkService from "@/services/faq/TalkService";

export default {
  data() {
    return {
      talkList: [], // 전체 데이터를 저장할 리스트
      searchKeyword: "", // 검색어
      pageIndex: 1, // 현재 페이지
      recordCountPerPage: 10, // 한 페이지에 표시할 레코드 수
    };
  },
  methods: {
    async fetchTalks() {
      try {
        const response = await TalkService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recordCountPerPage
        );
        const { results } = response.data; // 서버에서 반환된 결과
        this.talkList = results || [];
      } catch (error) {
        console.error("문의 전체 조회 실패:", error);
      }
    },
  },
  mounted() {
    this.fetchTalks(); // 컴포넌트가 마운트될 때 데이터 로드
  },
};
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
  display: inline-flex;
  top: 30px;
  right: 16%;
  position: relative;
}
/* 전체 박스 */
.announce_body_box {
  width: 70%;
  border: 2.5px solid black;
  border-radius: 10px;
  padding: 15px;
}
.notice_line {
  margin: 3px;
}
.notice_title {
  text-decoration: none;
  color: #333;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
}
.title_category_box {
  display: flex;
}
/* 제목 */
.talk_title {
  margin-left: 14%;
  margin-right: 2%;
  width: 40%;
}
/* 카테고리 */
.talk_category {
  width: 30%;
}
/* 내용, 사진 */
.talk_content,
.talk_picture {
  margin-left: 14%;
  width: 72%;
}
/* 라운드, 버튼 */
.talk_round,
.talk_button {
  margin-left: 14%;
}
.talk_button {
  display: flex;
}
.goto_button {
  margin-left: 1265%;
  color: blue;
}
.talk_big_title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
}
.talk_item {
  text-align: center;
}
</style>
