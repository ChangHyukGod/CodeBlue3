// views/advanced/fileDb/FileDbView.vue // vueInit
<template>
  <div>
    <!-- TODO: 검색어 입력상자 -->
    <div class="input-group mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="검색어"
        v-model="searchKeyword"
      />
      <button
        class="btn btn-outline-secondary"
        type="button"
        @click="getrecommend"
      >
        검색
      </button>
    </div>
    <!-- TODO: 카드 -->
    <div class="row">
      <div v-for="(data, index) in recommends" :key="index" class="col-6">
        <div class="card" style="width: 18rem">
          <img :src="data.imageUrl" class="card-img-top" alt="카드" />
          <div class="card-body">
            <h5 class="card-title">{{ data.tdName }}</h5>
            <p class="card-text">
              {{ data.loc }}
              <br />
              <br />
              {{ data.description }}
            </p>

            <router-link
              :to="'/recommend/recommendadd' + data.tdId"
              class="btn btn-primary"
            >
              수정
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <!-- TODO: 페이지 번호 : 부트스트랩뷰(페이지)  -->
    <div class="mt-3">
      <!-- TODO: v-model="뷰변수(현재페이지번호)"
                 ,total-rows="전체개수"
                 ,per-page="1페이지당화면에보일개수"
                    -->
      <b-pagination
        v-model="pageIndex"
        :total-rows="totalCount"
        :per-page="recordCountPerPage"
        @click="getrecommend"
      ></b-pagination>
    </div>
  </div>
</template>
<script>
import RecommendService from "@/services/recommend/RecommendService";
export default {
  data() {
    return {
      pageIndex: 1,
      totalCount: 0,
      recordCountPerPage: 3,
      searchKeyword: "",
      recommends: [], // 빈배열
    };
  },
  methods: {
    async getrecommend() {
      try {
        let response = await RecommendService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recordCountPerPage
        );
        // TODO: 벡엔드 전송되는것: results(배열), totalCount(총개수)
        const { results, totalCount } = response.data;
        console.log(response.data); // 디버깅
        this.recommends = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    this.getrecommend();
  },
};
</script>
<style></style>
