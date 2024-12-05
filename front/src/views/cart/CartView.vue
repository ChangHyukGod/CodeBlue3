<template>
  <div class="cart-container">
    <h1 class="cart-title">장바구니(현재:{{ cartCount }})</h1>
    <button @click="clearAllCart" class="clear-all-button">전체 삭제</button>
    <div v-if="cart.length > 0" class="cart-items">
      <div class="cart-item" v-for="(data, index) in cart" :key="index">
        <!-- 이미지 섹션 -->
        <div class="cart-item-image-container">
          <img
            :src="data.tourFileUrl"
            alt="Tour Image"
            class="cart-item-image"
          />
        </div>

        <!-- 정보 섹션 -->
        <div class="cart-item-details">
          <h2 class="cart-item-title">{{ data.tourName }}</h2>
          <p class="cart-item-room">🛏 {{ data.roomName }}</p>
          <p class="cart-item-duration">
            📅 숙박 일수 : {{ data.stayDuration }}박
          </p>
          <p class="cart-item-checkin">
            🕒 체크인 : {{ data.checkInDate }} {{ data.checkInTime }}
          </p>
          <p class="cart-item-checkin">
            🕒 체크아웃 : {{ data.checkOutDate }} {{ data.checkOutTime }}
          </p>

          <p class="cart-item-capacity">
            👥 인원(기준) : {{ data.capacity }}명
          </p>
          <p class="cart-item-price">💰 총 결제 금액 : {{ data.totalPrice }}</p>
          <div class="cart-item-buttons">
            <button class="cart-item-buy">예약하기</button>
            <button
              class="cart-item-remove"
              @click="deleteFromCartId(data.cartId)"
            >
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-else class="cart-empty">
      <p>장바구니가 비어 있습니다.</p>
    </div>
  </div>
</template>

<script>
import CartService from "@/services/cart/CartService"; // 장바구니 정보 서비스

export default {
  data() {
    return {
      userEmail: "",

      cart: [],

      cartCount: 0, // 장바구니 개수 세기 변수
    };
  },
  methods: {
    async getItemsFromEmail(userEmail) {
      try {
        const response = await CartService.getItemsFromEmail(userEmail);
        this.cart = response.data;
      } catch (error) {
        console.error("Error fetching cart:", error);
      }
    },

    async deleteFromCartId(cartId) {
      try {
        // 서버에서 삭제 요청
        const response = await CartService.deleteFromCartId(cartId);
        console.log(response.data);
        alert("삭제 되었습니다.");
        // 로컬에서 즉시 삭제 (UI 반영)
        // filter 메서드는 배열에서 조건에 맞지 않는 항목들을 제거하여 새로운 배열을 생성
        this.cart = this.cart.filter((item) => item.cartId !== cartId); // 삭제된 항목을 제외한 나머지 항목들만 남김
        this.countCartItems(this.userEmail);
      } catch (error) {
        console.error("Error fetching cart:", error);
      }
    },

    async clearAllCart() {
      try {
        // 서버에서 삭제 요청
        const response = await CartService.deleteFromCartEmail(this.userEmail);
        console.log(response.data);

        alert("전체삭제 되었습니다.");
        // 서버에서 삭제가 성공적으로 처리되면 클라이언트에서 장바구니 비우기
        this.cart = []; // 장바구니 배열을 빈 배열로 설정하여 즉시 UI에 반영
        this.countCartItems(this.userEmail);
      } catch (error) {
        console.error("Error clearing cart:", error);
      }
    },

    async countCartItems(userEmail) {
      try {
        // 서버에서 삭제 요청
        const response = await CartService.countCartItems(userEmail);
        this.cartCount = response.data;
      } catch (error) {
        console.error("Error clearing cart:", error);
      }
    },
  },
  mounted() {
    const user = localStorage.getItem("user"); // 저장된 사용자 정보 가져오기
    if (user) {
      const parsedUser = JSON.parse(user); // JSON 문자열을 객체로 파싱
      this.userEmail = parsedUser.email; // 이메일 정보 저장
    } else {
      console.error("No user data found in localStorage.");
    }

    this.getItemsFromEmail(this.userEmail);
    this.countCartItems(this.userEmail);
  },
};
</script>

