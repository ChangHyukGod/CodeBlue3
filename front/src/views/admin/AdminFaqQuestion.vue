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
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin4">
                    <i class="bi bi-ticket-perforated custom-icon"></i><br />쿠폰 안내
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin5">
                    <i class="bi bi-megaphone custom-icon"></i><br />공지사항
                </b-button>
            </div>

            <div class="bigbox">
                <div class="title">
                </div>
                <hr />
                <div class="announce_body_box">
                    <div class="notice_container">
                        <div class="notice_search">
                            <form class="search_input" @submit.prevent="searchFaq">
                                <div class="input_box typing form-group search_bar_announce">
                                    <input placeholder="제목, 내용" v-model="searchKeyword"
                                        class="input_text form-control" />
                                    <i class="bi bi-search search_glass_announce"></i>
                                </div>
                            </form>
                            <br />
                        </div>

                        <div class="notice_content">
                            <div class="accordion" id="faqAccordion">
                                <div class="accordion-item" v-for="(data, index) in faqList" :key="index">
                                    <h2 class="accordion-header" :id="'heading-' + index">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" :data-bs-target="'#collapse-' + index"
                                            :aria-expanded="false" :aria-controls="'collapse-' + index">
                                            {{ data.question }}
                                            <div class="upde">
                                                <button class="updede" @click="upde(data.ano)">
                                                    수정/삭제
                                                </button>
                                            </div>
                                        </button>

                                    </h2>
                                    <div :id="'collapse-' + index" class="accordion-collapse collapse"
                                        :aria-labelledby="'heading-' + index" data-bs-parent="#faqAccordion">
                                        <div class="accordion-body">
                                            {{ data.answer }}
                                            <br />
                                            <button type="button" class="btn btn-link"
                                                @click="redirectToHashtag(data.hashtag)">
                                                {{ data.hashtag }}
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p v-if="faqList.length === 0">등록된 질문이 없습니다.</p>
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
    </div>
</template>

<script>
import FaqService from "@/services/faq/FaqService";

export default {
    data() {
        return {
            pageIndex: 1, // 현재 페이지
            totalPages: 1, // 전체 페이지 수
            searchKeyword: "", // 검색어
            faqList: [], // FAQ 데이터 리스트
        };
    },
    methods: {
        async getFaq() {
            try {
                const response = await FaqService.getAll(
                    this.searchKeyword,
                    this.pageIndex - 1,
                    10 // 한 페이지에 표시할 데이터 개수
                );
                const { results, totalCount } = response.data;
                this.faqList = results || [];
                this.totalPages = Math.ceil(totalCount / 10);
            } catch (error) {
                console.error("FAQ 데이터를 가져오는 중 에러 발생:", error);
            }
        },
        goToPage(page) {
            if (page > 0 && page <= this.totalPages) {
                this.pageIndex = page;
                this.getFaq();
            }
        },
        searchFaq() {
            this.pageIndex = 1;
            this.getFaq();
        },
        searchByHashtag(hashtag) {
            this.searchKeyword = hashtag;
            this.searchFaq();
        },
        updateQuery() {
            this.$router.push({
                path: "list",
                query: { search: this.searchKeyword },
            });
        },

        redirectToHashtag(hashtag) {
            const sanitizedHashtag = hashtag.replace(/^#/, ""); // `#` 제거
            this.searchKeyword = sanitizedHashtag;
            this.pageIndex = 1; // 검색 시 첫 페이지로 초기화
            this.getFaq(); // 데이터 갱신
        },

        upde(fno) {
            this.$router.push(`addadmin/${fno}`)
        }
    },
    mounted() {
        // 초기화 시 URL 쿼리값을 동기화
        this.searchKeyword = this.$route.query.search || "";
        this.getFaq();
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
}

.bigbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 80%;
    /* 또는 원하는 비율로 설정 */
    max-width: 1200px;
    /* 최대 크기 제한을 설정할 수도 있음 */
    margin: 0 auto;
    /* 중앙 정렬 */
}

.lla,
.new,
.announce {
    color: #ffeb33;
    -webkit-text-stroke: 0.4px black;
    font-size: 16px;
    font-family: dohyeon;
}

/* 타이틀 */
.title {
    display: inline-flex;
    top: 30px;
    right: 16%;
    position: relative;
}

/* 전체 박스 */
.announce_body_box {
    width: 70%;
    border: 2.5px solid black;
    border-radius: 10px;
    padding: 15px;
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

.notice_line {
    margin: 3px;
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

.upde {
    display: inline-block;
    padding: 6px 15px;
    font-size: 14px;
    font-weight: bold;
    background-color: #ffeb33;
    /* 노란색 배경 */
    color: #000;
    border: 2px solid #ffeb33;
    /* 노란색 테두리 */
    border-radius: 25px;
    /* 둥근 모서리 */
    text-align: center;
    cursor: pointer;
    transition: all 0.3s ease;
    text-transform: uppercase;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    /* 살짝 그림자 효과 */
}

.upde:hover {
    background-color: #ffd700;
    /* 호버 시 밝은 노란색으로 변경 */
    color: white;
    border-color: #ffd700;
    /* 테두리 색상도 변경 */
    transform: scale(1.05);
    /* 살짝 커지는 효과 */
}

.upde:focus {
    outline: none;
    box-shadow: 0 0 0 2px rgba(255, 235, 51, 0.6);
    /* 포커스 시 황금색 테두리 */
}
</style>