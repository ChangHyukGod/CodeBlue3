<template>
<div class="container my-5">
  <!-- 배너(캐러셀) -->
  <div>
    <b-carousel id="Carousel" style="text-align: center; 1024px; margin: 10px;" controls indicators
      background="#ffeb33" img-width="1024" img-height="480" :interval="5000" fade>
      <!-- 첫 번째 슬라이드 -->
      <b-carousel-slide
        caption="첫 번째 슬라이드"
        text="Nulla vitae elit libero, a pharetra augue mollis interdum."
        img-src="https://via.placeholder.com/1024x480"
      ></b-carousel-slide>

      <!-- 두 번째 슬라이드 -->
      <b-carousel-slide
        caption="두 번째 슬라이드"
        text="Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        img-src="https://via.placeholder.com/1024x480?text=Slide+2"
      ></b-carousel-slide>

      <!-- 세 번째 슬라이드 -->
      <b-carousel-slide
        caption="세 번째 슬라이드"
        text="Praesent commodo cursus magna, vel scelerisque nisl consectetur."
        img-src="https://via.placeholder.com/1024x480?text=Slide+3"
      ></b-carousel-slide>
    </b-carousel>
  </div>

    <!-- 메뉴 1 -->
    <h2 class="text-center mb-3">투어 특가</h2>
    <p class="text-center text-muted mb-4">여행 전에도, 여행 중에도 안심</p>

    <!-- 필터 -->
    <div class="d-flex justify-content-center mb-4">
      <button class="btn btn-outline-secondary mx-2">패키지</button>
      <button class="btn btn-outline-secondary mx-2">국내숙소</button>
      <button class="btn btn-outline-secondary mx-2">해외숙소</button>
      <button class="btn btn-outline-secondary mx-2">투어·티켓</button>
    </div>

    <!-- 상품 카드 -->
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col" v-for="(data, index) in mains" :key="index">
        <div class="card h-100">
          <img :src="data.tourFileUrl" class="card-img-top">
          <div class="card-body" style="display: flex; flex-direction: column; justify-content: space-between; width: 300px;">
            <!-- ↓ 기본키는 잘 넘어가는지 확인용, 최종 디자인에서 지울예정 -->
            <!-- <span class="badge bg-primary">{{data.tourId}}</span> -->
            <h5 class="card-title mt-2">{{data.name}}</h5>
            <p class="card-text">{{data.location}}</p>
            <p class="card-text">{{data.description}}</p>
            <p class="text-primary fw-bold">{{data.price}}</p>
            <!-- 상세페이지 / 수정,삭제 페이지 이동 버튼 -->
              <div style="display:flex; justify-content: space-between;">
                <router-link :to='"/maindetail/" + data.tourId'>
                  <button class="btn btn-primary">보러가기</button>
                </router-link>
                <router-link :to='"/mainupdate/" + data.tourId'>
                  <button class="btn btn-primary">수정/삭제</button>
                </router-link>
              </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 메뉴 1 _ 버튼 -->
    <div class="text-center mt-4">
      <button class="btn btn-primary">투어 홈 바로가기</button>
    </div>
    
    <!-- 페이징 -->
    <div>
      <b-pagination
        v-model="pageIndex"
        :total-rows="totalCount"
        :per-page="recodeCountPerPage"
        @click="getMain"></b-pagination>
    </div>

  </div>
</template>
<script>
import MainService from "@/services/main/MainService";
export default {
  data() {
    return {
      pageIndex: 1,  //현재 페이지 번호
      totalCount: 0,  //전체 개수
      recodeCountPerPage: 3,  //화면에 보일 개수
      searchKeyword: "",  //검색어
      mains: [],  //빈배열(json)
    }
  },
  methods: {
    async getMain(){
      try {
        let response = await MainService.getAll(this.searchKeyword,this.pageIndex-1,this.recodeCountPerPage);
        const{results, totalCount} = response.data;
        console.log(response.data);
        this.mains = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
    }
  },

  mounted() {
    this.getMain();
  },
}
</script>
<style></style>