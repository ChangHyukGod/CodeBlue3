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

            <hr />

            <div class="bigbox">
                <div class="title">
                </div>
                <hr />
                <div class="announce_body_box">
                    <div class="notice_container">
                        <div class="notice_search">
                            <div class="notice_tab_area nav">
                                <div class="sub_tab_list" role="tablist"></div>
                            </div>
                            <!-- 검색창 -->
                            <form class="search_input">
                                <div class="input_box typing form-group search_bar_announce">
                                    <input placeholder="제목, 내용" name="searchValue" class="input_text form-control"
                                        value="" /><i class="bi bi-search search_glass_announce"></i>
                                </div>
                            </form>
                            <br />
                        </div>
                        <div class="notice_content">
                            <ul class="notice_table">
                                <li class="notice_item">
                                    <a href="#" class="notice_title">
                                        <p class="NoticeTableItem_title__KrN-Y">
                                            <span>&nbsp;&nbsp;&nbsp;쇼핑투데이 내 '오늘의 팝업'판이
                                                출시됩니다!</span>
                                        </p>
                                        <span class="date">2024.09.20&nbsp;&nbsp;&nbsp;</span>
                                    </a>
                                    <hr class="notice_line" />
                                </li>
                            </ul>
                        </div>
                        <br />
                        <!-- 페이징 -->
                        <div class="notice_paging">
                            <ul class="paging pagination">
                                <!-- 이전 버튼 -->
                                <li class="page-arrow page-item" :class="{ disabled: currentPage === 1 }">
                                    <a class="page-link" href="#" @click.prevent="goToPage(currentPage - 1)">
                                        &laquo;
                                    </a>
                                </li>

                                <!-- 페이지 번호 -->
                                <li v-for="page in totalPages" :key="page" class="page-item"
                                    :class="{ active: page === currentPage }">
                                    <a class="page-link" href="#" @click.prevent="goToPage(page)">
                                        {{ page }}
                                    </a>
                                </li>

                                <!-- 다음 버튼 -->
                                <li class="page-arrow page-item" :class="{ disabled: currentPage === totalPages }">
                                    <a class="page-link" href="#" @click.prevent="goToPage(currentPage + 1)">
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
export default {
    data() {
        return {
            currentPage: 1, // 현재 페이지
            totalPages: 5,  // 전체 페이지 수
        };
    },
    methods: {
        goToPage(page) {
            if (page > 0 && page <= this.totalPages) {
                this.currentPage = page;
                console.log(`이동할 페이지: ${page}`);
            }
        },
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

/* 공지 전체 */
.bigbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 1000px;

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

.bigbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
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
    /* 검색창의 너비 */
    margin: 1px auto;
    /* 검색창을 중앙 정렬 */
    border-radius: 25px;
    /* 테두리 둥글게 */
    border: none;
    position: relative;
    /* 자식 요소의 절대 위치 기준으로 설정 */
    background-color: white;
    margin-right: 10px;
    margin-bottom: 10px;
}

/* 돋보기 아이콘 */
.search_glass_announce {
    position: absolute;
    /* 검색창 내부에서 절대 위치 설정 */
    right: 15px;
    /* 검색창 내부에서 오른쪽 여백 */
    top: 50%;
    /* 검색창의 세로 중앙 정렬 */
    transform: translateY(-50%);
    /* 세로 중앙 정렬 보정 */
    font-size: 1.2rem;
    /* 아이콘 크기 */
    color: #ffeb33;
    /* 색상 */
    cursor: pointer;
    /* 클릭 가능한 상태 */
}

/* 입력 필드 */
.input_text {
    margin: 10px auto;
    /* 검색창을 중앙 정렬 */
    border-radius: 25px;
    /* 테두리 둥글게 */
    border: 1.5px solid #ccc;
    /* 테두리 색상 */
    padding: 5px 15px;
    /* 내부 여백 */
    position: relative;
    /* 자식 요소의 절대 위치 기준으로 설정 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    /* 그림자 효과 */
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

/* 날짜 */
.date {
    font-size: 0.9rem;
    color: #666;
    margin-left: auto;
    margin-right: 10px;
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
    /* 살짝 확대 */
}

.page-item.active .page-link {
    background-color: #ffeb33;
    /* 강조 색상 */
    color: #000;
    border: 1px solid #ffeb33;
    font-size: 1rem;
    font-weight: bold;
}

.page-item.disabled .page-link {
    color: #ccc;
    cursor: not-allowed;
}
</style>
