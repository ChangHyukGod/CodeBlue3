<template>
  <div class="bigbox2">
    <hr />
    <div class="announce_body_box2">
      <div class="notice_container2">
        <div class="notice_search2">
          <form class="search_input2" @submit.prevent="searchAnnouncement">
            <router-link :to="'/faq/list'" class="custom-link2">
              <p class="ano_top_title2">공지사항</p>
            </router-link>
            <div class="input_box2 typing2 form-group2 search_bar_announce2">
              <input
                placeholder="제목, 내용"
                v-model="searchKeyword"
                class="input_text2 form-control2"
              />
              <i class="bi bi-search search_glass_announce2"></i>
            </div>
          </form>
          <br />
        </div>

        <div class="notice_content2">
          <div class="announcement-list2">
            <div v-for="(data, index) in announcementList" :key="index">
              <router-link :to="'/announcement/' + data.ano" class="custom-link2">
                <h2 class="ano_title2">&nbsp;&nbsp;{{ data.title }}</h2>
                <p class="ano_date2">{{ data.createDate }}</p>
              </router-link>
              <hr class="notice_line2" />
            </div>
          </div>
          <p v-if="announcementList.length === 0">
            등록된 공지사항이 없습니다.
          </p>
          <router-link :to="'/faq'">
            <button type="button" class="btn btn-warning button2">
              <i class="bi bi-arrow-return-left"></i>
            </button>
          </router-link>
        </div>
        <br />

        <!-- 페이징 -->
        <div class="notice_paging2">
          <ul class="paging2 pagination2">
            <!-- 이전 버튼 -->
            <li
              class="page-arrow2 page-item2"
              :class="{ disabled: pageIndex === 1 }"
            >
              <a
                class="page-link2"
                href="#"
                @click.prevent="goToPage(pageIndex - 1)"
              >
                &laquo;
              </a>
            </li>

            <!-- 페이지 번호 -->
            <li
              v-for="page in totalPages"
              :key="page"
              class="page-item2"
              :class="{ active: page === pageIndex }"
            >
              <a class="page-link2" href="#" @click.prevent="goToPage(page)">
                {{ page }}
              </a>
            </li>

            <!-- 다음 버튼 -->
            <li
              class="page-arrow2 page-item2"
              :class="{ disabled: pageIndex === totalPages }"
            >
              <a
                class="page-link2"
                href="#"
                @click.prevent="goToPage(pageIndex + 1)"
              >
                &raquo;
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AnnouncementService from "@/services/faq/AnnouncementService";

export default {
  data() {
    return {
      pageIndex: 1, // 현재 페이지
      totalPages: 5, // 전체 페이지 수
      searchKeyword: "", // 검색어
      announcementList: [], // 공지사항 데이터 리스트
    };
  },
  methods: {
    async getAnnouncements() {
      try {
        const response = await AnnouncementService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          10 // 한 페이지에 표시할 데이터 개수
        );
        const { results, totalCount } = response.data;
        this.announcementList = results || [];
        this.totalPages = Math.ceil(totalCount / 10);
      } catch (error) {
        console.error("공지사항 데이터를 가져오는 중 에러 발생:", error);
      }
    },
    goToPage(page) {
      if (page > 0 && page <= this.totalPages) {
        this.pageIndex = page;
        this.updateQuery();
        this.getAnnouncements();
      }
    },
    searchAnnouncement() {
      this.pageIndex = 1;
      this.updateQuery();
      this.getAnnouncements();
    },
    updateQuery() {
      this.$router.push({
        path: "announcement",
        query: { search: this.searchKeyword },
      });
    },
  },

  mounted() {
    // 초기화 시 URL 쿼리값을 동기화
    this.searchKeyword = this.$route.query.search || "";
    this.getAnnouncements();
  },
};
</script>

<style>
.ano_top_title2 {
  font-weight: bolder;
  font-size: x-large;
  position: absolute;
  margin: 3px 0 0 30px;
}
/* 공지 전체 */
.bigbox2 {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.lla2,
.new2,
.announce2 {
  color: #ffeb33;
  -webkit-text-stroke: 0.4px black;
  font-size: 16px;
  font-family: dohyeon;
}
/* 타이틀 */
.title2 {
  text-align: center;
  font-weight: bolder;
  font-size: 25px;
}
/* 전체 박스 */
.announce_body_box2 {
  width: 70%;
  border: 2.5px solid black;
  border-radius: 10px;
  padding: 15px;
}
/* 검색창 */
.search_bar_announce2 {
  width: 35%;
  margin: 1px auto;
  border-radius: 25px;
  border: none;
  position: relative;
  background-color: white;
  margin-right: 10px;
  margin-bottom: 10px;
}
/* 돋보기 아이콘 */
.search_glass_announce2 {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  font-size: 1.2rem;
  color: #ffeb33;
  cursor: pointer;
}
/* 입력 필드 */
.input_text2 {
  margin: 10px auto;
  border-radius: 25px;
  border: 1.5px solid #ccc;
  padding: 5px 15px;
  position: relative;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: white;
}
/* 공지사항 리스트 스타일 */
.notice_table2 {
  list-style: none;
  display: flex;
  flex-direction: column;
  padding: 0;
  margin: 7px 0 0 10px;
}
.notice_line2 {
  margin: 3px;
}
.ano_title2 {
  font-size: 23px;
  padding: 5px;
}
.ano_title2:hover {
  transform: scale(1.01);
  transition: 0.2s;
}
.custom-link2 {
  text-decoration: none;
  color: inherit; /* 부모의 색상을 따릅니다 */
}
.custom-link2:visited,
.custom-link2:active {
  text-decoration: none; /* 모든 상태에서 밑줄 제거 */
  color: inherit;
}
.custom-link2:hover {
  transition: 0.3s;
}
/* 페이징 스타일 */
.notice_paging2 .pagination2 {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  padding: 10px;
}
.page-item2 {
  margin: 0 8px;
}
.page-link2 {
  color: #333;
  border: 1px solid #ccc;
  padding: 8px 16px;
  border-radius: 20px;
  font-size: 0.9rem;
  font-weight: bold;
  transition: all 0.3s ease;
}
.page-link2:hover {
  background-color: #f5f5f5;
  color: #333;
  transform: scale(1.1);
}
.page-item2.active .page-link2 {
  background-color: #ffeb33;
  color: #000;
  border: 1px solid #ffeb33;
  font-size: 1rem;
  font-weight: bold;
}
.page-item2.disabled .page-link2 {
  color: #ccc;
  cursor: not-allowed;
}
.button2 {
  position: relative;
  margin-top: 10px;
  left: 94.5%;
}
.ano_date2 {
  position: absolute;
  margin: -37.5px 0 0 780px;
  font-size: 13px;
}
</style>
