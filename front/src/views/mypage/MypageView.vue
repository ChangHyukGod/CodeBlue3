<template>
  <div class="container mt-5">
    <h1 class="text-center text-warning">My Page</h1>

    <!-- 사용자 정보 -->
    <div class="card mb-4">
      <div class="card-header bg-warning text-white">
        <h4>User Profile</h4>
      </div>
      <div class="card-body">
        <ul class="list-group">
          <li class="list-group-item">
            <strong>Email:</strong> {{ profile.email }}
          </li>
          <li class="list-group-item">
            <strong>Name:</strong> {{ profile.name }}
          </li>
          <li class="list-group-item">
            <strong>Membership:</strong> {{ profile.codeName }}
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
              <td>{{ index + 1 }}</td>
              <td>{{ reservation.date }}</td>
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
export default {
  data() {
    return {
      profile: {
        email: "",
        name: "",
        codeName: "",
      },
      reservations: [],
      coupons: [],
    };
  },

  methods: {
    async getUserProfile() {
      try {
        const response = // 사용자 정보 조회
          (this.profile = response.data);
        this.getReservations(); // 예약 데이터 조회
        this.getCoupons(); // 쿠폰 데이터 조회
      } catch (error) {
        console.error("Error loading user profile:", error);
      }
    },

    async getReservations() {
      // 예약 데이터 조회 (예시 데이터 사용)
      this.reservations = [
        { date: "2024-12-01", details: "VIP Movie Ticket" },
        { date: "2024-12-10", details: "Concert Ticket" },
      ];
    },

    async getCoupons() {
      // 쿠폰 데이터 조회 (예시 데이터 사용)
      this.coupons = [
        { name: "Discount Coupon", discount: 20, validUntil: "2024-12-31" },
        { name: "Bonus Coupon", discount: 10, validUntil: "2024-11-30" },
      ];
    },
  },

  mounted() {
    this.getUserProfile();
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
