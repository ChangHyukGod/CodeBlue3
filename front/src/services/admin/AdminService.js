
import axios from "axios";

const baseURL = "http://localhost:8080/api"


const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios
       .get(baseURL+`/admin/admin?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
};


const insert = (data) => {
    return axios.post(baseURL+"/admin/admin", data);
}

const get = (fno) => {
    return axios.get(baseURL+`/admin/admin/${fno}`);
}

const update = (fno, data)=>{
    return axios.put(baseURL+`/basic/admin/${fno}`,data);
}


const remove = (fno)=>{
    return axios.delete(baseURL+`/admin/admin/deletion/${fno}`);
}

const AdminService = {
    getAll,
    insert,
    get,
    update,
    remove
}
export default AdminService;