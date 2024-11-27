import axios from "axios";
// 백엔드 주소 ↓ : springboot 주소(컨트롤러 주소)
const baseURL = "http://localhost:8000/api"

// 전체조회
const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(baseURL+`/tour?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
};

// 추가, 업로드
const insert = (data) => {
    return axios.post(baseURL+"/tour/add", data);
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