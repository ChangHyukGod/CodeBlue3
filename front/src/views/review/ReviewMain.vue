<template>
  <div>
    <!-- 상단 텍스트와 이미지 컨테이너 -->
    <div class="d-flex align-items-center justify-content-between">
      <div>
        <h2 style="margin-bottom: 20px; font-weight: bold;">숙박업소후기</h2>
        <h5 style="margin-bottom: 20px;">숙박업소를 다녀온 고객분들의 솔직한 후기</h5>
      </div>
      <div class="image-container" style="margin-right: 20px">
        <img src="/images/icon-7680929_1920.png" alt="따봉"
          style="width: 200px; height: 200px; object-fit: contain; margin-right: 40px;" />
      </div>
    </div>

    <div class="container mt-5">
      <div class="row">
        <div class="col-md-3" v-for="review in topReviews" :key="review.reviewId">
          <div class="card" style="width: 15rem; border: none;">
            <img :src="review.imageUrl || '/images/침대.jfif'" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">
                <span>
                  <template v-for="star in 5" :key="star">
                    <i class="bi" :class="star <= review.rating ? 'bi-star-fill' : 'bi-star'"
                      style="color: #FFD700;"></i>
                  </template>
                </span><br>
                <span style="font-weight: bold; font-size: 1.1rem; cursor: pointer;"
                  @click="goToReviewDetail(review.reviewId)">
                  {{ review.title }}
                </span><br>
                {{ review.authorEmail }} / {{ formatDate(review.createdAt) }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container mt-5">
      <table class="table table-hover table-with-top-border">
        <thead>
          <tr>
            <th scope="col" style="width: 20%;">평점</th>
            <th scope="col" style="width: 40%;">제목</th>
            <th scope="col" style="width: 20%;">작성자</th>
            <th scope="col" style="width: 10%;">작성일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(review, index) in reviews" :key="index">
            <th>
              <template v-for="star in 5" :key="star">
                <i class="bi" :class="star <= review.rating ? 'bi-star-fill' : 'bi-star'" style="color: #FFD700;"></i>
              </template>
            </th>
            <td style="cursor: pointer;" @click="goToReviewDetail(review.reviewId)">
              {{ review.title }}
            </td>
            <td>{{ review.authorEmail }}</td>
            <td>{{ formatDate(review.createdAt) }}</td>
          </tr>
        </tbody>
      </table>

      <!-- 페이지네이션 추가 -->
      <div class="mt-3 d-flex justify-content-center">
        <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage" @change="getReview"
          class="custom-pagination"></b-pagination>
      </div>

      <!-- 글등록 버튼 추가 -->
      <div class="d-flex justify-content-end mt-3">
        <button type="button" class="btn btn-warning" @click="goToAddReview"
          style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
          글등록
        </button>
      </div>
    </div>

    <!-- 하단 코드 (예: 주의사항 카드) -->
    <div class="card w-100 mt-5 mb-5">
      <div class="card-body">
        <h5 class="card-title"> <i class="bi bi-exclamation-circle"></i> 꼭 읽어주세요</h5>
        <hr>
        <p class="card-text mt-4" style="font-size: 14px;">- 글 작성 시 정보 유출에 의한 피해방지를 위해 개인정보 기재는 삼가주시기 바랍니다.
          예) 주민등록번호, 전화번호, 여권번호, 신용카드번호, 계좌번호, 주소 등
          <br>
          - 해당 게시판과 글의 성격이 맞지 않을 경우, 관리자에 의해 게시글이 이동될 수 있습니다.
          <br>
          - 상업적인 광고 및 욕설, 도배성, 음해성 글의 경우 서비스 관리자에 의해 임의 수정 또는 삭제될 수 있음을 알려드립니다.
          <br>
          - 저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시글은 이용약관 및 관련법률에 의해 제재를 받을 수 있습니다.
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import ReviewService from "@/services/review/ReviewService";

export default {
  data() {
    return {
      pageIndex: 1,
      totalCount: 0,
      recodeCountPerPage: 10,
      searchKeyword: "",
      reviews: [],
    };
  },
  computed: {
    topReviews() {
      return this.reviews
        .slice()
        .sort((a, b) => b.rating - a.rating)
        .slice(0, 4);
    },
  },
  methods: {
    async getReview() {
      try {
        let response = await ReviewService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recodeCountPerPage
        );
        const { results, totalCount } = response.data;
        this.reviews = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.error("리뷰 목록을 가져오는 중 오류가 발생했습니다:", error);
      }
    },
    goToAddReview() {
      this.$router.push('/add-review');
    },
    formatDate(date) {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
      return new Date(date).toLocaleDateString('ko-KR', options);
    },
    goToReviewDetail(reviewId) {
      this.$router.push(`/review/${reviewId}`);
    },
  },
  mounted() {
    this.getReview();
  },
};
</script>

<style>


</style>
