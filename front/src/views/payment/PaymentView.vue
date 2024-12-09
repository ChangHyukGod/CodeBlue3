<template>
  <div class="payment-page">
    <h2 style="margin-bottom: 30px; font-weight: 900">예약</h2>

    <div v-if="reservation" class="reservation-details">
      <div class="image-container">
        <img
          :src="reservation.tourFileUrl"
          alt="숙소 이미지"
          class="tour-image"
        />
      </div>

      <div class="details-container">
        <h3 style="font-weight: 900">{{ reservation.tourName }}</h3>
        <p class="room-name" style="font-weight: 900">
          {{ reservation.roomName }}
        </p>
        <p class="capacity">인원(기준) : {{ reservation.capacity }}명</p>

        <div class="dates">
          <p>
            체크인 : {{ reservation.checkInDate }} {{ reservation.checkInTime }}
          </p>
          <p>
            체크아웃 : {{ reservation.checkOutDate }}
            {{ reservation.checkOutTime }}
          </p>
        </div>

        <div class="pricing">
          <p>숙박 일수 : {{ reservation.stayDuration }}일</p>
          <p class="total-price">
            총 결제 금액 : {{ reservation.totalPrice }}원
          </p>
        </div>
      </div>
    </div>
    <div class="payment-method">
      <h3>결제 수단</h3>
      <div class="payment-options">
        <!-- 카카오페이 -->
        <label>
          <input type="radio" name="paymentMethod" value="kakaoPay" />
          카카오페이
        </label>
        <!-- 토스페이 -->
        <label>
          <input type="radio" name="paymentMethod" value="tossPay" />
          토스페이
        </label>
        <!-- 네이버페이 -->
        <label>
          <input type="radio" name="paymentMethod" value="naverPay" />
          네이버페이
        </label>
        <!-- 휴대폰 -->
        <label>
          <input type="radio" name="paymentMethod" value="phonePay" />
          휴대폰
        </label>
      </div>

      <!-- 쿠폰등록하기 버튼 -->
      <div class="mt-3">
        <button class="btn btn-warning" @click="toggleCouponForm">
          쿠폰등록하기
        </button>
      </div>

      <!-- 쿠폰 선택 폼 -->
      <div v-if="showCouponForm" class="mt-3 p-3 bg-light border rounded">
        <h5>쿠폰 선택</h5>
        <ul class="list-group">
          <li
            v-for="coupon in coupons"
            :key="coupon.id"
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            <span>{{ coupon.name }} ({{ coupon.value }}%)</span>
            <button
              class="btn btn-primary btn-sm"
              @click="selectCoupon(coupon)"
            >
              선택
            </button>
          </li>
        </ul>
      </div>

      <!-- 선택된 쿠폰 표시 -->
      <div v-if="selectedCoupon" class="mt-3 alert alert-success">
        선택된 쿠폰: {{ selectedCoupon.name }} ({{ selectedCoupon.value }}%)
      </div>
    </div>

    <div class="payment-section">
      <button class="payment-button" @click="processPayment">
        {{ reservation.totalPrice }}원 결제하기
      </button>
      <button
        class="payment-button"
        @click="returnPage"
        style="margin-left: 10px; width: 150px"
      >
        뒤로가기
      </button>
    </div>
  </div>
</template>

<script>
import PortOne from "@portone/browser-sdk/v2";
import CouponService from "@/services/coupon/CounponService";

