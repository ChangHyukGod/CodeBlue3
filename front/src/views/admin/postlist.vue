<template>
    <div>
        <h2>게시물 목록</h2>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">아이디</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="post in posts" :key="post.id">
                    <td>{{ post.title }}</td>
                    <td>{{ post.author }}</td>
                    <td>{{ post.createdAt }}</td>
                    <td>
                        <button @click="editPost(post.id)">수정</button>
                        <button @click="deletePost(post.id)">삭제</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="button-container">
            <button class="custom-button" @click="createPost">새 게시물 작성</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'PostList',
    computed: {
        posts() {
            return this.$store.state.posts;
        },
    },
    methods: {
        createPost() {
            this.$router.push('/admin');
        },
        editPost(postId) {
            this.$router.push(`/admin/post/edit/${postId}`);
        },
        deletePost(postId) {
            this.$store.dispatch('deletePost', postId);
        },
    },
};
</script>
<style scoped>
.button-container {
    text-align: left;
}

.custom-button {
    padding: 10px 20px;
    background-color: #ffeb33;
    color: black;
    font-size: 16px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    margin-top: 20px;
    margin-left: 1000px;
}
</style>
