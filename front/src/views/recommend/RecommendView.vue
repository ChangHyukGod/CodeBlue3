<template>
  <div class="container mt-4">
    <!-- 검색 바 -->
    <div class="row mb-4">
      <div class="col-12">
        <input
          v-model="searchQuery"
          type="text"
          class="form-control"
          placeholder="어디든지 검색..."
        />
      </div>
    </div>

    <!-- 여행지 리스트 -->
    <div class="row">
      <div
        v-for="(place, index) in filteredPlaces"
        :key="index"
        class="col-md-4 mb-4"
      >
        <div class="card shadow-sm border-0">
          <img :src="place.image" class="card-img-top" alt="Place image" />
          <div class="card-body">
            <h5 class="card-title text-warning">{{ place.name }}</h5>
            <p class="card-text text-muted">{{ place.description }}</p>
            <div class="d-flex justify-content-between align-items-center">
              <div>
                <!-- 별점 -->
                <span class="text-warning">
                  <i
                    v-for="i in 5"
                    :key="i"
                    :class="i <= place.rating ? 'fas fa-star' : 'far fa-star'"
                  ></i>
                </span>
                {{ place.rating }} / 5
              </div>
              <div>
                <!-- 좋아요 -->
                <button
                  @click="toggleLike(index)"
                  class="btn btn-outline-warning"
                >
                  <i :class="place.liked ? 'fas fa-heart' : 'far fa-heart'"></i>
                  {{ place.likes }}
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: "",
      places: [
        {
          name: "비스타 루프탑 바",
          description:
            "항공 블럭 '세디고 파고다' 배경의 포토 스팟으로 유명한 루프탑 바.",
          image: "https://via.placeholder.com/400x200",
          rating: 5,
          likes: 23,
          liked: false,
        },
        {
          name: "파스테이스 드 벨렘",
          description: "200년 전통의 에그타르트 원조 맛집 매우 매우 맛있음",
          image: "https://via.placeholder.com/400x200",
          rating: 4.7,
          likes: 4161,
          liked: false,
        },
        {
          name: "마켓 광장",
          description:
            "매년 헬싱키 청년 축제가 개최되는, 기념품과 커피를 즐길 수 있는 명소",
          image: "https://via.placeholder.com/400x200",
          rating: 4.7,
          likes: 433,
          liked: false,
        },
        {
          name: "중앙 우체국",
          description: "건물 곳곳이 포토 스팟인 아름다운 우체국",
          image: "https://via.placeholder.com/400x200",
          rating: 4.0,
          likes: 7956,
          liked: false,
        },
        {
          name: "세비야 대성당",
          description: "스페인 최대 규모의 성당이자, 여행객의 필수 사진 명소",
          image: "https://via.placeholder.com/400x200",
          rating: 4.8,
          likes: 4220,
          liked: false,
        },
        {
          name: "시부야 스카이",
          description: "넓을 놓고 바라보게 만드는 스카이라인을 자랑하는 전망대",
          image: "https://via.placeholder.com/400x200",
          rating: 4.6,
          likes: 19936,
          liked: false,
        },
      ],
    };
  },
  computed: {
    filteredPlaces() {
      return this.places.filter((place) =>
        place.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
  },
  methods: {
    toggleLike(index) {
      const place = this.places[index];
      place.liked = !place.liked;
      place.likes += place.liked ? 1 : -1;
    },
  },
};
</script>

<style scoped>
/* 카드 이미지 크기 설정 */
.card-img-top {
  height: 200px;
  object-fit: cover;
}

/* 별점 아이콘 */
.fas,
.far {
  cursor: pointer;
}

/* 좋아요 아이콘 */
.fas.fa-heart {
  color: red;
}

.far.fa-heart {
  color: #ccc;
}

/* 배경 색상과 카드 스타일 */
.container {
  background-color: #fff9e6; /* 밝은 노란색 배경 */
}

.card {
  background-color: #fff; /* 카드 배경은 흰색 */
  border: 1px solid #f2c94c; /* 노란색 테두리 */
  border-radius: 10px;
}

.card-body {
  padding: 1.5rem;
}

/* 텍스트 색상 */
.card-title {
  color: #f39c12; /* 노란색 */
}

.card-text {
  color: #6c757d; /* 어두운 회색 */
}

.card-body .btn-outline-warning {
  color: #f39c12;
  border-color: #f39c12;
}

.card-body .btn-outline-warning:hover {
  background-color: #f39c12;
  color: #fff;
}

input.form-control {
  background-color: #f9e6a7;
  border: 1px solid #f39c12;
  color: #6c757d;
  padding: 0.75rem 1rem;
}

input.form-control::placeholder {
  color: #6c757d;
}
</style>
