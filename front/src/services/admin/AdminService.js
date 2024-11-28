import axios from "axios";

const baseURL = "http://localhost:8000/api";

const AdminService = {
    // 게시물 조회
    getPostById(postId) {
        return axios.get(`${baseURL}/posts/${postId}`);
    },

    // 게시물 생성
    createPost(formData) {
        return axios.post(`${baseURL}/posts`, formData);
    },

    // 게시물 수정
    updatePost(postId, formData) {
        return axios.put(`${baseURL}/posts/${postId}`, formData);
    },
};

export default AdminService;
