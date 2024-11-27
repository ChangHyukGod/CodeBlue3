<template>
    <div class="post-form-container">
        <h2>{{ formTitle }}</h2>
        <form @submit.prevent="savePost" class="post-form">
            <div class="form-group">
                <label for="title">제목</label>
                <input v-model="post.title" id="title" required class="form-input" />
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea v-model="post.content" id="content" required class="form-textarea"></textarea>
            </div>
            <div class="mb-3">
                <label for="formFile" class="form-label">{{ file }} </label>
                <input class="form-control" type="file" id="formFile" @change="handleFileUpload">
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-button">{{ formTitle }}</button>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'PostForm',
    data() {
        return {
            post: {
                title: '',
                content: '',
            },
            selectedFile: null, // 파일 저장을 위한 변수
        };
    },
    computed: {
        formTitle() {
            return this.$route.params.id ? '게시물 수정' : '게시물 작성';
        },
        file() {
            return this.$route.params.id ? '게시물 수정' : '첨부파일';
        }
    },
    created() {
        if (this.$route.params.id) {
            const post = this.$store.state.posts.find(
                (post) => post.id === this.$route.params.id
            );
            this.post = { ...post };
        }
    },
    methods: {
        // 파일 선택시 파일 처리
        handleFileUpload(event) {
            this.selectedFile = event.target.files[0];
        },

        // 게시물 저장
        async savePost() {
            try {
                const formData = new FormData();
                formData.append('title', this.post.title);
                formData.append('content', this.post.content);
                if (this.selectedFile) {
                    formData.append('file', this.selectedFile);
                }

                // 게시물 작성 API 호출
                if (this.$route.params.id) {
                    await axios.put(`/api/posts/${this.$route.params.id}`, formData); // 수정 요청
                } else {
                    await axios.post('/api/posts', formData); // 새 게시물 추가 요청
                }
                this.$router.push('/admin');
            } catch (error) {
                console.error('게시물 저장 실패:', error);
            }
        },
    },
};
</script>
<style scoped>
.post-form-container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h2 {
    font-size: 24px;
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

.post-form {
    display: flex;
    flex-direction: column;
}

.form-group {
    margin-bottom: 15px;
}

label {
    font-size: 16px;
    margin-bottom: 5px;
    color: #555;
}

.form-input,
.form-textarea {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 4px;
    margin-top: 5px;
}

.form-textarea {
    resize: vertical;
    height: 150px;
}

.submit-button {
    padding: 10px 20px;
    background-color: #ffeb33;
    color: black;
    font-size: 16px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    margin-top: 20px;
}

.submit-button:hover {
    background-color: #ffeb33;
}
</style>