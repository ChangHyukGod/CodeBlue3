import axios from "axios";

const baseURL = "http://localhost:8000/api";

const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(
    `${baseURL}/announcement?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
);
};

const getDetail = (ano) => {
    return axios.get(baseURL+`/announcement/detail/${ano}`);
}

const getUpdate = (ano, data) => {
    return axios.put(baseURL+`/announcement/list/update/${ano}`,data);
}

const getDelete = (ano) => {
    return axios.delete(baseURL+`/announcement/list/delete/${ano}`);
}


const AnnouncementService = {
    getAll,
    getDetail,
    getUpdate,
    getDelete,

};

export default AnnouncementService;