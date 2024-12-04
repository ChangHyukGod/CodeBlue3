<template>
  <div class="bigbox">
    <div class="title">
      <p @click="redirectToHome">공지사항</p>
      <button type="button" class="btn btn-link" @click="redirectToFaq">
        FAQ
      </button>
    </div>
    <hr />
    <div class="announce_body_box">
      <div class="notice_container">
        <div class="notice_search">
          <form class="search_input" @submit.prevent="searchAnnouncement">
            <div class="input_box typing form-group search_bar_announce">
              <input
                placeholder="제목, 내용"
                v-model="searchKeyword"
                class="input_text form-control"
              />
              <i class="bi bi-search search_glass_announce"></i>
            </div>
          </form>
          <br />
        </div>

        <div class="notice_content">
          <div class="accordion" id="announcementAccordion">
            <div
              class="accordion-item"
              v-for="(data, index) in announcementList"
              :key="index"
            >
              <h2 class="accordion-header" :id="'heading-' + index">
                <router-link
                  :to="`/announcement/${data.ano}`"
                  class="accordion-button collapsed"
                >
                  {{ data.title }}
                </router-link>
                <!-- <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  :data-bs-target="'#collapse-' + index"
                  :aria-expanded="false"
                  :aria-controls="'collapse-' + index"
                >
                  {{ data.title }}
                </button> -->
              </h2>
              <div
                :id="'collapse-' + index"
                class="accordion-collapse collapse"
                :aria-labelledby="'heading-' + index"
                data-bs-parent="#announcementAccordion"
              >
                <div class="accordion-body">
                  {{ data.content }}
                </div>
              </div>
            </div>
          </div>
          <p v-if="announcementList.length === 0">
            등록된 공지사항이 없습니다.
          </p>
        </div>
        <br />

        <!-- 페이징 -->
        <div class="notice_paging">
          <ul class="paging pagination">
            <!-- 이전 버튼 -->
            <li
              class="page-arrow page-item"
              :class="{ disabled: pageIndex === 1 }"
            >
              <a
                class="page-link"
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
              class="page-item"
              :class="{ active: page === pageIndex }"
            >
              <a class="page-link" href="#" @click.prevent="goToPage(page)">
                {{ page }}
              </a>
            </li>

            <!-- 다음 버튼 -->
            <li
              class="page-arrow page-item"
              :class="{ disabled: pageIndex === totalPages }"
            >
              <a
                class="page-link"
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
      totalPages: 1, // 전체 페이지 수
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
/* 공지 전체 */
.bigbox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.lla,
.new,
.announce {
  color: #ffeb33;
  -webkit-text-stroke: 0.4px black;
  font-size: 16px;
  font-family: dohyeon;
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
/* 검색창 */
.search_bar_announce {
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
.search_glass_announce {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  font-size: 1.2rem;
  color: #ffeb33;
  cursor: pointer;
}
/* 입력 필드 */
.input_text {
  margin: 10px auto;
  border-radius: 25px;
  border: 1.5px solid #ccc;
  padding: 5px 15px;
  position: relative;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: white;
}
/* 공지사항 리스트 스타일 */
.notice_table {
  list-style: none;
  display: flex;
  flex-direction: column;
  padding: 0;
  margin: 7px 0 0 10px;
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
/* 페이징 스타일 */
.notice_paging .pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  padding: 10px;
}
.page-item {
  margin: 0 8px;
}
.page-link {
  color: #333;
  border: 1px solid #ccc;
  padding: 8px 16px;
  border-radius: 20px;
  font-size: 0.9rem;
  font-weight: bold;
  transition: all 0.3s ease;
}
.page-link:hover {
  background-color: #f5f5f5;
  color: #333;
  transform: scale(1.1);
}
.page-item.active .page-link {
  background-color: #ffeb33;
  color: #000;
  border: 1px solid #ffeb33;
  font-size: 1rem;
  font-weight: bold;
}
.page-item.disabled .page-link {
  color: #ccc;
  cursor: not-allowed;
}
</style>
