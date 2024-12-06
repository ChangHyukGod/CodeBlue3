<template>
    <div class="container">
        <div class="main-layout">
            <!-- 버튼 그룹 박스 (왼쪽) -->
            <div id="main_button_group" class="custom-button-group">
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin1">
                    <i class="bi bi-chat-square-dots custom-icon"></i><br />1:1 문의
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="mainadmin2">
                    <i class="bi bi-receipt-cutoff custom-icon"></i><br />질문 게시판
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin3">
                    <i class="bi bi-cash-coin custom-icon"></i><br />결제 방법
                </b-button>
                <b-button variant="outline-dark" class="custom-button">
                    <i class="bi bi-ticket-perforated custom-icon"></i><br />쿠폰 안내
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="mainadmin4">
                    <i class="bi bi-megaphone custom-icon"></i><br />공지사항
                </b-button>
            </div>

            <!-- 테이블 (오른쪽) -->
            <div class="table-container">
                <!-- 검색어 입력상자 -->
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="검색어" v-model="searchKeyword" />
                    <button class="btn btn-outline-secondary" type="button" @click="getAdmin">
                        검색
                    </button>
                </div>
                <!-- 부트스트랩 테이블 -->
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">fno</th>
                            <th scope="col">question</th>
                            <th scope="col">answer</th>
                            <th scope="col">hashtag</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(data, index) in admins" :key="index">
                            <td>{{ data.fno }}</td>
                            <td>{{ data.question }}</td>
                            <td>{{ data.hashtag }}</td>
                            <td>
                                <router-link :to="'/admin/' + data.fno">
                                    <span class="badge text-bg-success">수정</span>
                                </router-link>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <!-- 페이지 번호 -->
                <div>
                    <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage"
                        @click="getAdmin"></b-pagination>
                </div>
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
        this.getAdmin(); // 컴포넌트가 로드될 때 데이터 호출
    },
};
</script>

<style>
.custom-icon {
    font-size: 40px;
    color: #ffeb33;
    margin-bottom: 0.5rem;
    /* 아이콘과 텍스트 간격 */
}

/* 버튼 그룹을 세로로 배치하고 왼쪽 정렬 */
/* 버튼 그룹을 세로로 배치하고 왼쪽 정렬 */
#main_button_group {
    display: flex;
    flex-direction: column;
    /* 버튼들을 세로로 배치 */
    align-items: flex-start;
    /* 왼쪽 정렬 */
    padding: 10px;
    gap: 10px;
    /* 버튼 간격 */
    width: 200px;
    /* 버튼 그룹의 너비 */
}

/* 개별 버튼 스타일 */
.custom-button-group .btn {
    margin: 0 20px;
    margin-bottom: 20px;
    width: 100%;
    /* 너비를 100%로 설정하여 일관성 유지 */
    height: 100px;
    /* 버튼 높이 고정 */
    text-align: center;
    /* 텍스트 중앙 정렬 */
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

/* 버튼 텍스트와 아이콘 스타일 */
.custom-button {
    font-size: 15px;
    font-weight: bold;
    color: #333;
    border: 2px solid #ccc;
    text-align: center;
}

/* 버튼 호버 스타일 */
.custom-button:hover {
    background-color: #464444;
    border-color: #ccc;
    color: white;
}

/* 테이블 컨테이너 스타일 */
.table-container {
    display: flex;
    flex-direction: column;
    margin-left: 20px;
    /* 버튼과 테이블 사이의 간격 */
    flex-grow: 1;
}

/* 테이블 스타일 */
.table-container table {
    width: 100%;
    border-collapse: collapse;
}

.table-container th,
.table-container td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
}

/* 레이아웃 스타일 */
.main-layout {
    display: flex;
    gap: 20px;
    /* 버튼과 테이블 간의 간격 */
}
</style>
