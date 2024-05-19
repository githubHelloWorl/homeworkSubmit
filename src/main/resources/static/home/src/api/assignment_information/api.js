import axios from "axios";

export function getList(query){
    return axios.get("http://localhost:5000/api/assignment_information/getList?page="+query.page+"&size="+query.size);
}

export function toPublic(id){
    return axios.get("http://localhost:5000/api/assignment_information/public?id="+id)
}