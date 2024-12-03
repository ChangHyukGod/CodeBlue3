<template>
  <div>
    <!-- 방 추가 버튼, tourId를 URL에 포함하여 전달 -->
    <b-nav-item
      v-if="userRole === 'ROLE_ADMIN'"
      :href="'/addRoom/' + tourId"
      class="btn btn-primary px-4 py-2 rounded-pill shadow-sm text-white mb-3"
    >
      방 추가
    </b-nav-item>

    <!-- 최상단 캐러셀 -->
    <div id="topCarousel" class="carousel slide mb-5" data-bs-ride="false">
      <div class="carousel-inner">
        <!-- 모든 방 이미지들을 최상단 캐러셀로 반복 -->
        <div
          v-for="(imageUrl, idx) in AllImages"
          :key="idx"
          :class="['carousel-item', { active: idx === 0 }]"
        >
          <img
            :src="imageUrl"
            class="d-block w-100"
            alt="방 이미지"
            style="height: 400px; object-fit: cover"
          />
        </div>
      </div>
      <!-- 이전 버튼 -->
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#topCarousel"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <!-- 다음 버튼 -->
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#topCarousel"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>

    <!-- 각 방의 정보와 캐러셀 -->
    <div v-if="rooms.length > 0" class="d-flex flex-column align-items-center">
      <div
        v-for="room in rooms"
        :key="room.roomId"
        class="room-container d-flex align-items-center mb-4"
      >
        <!-- 좌측 박스: 방 캐러셀 이미지 -->
        <div class="image-box">
          <div
            :id="'carouselExampleControls-' + room.roomId"
            class="carousel slide"
          >
            <div class="carousel-inner">
              <!-- 첫 번째 이미지 활성화 -->
              <div
                v-for="(imageUrl, idx) in [
                  room.roomUrl,
                  ...(EachRoomUrls[room.roomId] || []),
                ]"
                :key="idx"
                :class="['carousel-item', { active: idx === 0 }]"
              >
                <img
                  :src="imageUrl"
                  class="d-block w-100"
                  alt="방 이미지"
                  style="height: 300px; object-fit: cover"
                  @click="
                    userRole === 'ROLE_ADMIN' && addRoomPhoto(room.roomId)
                  "
                />
              </div>
            </div>
            <button
              class="carousel-control-prev"
              type="button"
              :data-bs-target="'#carouselExampleControls-' + room.roomId"
              data-bs-slide="prev"
            >
              <span
                class="carousel-control-prev-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button
              class="carousel-control-next"
              type="button"
              :data-bs-target="'#carouselExampleControls-' + room.roomId"
              data-bs-slide="next"
            >
              <span
                class="carousel-control-next-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>

        <!-- 우측 박스: 방 정보 -->
        <div class="info-box d-flex flex-column justify-content-between">
          <div>
            <h5 class="card-title">{{ room.roomName }}</h5>
            <p class="card-text">
              <strong>최대 인원:</strong> {{ room.capacity }}<br />
              <strong>가격:</strong> {{ room.price }}<br />
              <strong>설명:</strong> {{ room.description }}<br />
              <strong>체크인:</strong> {{ room.checkIn }}<br />
              <strong>체크아웃:</strong> {{ room.checkOut }}
            </p>
          </div>
          <div class="button-group mt-2">
            <button
              v-if="userRole === 'ROLE_ADMIN'"
              @click="editRoom(room.roomId)"
              class="btn btn-outline-warning me-2"
            >
              업데이트
            </button>
            <button
              v-if="userRole === 'ROLE_ADMIN'"
              @click="deleteRoom(room.roomId)"
              class="btn btn-outline-danger"
            >
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-else class="text-center">
      <p>방 정보가 없습니다.</p>
    </div>
  </div>
</template>

<script>
import MainService from "@/services/main/MainService"; // 숙소 정보 서비스
import RoomService from "@/services/room/RoomService"; // 방 정보 서비스

