import axios from "axios";
// 백엔드 주소 ↓ : springboot 주소(컨트롤러 주소)
const baseURL = "http://localhost:8000/api";

let user = JSON.parse(localStorage.getItem("user"));
// 조회용
// const token = { Authorization: "Bearer " + user?.accessToken };

// insert/update 용

const token2 = {
  "Content-Type": "application/json",
  Authorization: "Bearer " + user?.accessToken,
};

// 생성
const insert = (data) => {
  return axios.post(baseURL + `/comments/add`, data, {
    headers: token2,
  });
};

const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
  return axios.get(
    baseURL +
      `/comments?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
  );
};

const CommentsService = {
  insert,
  getAll,
};

export default CommentsService;
