import axios from "axios";

const baseURL = "http://localhost:8000/api";

const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
  return axios.get(
    `${baseURL}/announcement?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
  );
};

const get = (ano) => {
  return axios.get(`${baseURL}/announcement/${ano}`);
}

const AnnouncementService = {
  getAll,
  get,
};

export default AnnouncementService;
