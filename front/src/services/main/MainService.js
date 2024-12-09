import axios from "axios";
// 백엔드 주소 ↓ : springboot 주소(컨트롤러 주소)
const baseURL = "http://localhost:8000/api";

// 전체조회
const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(baseURL+`/tour?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
};

const getALLnp = () => {
    return axios.get(baseURL+`/tour`);
};

// 이미지 업로드 및 FormData 생성
const insertForm = (data) => {
    let formData = new FormData();
    formData.append("name", data.name);
    formData.append("location", data.location);
    formData.append("description", data.description);
    formData.append("price", data.price);
    formData.append("view", data.view);
    formData.append("category", data.category);
    
    // 이미지가 있을 경우만 추가
    if (data.image) {
        formData.append("image", data.image);
    }

    return formData;
};

// 추가 및 업로드
const insert = (data) => {
    let form = insertForm(data);
    return axios.post(baseURL+"/tour/add", form);
};

// 상세조회
const get = (tourId) => {
    return axios.get(baseURL+`/tour/get/${tourId}`);
};

// 수정
const update = (tourId, data) => {
    let form = insertForm(data); // 이미지가 없으면 formData에 image가 추가되지 않음
    return axios.put(baseURL+`/tour/update/${tourId}`, form, {
        headers: {
            'Content-Type': 'multipart/form-data' // 이미지 업로드를 위한 헤더 설정
        }
    });
};

// 삭제
const remove = (tourId) => {
    return axios.delete(baseURL+`/tour/deletion/${tourId}`);
};

const MainService = {
    getAll,
    insert,
    get,
    update,
    remove,
    getALLnp
};

export default MainService;