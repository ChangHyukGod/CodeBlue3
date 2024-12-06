import axios from "axios";

const baseURL = "http://localhost:8000/api";

const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(
    `${baseURL}/faq/list?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
);
};

const getDetail = (fno) => {
    return axios.get(baseURL+`/faq/list/${fno}`);
}

const getUpdate = (fno, data) => {
    return axios.put(baseURL+`/faq/list/update/${fno}`,data);
}

const getDelete = (fno) => {
    return axios.delete(baseURL+`/faq/list/delete/${fno}`);
}


const FaqService = {
    getAll,
    getDetail,
    getUpdate,
    getDelete,

};

export default FaqService;