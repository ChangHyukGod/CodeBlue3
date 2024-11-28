// views/advanced/fileDb/FileDbView.vue // vueInit
<template>
    <div>
        <!-- TODO: 검색어 입력상자 -->
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="검색어" v-model="searchKeyword" />
            <button class="btn btn-outline-secondary" type="button" @click="getFileDb">
                검색
            </button>
        </div>
        <!-- TODO: 카드 -->
        <div class="row">
            <div v-for="(data, index) in adminDBs" :key="index" class="col-6">
                <div class="card" style="width: 18rem">
                    <img :src="data.fileUrl" class="card-img-top" alt="카드" />
                    <div class="card-body">
                        <h5 class="card-title">{{ data.boardTitle }}</h5>
                        <p class="card-text">
                            {{ data.boardContent }}
                        </p>
                        <router-link :to="'/admin/' + data.uuid" class="btn btn-primary">
                            수정
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

        <!-- TODO: 페이지 번호 : 부트스트랩뷰(페이지)  -->
        <div class="mt-3">
            <!-- TODO: v-model="뷰변수(현재페이지번호)"
                 ,total-rows="전체개수"
                 ,per-page="1페이지당화면에보일개수"
                    -->
            <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage"
                @click="getadmin"></b-pagination>
        </div>
    </div>
</template>
<script>
import AdminService from "@/services/admin/AdminService";
export default {
    data() {
        return {
            pageIndex: 1,
            totalCount: 0,
            recodeCountPerPage: 3,
            searchKeyword: "",
            fileDBs: [], // 빈배열
        };
    },
    methods: {
        async getFileDb() {
            try {
                let response = await AdminService.getAll(
                    this.searchKeyword,
                    this.pageIndex - 1,
                    this.recodeCountPerPage
                );
                // TODO: 벡엔드 전송되는것: results(배열), totalCount(총개수)
                const { results, totalCount } = response.data;
                console.log(response.data); // 디버깅
                this.fileDBs = results;
                this.totalCount = totalCount;
            } catch (error) {
                console.log(error);
            }
        },
    },
    mounted() {
        this.getAdmin();
    },
};
</script>
<style></style>
