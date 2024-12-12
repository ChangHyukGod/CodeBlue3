<template>
    <div class="container">
        <div class="main-layout">
            <!-- 버튼 그룹 박스 (왼쪽) -->
            <div id="main_button_group" class="custom-button-group">
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin1">
                    <i class="bi bi-chat-square-dots custom-icon"></i><br />1:1 문의
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin2">
                    <i class="bi bi-receipt-cutoff custom-icon"></i><br />질문 게시판
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin3">
                    <i class="bi bi-cash-coin custom-icon"></i><br />결제 방법
                </b-button>

                <b-button variant="outline-dark" class="custom-button" href="/mainadmin5">
                    <i class="bi bi-megaphone custom-icon"></i><br />공지사항
                </b-button>
            </div>

            <hr />
            <div class="announce_body_box">
                <div class="notice_container">
                    <div class="notice_search">
                        <form class="search_input" @submit.prevent="searchAnnouncement">
                            <div class="input_box typing form-group search_bar_announce">
                                <input placeholder="제목, 내용" v-model="searchKeyword" class="input_text form-control" />
                                <i class="bi bi-search search_glass_announce"></i>
                            </div>
                        </form>
                        <br />
                    </div>

                    <div class="notice_content">
                        <div class="accordion" id="announcementAccordion">
                            <div class="accordion-item" v-for="(data, index) in announcementList" :key="index">
                                <h2 class="accordion-header" :id="'heading-' + index">
                                    <router-link :to="'/announcement/' + data.ano" class="custom-link">
                                        {{ data.title }}
                                    </router-link>
                                    <div class="upde">
                                        <button class="updede" @click="upde(data.ano)">
                                            수정/삭제
                                        </button>
                                    </div>

                                </h2>
                                <div :id="'collapse-' + index" class="accordion-collapse collapse"
                                    :aria-labelledby="'heading-' + index" data-bs-parent="#announcementAccordion">
                                    <div class="accordion-body">
                                        {{ data.content }}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <p v-if="announcementList.length === 0">
                            등록된 공지사항이 없습니다.
                        </p>
                    </div>
                    <br />

                    <!-- 페이징 -->
                    <div class="notice_paging">
                        <ul class="paging pagination">
                            <!-- 이전 버튼 -->
                            <li class="page-arrow page-item" :class="{ disabled: pageIndex === 1 }">
                                <a class="page-link" href="#" @click.prevent="goToPage(pageIndex - 1)">
                                    &laquo;
                                </a>
                            </li>

                            <!-- 페이지 번호 -->
                            <li v-for="page in totalPages" :key="page" class="page-item"
                                :class="{ active: page === pageIndex }">
                                <a class="page-link" href="#" @click.prevent="goToPage(page)">
                                    {{ page }}
                                </a>
                            </li>

                            <!-- 다음 버튼 -->
                            <li class="page-arrow page-item" :class="{ disabled: pageIndex === totalPages }">
                                <a class="page-link" href="#" @click.prevent="goToPage(pageIndex + 1)">
                                    &raquo;
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import AnnouncementService from "@/services/faq/AnnouncementService";

export default {
    data() {
        return {
            pageIndex: 1, // 현재 페이지
            totalPages: 1, // 전체 페이지 수
            searchKeyword: "", // 검색어
            announcementList: [], // 공지사항 데이터 리스트

            detailAnnounce: {},
        };
    },
    methods: {
        async getAnnouncements() {
            try {
                const response = await AnnouncementService.getAll(
                    this.searchKeyword,
                    this.pageIndex - 1,
                    10 // 한 페이지에 표시할 데이터 개수
                );
                const { results, totalCount } = response.data;
                this.announcementList = results || [];
                this.totalPages = Math.ceil(totalCount / 10);
            } catch (error) {
                console.error("공지사항 데이터를 가져오는 중 에러 발생:", error);
            }
        },

        async getAnnouncementsDetail() {
            try {
                const response = await AnnouncementService.getDetail();
                this.detailAnnounce = response.data;
            } catch (error) {
                console.error("공지사항 데이터를 가져오는 중 에러 발생:", error);
            }
        },

        goToPage(page) {
            if (page > 0 && page <= this.totalPages) {
                this.pageIndex = page;
                this.getAnnouncements();
            }
        },
        searchAnnouncements() {
            this.pageIndex = 1;
            this.getAnnouncements(); // 수정: getAnnouncements()로 변경
        },

        updateQuery() {
            this.$router.push({
                path: "list",
                query: { search: this.searchKeyword },
            });
        },
        upde(ano) {
            this.$router.push(`/admin/fix/${ano}`);  // 수정된 부분
        }
    },
    mounted() {
        // 초기화 시 URL 쿼리값을 동기화
        this.searchKeyword = this.$route.query.search || "";
        this.getAnnouncements(); // 수정: getFaq() -> getAnnouncements()
    },
};
</script>


<style>
.container {
    width: 100%;
    /* 가로 크기 전체 */
    height: 100vh;
    /* 세로 크기를 화면 전체로 설정 */
    position: relative;
    /* 고정된 위치를 위한 기준 설정 */
    top: 0;
    /* 상단 고정 */
    left: 0;
    /* 좌측 고정 */
    overflow: hidden;
    /* 내용이 container 밖으로 나가지 않도록 설정 */
    padding: 20px;
    /* 안쪽 여백 설정 */
}



.custom-icon {
    font-size: 40px;
    color: #ffeb33;
    margin-bottom: 0.5rem;
}


