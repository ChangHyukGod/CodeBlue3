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
      <div class="btn-group">
        <button type="button" class="btn dropdown-toggle custom-dropdown mt-4"
          style="border: 2px solid black; padding: 10px 50px;" data-bs-toggle="dropdown"
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

      <!-- 평점 매기기 -->
      <div class="ms-auto d-flex align-items-center" style="padding-right: 500px; margin-top: 20px;">
        <span style="font-weight: bold; margin-right: 10px;">평점:</span>
        <div style="margin-right: 100px;">
          <span v-for="star in 5" :key="star" @click="setRating(star)" style="cursor: pointer;">
            <i :class="['bi', star <= rating ? 'bi-star-fill' : 'bi-star']" style="font-size: 1.5rem; color: #FFD700;"></i>
          </span>
        </div>
      </div>

      <!-- 작성자 정보 -->
      <div class="ms-auto d-flex align-items-center">
        <h5 style="font-weight: bold; margin-bottom: 0; margin-right: 30px;">작성자</h5>
        <p style="margin: 0; font-size: 1rem; margin-right: 60px;">최형표</p>
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
      <div
        id="contentUpload"
        contenteditable="true"
        @input="updateContent"
        class="form-control"
        style="height: 400px; overflow-y: auto; border: 1px solid #ced4da;"
      ></div>
      <input type="file" id="imageUpload" @change="insertImage" accept="image/*" class="form-control mt-2" />
    </div>

    <div class="d-flex justify-content-center" style="margin-top: 40px; margin-bottom: 40px;">
      <button type="button" class="btn btn-warning" @click="goToAddReview" style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
        글등록
      </button>
      <button type="button" class="btn btn-secondary ms-3" style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
        취소
      </button>
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
export default {
  data() {
    return {
      selectedRegion: null, // 선택된 지역을 저장할 변수
      rating: 0, // 평점을 저장할 변수
      title: '', // 제목을 저장할 변수
      content: '', // 내용을 저장할 변수
      imageUrl: '', // 미리보기 이미지 URL (이제 사용되지 않음)
      previewVisible: false, // 미리보기 표시 여부 (이제 사용되지 않음)
    };
  },
  methods: {
    selectRegion(region) {
      this.selectedRegion = region; // 선택된 지역을 변수에 저장
    },
    setRating(star) {
      this.rating = star; // 클릭한 별의 수를 평점으로 설정
    },
    updateContent(event) {
      this.content = event.target.innerHTML; // contenteditable div의 내용을 업데이트
    },
    insertImage(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          const img = document.createElement('img');
          img.src = e.target.result;
          img.style.maxWidth = '100%'; // 이미지의 최대 너비를 설정
          img.style.height = 'auto'; // 비율에 맞게 높이 조정
          const contentDiv = document.getElementById('contentUpload');
          contentDiv.appendChild(img); // 이미지를 내용에 추가
        };
        reader.readAsDataURL(file); // 파일을 URL로 읽기
      }
    },
    goToAddReview() {
      this.$router.push('/add-review'); // '/add-review' 페이지로 이동
    },
  },
}
</script>

<style>
</style>