export default {
  data() {
    return {
      tour: {
        name: "",
        location: "",
        description: "",
        price: "",
      },
      rooms: [],
      tourId: null, // tourId 변수 추가

      EachRoomUrls: {}, // roomId로 조회한 각 방의 url들을 담을 변수 (객체)

      AllImages: [],

      userRole: "", // 유저 권한
    };
  },
  methods: {
    // 숙소(Tour) 정보 가져오기
    async getTourDetail(tourId) {
      try {
        const response = await MainService.get(tourId);
        this.tour = response.data;
      } catch (error) {
        console.error("Error fetching tour details:", error);
      }
    },

    // 방 정보 가져오기
    async getRoomsByTourId(tourId) {
      try {
        const response = await RoomService.getRoomsByTourId(tourId);
        this.rooms = response.data;

        // 각 방의 이미지 URL들을 비동기적으로 가져오기
        let allImages = []; // 모든 방의 이미지 URL들을 담을 배열
        for (const room of this.rooms) {
          await this.getUrlsByRoomId(room.roomId); // 각 방의 이미지 URL을 가져옴
          allImages = [
            ...allImages, // 기존의 allImages 배열 (빈 배열일 수 있음)
            room.roomUrl, // 첫 번째 방의 기본 이미지 'room1.jpg'
            ...this.EachRoomUrls[room.roomId], // 첫 번째 방의 추가 이미지들 ['room1_add1.jpg', 'room1_add2.jpg']
          ]; // 이미지들을 배열에 추가
        }

        // 모든 이미지들을 AllImages 배열에 담기
        this.AllImages = allImages;
      } catch (error) {
        console.error("Error fetching rooms:", error);
      }
    },

    // 각 방의 RoomId에 대한 여러 이미지 경로 가져오기
    async getUrlsByRoomId(roomId) {
      try {
        const response = await RoomService.getUrlsByRoomId(roomId);

        // this.EachRoomUrls[roomId] = response.data; 방식으로 저장
        this.EachRoomUrls[roomId] = response.data; // 방 ID에 해당하는 URL 배열을 EachRoomUrls에 저장
        console.log("EachRoomUrls after update:", this.EachRoomUrls); // 각 방에 대한 URL이 잘 들어갔는지 확인
      } catch (error) {
        console.error("Error fetching rooms:", error);
      }
    },

    // 방 업데이트 페이지 이동
    editRoom(roomId) {
      this.$router.push(`/editRoom/${roomId}`); // 업데이트 페이지로 리다이렉트
    },

    // 각 방에 사진 추가 페이지
    addRoomPhoto(roomId) {
      this.$router.push(`/addRoomPhoto/${roomId}`); // 각 방에 사진 추가 페이지로 리다이렉트
    },

    // 방 삭제 처리
    async deleteRoom(roomId) {
      try {
        const response = await RoomService.RemoveRoom(roomId); // 방 삭제 API 호출
        console.log("Room deleted:", response.data);

        // 방 삭제 후, 목록에서 해당 방 제거
        this.rooms = this.rooms.filter((room) => room.roomId !== roomId);
      } catch (error) {
        console.error("Error deleting room:", error);
      }
    },
  },
  mounted() {
    const tourId = this.$route.params.tourId; // URL에서 tourId 가져오기
    if (tourId) {
      this.tourId = tourId; // tourId 값을 데이터 속성에 저장
      this.getTourDetail(tourId); // 투어 상세 정보 가져오기
      this.getRoomsByTourId(tourId); // 방 정보 가져오기
    } else {
      console.error("tourId is missing in the route.");
    }

    // 로컬스토리지에서 토큰 정보 가져오기
    const user = localStorage.getItem("user"); // 저장된 사용자 정보 가져오기
    if (user) {
      const parsedUser = JSON.parse(user); // JSON 문자열을 객체로 파싱
      this.userRole = parsedUser.codeName; // 역할 정보 저장
    } else {
      console.error("No user data found in localStorage.");
    }
  },
};
</script>

<style scoped>
.room-container {
  display: flex;
  flex-direction: row; /* 가로 정렬 */
  width: 100%;
  max-width: 900px; /* 카드 최대 너비 */
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
  padding: 15px;
  margin: auto; /* 가운데 정렬 */
}

.image-box {
  flex: 1; /* 이미지 박스 비율 조정 */
  width: 50%; /* 5:5 비율 */
  /* height: 200px; 이미지 높이 고정 */
  background-color: #f8f9fa;
}

.info-box {
  flex: 1; /* 정보 박스 비율 조정 */
  width: 50%; /* 5:5 비율 */
  height: 300px;
  padding: 15px;
  background-color: #fff;
}

.card-title {
  font-size: 1.2rem;
  font-weight: bold;
}

.card-text {
  font-size: 0.9rem;
  line-height: 1.5;
}

.button-group button {
  width: 100px;
}

/* 캐러셀 너비를 카드와 동일하게 설정 */
#carouselExampleControls,
#topCarousel {
  max-width: 900px; /* 카드와 동일한 최대 너비 */
  margin: auto; /* 가운데 정렬 */
  overflow: hidden; /* 넘친 요소를 잘라냄 */
}

.carousel-inner {
  max-width: 900px; /* 카드와 동일한 최대 너비 */
  margin: auto; /* 가운데 정렬 */
}

.carousel-inner img {
  height: 600px; /* 이미지 높이 유지 */
  width: 100%; /* 부모 요소 너비에 맞춤 */
  object-fit: cover; /* 비율을 유지하면서 잘림 */
}

/* 작은 화면에서 캐러셀 높이를 줄임 */
@media (max-width: 768px) {
  .carousel-inner img {
    height: 300px; /* 작은 화면에서는 높이를 줄임 */
  }
}

/* 캐러셀 버튼 꾸미기 */
.carousel-control-prev-icon,
.carousel-control-next-icon {
  background-color: rgba(0, 0, 0, 0.5); /* 반투명 배경 추가 */
  border-radius: 50%; /* 동그란 모양으로 변경 */
  width: 50px; /* 크기 조정 */
  height: 50px;
  background-size: 50%; /* 아이콘 크기 조정 */
  background-repeat: no-repeat;
  background-position: center;
}

/* 왼쪽 버튼 아이콘 커스터마이징 */
.carousel-control-prev-icon {
  background-image: url("https://img.icons8.com/ios-filled/50/ffffff/chevron-left.png"); /* 새로운 아이콘 이미지 추가 */
}

/* 오른쪽 버튼 아이콘 커스터마이징 */
.carousel-control-next-icon {
  background-image: url("https://img.icons8.com/ios-filled/50/ffffff/chevron-right.png"); /* 새로운 아이콘 이미지 추가 */
}
</style>
