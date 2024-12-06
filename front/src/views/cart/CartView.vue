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
          <p class="cart-item-room">{{ data.roomName }}</p>
          <p class="cart-item-duration">
            숙박 일수 : {{ data.stayDuration }}박
          </p>
          <p class="cart-item-checkin">
            체크인 : {{ data.checkInDate }} {{ data.checkInTime }}
          </p>
          <p class="cart-item-checkin">
            체크아웃 : {{ data.checkOutDate }} {{ data.checkOutTime }}
          </p>

          <p class="cart-item-capacity">인원(기준) : {{ data.capacity }}명</p>
          <p class="cart-item-price" style="font-weight: 900; color: #e74c3c">
            총 결제 금액 : {{ data.totalPrice }}
          </p>
          <div class="cart-item-buttons">
            <button class="cart-item-buy" @click="makeReservation(data)">
              결제하기
            </button>
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

      reservation: {
        totalPrice: "", // 총 금액(1박 금액 * 숙박 일수)
        userEmail: "", // 유저 이메일
        stayDuration: 0, // 숙박 일수
        tourFileUrl: "", // 숙소 이미지
        tourName: "", // 숙소 명
        roomName: "", // 방 이름
        capacity: 0, // 수용 기준 인원
        checkInDate: "", // 체크인 날짜
        checkOutDate: "", // 체크아웃 날짜
        checkInTime: "", // 체크인 시간
        checkOutTime: "", // 체크아웃 날짜
      },
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
        localStorage.setItem("cartCount", this.cartCount.toString());
        // **객체나 배열 같은 복잡한 데이터는 JSON.stringify()를 사용하고, 숫자나 문자열 같은 단순한 데이터는 toString()을 사용해도 됨**
      } catch (error) {
        console.error("Error clearing cart:", error);
      }
    },

    makeReservation(data) {
      this.reservation.totalPrice = data.totalPrice;
      this.reservation.userEmail = data.userEmail;
      this.reservation.stayDuration = data.stayDuration;
      this.reservation.tourFileUrl = data.tourFileUrl;
      this.reservation.tourName = data.tourName;
      this.reservation.roomName = data.roomName;
      this.reservation.capacity = data.capacity;
      this.reservation.checkInTime = data.checkInTime;
      this.reservation.checkOutTime = data.checkOutTime;
      this.reservation.checkInDate = data.checkInDate;
      this.reservation.checkOutDate = data.checkOutDate;

      // 각 방에 대해 독립적인 예약할 정보 저장
      localStorage.setItem(
        `reservation_${data.cartId}`,
        JSON.stringify(this.reservation)
      );

      // 예약 페이지로 이동
      this.$router.push(`/cart/payment/${data.cartId}`);
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
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.cart-title {
  text-align: center;
  font-size: 1.8rem;
  margin-bottom: 20px;
  color: #333;
}

.clear-all-button {
  padding: 12px 24px; /* 적당히 큰 버튼 */
  background-color: #e74c3c;
  color: white;
  border: none;
  border-radius: 25px; /* 둥글게 */
  font-size: 1.2rem;
  cursor: pointer;
  width: auto;
  margin: 0 auto 20px;
  display: block;
  transition: background-color 0.3s, transform 0.2s ease;
}

.clear-all-button:hover {
  background-color: #c0392b;
  transform: scale(1.05); /* 호버 시 크기 약간 증가 */
}

.cart-items {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.cart-item {
  display: flex;
  background-color: white;
  padding: 15px;
  border-radius: 12px; /* 둥근 모서리 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.cart-item-image-container {
  width: 70%;
  height: auto;
  overflow: hidden;
  border-radius: 8px;
}

.cart-item-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.cart-item-details {
  width: 45%;
  padding-left: 15px;
  padding-right: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: auto;
}

.cart-item-title {
  font-size: 1.4rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 10px;
}

.cart-item-room,
.cart-item-duration,
.cart-item-checkin,
.cart-item-checkout,
.cart-item-price {
  font-size: 1rem;
  color: #666;
  margin: 5px 0;
}

.cart-item-buttons {
  display: flex;
  gap: 10px;
  margin-top: 15px;
  justify-content: flex-start;
}

.cart-item-buy,
.cart-item-remove {
  padding: 12px 24px;
  font-size: 1.1rem;
  border-radius: 25px; /* 둥글고 귀여운 버튼 */
  border: none;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s ease;
}

.cart-item-buy {
  background-color: #2ecc71;
  color: white;
}

.cart-item-buy:hover {
  background-color: #2980b9;
  transform: scale(1.05);
}

.cart-item-remove {
  background-color: #e74c3c;
  color: white;
}

.cart-item-remove:hover {
  background-color: #c0392b;
  transform: scale(1.05);
}

.cart-empty {
  text-align: center;
  color: #999;
  font-size: 1.2rem;
}
</style>
