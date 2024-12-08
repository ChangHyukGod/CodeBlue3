<template>
  <!--완성 최종 커밋부탁 -->
  <div class="container mt-5">
    <div class="content-wrapper">
      <!-- 텍스트 영역 -->
      <div class="text-area">
        <h1 class="text-title">{{ tdName }}</h1>
        <p class="text-description">{{ description }}</p>
        <p class="text-location">위치: {{ loc }}</p>
      </div>

      <!-- 이미지 영역 -->
      <div class="image-area">
        <img :src="imageUrl" alt="Detail Image" class="detail-image" />
      </div>
    </div>
  </div>

  <div class="menu-container">
    <!-- <h2 class="menu-title" style="margin-top: 150px; margin-bottom: 135px">
      {{ menuTitle }}
    </h2> -->
    <div class="menu-grid">
      <!-- 메뉴 카드 -->
      <div v-for="menu in menuItems" :key="menu.MENU_ID" class="menu-card">
        <img
          :src="require(`@/assets/images/${menu.MENU_IMAGE_URL}`)"
          :alt="menu.MENU_NAME"
          class="menu-image"
        />
        <div class="menu-description">
          <h3 class="menu-name">{{ menu.MENU_NAME }}</h3>
          <p class="menu-detail">{{ menu.MENU_DETAIL }}</p>
          <p class="menu-price">{{ menu.MENU_PRICE }}</p>
        </div>
      </div>
    </div>
    <!-- 절취선 -->
    <div class="paragraph-wrapper"></div>
    <!-- <h2 class="menu-title2">{{ menuTitle2 }}</h2> -->
    <br />
    <!-- 맵 이미지 영역 -->
    <div
      v-for="menu in menuItems2"
      :key="menu.MENU_ID2"
      class="additional-image-area"
      
    >
      <img
        :src="require(`@/assets/images/${menu.MENU_MAP}`)"
        alt="Additional Detail"
        class="additional-image"
        @click="navigateToRecommendMap(menu)"
      />
    </div>
    <div class="paragraph-wrapper" style="margin-bottom: 50px"></div>

    <!-- 드롭다운 -->

    <div class="accordion" id="operationHours">
      <div class="accordion-item">
        <h2 class="accordion-header" id="headingHours">
          <button
            class="accordion-button"
            type="button"
            @click="toggleCollapse"
          >
            오시는 길, 기본정보, 이용시간, 팁
          </button>
        </h2>
        <div
          id="collapseHours"
          class="accordion-collapse collapse"
          :class="{ show: isCollapsed }"
          aria-labelledby="headingHours"
          data-bs-parent="#operationHours"
        >
          <div
            v-for="menu in menuItems3"
            :key="menu.MENU_ID3"
            class="accordion-body"
          >
            <ul class="list-unstyled">
              <pre>{{ menu.MENU_ID3 }}</pre>
            </ul>
          </div>
        </div>
      </div>

      <div class="review-section" style="border: 2px solid cornflowerblue; margin-top: 80px">
        <!-- 댓글 리스트 -->
        <div class="review-list">
          <!-- 반복문으로 댓글 항목 렌더링 -->
          <div
            v-for="(data, index) in comments"
            :key="index"
            class="review-item"
          >
            <div
              class="review-header d-flex justify-content-between align-items-center"
            >
              <div>
                <p class="review-user mb-1 fw-bold" style="color: black;">
                  {{ data.email }}
                </p>
                <p class="review-location text-primary mb-0">
                  {{ data.commentLoc }}
                </p>
              </div>
              <span class="review-time text-muted">{{ data.createdAt }}</span>
            </div>
            <div class="review-content mt-2">
              <p class="review-text text-dark">{{ data.commentText }}</p>
              <div class="review-actions mt-2">
                <router-link :to="'/recommendcomupdate/' + data.comId">
                  <button class="action-btn btn btn-primary text-white">
                    관리
                  </button>
                </router-link>
              </div>
            </div>
          </div>
        </div>

        <!-- 페이지네이션 -->
        <b-pagination
          v-model="pageIndex"
          :total-rows="totalCount"
          :per-page="recordCountPerPage"
          @click="getComments"
          class="mt-3"
          style="
            --bs-pagination-active-bg: #0288d1;
            --bs-pagination-active-color: white;
          "
        ></b-pagination>

        <!-- 댓글 쓰기 버튼 -->
        <div class="review-write-button text-end mt-3">
          <a
            href="/recommendcomadd"
            class="write-btn btn btn-primary text-white"
          >
            댓글 쓰기
          </a>
        </div>
        <!-- TODO: 검색어 입력상자 -->
        <div class="input-group mb-3 mt-3">
          <input
            type="text"
            class="form-control"
            placeholder="검색어"
            v-model="searchKeyword"
            style="border: 3px solid cornflowerblue"
          />
          <button
            class="write-btn btn btn-primary text-white"
            type="button"
            @click="getComments"
          >
            검색
          </button>
        </div>
      </div>

      <!-- 여기까지 -->
    </div>
  </div>
