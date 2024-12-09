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
        <p class="room-name">
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
          <p>숙박 일수 : {{ reservation.stayDuration }}박</p>
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
        <!-- 카카오페이 -->
        <label class="payment-option">
          <input
            type="radio"
            name="paymentMethod"
            value="kakaoPay"
            v-model="selectedPaymentMethod"
          />
          <img
            src="@/assets/images/PaymentMethod/ㅋㅋㅇㅍㅇ 아이콘.png"
            alt="카카오페이"
          />
        </label>
        <!-- 토스페이 -->
        <label class="payment-option">
          <input
            type="radio"
            name="paymentMethod"
            value="tossPay"
            v-model="selectedPaymentMethod"
          />
          <img
            src="@/assets/images/PaymentMethod/ㅌㅅ 아이콘.png"
            alt="토스페이"
          />
        </label>
        <!-- 네이버페이 -->
        <label class="payment-option">
          <input
            type="radio"
            name="paymentMethod"
            value="naverPay"
            v-model="selectedPaymentMethod"
          />
          <img
            src="@/assets/images/PaymentMethod/ㄴㅇㅂㅍㅇ 아이컨.png"
            alt="네이버페이"
          />
        </label>
        <!-- 휴대폰 -->
        <label class="payment-option">
          <input
            type="radio"
            name="paymentMethod"
            value="phonePay"
            v-model="selectedPaymentMethod"
          />
          <img
            src="@/assets/images/PaymentMethod/ㅎㄷㅍ 아이콘.png"
            alt="휴대폰"
          />
        </label>
      </div>
    </div>

    <div class="payment-section">
      <button class="payment-button primary-button" @click="processPayment">
        {{ reservation.totalPrice }}원 결제하기
      </button>
      <button class="payment-button secondary-button" @click="returnPage">
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
    const cartId = this.$route.params.cartId;

    // 해당 roomId에 대한 예약 정보를 로컬 스토리지에서 가져오기
    const reservationData = localStorage.getItem(`reservation_${cartId}`);

    if (reservationData) {
      this.reservation = JSON.parse(reservationData);
    } else {
      alert("예약 정보가 없습니다.");
      this.$router.push("/"); // 예약 정보가 없으면 홈으로 리다이렉트
    }
  },
  methods: {
    processPayment() {
      if (!this.selectedPaymentMethod) {
        alert("결제 수단을 선택해주세요."); // 결제 수단 미선택 시 경고
        return;
      }
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
  text-align: center;
}

h2 {
  margin-bottom: 20px;
}

.reservation-details {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 50px;
  margin: 0 auto;
  max-width: 900px;
  text-align: left;
}

.image-container {
  flex: 1;
  max-width: 300px;
  text-align: center;
}

.tour-image {
  width: 300px;
  height: 100%;
  border-radius: 8px;
  object-fit: cover;
}

.details-container {
  flex: 2;
  max-width: 300px;
}

h3 {
  font-size: 1.8em;
  margin-bottom: 10px;
}

.capacity {
  margin-bottom: 10px;
}

.pricing {
  font-size: 1.4em;
}

.total-price {
  color: #e74c3c;
  font-weight: bold;
}

.payment-method {
  margin-top: 20px;
}

.payment-options {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 10px;
}

.payment-option {
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
}

.payment-option input {
  display: none;
}

.payment-option img {
  width: 60px;
  height: 60px;
  object-fit: cover;
  border-radius: 50%;
  border: 2px solid transparent;
  transition: all 0.3s ease;
}

.payment-option input:checked + img {
  border-color: #ff4b2b;
  box-shadow: 0 0 8px rgba(255, 75, 43, 0.5);
}

.payment-option img:hover {
  transform: scale(1.1);
}

.payment-section {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 15px;
  margin-top: 20px;
}

.payment-button {
  padding: 12px 20px;
  font-size: 16px;
  font-weight: bold;
  border-radius: 25px;
  border: none;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
}

.payment-button:hover {
  transform: scale(1.05);
}

.payment-button:active {
  transform: scale(0.95);
}

/* 구매하기 버튼 스타일 */
.primary-button {
  background: linear-gradient(90deg, #ff4b2b, #ff416c);
  color: white;
}

.primary-button:hover {
  background: linear-gradient(90deg, #ff416c, #ff4b2b);
  box-shadow: 0 6px 8px rgba(255, 75, 43, 0.4);
}

.primary-button:active {
  box-shadow: 0 3px 5px rgba(255, 75, 43, 0.3);
}

/* 뒤로가기 버튼 스타일 */
.secondary-button {
  background: linear-gradient(90deg, #434343, #000000);
  color: white;
}

.secondary-button:hover {
  background: linear-gradient(90deg, #000000, #434343);
}
</style>
