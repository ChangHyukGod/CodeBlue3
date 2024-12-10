<template>
  <div>
    <!-- dname -->
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
        <option value="경남 함안">경남 함안</option>
        <option value="충북 제청">충북 제천</option>
        <option value="일본 오사카">일본 오사카</option>
      </select>
      <label for="commentLoc">Location</label>
    </div>

    <!-- 버튼 -->
    <button type="button" class="btn btn-primary" @click="save">저장</button>
  </div>
</template>
<script>
import CommentsService from "@/services/recommend/CommentsService";
export default {
  data() {
    return {
      comments: {
        commentText: "",
        commentLoc: "",
      },
    };
  },
  methods: {
    async save() {
      try {
        let response = await CommentsService.insert(this.comments);
        console.log(response.data); // 디버깅
        // TODO: 성공하면 강제이동 (전체조회/dept)
        this.$router.push("/recommend");
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
<style></style>
