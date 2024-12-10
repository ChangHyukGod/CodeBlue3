import axios from "axios";

const baseURL = "http://localhost:8000/api/announcement";

// 공지 전체 목록 조회
const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {

  return axios.get(
    `${baseURL}?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
  );
};

// 특정 공지 상세 조회
const get = (ano) => {
  return axios.get(`${baseURL}/${ano}`);
};

// 공지 수정
const update = (ano, data) => {
  return axios.put(`${baseURL}/fix/${ano}`, data, {
    headers: {
      "Content-Type": "application/json",
    },
  });
};

// 공지 삭제
const remove = (ano) => {
  return axios.delete(`${baseURL}/fix/${ano}`);
};

// 서비스 객체
const AnnouncementService = {
  getAll,
  get,
  update,
  remove,
};

export default AnnouncementService;