<template>
  <div v-for="(data, index) in announcementList" :key="index">
    <h1>{{ data.title }}</h1>
    <p>{{ data.content }}</p>
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
  },
  mounted() {
    // 초기화 시 URL 쿼리값을 동기화
    this.searchKeyword = this.$route.query.search || "";
    this.getAnnouncements();
  },
};
</script>
