<template>
  <div class="container mt-5">
    <!-- 사용자 정보 -->
    <div class="card mb-4">
      <div class="card-header bg-warning text-white">
        <h4>User Profile</h4>
      </div>

        <div class="card-body">
          <ul class="list-group">
            <li class="list-group-item">
              <strong>Email:</strong> {{ email }}
            </li>
          </ul>
        </div>
      </div>

      
  


    <!-- 탭 네비게이션 -->
    <ul class="nav nav-tabs" id="myTab" role="tablist">
      <li class="nav-item">
        <a
          class="nav-link active"
          id="reservations-tab"
          data-bs-toggle="tab"
          href="#reservations"
          role="tab"
          aria-controls="reservations"
          aria-selected="true"
        >
          예약
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          id="coupons-tab"
          data-bs-toggle="tab"
          href="#coupons"
          role="tab"
          aria-controls="coupons"
          aria-selected="false"
        >
          문의
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          id="coupons-tab"
          data-bs-toggle="tab"
          href="#coupons"
          role="tab"
          aria-controls="coupons"
          aria-selected="false"
        >
          쿠폰
        </a>
      </li>
    </ul>

    <div class="tab-content mt-3" id="myTabContent">
      <!-- 예약 탭 -->
      <div
        class="tab-pane fade show active"
        id="reservations"
        role="tabpanel"
        aria-labelledby="reservations-tab"
      >
        <h5 class="text-warning">My Reservations</h5>
        <table class="table table-striped">
          <thead class="bg-warning text-white">
            <tr>
              <th>#</th>
              <th>Reservation Date</th>
              <th>Details</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(reservation, index) in reservations" :key="index">
              <td>{{ reservation.reviewId }}</td>
              <td>{{ reservation.details }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 쿠폰 탭 -->
      <div
        class="tab-pane fade"
        id="coupons"
        role="tabpanel"
        aria-labelledby="coupons-tab"
      >
        <h5 class="text-warning">My Coupons</h5>
        <table class="table table-striped">
          <thead class="bg-warning text-white">
            <tr>
              <th>#</th>
              <th>Coupon Name</th>
              <th>Discount</th>
              <th>Valid Until</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(coupon, index) in coupons" :key="index">
              <td>{{ index + 1 }}</td>
              <td>{{ coupon.name }}</td>
              <td>{{ coupon.discount }}%</td>
              <td>{{ coupon.validUntil }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import MypageService from "@/services/mypage/MypageService";


export default {
  data() {
    return {
      email:null ,
      pageIndex: 1, //현재페이지번호
      totalCount: 0, // 전체개수
      recordCountPerPage: 1, //화면에 보일개수
      searchKeyword: "",
      mypages: [], // 빈배열(json)
    };
  },



  

  methods: {
    async getMypage() {
      try {
        let response = await MypageService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recordCountPerPage
        );
        // TODO: 백엔드 전송되는 것 : results(배열), totalCount(총개수)
        const { results, totalCount } = response.data;
        console.log(response.data); // 디버깅
        this.mypages = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
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
