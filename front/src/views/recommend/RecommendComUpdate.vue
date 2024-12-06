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

    <!-- Dropdown for Location -->
    <div class="form-floating mb-3">
      <select class="form-select" id="commentLoc" v-model="comments.commentLoc">
        <option value="" disabled selected>Choose a location</option>
        <option value="경상남도 함안">경상남도</option>
        <option value="충북 제천지">충북</option>
        <option value="오사카">오사카</option>
        <option value="오타루">오타루</option>
        <option value="호놀룰루">호놀룰루</option>
        <option value="가오슝">가오슝</option>
        <option value="아유타야">아유타야</option>
        <option value="뉴욕">뉴욕</option>
      </select>
      <label for="commentLoc">Location</label>
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
        commentLoc: "",
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