<style scoped>
.cart-container {
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
}

.cart-title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
  font-weight: bold;
}

.cart-items {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.cart-item {
  display: flex;
  border: 1px solid #ddd;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  background: #fff;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.cart-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.cart-item-image-container {
  flex: 1;
  display: flex;
  align-items: stretch; /* 이미지가 카드의 전체 높이를 채우도록 설정 */
  overflow: hidden; /* 이미지가 컨테이너를 벗어나지 않도록 설정 */
}

.cart-item-image {
  width: 100%; /* 너비를 컨테이너에 맞게 설정 */
  height: 100%; /* 높이를 컨테이너에 딱 맞게 설정 */
  object-fit: cover; /* 비율을 유지하면서 이미지를 꽉 채움 */
}

.cart-item-details {
  flex: 1;
  padding: 15px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.cart-item-title {
  font-size: 1.4rem;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.cart-item-room,
.cart-item-duration,
.cart-item-checkin,
.cart-item-capacity,
.cart-item-price {
  margin: 5px 0;
  font-size: 0.9rem;
  color: #666;
}

.cart-item-price {
  font-weight: bold;
  color: #000;
}
.cart-item-buttons {
  display: flex; /* 가로로 배치 */
  gap: 10px; /* 버튼 사이 간격 */
  justify-content: flex-start; /* 왼쪽 정렬 */
  margin-top: 10px; /* 버튼들 위에 여백 */
}

.cart-item-buy,
.cart-item-remove {
  padding: 10px 20px; /* 버튼 크기 */
  font-size: 1rem; /* 글씨 크기 */
  border-radius: 5px; /* 버튼 둥근 모서리 */
  cursor: pointer; /* 마우스 커서 변경 */
  transition: background-color 0.3s ease; /* 버튼 배경 색상 변화 */
}

.cart-item-buy {
  background-color: #4caf50; /* 예약하기 버튼 색상 */
  color: white;
  border: none;
}

.cart-item-buy:hover {
  background-color: #45a049; /* 호버 시 색상 변화 */
}

.cart-item-remove {
  background-color: #ff4d4d; /* 삭제 버튼 색상 */
  color: white;
  border: none;
}

.cart-item-remove:hover {
  background-color: #ff1a1a; /* 호버 시 색상 변화 */
}

.cart-empty {
  text-align: center;
  font-size: 1.2rem;
  color: #999;
}

/* 전체 삭제 버튼 스타일 */
.clear-all-button {
  padding: 15px 30px; /* 버튼 크기 조정 */
  font-size: 15px; /* 글씨 크기 */
  font-weight: bold; /* 글씨 두껍게 */
  color: white; /* 글씨 색상 */
  background-color: #f44336; /* 버튼 배경 빨간색 */
  border: none; /* 테두리 없애기 */
  border-radius: 30px; /* 버튼 둥근 모서리 */
  cursor: pointer; /* 마우스 커서 변경 */
  transition: all 0.3s ease; /* 부드러운 변환 효과 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 버튼 그림자 */
  width: 100%; /* 버튼 넓이를 100%로 */
  max-width: 150px; /* 최대 너비 제한 */
  margin: 20px auto; /* 버튼을 화면 중앙에 정렬 */
}

.clear-all-button:hover {
  background-color: #d32f2f; /* 호버 시 배경색 진해지기 */
  transform: translateY(-2px); /* 버튼 살짝 위로 뜨는 효과 */
}

.clear-all-button:active {
  background-color: #c62828; /* 클릭 시 더 진한 빨간색 */
  transform: translateY(2px); /* 클릭할 때 눌리는 효과 */
}
</style>
