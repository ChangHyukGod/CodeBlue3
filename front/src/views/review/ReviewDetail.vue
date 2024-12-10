<template>
    <div>
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
      <br>
      <h3 v-if="review.title" style="font-weight: bold; padding: 3px;">{{ review.title }}</h3>
      <div class="d-flex justify-content-between align-items-center" style="margin-top: 10px;">
  <div class="d-flex align-items-center">
    <p style="margin-right: 20px; margin-top: 10px">평점:    
      <template v-for="star in 5" :key="star">
        <i class="bi" :class="star <= review.rating ? 'bi-star-fill' : 'bi-star'" style="color: #FFD700;"></i>
      </template>
    </p>
  </div>
  <div style="margin-left: auto; text-align: right;">
    <span>작성일 : {{ formatDate(review.createdAt) }}</span>
    <span style="margin-left: 20px;">작성자 : {{ review.authorEmail }}</span>
  </div>
</div>
      <hr>

        <br>
    <div class="mb-3 col-12">
      <img :src="review.imageUrl" width="200px" alt="이미지" />
    </div>
      <p>{{ review.content }}</p>


      <br>
      <div class="d-flex justify-content-end">
        <button type="button" class="btn btn-secondary" @click="goToUpdateReview(review.reviewId)" style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;  margin-right: 20px;">
          수정
        </button>

        <button type="button" class="btn btn-danger" @click="remove" style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;  margin-right: 20px;">
          삭제
        </button>

        <button type="button" class="btn btn-warning" @click="goToAddReview" style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
          목록
        </button>
      </div>
    </div>
  </template>
  
  <script>
  import ReviewService from "@/services/review/ReviewService";
  
  export default {
    data() {
      return {
        review: {  
            reviewId: this.$route.params.reviewId,    
            rating: 0,
            title: "",
            content: "", 
            authorEmail: "",
            targetId: null, 
            imageUrl: "",
            image: undefined
       }, // 선택된 리뷰 정보를 저장할 객체
      };
    },
    methods: {
      formatDate(date) {
        const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
        return new Date(date).toLocaleDateString('ko-KR', options);
      },
      async getDetail(reviewId) {
        try {
          let response = await ReviewService.get(reviewId); // 리뷰 상세 정보 가져오기
          this.review = response.data; // 리뷰 정보를 저장
        } catch (error) {
          console.error("리뷰 상세 정보를 가져오는 중 오류가 발생했습니다:", error);
          // 오류 메시지를 화면에 표시하는 등의 처리 필요
        }
      },
      setRating(star) {
      this.review.rating = star; // 평점 설정
      },
      select() {
      this.review.image = this.$refs.file.files[0];
      },
      goToAddReview() {
        this.$router.push('/review');
      },
      goToUpdateReview(reviewId) {
        this.$router.push(`/reviewupdate/${reviewId}`);
      },

      async update() {
      try {

        let response = await ReviewService.update(
          this.review.reviewId,
          this.review
        );
        console.log(response.data); // 디버깅
        alert("수정되었습니다.")
      } catch (error) {
        this.review.image = undefined;
        console.log(error);
      }
    },
    async remove() {
      try {
        let response = await ReviewService.remove(this.review.reviewId);
        console.log(response.data); // 디버깅
        // 전체조회 화면 강제 이동
        this.$router.push("/review");
      } catch (error) {
        console.log(error);

      }
    },
      goToReview() {
    this.$router.push("/review"); // /review로 이동
  }
    },
    mounted() {
      
      this.getDetail(this.$route.params.reviewId); // 리뷰 정보 가져오기
    },
  };
  </script>
  
  <style>
  /* 추가 스타일 필요 시 작성 */
  </style>
  