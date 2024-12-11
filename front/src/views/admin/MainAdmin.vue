<template>
    <div class="container">
        <div class="admin-layout">
            <!-- 관리자 페이지 타이틀 -->
            <div class="admin-header">
                <h1>관리자 대시보드</h1>
                <p>귀하의 웹사이트 관리 기능을 확인하세요</p>
            </div>

            <!-- 버튼 그룹 박스 (왼쪽) -->
            <div id="main_button_group" class="custom-button-group">
                <b-button variant="outline-dark" class="custom-button" href="/mainadmin1">
                    <i class="bi bi-question-circle custom-icon"></i><br />FAQ 페이지
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="/review">
                    <i class="bi bi-chat-left-quote custom-icon"></i><br />리뷰게시판
                </b-button>
                <b-button variant="outline-dark" class="custom-button" href="/recommend">
                    <i class="bi bi-map custom-icon"></i><br />추천여행지 페이지
                </b-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'AdminPage'
};
</script>

<style>
/* 전체 컨테이너 스타일 */
.container {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    /* 수평 중앙 정렬 */
    align-items: flex-start;
    /* 위쪽 정렬 */
    padding-top: 60px;
    /* 위쪽 여백 추가 */
}

/* 관리자 페이지 레이아웃 */
.admin-layout {
    width: 80%;
    max-width: 1200px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    padding: 40px;
    display: flex;
    flex-direction: column;
    gap: 30px;
}

/* 관리자 페이지 헤더 */
.admin-header {
    text-align: center;
}

.admin-header h1 {
    font-size: 2.5rem;
    color: #333;
    margin-bottom: 10px;
    font-weight: bold;
}

.admin-header p {
    font-size: 1rem;
    color: #555;
    font-weight: 300;
}

/* 버튼 그룹 스타일 */
.custom-button-group {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    gap: 20px;
}

/* 버튼 스타일 */
.custom-button {
    font-size: 16px;
    font-weight: bold;
    color: #333;
    border: 2px solid #ccc;
    text-align: center;
    width: 220px;
    height: 120px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border-radius: 10px;
    background-color: #f9f9f9;
    transition: all 0.3s ease;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* 버튼 호버 효과 */
.custom-button:hover {
    background-color: #ffeb33;
    border-color: #ffeb33;
    color: white;
    transform: translateY(-5px);
}

/* 아이콘 스타일 */
.custom-icon {
    font-size: 40px;
    color: #333;
    margin-bottom: 8px;
}

/* 버튼 포커스 효과 */
.custom-button:focus {
    outline: none;
    box-shadow: 0 0 0 2px rgba(255, 235, 51, 0.6);
}

/* 반응형 디자인: 작은 화면에서 버튼을 세로로 배치 */
@media (max-width: 768px) {
    .custom-button-group {
        flex-direction: column;
        align-items: center;
    }

    .custom-button {
        width: 90%;
    }
}
</style>
