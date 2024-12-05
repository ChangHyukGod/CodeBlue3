import axios from "axios";

// 백엔드 주소 (SpringBoot API URL)
const baseURL = "http://localhost:8000/api";



let user = JSON.parse(localStorage.getItem("user"));
// 조회용
const token = { Authorization: "Bearer " + user?.accessToken };
// insert/update 용
// const token2 = {
//   "Content-Type": "multipart/form-data",
//   Authorization: "Bearer " + user?.accessToken,
// };

const token2 = {
  "Content-Type": "multipart/form-data",
  Authorization: "Bearer " + user?.accessToken,
};






// 전체 조회
const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
  return axios.get(baseURL + `/review/review?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`,
    { headers: token }

);
};

// 이미지 업로드 및 폼 데이터 준비
const insertForm = (data) => {
  let formData = new FormData();
  formData.append("title", data.title);
  formData.append("content", data.content);
  formData.append("rating", data.rating);
  formData.append("imageUrl", data.imageUrl);
  formData.append("authorEmail", data.authorEmail);
  formData.append("targetId", data.targetId);
  formData.append("loc", data.loc);
  formData.append("createdAt", data.createdAt);
  formData.append("updatedAt", data.updatedAt);

  return formData;
};

// 리뷰 추가 (업로드)
const insert = (data) => {
  let form = insertForm(data);
  return axios.post(baseURL + "/review/add", form,
    { headers: token2 }
  );
};

// 상세조회
const get = (reviewId) => {
  return axios.get(baseURL + `/review/${reviewId}`,
    { headers: token }
  );
};

// 리뷰 수정
const update = (reviewId, data) => {
  let form = insertForm(data);
  return axios.put(baseURL + `/review/update/${reviewId}`, form,
    { headers: token2 }
  );
};

// 리뷰 삭제
const remove = (reviewId) => {
  return axios.delete(baseURL + `/review/deletion/${reviewId}`,
    { headers: token2 }
  );
};

const ReviewService = {
  getAll,
  insert,
  get,
  update,
  remove,
};

export default ReviewService;