</template>

<script>
import CommentsService from "@/services/recommend/CommentsService";
import RecommendService from "@/services/recommend/RecommendService";
export default {
  data() {
    return {
      comId: "",
      tdName: "",
      imageUrl: "",
      description: "",
      loc: "",
      commentText: "",
      createdAt: "",
      commentLoc: "",
      email: "",
      comments: [],
      pageIndex: 1, //현재페이지번호
      totalCount: 0, // 전체개수
      recordCountPerPage: 10, //화면에 보일개수
      searchKeyword: "",
      menuItems: [],
      menuItems2: [],
      menuItems3: [],
      menuTitle: "", // 메뉴 데이터를 저장할 배열
      // menuTitle2: "",
      isCollapsed: false,
    };
  },

  mounted() {
    const tdId = this.$route.params.tdId;
    console.log("Received tdId:", tdId);
    this.fetchDetailData(tdId);
    this.getComments();
  },

  methods: {
    async fetchDetailData(tdId) {
      try {
        const response = await RecommendService.get(tdId);
        const data = response.data;

        this.tdName = data.tdName;
        this.imageUrl = data.imageUrl;
        this.description = data.description;
        this.loc = data.loc;

        console.log("Fetched data:", data);

        // 메뉴 데이터 하드코딩
        if (tdId == 11) {
          // this.menuTitle = "대표 메뉴"; // tdId 7에 맞는 메뉴 제목
          // this.menuTitle2 = "찾아오시는 길";
          this.menuItems = [
            {
              MENU_ID: 1,
              MENU_NAME: "함안 대평늪",
              MENU_DETAIL: "천넌기념물 대평습지",
              // MENU_PRICE: "3인분 xxx,xxx원",
              MENU_IMAGE_URL: "메뉴첫번째2.png",
            },
            {
              MENU_ID: 2,
              MENU_NAME: "고려동 유적지",
              MENU_DETAIL: "경상남도 기념물 제56호",
              // MENU_PRICE: "xxx,xx원",
              MENU_IMAGE_URL: "메뉴첫번째3.png",
            },
            {
              MENU_ID: 2,
              MENU_NAME: "말이산 고분군",
              MENU_DETAIL: "푸릇한 농과 나홀로 나무",
              // MENU_PRICE: "3인분 xxx,xx원",
              MENU_IMAGE_URL: "메뉴첫번째4.png",
            },
          ];
          this.menuItems2 = [{ MENU_ID2: 1, MENU_MAP: "맵함안.png" }];
          this.menuItems3 = [
            {
              MENU_ID3: `부산역에서 KTX 또는 SRT를 타고 진주역으로 가서, 거기서 버스나 택시로 함안군으로 이동하면 됩니다.




                `,
            },
          ];
        } else if (tdId == 12) {
          this.menuTitle = "병신의 가볼만한 곳";
          this.menuItems = [
            {
              MENU_ID: 3,
              MENU_NAME: "청풍문화재단지",
              MENU_DETAIL:
                "조선 시대의 건축물과 생활상을 엿볼 수 있는 역사적인 장소",
              // MENU_PRICE: "3,500짯(640ml)",
              MENU_IMAGE_URL: "메뉴두번째2.jpg",
            },
            {
              MENU_ID: 4,
              MENU_NAME: "제천 청풍호반 케이블카",
              MENU_DETAIL: "청풍호를 한눈에 볼 수 있는 케이블카",
              // MENU_PRICE: "15,000짯",
              MENU_IMAGE_URL: "메뉴두번째3.jpg",
            },
            {
              MENU_ID: 2,
              MENU_NAME: "의림지솔밭공원",
              MENU_DETAIL: "의림지 주변에 조성된 솔밭공원에",
              // MENU_PRICE: "3,000짯(330ml)",
              MENU_IMAGE_URL: "메뉴두번째4.jpg",
            },
          ];
          this.menuItems2 = [{ MENU_ID2: 1, MENU_MAP: "맵2.png" }];

          this.menuItems3 = [
            {
              MENU_ID3: `오시는 길: 버스: 제천 시내에서 의림지행 버스를 타고 약 20분 정도 소요됩니다.
자가용: 제천시청에서 의림지까지 약 10분 거리에 있습니다. 네비게이션에 "의림지"를 입력하면 됩니다.
기차: 제천역에서 택시나 버스를 타고 약 10분 거리에 의림지가 있습니다. 의림지는 공공장소로, 특별한 휴무일은 없습니다. 
연중무휴로 개방되며, 방문 시간에 제한은 없습니다. 다만, 특정 시설(예: 전시관, 카페 등)은
 운영 시간이나 휴무일이 있을 수 있으므로 해당 시설의 정보를 확인하는 것이 좋습니다.
                `,
            },
          ];
        } else if (tdId == 13) {
          // this.menuTitle = "ㅇㅇ";
          this.menuItems = [
            {
              MENU_ID: 5,
              MENU_NAME: "유니버설 스튜디오 재팬",
              MENU_DETAIL:
                "남녀노소 누구나 즐길 수 있는 오사카 최고의 테마파크",
              // MENU_PRICE: "12,000짯",
              MENU_IMAGE_URL: "세번째메뉴2.jpg",
            },
            {
              MENU_ID: 6,
              MENU_NAME: "오사카 성",
              MENU_DETAIL: "오사카 여행 필수 명소",
              // MENU_PRICE: "4,000짯(500ml)",
              MENU_IMAGE_URL: "세번째메뉴3.jpg",
            },
            {
              MENU_ID: 6,
              MENU_NAME: "가이유칸 수족관",
              MENU_DETAIL: "세계 최대 규모의 수족관",
              // MENU_PRICE: "4,000짯(500ml)",
              MENU_IMAGE_URL: "세번째메뉴4.jpg",
            },
          ];
          this.menuItems2 = [{ MENU_ID2: 1, MENU_MAP: "맵3.png" }];

          this.menuItems3 = [
            {
              MENU_ID3: `오사카 항공이 위치한 간사이 공항에서 도톤보리까지는 JR 간사이 공항선으로 난카이 난바역까지 이동한 후,
             도보로 약 10분 정도 소요됩니다.
            또는 난카이 공항선으로 난바역까지 가서, 도톤보리까지도 가까운 거리입니다.
                `,
            },
          ];
        }

        // 추가적인 tdId에 대한 조건을 계속 추가할 수 있음
      } catch (error) {
        console.error("Error fetching detail data:", error);
        // 에러 처리 로직 추가 (예: 에러 메시지 표시)
      }
    },
    toggleCollapse() {
      this.isCollapsed = !this.isCollapsed; // 상태 토글
    },

    async getComments() {
      try {
        let response = await CommentsService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recordCountPerPage
        );
        // TODO: 백엔드 전송되는 것 : results(배열), totalCount(총개수)
        const { results, totalCount } = response.data;
        console.log(response.data); // 디버깅
        this.comments = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
    },

    navigateToRecommendMap(menu) {
      const tdId = this.$route.params.tdId; // 구글 api 연동할tdId 값 가져오기
      // tdId 값을 기준으로 경로 다르게 설정
      if (tdId == 11) {
        // tdId가 7일 때
        if (menu.MENU_ID2 === 1) {
          this.$router.push({
            path: "/recommendmap",
            query: { menuId: menu.MENU_ID2 },
          });
        } else if (menu.MENU_ID2 === 2) {
          this.$router.push({
            path: "/recommendmap",
            query: { menuId: menu.MENU_ID2 },
          });
        }
      } else if (tdId == 12) {
        // tdId가 8일 때
        if (menu.MENU_ID2 === 1) {
          this.$router.push({
            path: "/recommendmap2",
            query: { menuId: menu.MENU_ID2 },
          });
        } else if (menu.MENU_ID2 === 2) {
          this.$router.push({
            path: "/recommendmap2",
            query: { menuId: menu.MENU_ID2 },
          });
        }
      } else if (tdId == 13) {
        // tdId가 9일 때
        if (menu.MENU_ID2 === 1) {
          this.$router.push({
            path: "/recommendmap3",
            query: { menuId: menu.MENU_ID2 },
          });
        } else if (menu.MENU_ID2 === 2) {
          this.$router.push({
            path: "/recommendmap3",
            query: { menuId: menu.MENU_ID2 },
          });
        }
      }
    },
  },
};
</script>

