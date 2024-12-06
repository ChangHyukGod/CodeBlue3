<template>
  <div class="cart-container">
    <h1 class="cart-title">장바구니(현재: {{ cartCount }})</h1>

    <!-- 전체 선택 및 선택 삭제 버튼 -->
    <div class="cart-actions">
      <label>
        <input
          type="checkbox"
          @change="toggleSelectAll"
          :checked="isAllSelected"
        />
        전체 선택
      </label>
      <button @click="deleteSelectedItems" class="delete-selected-button">
        선택 삭제
      </button>
    </div>

    <!-- 장바구니 아이템 목록 -->
    <div v-if="cart.length > 0" class="cart-items">
      <div class="cart-item" v-for="(data, index) in cart" :key="index">
        <!-- 이미지 섹션 -->
        <div class="cart-item-image-container">
          <!-- 이미지 상단 왼쪽 체크박스 -->
          <input
            type="checkbox"
            v-model="selectedItems"
            :value="data.cartId"
            class="cart-item-checkbox"
          />
          <img
            :src="data.tourFileUrl"
            alt="Tour Image"
            class="cart-item-image"
            @click="makeReservation(data)"
          />
        </div>

        <!-- 정보 섹션 -->
        <div class="cart-item-details">
          <button
            class="cart-item-remove"
            @click="deleteFromCartId(data.cartId)"
          >
            &times;
            <!-- X 아이콘 -->
          </button>
          <h2 class="cart-item-title">{{ data.tourName }}</h2>
          <p class="cart-item-room">{{ data.roomName }}</p>
          <p class="cart-item-duration">
            숙박 일수 : {{ data.stayDuration }}박
          </p>
          <p class="cart-item-checkin">
            체크인 : {{ data.checkInDate }} {{ data.checkInTime }}
          </p>
          <p class="cart-item-checkout">
            체크아웃 : {{ data.checkOutDate }} {{ data.checkOutTime }}
          </p>
          <p class="cart-item-capacity">인원(기준) : {{ data.capacity }}명</p>
          <p class="cart-item-price" style="font-weight: 900; color: #e74c3c">
            총 결제 금액 : {{ data.totalPrice }}
          </p>
        </div>
      </div>
    </div>
    <div v-else class="cart-empty">
      <p>장바구니가 비어 있습니다.</p>
    </div>
  </div>
</template>

<script>
import CartService from "@/services/cart/CartService";

export default {
  data() {
    return {
      userEmail: "",
      user: "",
      cart: [],
      cartCount: 0, // 장바구니 개수
      selectedItems: [], // 선택된 cartId 리스트
      isAllSelected: false, // 전체 선택 상태
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
        await CartService.deleteFromCartId(cartId);
        alert("삭제 되었습니다.");
        this.cart = this.cart.filter((item) => item.cartId !== cartId);
        this.countCartItems(this.userEmail);
      } catch (error) {
        console.error("Error deleting item:", error);
      }
    },
    async deleteSelectedItems() {
      if (this.selectedItems.length === 0) {
        alert("선택된 항목이 없습니다.");
        return;
      }

      try {
        await Promise.all(
          this.selectedItems.map((cartId) =>
            CartService.deleteFromCartId(cartId)
          )
        );
        alert("선택된 항목이 삭제되었습니다.");
        this.cart = this.cart.filter(
          (item) => !this.selectedItems.includes(item.cartId)
        );
        this.selectedItems = []; // 선택 항목 초기화
        this.isAllSelected = false; // 전체 선택 초기화
        this.countCartItems(this.userEmail);
      } catch (error) {
        console.error("Error deleting selected items:", error);
      }
    },
    toggleSelectAll() {
      if (this.isAllSelected) {
        this.selectedItems = []; // 전체 선택 해제
      } else {
        this.selectedItems = this.cart.map((item) => item.cartId); // 전체 선택
      }
      this.isAllSelected = !this.isAllSelected;
    },
    async clearAllCart() {
      try {
        await CartService.deleteFromCartEmail(this.userEmail);
        alert("전체 삭제 되었습니다.");
        this.cart = [];
        this.countCartItems(this.userEmail);
      } catch (error) {
        console.error("Error clearing cart:", error);
      }
    },
    async countCartItems(userEmail) {
      try {
        const response = await CartService.countCartItems(userEmail);
        this.cartCount = response.data;
        localStorage.setItem("cartCount", this.cartCount.toString());
      } catch (error) {
        console.error("Error counting cart items:", error);
      }
    },
    makeReservation(data) {
      const reservation = {
        totalPrice: data.totalPrice,
        userEmail: data.userEmail,
        stayDuration: data.stayDuration,
        tourFileUrl: data.tourFileUrl,
        tourName: data.tourName,
        roomName: data.roomName,
        capacity: data.capacity,
        checkInDate: data.checkInDate,
        checkInTime: data.checkInTime,
        checkOutDate: data.checkOutDate,
        checkOutTime: data.checkOutTime,
      };

      localStorage.setItem(
        `reservation_${data.cartId}`,
        JSON.stringify(reservation)
      );

      this.$router.push(`/cart/payment/${data.cartId}`);
    },
  },
  mounted() {
    const user = localStorage.getItem("user");
    if (user) {
      const parsedUser = JSON.parse(user);
      this.user = parsedUser;
      this.userEmail = parsedUser.email;
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

/* 전체 선택/삭제 버튼 */
.cart-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.delete-selected-button {
  color: #3498db; /* 파란색 글씨 */
  background-color: transparent; /* 배경색 제거 */
  border: none;
  font-size: 1rem;
  cursor: pointer;
  transition: color 0.3s, transform 0.2s ease;
}

.delete-selected-button:hover {
  color: #2980b9; /* 호버 시 글씨 색 변경 */
  transform: scale(1.05); /* 호버 시 약간 확대 */
}

.cart-items {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.cart-item {
  display: flex;
  position: relative; /* 상대 위치 설정 */
  background-color: white;
  padding: 15px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.cart-item-image-container {
  width: 70%;
  overflow: hidden;
  border-radius: 8px;
  position: relative; /* 체크박스 위치 지정 */
}

.cart-item-image {
  width: 100%;
  height: auto;
  object-fit: cover;
}

/* 이미지 상단 왼쪽 체크박스 */
.cart-item-image-container input[type="checkbox"] {
  position: absolute;
  top: 10px;
  left: 10px;
  width: 20px;
  height: 20px;
  z-index: 2;
}

.cart-item-details {
  position: relative; /* 상대적 위치를 설정 */
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
}

.cart-item-remove {
  position: absolute; /* 절대 위치 */
  top: 5px; /* 상단에서 5px */
  right: 15px; /* 우측에서 5px */
  background: none; /* 배경 제거 */
  border: none; /* 테두리 제거 */
  font-size: 1.8rem; /* 글씨 크기 감소 */
  cursor: pointer; /* 커서 변경 */
  transition: color 0.2s ease;
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

.cart-item-buy {
  padding: 10px 20px;
  font-size: 1rem;
  border-radius: 25px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s ease;
}

.cart-empty {
  text-align: center;
  color: #999;
  font-size: 1.2rem;
}
</style>
