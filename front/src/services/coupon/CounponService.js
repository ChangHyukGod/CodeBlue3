import axios from "axios";
// 백엔드 주소 : 스트링부트 주소 (컨트롤러주소)
const baseURL = "http://localhost:8000/api";

let user = JSON.parse(localStorage.getItem("user"));
const token = { Authorization: "Bearer " + user?.accessToken };

const insert = (data) => {
  return axios.post(baseURL + `/coupon/coupon`, data, { headers: token });
};

const CouponService = {
  insert,
};

export default CouponService;