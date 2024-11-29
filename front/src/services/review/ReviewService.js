import axios from "axios";

// 백엔드 주소 (SpringBoot API URL)
const baseURL = "http://localhost:8000/api/review/review";

// 전체 조회
const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
  return axios.get(baseURL + `/review?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`, {
    headers: { 
      // 인증 헤더를 추가하지 않음
      'Content-Type': 'application/json', // 기본 JSON 형식 헤더
    }
  });
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
  return axios.post(baseURL + "/add", form, {
    headers: {
      "Content-Type": "multipart/form-data", // 파일 업로드를 위한 헤더
      // 인증 헤더를 추가하지 않음
    }
  });
};

// 상세조회
const get = (reviewId) => {
  return axios.get(baseURL + `/review/${reviewId}`, {
    headers: { 
      // 인증 헤더를 추가하지 않음
    }
  });
};

// 리뷰 수정
const update = (reviewId, data) => {
  let form = insertForm(data);
  return axios.put(baseURL + `/update/${reviewId}`, form, {
    headers: {
      "Content-Type": "multipart/form-data", // 파일 업로드를 위한 헤더
      // 인증 헤더를 추가하지 않음
    }
  });
};

// 리뷰 삭제
const remove = (reviewId) => {
  return axios.delete(baseURL + `/deletion/${reviewId}`, {
    headers: { 
      // 인증 헤더를 추가하지 않음
    }
  });
};

const ReviewService = {
  getAll,
  insert,
  get,
  update,
  remove,
};

export default ReviewService;
