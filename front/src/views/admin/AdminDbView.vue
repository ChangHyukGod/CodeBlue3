<template>
    <div>
        <!-- TODO: 검색어 입력상자 -->
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="검색어" v-model="searchKeyword" />
            <button class="btn btn-outline-secondary" type="button" @click="getAdmin">
                검색
            </button>
        </div>
        <!-- TODO: 부트스트랩 테이블 -->
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
                        <!-- a태그, router-link태그 -->
                        <router-link :to="'/admin/' + data.fno">
                            <span class="badge text-bg-success">수정</span>
                        </router-link>
                    </td>
                </tr>
            </tbody>
        </table>
        <!-- TODO: 페이지 번호 : 부트스트랩뷰(페이지)  -->
        <div>
            <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage"
                @click="getAdmin"></b-pagination>
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
/* 스타일은 그대로 유지 */
</style>
