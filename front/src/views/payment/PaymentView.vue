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

    <!-- 결제 수단 섹션 추가 -->
    <div class="payment-method">
      <h3>결제 수단</h3>
      <div class="payment-options">
        <label>
          <input type="radio" name="paymentMethod" value="kakaoPay" />
          카카오페이
        </label>
        <label>
          <input type="radio" name="paymentMethod" value="tossPay" />
          토스페이
        </label>
        <label>
          <input type="radio" name="paymentMethod" value="naverPay" />
          네이버페이
        </label>
        <label>
          <input type="radio" name="paymentMethod" value="phonePay" />
          휴대폰
        </label>
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
export default {
  data() {
    return {
      reservation: {}, // 예약 정보 저장 객체
      selectedPaymentMethod: "", // 선택된 결제 수단
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
  },
  methods: {
    processPayment() {
      // alert("결제 수단을 선택해주세요.");
      // return;
    },

    returnPage() {
      // 이전 페이지로 이동
      this.$router.go(-1);
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
