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

        </div>
    </div>
</template>

<script>
import AdminService from "@/services/admin/AdminService";

export default {
    data() {
        return {
            pageIndex: 1, // 현재 페이지 번호
            totalCount: 0, // 전체 개수
            recodeCountPerPage: 3, // 화면에 보일 개수
            searchKeyword: "", // 검색어
            admins: [], // 빈 배열 (json)
        };
    },
    methods: {
        async getAdmin() {
            try {
                const response = await AdminService.getAll(
                    this.searchKeyword,
                    this.pageIndex - 1,
                    this.recodeCountPerPage
                );
                const { results, totalCount } = response.data;
                this.admins = results; // 받아온 데이터 저장
                this.totalCount = totalCount; // 총 개수 저장
            } catch (error) {
                console.error("데이터를 가져오는 중 오류 발생:", error);
            }
        },
    },
    mounted() {
        this.getAdmin();
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
</style>