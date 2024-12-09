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
        <a href="/maindetail/104">
          <img :src="require(`@/assets/images/main/Gangan_1.webp`)" class="d-block w-100" width="800" height="600" />
        </a>
        <div class="carousel-caption d-none d-md-block">
          <a href="/maindetail/104">
            <button class="btn btn-warning" style="border-radius: 10px; color: white; font-size: 21px;">2024 연말, 마지막 특가</button>
          </a>
          <p>광안대교 야경 보면서 따뜻한 연말갬성 챙기자구요~</p>
        </div>
      </div>
      <div class="carousel-item">
        <a href="/maindetail/85">
          <img :src="require(`@/assets/images/main/Heaundae_1.webp`)" class="d-block w-100" width="800" height="600" />
        </a>
        <div class="carousel-caption d-none d-md-block">
          <a href="/maindetail/85">
            <button class="btn btn-warning" style="border-radius: 10px; color: white; font-size: 21px;">속초, 바다 앞 고급호텔!</button>
          </a>
          <p>속초 라마다 호텔 예약을 여기로~</p>
        </div>
      </div>
      <div class="carousel-item">
        <a href="/coupon">
          <img :src="require(`@/assets/images/coupon3.png`)" class="d-block w-100" width="800" height="600" />
        </a>
        <div class="carousel-caption d-none d-md-block">
          <a href="/coupon">
            <button class="btn btn-warning" style="border-radius: 10px; color: white; font-size: 21px;">신규회원 쿠폰 받아가세요!</button>
          </a>
          <p>신규회원 대상, 20% 할인 쿠폰 발급 이벤트 중~</p>
        </div>
      </div>
      <div class="carousel-item">
        <a href="/recommenddetail/14">
          <img :src="require(`@/assets/images/메뉴5.jpg`)" class="d-block w-100" width="800" height="600" />
        </a>
        <div class="carousel-caption d-none d-md-block">
          <a href="/recommenddetail/14">
            <button class="btn btn-warning" style="border-radius: 10px; color: white; font-size: 21px;">겨울여행 추천!</button>
          </a>
          <p>겨울맞이 이런 곳은 어떠신가요? 추천페이지에서 둘러보아요~</p>
        </div>
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
            <img :src="require(`@/assets/images/main/Gangan_1.webp`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">낭만가득, 연인과 함께 즐기는 광안대교 뷰</h5>
            <p class="card-text">부산</p>
            <p class="card-text">광안대교 뷰가 보이는 숙소에서 낭만 한잔 어떠세요♡</p>
            <p class="text-primary fw-bold">97,000원~</p>
              <!-- 상세페이지 주소창 하드코딩 예정 -->
              <div style="display: flex; justify-content: space-between">
                <a href="/maindetail/104"><button class="btn btn-primary">보러가기</button></a>
              </div>
          </div>
        </div>
      </div>
      <!-- 추천 카드 2 -->
      <div class="col">
        <div class="card h-100">
          <img :src="require(`@/assets/images/main/Namcheon_1.webp`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">낭만가득, 연인과 함께 즐기는 광안대교 뷰</h5>
            <p class="card-text">부산</p>
            <p class="card-text">광안대교 뷰가 보이는 숙소에서 낭만 한잔 어떠세요♡</p>
            <p class="text-primary fw-bold">97,000원~</p>
              <!-- 상세페이지 주소창 하드코딩 예정 -->
              <div style="display: flex; justify-content: space-between">
                <a href="/"><button class="btn btn-primary">보러가기</button></a>
              </div>
          </div>
        </div>
      </div>
      <!-- 추천 카드 3 -->
      <div class="col">
        <div class="card h-100">
          <img :src="require(`@/assets/images/main/Heaundae_1.webp`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">낭만가득, 연인과 함께 즐기는 광안대교 뷰</h5>
            <p class="card-text">부산</p>
            <p class="card-text">광안대교 뷰가 보이는 숙소에서 낭만 한잔 어떠세요♡</p>
            <p class="text-primary fw-bold">97,000원~</p>
              <!-- 상세페이지 주소창 하드코딩 예정 -->
              <div style="display: flex; justify-content: space-between">
                <a href="/"><button class="btn btn-primary">보러가기</button></a>
              </div>
          </div>
        </div>
      </div>
      <!-- 추천 카드 4 -->
      <div class="col">
        <div class="card h-100">
          <img :src="require(`@/assets/images/coupon3.png`)" class="card-img-top" style="width: 100%; height: 200px; object-fit: cover;"/>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">낭만가득, 연인과 함께 즐기는 광안대교 뷰</h5>
            <p class="card-text">부산</p>
            <p class="card-text">광안대교 뷰가 보이는 숙소에서 낭만 한잔 어떠세요♡</p>
            <p class="text-primary fw-bold">97,000원~</p>
              <!-- 상세페이지 주소창 하드코딩 예정 -->
              <div style="display: flex; justify-content: space-between">
                <a href="/"><button class="btn btn-primary">보러가기</button></a>
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
    <div class="d-flex mb-4">
      <button class="btn btn-outline-secondary mx-2">전체보기</button>
      <button class="btn btn-outline-secondary mx-2">국내숙소</button>
      <button class="btn btn-outline-secondary mx-2">해외숙소</button>
      <button class="btn btn-outline-secondary mx-2">바다뷰</button>
      <button class="btn btn-outline-secondary mx-2">산뷰</button>
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
            <img :src="require(`@/assets/images/main/Namcheon_1.webp`)" class="card-img-top" style="width: 100%; height: 150px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <p>숙소 근처 관광지, 인스타 핫플 췍~!</p>
            <a href="/recommend"><button class="btn btn-primary">추천페이지 보러가기</button></a>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100">
          <a href="/coupon">
            <img :src="require(`@/assets/images/coupon3.png`)" class="card-img-top" style="width: 100%; height: 150px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <p>쏠쏠한 혜택, 쿠폰/이벤트 췍~!</p>
            <a href="/coupon"><button class="btn btn-primary">쿠폰/이벤트 보러가기</button></a>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100">
          <a href="/review">
            <img :src="require(`@/assets/images/coupon3.png`)" class="card-img-top" style="width: 100%; height: 150px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <p>다른 사람들의 후기도 궁금해!</p>
            <a href="/review"><button class="btn btn-primary">리뷰게시판 보러가기</button></a>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100">
          <a href="/faq">
            <img :src="require(`@/assets/images/main/QnA.png`)" class="card-img-top" style="width: 100%; height: 150px; object-fit: cover;"/>
          </a>
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <p>기타문의 사항은 여기로!</p>
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
    };
  },
  methods: {
    async getAll(){
      try {
        let response = await MainService.getALLnp();
        const { results, totalCount } = response.data;
        console.log(response.data);
        this.mains = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
    }
  },
  mounted() {
    this.getAll();
  },
};
</script>
<style></style>