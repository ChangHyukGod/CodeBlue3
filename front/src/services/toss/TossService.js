import axios from "axios"

const baseURL = "http://localhost:8000/api";

// data를 JSON 형식으로 변환하는 함수
const insertForm = (data) => {
    return JSON.stringify(data);
  };

const notified = (data) =>{
    let from = insertForm(data);
    console.log(from);
    return axios.post(baseURL+"/payment/complete",from,{
        headers:{
            'Content-Type': 'application/json' //json 형식으로 전송
        }
    });
};

const TossService = {
    notified,
}

export default TossService;