export default {
  data() {
    return {
      reservation: {}, // 예약 정보 저장 객체
      selectedPaymentMethod: "", // 선택된 결제 수단

      showCouponForm: false, // 쿠폰 폼 표시 여부
      coupons: [],
      pageIndex: 1, //현재페이지번호
      totalCount: 0, // 전체개수
      recordCountPerPage: 1, //화면에 보일개수
      searchKeyword: "",
      selectedCoupon: null, // 선택된 쿠폰
      value: 20.0,
      name: "",
      id: "",

      main: {
        channelKey: "channel-key-79ccff33-9c28-4395-854d-a2c186f8d461",
        payMethod: "EASY_PAY",
        totalAmount: "60000",
        orderName: "매운라면",
        merchant_uid: "ORD20231030-000001",
        storeId: "store-37adc342-491c-4a84-ae08-08fe128442bb",
        paymentId: "758771037252287",
        currency: "KRW",
      },
    };
  },
  mounted() {
    // URL에서 roomId 파라미터 가져오기
    const roomId = this.$route.params.roomId;

    // 해당 roomId에 대한 예약 정보를 로컬 스토리지에서 가져오기
    const reservationData = localStorage.getItem(`reservation_${roomId}`);

    if (reservationData) {
      this.reservation = JSON.parse(reservationData);
    } else {
      alert("예약 정보가 없습니다.");
      this.$router.push("/"); // 예약 정보가 없으면 홈으로 리다이렉트
    }
    this.getCoupon();
  },
  methods: {
    async processPayment() {
      try {
        // 쉼표를 제거하고 정수형으로 변환
        const sanitizedPrice = parseInt(
          this.reservation.totalPrice.replace(/,/g, ""),
          10
        );
        this.main.totalAmount = sanitizedPrice; // 정수형으로 업데이트
        PortOne.requestPayment(this.main);
      } catch (error) {
        console.log(error);
      }
    },

    returnPage() {
      // 이전 페이지로 이동
      this.$router.go(-1);
    },

    toggleCouponForm() {
      this.showCouponForm = !this.showCouponForm;
    },

    selectCoupon(coupon) {
      // 선택된 쿠폰 저장
      this.selectedCoupon = coupon;

      // 원래 금액에서 할인 적용
      if (this.reservation.originalPrice === undefined) {
        this.reservation.originalPrice = parseInt(
          this.reservation.totalPrice.replace(/,/g, ""),
          10
        );
      }

      const discountRate = coupon.value / 100; // 할인율 (예: 20% -> 0.2)
      const discountedPrice =
        this.reservation.originalPrice * (1 - discountRate);
      this.reservation.totalPrice = discountedPrice.toLocaleString(); // 쉼표 형식 적용

      this.showCouponForm = false; // 폼 닫기
      console.log("선택된 쿠폰:", coupon);
      console.log("할인된 금액:", discountedPrice);
    },

    async getCoupon() {
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
  },
};
</script>

<style scoped>
.payment-page {
  padding: 20px;
  font-family: Arial, sans-serif;
  max-width: 1200px;
  margin: 0 auto;
  text-align: center; /* 모든 텍스트를 가운데 정렬 */
}

h2 {
  margin-bottom: 20px;
}

.reservation-details {
  display: flex;
  justify-content: center; /* 이미지와 예약 정보를 가로로 가운데 정렬 */
  align-items: flex-start; /* 세로로 상단 정렬 */
  gap: 10px; /* 간격을 10px로 줄였습니다 */
  margin-bottom: 30px;
}

.image-container {
  flex: 1;
  max-width: 300px; /* 이미지 최대 너비 설정 */
  text-align: center; /* 이미지 가운데 정렬 */
}

.tour-image {
  width: 450px;
  height: 100%;
  border-radius: 8px;
  object-fit: cover;
}

.details-container {
  flex: 2;
  max-width: 600px;
}

h3 {
  font-size: 1.8em;
  margin-bottom: 10px;
}

.room-name {
  font-size: 1.4em;
  margin-bottom: 10px;
}

.capacity {
  margin-bottom: 10px;
}

.dates {
  font-size: 18px;
  margin-bottom: 20px;
  font-weight: 700;
}

.pricing {
  font-size: 1.4em;
}

.total-price {
  color: #e74c3c;
  font-weight: bold;
}

.payment-method {
  margin-top: 30px;
}

.payment-options {
  display: flex;
  justify-content: center;
  gap: 20px; /* 결제 수단 사이에 여백 추가 */
  margin-top: 20px;
}

.payment-options label {
  font-size: 1.2em;
  cursor: pointer;
}

.payment-section {
  margin-top: 40px;
}

.payment-button {
  background-color: #2ecc71;
  color: white;
  padding: 12px 24px;
  font-size: 1.5em;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
  width: 100%;
  max-width: 300px;
  margin: 0 auto; /* 버튼을 가운데 정렬 */
}

.payment-button:hover {
  background-color: #27ae60;
}
</style>
