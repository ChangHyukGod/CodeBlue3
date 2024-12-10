<template>
  <div class="container my-5">
    <!-- 배너(캐러셀) -->
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel" data-bs-interval="4000">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="2" aria-label="Slide 3"></button>
      <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="3" aria-label="Slide 4"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <a href="/maindetail/164">
          <img :src="require(`@/assets/images/main/배너제주.png`)" class="d-block w-100" width="800" height="600" />
        </a>
      </div>
      <div class="carousel-item">
        <a href="/maindetail/104">
          <img :src="require(`@/assets/images/main/배너광안.png`)" class="d-block w-100" width="800" height="600" />
        </a>
      </div>
      <div class="carousel-item">
        <a href="/coupon">
          <img :src="require(`@/assets/images/main/배너쿠폰.png`)" class="d-block w-100" width="800" height="600" />
        </a>
      </div>
      <div class="carousel-item">
        <a href="/recommenddetail/14">
          <img :src="require(`@/assets/images/main/배너추천.png`)" class="d-block w-100" width="800" height="600" />
        </a>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <br/>

    <!-- 메뉴 1 : 연말특가 -->
    <div>
      <button class="btn btn-warning" style="border-radius: 10px; color: white; font-size: 21px;">2024 연말, 마지막 특가</button>
      <p style="font-size: 16px; margin-top: 10px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      font-weight: 400; opacity: 0.8;">아직 늦지 않았어요! 연말엔 이쁜 숙소에서 낭만 가득 휴식 어때요?</p>
    </div>
    <!-- 추천 카드 1 -->
    <div class="row row-cols-1 row-cols-md-4 g-3">
      <div class="col">
        <div class="card h-100">
          <a href="/maindetail/104">
            <img :src="require(`@/assets/images/main/추천광안.avif`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">낭만가득, 연인과 함께 즐기는 광안대교 뷰</h5>
            <p class="card-text">부산</p>
            <p class="card-text">광안대교 뷰가 보이는 숙소에서 낭만 한잔 어떠세요♡</p>
            <p class="text-primary fw-bold">70,000원</p>
              <div style="display: flex; justify-content: space-between">
                <a href="/maindetail/104"><button class="btn btn-primary">보러가기</button></a>
              </div>
          </div>
        </div>
      </div>
      <!-- 추천 카드 2 -->
      <div class="col">
        <div class="card h-100">
          <a href="/maindetail/164">
            <img :src="require(`@/assets/images/main/추천제주.avif`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">올 겨울, 제주 어때~?</h5>
            <p class="card-text">제주</p>
            <p class="card-text">시골갬성 가득, 한옥숙소</p>
            <p class="text-primary fw-bold">185,400원</p>
              <!-- 상세페이지 주소창 하드코딩 예정 -->
              <div style="display: flex; justify-content: space-between">
                <a href="/maindetail/164"><button class="btn btn-primary">보러가기</button></a>
              </div>
          </div>
        </div>
      </div>
      <!-- 추천 카드 3 -->
      <div class="col">
        <div class="card h-100">
          <a href="/maindetail/165">
            <img :src="require(`@/assets/images/main/추천프랑스.avif`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">유럽의 문화 중심지, 프랑스에서 하루</h5>
            <p class="card-text">프랑스</p>
            <p class="card-text">프랑스에서 즐기는 지중해 바다</p>
            <p class="text-primary fw-bold">211,040원</p>
              <!-- 상세페이지 주소창 하드코딩 예정 -->
              <div style="display: flex; justify-content: space-between">
                <a href="/maindetail/165"><button class="btn btn-primary">보러가기</button></a>
              </div>
          </div>
        </div>
      </div>
      <!-- 추천 카드 4 -->
      <div class="col">
        <div class="card h-100">
          <a href="/maindetail/169">
            <img :src="require(`@/assets/images/main/추천훗카이도.avif`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">겨울왕국, 스키타고 즐기는 훗카이도</h5>
            <p class="card-text">훗카이도</p>
            <p class="card-text">유명한 스키리조트 윈저스노우 빌리지에서 15분 거리</p>
            <p class="text-primary fw-bold">152,800원</p>
              <!-- 상세페이지 주소창 하드코딩 예정 -->
              <div style="display: flex; justify-content: space-between">
                <a href="/maindetail/169"><button class="btn btn-primary">보러가기</button></a>
              </div>
          </div>
        </div>
      </div>
    </div>  <!-- 연말특가 닫는태그 -->
    <br/>

    <!-- 메뉴 2 : 숙소 전체 -->
    <div>
      <button class="btn btn-warning" style="border-radius: 10px; color: white; font-size: 21px;">최다 숙소 보유</button>
      <p style="font-size: 16px; margin-top: 10px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      font-weight: 400; opacity: 0.8;">여행 전에도, 여행 중에도 언제 어디서든 예약OK</p>
    </div>

    <!-- 필터 -->
    <div class="d-flex justify-content-between align-items-center mb-4">
    <div class="d-flex  mb-4">
      <button class="btn btn-outline-secondary mx-2" @click="getreset()">전체보기</button>
      <button class="btn btn-outline-secondary mx-2" @click="getAll('국내','')">국내숙소</button>
      <button class="btn btn-outline-secondary mx-2" @click="getAll('해외','')">해외숙소</button>
      <button class="btn btn-outline-secondary mx-2" @click="getAll('','바다')">바다뷰</button>
      <button class="btn btn-outline-secondary mx-2" @click="getAll('','산')">산뷰</button>    
    </div>
    <div class="d-flex mb-2">    
      <button
    class="btn btn-outline-secondary dropdown-toggle form-control me-2"
    type="button"
    id="dropdownMenuButton"
    data-bs-toggle="dropdown"
    aria-expanded="false"
  >
    {{ searchKeyword || "여행의 모든 것" }}
  </button>
  <ul class="dropdown-menu w-10" aria-labelledby="dropdownMenuButton">
    <li>
      <button class="dropdown-item"
        v-for="keyword in keywords"
        :key="keyword"
        @click="selectKeyword(keyword)"
      >
        {{ keyword }}
      </button>
    </li>
  </ul>
  <button class="btn btn-outline-warning search_glass" type="button"
    @click="getAll()"
  >
    <i class="bi bi-search"></i>
  </button>
            </div>
      </div>


    <!-- 상품 카드 2 -->
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col" v-for="(data, index) in mains" :key="index">
        <div class="card h-100">
          <router-link :to="'/maindetail/' + data.tourId">
            <img :src="data.tourFileUrl" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </router-link>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">{{ data.name }}</h5>
            <p class="card-text">{{ data.location }}</p>
            <p class="card-text">{{ data.description }}</p>
            <p class="text-primary fw-bold">{{ data.price }}</p>
            <!-- 상세페이지 / 수정,삭제 페이지 이동 버튼 -->
            <div style="display: flex; justify-content: space-between">
              <router-link :to="'/maindetail/' + data.tourId">
                <button class="btn btn-primary">보러가기</button>
              </router-link>
              <router-link :to="'/mainupdate/' + data.tourId">
                <button class="btn btn-primary">수정/삭제</button>
              </router-link>
            </div>
          </div>
        </div> 
      </div> <!-- 카드 닫는태그 -->
    </div>
    <br/>

    <!-- 메뉴 3 : 추천/쿠폰/리뷰/faq 이동 카드 -->
    <div>
      <button class="btn btn-warning" style="border-radius: 10px; color: white; font-size: 21px;">☆추천 모음집☆</button>
      <p style="font-size: 16px; margin-top: 10px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      font-weight: 400; opacity: 0.8;">각종 리뷰, 숙소 근처 핫플, 쿠폰혜택까지 놓치지 마세요~!</p>
    </div>
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col">
        <div class="card h-100">
          <a href="/recommend">
            <img :src="require(`@/assets/images/main/추천.png`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <a href="/recommend"><button class="btn btn-primary">추천페이지 보러가기</button></a>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100">
          <a href="/coupon">
            <img :src="require(`@/assets/images/main/쿠폰.png`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <a href="/coupon"><button class="btn btn-primary">쿠폰/이벤트 보러가기</button></a>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100">
          <a href="/review">
            <img :src="require(`@/assets/images/main/리뷰.png`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <a href="/review"><button class="btn btn-primary">리뷰게시판 보러가기</button></a>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100">
          <a href="/faq">
            <img :src="require(`@/assets/images/main/FAQ.png`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <a href="/faq"><button class="btn btn-primary">고객센터 보러가기</button></a>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>
<script>
import MainService from "@/services/main/MainService";
export default {
  data() {
    return {
      mains: [], //빈배열(json)
      searchKeyword:"", // 검색어
      keywords: ["서울", "부산", "제주", "강원"], //드롭다운 항목
      
    };
  },
  methods: {
    async getAll(category = "", view = ""){
      this.category = category;
      this.view = view;
      console.log(this.category);
      console.log(this.view);
      console.log(this.searchKeyword);
      try {
        let response = await MainService.getALLnp(this.searchKeyword,this.category,this.view);
        const { results, totalCount } = response.data;
        console.log(response.data);
        this.mains = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
    },

    selectKeyword(keyword){
      this.searchKeyword = keyword;
    },

    getreset(){
      this.searchKeyword = "";
      this.getAll();
    },
  },
  mounted() {
    this.getAll();
  },
};
</script>
<style></style>