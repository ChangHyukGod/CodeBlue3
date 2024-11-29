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
    <h5 style="margin-bottom: 40px; font-weight: bold;">이달의 추천 숙박업소</h5>
    <div class="container">
      <div class="row">
        <div class="col-md-3" v-for="review in reviews" :key="review.reviewId">
          <div class="card" style="width: 15rem; border: none;">
            <img :src="review.imageUrl || '/images/침대.jfif'" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">
                {{ review.title }}<br><br>
                {{ review.authorEmail }} / {{ formatDate(review.createdAt) }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 지역 선택 -->
    <div class="d-flex">
      <div class="btn-group">
        <button type="button" class="btn dropdown-toggle custom-dropdown mt-4"
          style="border: 1px solid gold; padding: 5px 20px;" data-bs-toggle="dropdown"
          aria-expanded="false" data-bs-offset="10,10">
          {{ selectedRegion || '지역선택' }}
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#" @click="selectRegion('지역선택')">지역선택</a></li>
          <li><a class="dropdown-item" href="#" @click="selectRegion('서울')">서울</a></li>
          <li><a class="dropdown-item" href="#" @click="selectRegion('부산')">부산</a></li>
          <li><a class="dropdown-item" href="#" @click="selectRegion('제주')">제주</a></li>
        </ul>
      </div>
    </div>

    <!-- 리뷰 목록 테이블 -->
    <div class="container mt-2">
      <table class="table table-hover table-with-top-border">
        <thead>
          <tr>
            <th scope="col">번호</th>
            <th scope="col">지역</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(review, index) in filteredReviews" :key="review.reviewId">
            <th scope="row">{{ index + 1 }}</th>
            <td>
              <button type="button" class="btn btn-outline-warning" disabled style="font-weight: bold; color: black;">
                {{ review.loc }}
              </button>
            </td>
            <td>{{ review.title }}</td>
            <td>{{ review.authorEmail }}</td>
            <td>{{ formatDate(review.createdAt) }}</td>
          </tr>
        </tbody>
      </table>
      <div class="d-flex justify-content-end">
        <button type="button" class="btn btn-warning" @click="goToAddReview"
          style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
          글등록
        </button>
      </div>
    </div>

    <!-- 주의사항 카드 -->
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
import ReviewService from "@/services/review/ReviewService"; // ReviewService를 import

export default {
  data() {
    return {
      selectedRegion: null, // 선택된 지역을 저장할 변수
      reviews: [], // 리뷰 목록
    };
  },
  computed: {
    // 선택된 지역에 따라 필터링된 리뷰 목록
    filteredReviews() {
      if (!this.selectedRegion || this.selectedRegion === '지역선택') {
        return this.reviews;
      }
      return this.reviews.filter(review => review.loc === this.selectedRegion);
    },
  },
 methods: {
  // 리뷰 목록을 불러오는 메소드
  async loadReviews() {
    try {
      const response = await ReviewService.getAll('', 0, 10); // 인증 없이 데이터 호출
      this.reviews = response.data;
    } catch (error) {
      console.error("리뷰 목록을 가져오는 중 오류가 발생했습니다:", error);
    }
  },
  // 지역 선택 메소드
  selectRegion(region) {
    this.selectedRegion = region; // 선택된 지역을 변수에 저장
  },
  // 글등록 버튼 클릭 시 이동
  goToAddReview() {
    this.$router.push('/add-review'); // '/add-review' 페이지로 이동
  },
  // 날짜 포맷팅
  formatDate(date) {
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return new Date(date).toLocaleDateString('ko-KR', options);
  },
},

mounted() {
  this.loadReviews(); // 페이지 로드 시 리뷰 목록을 불러옵니다.
},
};
</script>

<style>

</style>