#main_button_group {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 10px;
    gap: 10px;
    width: 200px;
}

.custom-button-group .btn {
    margin: 0 20px;
    margin-bottom: 20px;
    width: 100%;
    height: 100px;
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.custom-button {
    font-size: 15px;
    font-weight: bold;
    color: #333;
    border: 2px solid #ccc;
    text-align: center;
}

.custom-button:hover {
    background-color: #464444;
    border-color: #ccc;
    color: white;
}


.main-layout {
    display: flex;
    gap: 20px;
    flex-grow: 1;
    /* 부모 요소가 가능한 공간을 모두 차지하도록 설정 */
}

.bigbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 0 auto;
    box-sizing: border-box;
    font-size: 0.8rem;
    flex-shrink: 1;
    /* 가로 길이를 100px로 줄임 */
}

/* 타이틀 글씨 크기 줄이기 */
.title {
    font-size: 0.8rem;
    /* 글씨 크기 줄임 */
}

/* .lla, .new, .announce 글씨 크기 줄이기 */
.lla,
.new,
.announce {
    font-size: 10px;
    /* 글씨 크기 줄임 */
}

/* 전체 박스 */
.announce_body_box {

    border: 2.5px solid black;
    border-radius: 10px;
    padding: 15px;
    margin-left: 66px;
    width: 62.7%;
}

/* 검색창 */
.search_bar_announce {
    width: 35%;
    margin: 1px auto;
    border-radius: 25px;
    border: none;
    position: relative;
    background-color: white;
    margin-right: 10px;
    margin-bottom: 10px;
}

/* 돋보기 아이콘 */
.search_glass_announce {
    position: absolute;
    right: 15px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 1.2rem;
    color: #ffeb33;
    cursor: pointer;
}

/* 입력 필드 */
.input_text {
    margin: 10px auto;
    border-radius: 25px;
    border: 1.5px solid #ccc;
    padding: 5px 15px;
    position: relative;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    background-color: white;
}

/* 공지사항 리스트 스타일 */
.notice_table {
    list-style: none;
    display: flex;
    flex-direction: column;
    padding: 0;
    margin: 7px 0 0 10px;

}



.notice_title {
    text-decoration: none;
    color: #333;
    font-weight: bold;
    display: flex;
    justify-content: space-between;

}

/* 페이징 스타일 */
.notice_paging .pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px;
    padding: 10px;
}

.page-item {
    margin: 0 8px;
}

.page-link {
    color: #333;
    border: 1px solid #ccc;
    padding: 8px 16px;
    border-radius: 20px;
    font-size: 0.9rem;
    font-weight: bold;
    transition: all 0.3s ease;
}

.page-link:hover {
    background-color: #f5f5f5;
    color: #333;
    transform: scale(1.1);
}

.page-item.active .page-link {
    background-color: #ffeb33;
    color: #000;
    border: 1px solid #ffeb33;
    font-size: 1rem;
    font-weight: bold;
}

.page-item.disabled .page-link {
    color: #ccc;
    cursor: not-allowed;
}

.accordion-header button.updede {
    margin: 5px;
    text-align: center;
    display: inline-block;
    font-size: 15px;
    /* 글씨 크기 키움 */
    font-weight: bold;
    padding: 15px 30px;
    /* 패딩을 크게 설정 */
    height: 30px;
    width: 100px;
    /* 버튼 높이 설정 */
    line-height: 20px;
    /* 글씨와 버튼 사이 간격 조정 */
    background-color: #ffee00b4;
    color: #000;
    border: 1px solid #ccc;
    border-radius: 10px;
    cursor: pointer;
    transition: all 0.3s ease;


}

/* 호버 상태에서 버튼 색상 변경 */
.accordion-header button.updede:hover {
    background-color: #e0c200;
    border-color: #e0c200;
    color: #fff;
}



/* 수정/삭제 버튼의 새 스타일 (노란색 배경으로 변경) */
.accordion-header button.updede {
    text-align: center;
    display: inline-block;
    font-size: 1rem;
    font-weight: bold;
    padding: 4px 8px;
    background-color: #ffc107;
    /* 노란색 배경 */
    color: white;
    /* 검은색 텍스트 */
    border: 1px solid #ffc107;
    border-radius: 8px;
    cursor: pointer;
    transition: all 0.3s ease;
}

/* 호버 상태에서 버튼 색상 변경 */
.accordion-header button.updede:hover {
    background-color: #ff9800;
    /* 살짝 어두운 노란색으로 변경 */
    border-color: #ff9800;
    /* 테두리 색도 변경 */
    color: #fff;
    transform: scale(1.05);
    /* 글씨 색을 검정색으로 유지 */
}

.accordion-header button.updede:active {
    background-color: #e68900;
    border-color: #e68900;
    transform: scale(1);

}

.accordion-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.upde {
    margin-left: auto;
    margin-right: 10px;
    margin-bottom: 7px;
    /* 왼쪽 여백을 자동으로 설정하여 오른쪽 정렬 */
    text-align: right;


}

.updede {
    border-radius: 10px;
    background-color: yellow;

}

/* router-link 스타일 수정 */
.custom-link {
    text-decoration: none;
    /* 밑줄 제거 */
    color: inherit;
    font-size: 18px;
    margin-left: 15px;
    /* 글씨 색상 상속 (기본 글씨색과 동일) */
}

.custom-link:hover {
    text-decoration: none;
    /* 호버 시에도 밑줄 제거 */
    color: inherit;
    /* 호버 시에도 글씨 색상 상속 */
}
</style>
