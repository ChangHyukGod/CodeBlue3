<template>
    <div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="question" placeholder="질문" v-model="faq.question" />

        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="answer" placeholder="답변" v-model="faq.answer" />

        </div>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="hashtag" placeholder="해시태그" v-model="faq.hashtag" />

        </div>

        <button @click="getFaqUpdate" style="margin-right: 10px; background-color: yellow;"> 수정 </button>
        <button @click="getFaqDelete" style="background-color: red;"> 삭제 </button>

    </div>
</template>

<script>

import FaqService from "@/services/faq/FaqService";
export default {
    data() {
        return {
            faq: {
                question: "",
                answer: "",
                hashtag: "",
            }
        }
    },
    methods: {
        async getFaqDetails(fno) {
            try {
                const response = await FaqService.getDetail(fno); // 방 ID로 방 상세 정보 가져오기
                console.log(response); // 응답 로그로 확인
                if (response && response.data) {
                    this.faq = response.data; // 방 정보 저장
                }
            } catch (error) {
                console.error("Error fetching room details:", error);
                if (error.response) {
                    console.error("Response error:", error.response.data);
                }
            }
        },

        async getFaqUpdate() {
            try {
                // TODO: fileDB -> fileDb 고칠것
                let response = await FaqService.getUpdate(
                    this.faq.fno,
                    this.faq
                );
                console.log(response.data); // 디버깅
                this.$router.go(-1);

            } catch (error) {
                console.log(error);
            }

        },

        async getFaqDelete() {
            try {
                let response = await FaqService.getDelete(this.faq.fno);
                console.log(response.data); // 디버깅
                // 전체조회 화면 강제 이동
                this.$router.go(-1);

            } catch (error) {
                console.log(error);
            }
        },

    },
    mounted() {
        const fno = this.$route.params.fno;
        if (fno) {
            this.getFaqDetails(fno);
        } else {
            console.error("tourId is missing in the route.");
        }
    },
};
</script>

<style></style>