<style scoped>
/* 전체 컨테이너 */
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f8f8f8; /* 컨테이너 배경 */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  border: 3px solid #0288d1;
}

/* 콘텐츠 래퍼: 그리드 레이아웃 */
.content-wrapper {
  display: grid;
  grid-template-columns: 1fr 1.5fr; /* 텍스트 1, 이미지 1.5 비율 */
  gap: 20px;
  align-items: center; /* 세로 가운데 정렬 */
}

.text-area {
  text-align: left;
}

.text-title {
  font-size: 24px;
  font-weight: bold;
  color: #000;
  margin-bottom: 10px;
}

.text-description,
.text-location {
  font-size: 16px;
  color: #000;
  margin-bottom: 10px;
}

.image-area {
  background-color: #fff;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.detail-image {
  width: 100%;
  max-width: 100%;
  height: auto;
  border-radius: 10px;
  object-fit: cover;
}

@media (max-width: 768px) {
  .content-wrapper {
    grid-template-columns: 1fr; /* 한 열로 배치 */
    gap: 10px;
  }

  .image-area {
    padding: 5px;
  }
}

/* 전체 컨테이너 스타일 */
.menu-container {
  margin: 20px auto;
  max-width: 900px;
  text-align: center;
}

/* 제목 스타일 */
.menu-title {
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

/* 메뉴 그리드 레이아웃 */
.menu-grid {
  display: flex;
  gap: 20px;
  justify-content: center;
  flex-wrap: wrap;
}

/* 개별 메뉴 카드 */
.menu-card {
  width: 250px;
  background-color: #fff;
  border: 2px solid #0288d1;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.menu-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
}

/* 이미지 스타일 */
.menu-image {
  width: 100%;
  height: 160px;
  object-fit: cover;
  border-bottom: 2px solid #0288d1;
}

.menu-description {
  padding: 10px;
  text-align: left;
}

.menu-name {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.menu-detail {
  font-size: 14px;
  color: #666;
  margin-bottom: 5px;
}

.menu-price {
  font-size: 16px;
  font-weight: bold;
  color: #000;
}

.paragraph-wrapper {
  position: relative;
  padding: 1rem;
  margin-bottom: 2rem;
}

.paragraph-wrapper::before {
  content: "";
  position: absolute;
  bottom: -1rem;
  left: 10%;
  width: 80%;
  height: 1px;
  background-color: #0288d1; /* 변경된 색상 */
  box-shadow: 0 4px 8px rgba(2, 136, 209, 0.5); /* 그림자 효과 */
}

/* 부모와 동일한 가로 크기를 가지는 추가 이미지 영역 */
.additional-image-area {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  text-align: center;
  position: relative;
  padding-top: 56.25%;
  border-radius: 10px;
  overflow: hidden;
  background-color: #ddd;
}

/* 추가 이미지 */
.additional-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.menu-title {
  font-family: "Garamond", serif;
  font-size: 36px;
  font-weight: bold;
  color: #333;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
}

.text-title {
  font-family: "Arial", sans-serif;
  font-size: 30px;
  font-weight: bold;
  color: #2c3e50;
  text-align: center;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  letter-spacing: 2px;
  transition: transform 0.3s ease-in-out;
}

.text-title:hover {
  transform: scale(1.1);
}

.text-area {
  text-align: left;
  margin-left: 0;
}

.text-title {
  font-family: "Arial", sans-serif;
  font-size: 30px;
  font-weight: bold;
  color: #2c3e50;
  text-align: left;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  letter-spacing: 2px;
  transition: transform 0.3s ease-in-out;
  margin-bottom: 10px;
}

.text-description,
.text-location {
  font-size: 16px;
  color: #000;
  margin-bottom: 10px;
  text-align: left;
}

/* 개별 메뉴 카드 */
.menu-card {
  width: 250px;
  background-color: #fff;
  border: 2px solid #0288d1;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.menu-card:hover {
  transform: scale(1.4);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
}

/* 아코디언 전체 스타일 */
.accordion {
  max-width: 810px;
  margin: 0 auto;
}

/* 아코디언 헤더 버튼 스타일 */
.accordion-button {
  background-color: #0288d1; /* 변경된 색상 */
  color: #fff;
  border: 1px solid #0288d1;
  font-weight: bold;
}

/* 아코디언 버튼이 활성화될 때 색상 */
.accordion-button:focus {
  box-shadow: none;
}

/* 아코디언 내용 영역 */
.accordion-collapse {
  background-color: #0288d1;
}

.accordion-body {
  color: white;
}

/* 아코디언 내용 열릴 때의 스타일 */
.accordion-button:not(.collapsed) {
  background-color: #01579b;
  color: #fff;
}

pre {
  white-space: pre-line;
  margin: 0;
}

/* 이미지 오버 시 마우스 커서 모양 변경 */
.additional-image:hover {
  cursor: pointer;
}

/* 전체 댓글 섹션 스타일 */
.review-section {
  background-color: #0288D1;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  font-family: Arial, sans-serif;
}

/* 댓글 리스트 항목 스타일 */
.review-item {
  background-color: #fff;
  border: 10px solid #0288d1; /* 변경된 색상 */
  border-radius: 8px;
  margin-bottom: 15px;
  padding: 15px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.review-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

/* 댓글 헤더 (사용자 정보 및 시간) */
.review-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.review-user {
  color: #f57c00;
  font-weight: bold;
  margin: 0;
}

.review-location {
  color: #000;
  font-size: 1.5rem;
  margin-top: 5px;
}

.review-time {
  color: #9e9e9e;
  font-size: 0.85rem;
}

/* 댓글 내용 */
.review-content {
  font-size: 1rem;
  color: #333;
  margin-top: 10px;
}

/* 댓글 추가 버튼 스타일 */
.review-add-button {
  background-color: #0288d1;
  color: white;
  font-weight: bold;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.review-add-button:hover {
  background-color: #01579b;
}


</style>
