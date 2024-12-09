import axios from "axios";

const baseURL = "http://localhost:8000/api";

const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
  return axios.get(
    `${baseURL}/faq/talk?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
  );
};

const get = (tno) => {
  return axios.get(`${baseURL}/faq/talk/${tno}`);
};

const insert = (data) => {
  return axios.post(`${baseURL}/faq/talk`, data);
};

const updated = (tno, data) => {
  return axios.put(`${baseURL}/faq/talk/${tno}`, data);
};

const insertForm = (data)=>{
  console.log(data)
  // FormData 객체 생성
  let formData = new FormData();
  // FormData 객체에 넣기 : .append(키(벡엔드변수명), 값)
  formData.append("image", data.image);

  return formData;
};
const TalkService = {
  get,
  getAll,
  insert,
  updated,
  insertForm
};

export default TalkService;
