<template>
  <div class="payment-page">
    <h1 class="page-title">결제 페이지</h1>

    <!-- 스티키박스: 상단 고정 -->
    <div class="sticky-box">
      <div class="sticky-box-info">
        <p class="left">총 {{ selectedItems.length }}건</p>
        <!-- 체크된 아이템의 개수 -->
        <p class="right">총 결제 금액 {{ formatPrice(totalPrice) }}원</p>
        <!-- 체크된 아이템의 총 가격 -->
      </div>
    </div>

    <!-- 선택된 아이템이 있을 때 -->
    <div v-if="selectedItems.length" class="cart-items">
      <div
        class="cart-item"
        v-for="(item, index) in selectedItems"
        :key="index"
      >
        <div class="cart-item-image">
          <img :src="item.tourFileUrl" alt="Tour Image" />
        </div>
        <div class="cart-item-details">
          <h3 class="tour-name">{{ item.tourName }}</h3>
          <p>
            {{ item.roomName }}
          </p>
          <p>인원(기준) : {{ item.capacity }}명</p>
          <p>
            체크인 : {{ item.checkInDate }}
            {{ item.checkInTime }}
          </p>
          <p>
            체크아웃 : {{ item.checkOutDate }}
            {{ item.checkOutTime }}
          </p>
          <p>숙박 일수 : {{ item.stayDuration }}박</p>
          <p class="item-price">
            결제 금액 : <span>{{ item.totalPrice }}원</span>
          </p>
        </div>
      </div>
    </div>

    <!-- 선택된 아이템이 없을 때 -->
    <div v-else>
      <p class="no-items">선택된 항목이 없습니다.</p>
    </div>

    <!-- 결제 수단 섹션 추가 -->
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

      <button
        class="sticky-box-button"
        style="
          margin-top: 20px;
          height: 50px;
          width: 100%;
          background-color: #e74c3c;
          font-size: 20px;
          color: white;
          border: none;
          border-radius: 10px;
        "
        @click="processPayment"
      >
        {{ formatPrice(totalPrice) }}원 결제하기
      </button>

      <!-- 상단으로 스크롤 이동하는 화살표 버튼 -->
      <button class="scroll-to-top" @click="scrollToTop">↑</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedItems: [], // 로컬스토리지에서 가져온 데이터
      totalPrice: 0, // 총 결제 금액
      sselectedPaymentMethod: null, // 선택된 결제 수단
    };
  },
  mounted() {
    // localStorage에서 데이터 로드
    this.selectedItems =
      JSON.parse(localStorage.getItem("selectedItems")) || [];
    // 총 결제 금액 계산
    this.calculateTotalPrice();
  },
  methods: {
    // 총 결제 금액 계산
    calculateTotalPrice() {
      this.totalPrice = this.selectedItems.reduce((sum, item) => {
        if (item && item.totalPrice) {
          // item.totalPrice가 문자열이라면 쉼표 제거 후 숫자 변환
          return sum + Number(item.totalPrice.replace(/,/g, ""));
        }
        return sum;
      }, 0);
    },

    // 결제 금액 포맷팅 (천 단위 구분 쉼표 추가)
    formatPrice(price) {
      if (!price || isNaN(price)) return "0";
      return price.toLocaleString(); // 가격에 쉼표 추가
    },

    // 상단으로 스크롤 이동하는 메서드
    scrollToTop() {
      window.scrollTo(0, 0);
    },

    processPayment() {
      if (!this.selectedPaymentMethod) {
        alert("결제 수단을 선택해주세요."); // 결제 수단 미선택 시 경고
        return;
      }
    },
  },
};
</script>

<style scoped>
.payment-page {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
}

.page-title {
  text-align: center;
  font-size: 2.2rem;
  margin-bottom: 30px;
  font-weight: bold;
}

.sticky-box,
.cart-items,
.payment-method {
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
}

.sticky-box {
  position: sticky;
  top: 0;
  background-color: #f1f1f1;
  padding: 10px 0;
  z-index: 10;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.sticky-box-info {
  display: flex;
  justify-content: space-between;
  padding: 0 20px;
  font-size: 1.6rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.sticky-box-info p {
  margin: 0;
  font-weight: bold;
}

.cart-item {
  display: flex;
  background-color: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  padding: 15px;
  box-sizing: border-box;
  width: 100%;
}

.cart-item-image,
.cart-item-details {
  min-height: 220px; /* 최소 높이 설정 */
}

.cart-item-image {
  width: 60%; /* 이미지 비율을 6에 맞게 설정 */
  margin-right: 20px;
}

.cart-item-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.cart-item-details {
  flex: 1;
  width: 40%; /* 텍스트 비율을 4에 맞게 설정 */
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* 내용이 상하로 균등하게 배치되도록 설정 */
}

.cart-item-details p {
  margin: 6px 0;
  overflow: hidden;
  text-overflow: ellipsis; /* 텍스트가 길면 ...으로 표시 */
}

.cart-item-details h3 {
  font-size: 1.6rem;
  font-weight: bold;
  margin-bottom: 15px;
}

.tour-name {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 15px;
}

.item-price {
  font-size: 1.6rem;
  color: #e74c3c;
  margin-bottom: 15px;
}

.payment-method {
  text-align: center;
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
  display: none; /* 라디오 버튼 숨기기 */
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

.payment-options label {
  font-size: 1.4rem;
}

.payment-method h3 {
  text-align: center;
  font-size: 1.8rem;
  margin-bottom: 20px;
}

.scroll-to-top {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background: linear-gradient(135deg, #333, #555);
  color: white;
  border: none;
  border-radius: 15%;
  width: 40px;
  height: 40px;
  font-size: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3);
  z-index: 100;
  transition: all 0.3s ease;
}

.scroll-to-top:hover {
  transform: scale(1.1);
  background: linear-gradient(135deg, #555, #777);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.4);
}
</style>
