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
    <hr>

    <div class="d-flex align-items-center" style="margin-bottom: 30px;">
      <div class="d-flex align-items-center" style="margin-top: 20px;">
        <span style="font-weight: bold; margin-right: 10px;">평점:</span>
        <div>
          <span v-for="star in 5" :key="star" @click="setRating(star)" style="cursor: pointer;">
            <i :class="['bi', star <= review.rating ? 'bi-star-fill' : 'bi-star']"
              style="font-size: 1.5rem; color: #FFD700;"></i>
          </span>
        </div>
      </div>

      <div class="ms-auto d-flex align-items-center">
        <h5 style="font-weight: bold; margin-bottom: 0; margin-right: 30px;">작성자</h5>
        <p style="margin: 0; font-size: 1rem; margin-right: 60px;">{{ review.authorEmail }}</p>
      </div>
    </div>
    <hr>

    <!-- 제목 입력 -->
    <div class="input-group">
      <span class="input-group-text" style="font-weight: bold;">제목</span>
      <textarea v-model="review.title" class="form-control" aria-label="With textarea" style="height: 30px;"></textarea>
    </div>
    <hr>

    <!-- targetId 입력 -->
    <div class="input-group">
      <span class="input-group-text" style="font-weight: bold;">상품명</span>
      <input type="number" v-model="review.targetId" class="form-control" />
    </div>
    <hr>

    <!-- 내용 입력 섹션 -->
    <div class="mb-3">
      <label for="content" class="form-label" style="font-weight: bold;">내용</label>
      <textarea v-model="review.content" id="content" class="form-control" rows="10" placeholder="후기 내용을 입력하세요."></textarea>
      <input type="file" id="imageUpload" @change="insertImage" accept="image/*" class="form-control mt-2" />
    </div>

    <div class="d-flex justify-content-center" style="margin-top: 40px; margin-bottom: 40px;">
      <button type="button" class="btn btn-warning" @click="save"
        style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
        글등록
      </button>
      <button type="button" class="btn btn-secondary ms-3"
        style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
        취소
      </button>
    </div>

    <div class="card w-100 mt-5 mb-5">
      <div class="card-body">
        <h5 class="card-title"><i class="bi bi-exclamation-circle"></i> 꼭 읽어주세요</h5>
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
      review: {      
        rating: 0,
        title: "",
        content: "", 
        authorEmail: "",
        targetId: null, 
        imageUrl: "",
        image: undefined
      },
    }
  },
  created() {
    const user = JSON.parse(localStorage.getItem("user"));
    this.review.authorEmail = user?.email || "알 수 없음"; // 사용자 이메일 표시
  },
  methods: {
    insertImage(event) {
      const file = event.target.files[0];
      if (file) {
        this.review.image = file; // 선택한 파일을 review.image에 저장
        this.review.imageUrl = URL.createObjectURL(file); // 이미지 URL 생성
      }
    },
    
    setRating(star) {
      this.review.rating = star; // 평점 설정
    },
    
    async save() {
  if (!this.review.title) {
    alert("제목을 입력해 주세요."); // 제목이 비어 있을 경우 경고 메시지
    return;
  }

  try {
    let response = await ReviewService.insert(this.review);
    console.log(response.data);
    this.$router.push("/review");
  } catch (error) {
    console.error(error.response ? error.response.data : error.message);
  }
}
  },
}
</script>

<style>
/* 스타일 추가 필요 시 여기에 작성 */
</style>
