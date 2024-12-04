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

    <!-- 드롭다운 버튼과 작성자 정보 -->
    <div class="d-flex align-items-center" style="margin-bottom: 30px;">
      <!-- 평점 매기기 -->
      <div class="d-flex align-items-center" style="margin-top: 20px;">
        <span style="font-weight: bold; margin-right: 10px;">평점:</span>
        <div>
          <span v-for="star in 5" :key="star" @click="setRating(star)" style="cursor: pointer;">
            <i :class="['bi', star <= rating ? 'bi-star-fill' : 'bi-star']"
              style="font-size: 1.5rem; color: #FFD700;"></i>
          </span>
        </div>
      </div>

      <!-- 작성자 정보 -->
      <div class="ms-auto d-flex align-items-center">
        <h5 style="font-weight: bold; margin-bottom: 0; margin-right: 30px;">작성자</h5>
        <p style="margin: 0; font-size: 1rem; margin-right: 60px;">{{ authorEmail }}</p>
      </div>
    </div>
    <hr>

    <!-- 제목 입력 -->
    <div class="input-group">
      <span class="input-group-text" style="font-weight: bold;">제목</span>
      <textarea v-model="title" class="form-control" aria-label="With textarea" style="height: 30px;"></textarea>
    </div>
    <hr>

    <!-- 내용 및 파일 첨부 섹션 -->
    <div class="mb-3">
      <label for="contentUpload" class="form-label" style="font-weight: bold;">내용</label>
      <div id="contentUpload" contenteditable="true" @input="updateContent" class="form-control"
        style="height: 400px; overflow-y: auto; border: 1px solid #ced4da;"></div>
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

    <!-- 알림 메시지 -->
    <div v-if="message" class="alert" :class="alertClass" role="alert">
      {{ message }}
    </div>

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
      rating: 0,
      title: "",
      content: "",
      authorEmail: "",
      imageUrl: null, // 업로드한 이미지 파일
      message: "", // 알림 메시지
      alertClass: "", // 알림 스타일
    };
  },
  computed: {
    token2() {
      const user = JSON.parse(localStorage.getItem("user"));
      return {
        "Content-Type": "multipart/form-data",
        Authorization: "Bearer " + (user?.accessToken || ""),
      };
    },
  },
  created() {
    const user = JSON.parse(localStorage.getItem("user"));
    // 사용자 이메일을 가져와 authorEmail로 저장
    this.authorEmail = user?.email || "알 수 없음"; // 사용자 이메일 표시
  },
  methods: {
    async save() {
      // 내용이 없는 경우
      if (!this.title || !this.content) {
        this.message = "작성 미완료되었습니다";
        this.alertClass = "alert-danger"; // 오류 메시지 스타일
        return;
      }

      try {
        // FormData로 전송할 데이터 준비
        const formData = new FormData();
        formData.append("title", this.title);
        formData.append("content", this.content);
        formData.append("rating", this.rating);
        formData.append("authorEmail", this.authorEmail);

        if (this.imageUrl) {
          formData.append("imageUrl", this.imageUrl); // 이미지 파일 추가
        }

        // 서버로 전송
        let response = await ReviewService.insert(formData, this.token2);
        console.log(response.data);
        this.message = "글등록이 완료되었습니다!";
        this.alertClass = "alert-success"; // 성공 메시지 스타일

        // 일정 시간 후 리뷰 목록으로 리다이렉트
        setTimeout(() => {
          this.$router.push("/review");
        }, 1500);
      } catch (error) {
        console.error("저장 실패", error);
        this.message = "저장 실패. 다시 시도해주세요.";
        this.alertClass = "alert-danger"; // 오류 메시지 스타일
      }
    },
    setRating(star) {
      this.rating = star; // 평점 설정
    },
    updateContent(event) {
      this.content = event.target.innerHTML; // 내용 업데이트
    },
    insertImage(event) {
      const file = event.target.files[0];
      if (file) {
        this.imageUrl = file; // 선택된 이미지 파일을 저장
      }
    },
  },
};
</script>

<style>
/* 메시지 알림 스타일 */
.alert {
  font-size: 1.2rem;
  margin-top: 20px;
}
</style>
