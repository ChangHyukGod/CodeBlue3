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
        <div class="col-md-3">
          <div class="card" style="width: 15rem; border: none;">
            <img src="/images/침대.jfif" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">좋음
                <br>
                <br> 박창혁 / 2024.11.11
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card" style="width: 15rem; border: none;">
            <img src="/images/침대.jfif" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">개좋음
                <br>
                <br> 최현정 / 2024.11.11
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card" style="width: 15rem; border: none;">
            <img src="/images/침대.jfif" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">너무 좋음
                <br>
                <br> 장우석 / 2024.11.11
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card" style="width: 15rem; border: none;">
            <img src="/images/침대.jfif" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">최고
                <br>
                <br> 곽효진 / 2024.11.11
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

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
          <tr>
            <th scope="row">1</th>
            <td>
              <button type="button" class="btn btn-outline-warning" disabled style="font-weight: bold; color: black;">부산</button>
            </td>
            <td>좋음</td>
            <td>박창혁</td>
            <td>2024.11.11</td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>
              <button type="button" class="btn btn-outline-warning" disabled style="font-weight: bold; color: black">부산</button>
            </td>
            <td>개좋음</td>
            <td>최현정</td>
            <td>2024.11.11</td>
          </tr>
          <tr>
            <th scope="row">3</th>
            <td>
              <button type="button" class="btn btn-outline-warning" disabled style="font-weight: bold; color: black">부산</button>
            </td>
            <td>너무 좋음</td>
            <td>장우석</td>
            <td>2024.11.11</td>
          </tr>
          <tr>
            <th scope="row">4</th>
            <td>
              <button type="button" class="btn btn-outline-warning" disabled style="font-weight: bold; color: black;">부산</button>
            </td>
            <td>최고</td>
            <td>곽효진</td>
            <td>2024.11.11</td>
          </tr>
        </tbody>
      </table>
      <div class="d-flex justify-content-end">
        <button type="button" class="btn btn-warning" @click="goToAddReview" style="font-size: 1.5rem; font-weight: bold; padding: 8px 40px;">
          글등록
        </button>
      </div>
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
    };
  },
  methods: {
    selectRegion(region) {
      this.selectedRegion = region; // 선택된 지역을 변수에 저장
    },
    goToAddReview() {
      this.$router.push('/add-review'); // '/add-review' 페이지로 이동
    },
  },
}
</script>

<style>
</style>
