import axios from "axios";

const baseURL = "http://localhost:8000/api";

// 장바구니에 추가
const addToCart = (data) => {
  return axios.post(baseURL + `/cart/add`, data);
};

// 이메일마다 장바구니 전체조회
const getItemsFromEmail = (userEmail) => {
  return axios.get(baseURL + `/cart/detail/${userEmail}`);
};

// 장바구니 개별 삭제
const deleteFromCartId = (cartId) => {
  return axios.delete(baseURL + `/cart/delete/${cartId}`);
};

// 장바구니 전체 삭제
const deleteFromCartEmail = (userEmail) => {
  return axios.delete(baseURL + `/cart/delete/all/${userEmail}`);
};

// 장바구니 개수 카운트
const countCartItems = (userEmail) => {
  return axios.get(baseURL + `/cart/count/${userEmail}`);
};

// cartId로 장바구니 상세조회
const getItemsFromCartId = (cartId) => {
  return axios.get(baseURL + `/cart/detail/item/${cartId}`);
};

const CartService = {
  addToCart,
  getItemsFromEmail,
  deleteFromCartId,
  deleteFromCartEmail,
  countCartItems,
  getItemsFromCartId,
};

export default CartService;
