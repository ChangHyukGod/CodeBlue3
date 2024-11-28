import axios from "axios";
// 백엔드 주소 ↓ : springboot 주소(컨트롤러 주소)
const baseURL = "http://localhost:8000/api"

// 전체조회
const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(baseURL+`/tour?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
};

// 이미지 업로드
const insertForm = (data) => {
    let formData= new FormData();
    formData.append("name", data.name);
    formData.append("location", data.location);
    formData.append("description", data.description);
    formData.append("price", data.price);
    formData.append("image", data.image);

    return formData;
}
// 추가, 업로드
const insert = (data) => {
    let form = insertForm(data);
    return axios.post(baseURL+"/tour/add", form, data);
}

// 상세조회
const get = (tourId) => {
    return axios.get(baseURL+`/tour/get/${tourId}`);
}

const MainService = {
    getAll,
    insert,
    get,
}
export default MainService;