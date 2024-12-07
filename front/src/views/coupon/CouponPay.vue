<template>
    <div>
    <h4>쿠폰 목록</h4>
    <div v-for="coupon in coupons" :key="coupon.id" class="coupon-item">
      <div>
        <strong>{{ coupon.name }}</strong> - 할인 금액: {{ coupon.discount }}원
      </div>
      <button @click="selectCoupon(coupon)">선택</button>
    </div>
  
    
  

    </div>
  </template>
  
  <script>
import CouponService from '@/services/coupon/CounponService';

  
  
  
  export default {
    data() {
      return {
        email:null ,
        pageIndex: 1, //현재페이지번호
        totalCount: 0, // 전체개수
        recordCountPerPage: 1, //화면에 보일개수
        searchKeyword: "",
        coupons: [
 

        { id: 2, name: "20% 할인 쿠폰", value: 300000 },

        ], // 빈배열(json)
        value: 20.0,
        name: "",
        memberEmail: "",
        id:"",
      };
    },
  
  
  
    
  
    methods: {
      async getMypage() {
        try {
          let response = await CouponService.getAll(
            this.searchKeyword,
            this.pageIndex - 1,
            this.recordCountPerPage
          );
          // TODO: 백엔드 전송되는 것 : results(배열), totalCount(총개수)
          const { results, totalCount } = response.data;
          console.log(response.data); // 디버깅
          this.coupons = results;
          this.totalCount = totalCount;
        } catch (error) {
          console.log(error);
        }
      },


      selectCoupon(coupon) {
      // 선택된 쿠폰 정보를 저장 (localStorage 또는 Vuex)
      localStorage.setItem("selectedCoupon", JSON.stringify(coupon));
      alert(`${coupon.name}이(가) 선택되었습니다!`);
      this.$router.push(`/payment`);
    },
  






      
    },
  
    mounted() {
      this.getMypage();
      this.email = localStorage.getItem("userEmail");



      
    },
  };
  </script>
  
  <style scoped>
  .card-header {
    background-color: #f8c102; /* 노란색 */
  }
  
  .nav-tabs .nav-link.active {
    background-color: #f8c102;
    color: white;
  }
  
  .nav-tabs .nav-link {
    border-color: #f8c102;
  }
  
  .table th {
    background-color: #f8c102;
    color: white;
  }
  
  .table-striped tbody tr:nth-child(odd) {
    background-color: #fef7e2;
  }
  </style>
  