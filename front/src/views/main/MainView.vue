<template>
  <div class="container my-5">
    <!-- 배너(캐러셀) -->
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel" :interval="4000">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img :src="require(`@/assets/images/coupon3.png`)" class="d-block w-100" alt="..."/>
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
    
    <!-- 메뉴 1 -->
    <br/>
    <h2 class="text-center mb-3">2024 연말, 마지막 특가</h2>
    <p class="text-center text-muted mb-4">아직 늦지 않았어요! 연말엔 이쁜 숙소에서 낭만 가득 휴식 어때요?</p>
    <!-- 상품 카드 1 -->
    <div class="row row-cols-1 row-cols-md-4 g-3">
      <div class="col">
        <div class="card h-100">
          <img :src="require(`@/assets/images/coupon3.png`)" class="card-img-top" />
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <h5 class="card-title mt-2">낭만가득, 연인과 함께 즐기는 광안대교 뷰</h5>
            <p class="card-text">부산</p>
            <p class="card-text">광안대교 뷰가 보이는 숙소에서 낭만 한잔 어떠세요♡</p>
            <p class="text-primary fw-bold">97,000원~</p>
            <!-- 상세페이지 / 수정,삭제 페이지 이동 버튼 -->
            <div style="display: flex; justify-content: space-between">
              <router-link>
                <button class="btn btn-primary">보러가기</button>
              </router-link>
              <router-link>
                <button class="btn btn-primary">수정/삭제</button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 메뉴 2 -->
    <br/>
    <h2 class="text-center mb-3">최다 숙소 보유</h2>
    <p class="text-center text-muted mb-4">여행 전에도, 여행 중에도 언제 어디서든 예약OK</p>

    <!-- 필터 -->
    <div class="d-flex justify-content-center mb-4">
      <button class="btn btn-outline-secondary mx-2">전체보기</button>
      <button class="btn btn-outline-secondary mx-2">국내숙소</button>
      <button class="btn btn-outline-secondary mx-2">해외숙소</button>
      <button class="btn btn-outline-secondary mx-2">최저가</button>
      <button class="btn btn-outline-secondary mx-2">쿠폰·이벤트</button>
    </div>

    <!-- 상품 카드 2 -->
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col" v-for="(data, index) in mains" :key="index">
        <div class="card h-100">
          <img :src="data.tourFileUrl" class="card-img-top" />
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
        </div> <!-- 카드 닫는태그 -->
      </div>
    </div>
    <br/>

    <!-- 메뉴 3 -->
    <h2 class="text-center mb-3">☆추천 맛집! 할인혜택! 각종 리뷰 모음☆</h2>
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col">
        <b-card-group deck>
          <b-card :img-src="require(`@/assets/images/coupon3.png`)" img-alt="Card image" img-top>
            <b-card-text>숙소 근처 찐맛집, 인스타 핫플 췍~!</b-card-text>
            <router-link :to='/coupon/'>
              <button class="btn btn-primary">추천페이지 보러가기</button>
            </router-link>
          </b-card>
        </b-card-group>
      </div>
      <div class="col">
        <b-card-group deck>
          <b-card :img-src="require(`@/assets/images/coupon3.png`)" img-alt="Card image" img-top>
            <b-card-text>쏠쏠한 혜택, 쿠폰/이벤트 췍~!</b-card-text>
            <router-link :to='/recommend/'>
              <button class="btn btn-primary">쿠폰/이벤트 보러가기</button>
            </router-link>
          </b-card>
        </b-card-group>
      </div>
      <div class="col">
        <b-card-group deck>
          <b-card :img-src="require(`@/assets/images/coupon3.png`)" img-alt="Card image" img-top>
            <b-card-text>다른 사람들의 후기도 궁금해!</b-card-text>
            <router-link :to='/review/'>
              <button class="btn btn-primary">리뷰 보러가기</button>
            </router-link>
          </b-card>
        </b-card-group>
      </div>
      <div class="col">
        <b-card-group deck>
          <b-card :img-src="require(`@/assets/images/coupon3.png`)" img-alt="Card image" img-top>
            <b-card-text>기타문의 사항은 여기로!</b-card-text>
            <router-link :to='/faq/'>
              <button class="btn btn-primary">고객센터 보러가기</button>
            </router-link>
          </b-card>
        </b-card-group>
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