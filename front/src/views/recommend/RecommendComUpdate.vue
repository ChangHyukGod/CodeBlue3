<template>
  <div>
    <!-- data.commentText -->
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="commentText"
        placeholder="commentText"
        v-model="comments.commentText"
      />
      <label for="dname">commentText</label>
    </div>

    <!-- 수정버튼 -->
    <button type="button" class="btn btn-warning" @click="update">수정</button>
    <!-- 삭제버튼 -->
    <button
      type="button"
      class="btn btn-danger"
      @click="remove"
      style="margin-left: 8px"
    >
      삭제
    </button>
  </div>
</template>
<script>
import CommentsService from "@/services/recommend/CommentsService";
export default {
  data() {
    return {
      comments: {
        comId: "",
        commentText: "",
      },
    };
  },
  methods: {
    // 함수작성
    // TODO: 상세조회
    async getDetail(comId) {
      try {
        let response = await CommentsService.get(comId);
        console.log(response.data);
        this.comments = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    //TODO: 수정
    async update() {
      try {
        let response = await CommentsService.update(
          this.comments.comId,
          this.comments
        );
        console.log(response.data);
        this.$router.push("/recommend/");
      } catch (error) {
        console.log(error);
      }
    },

    /* //TODO: 삭제 */

    async remove() {
      try {
        let response = await CommentsService.remove(this.comments.comId);
        console.log(response.data);
        this.$router.push("/recommend");
      } catch (error) {
        console.log(error);
      }
    },
  },

  mounted() {
    this.getDetail(this.$route.params.comId);
  },
};
</script>
<style></style>
