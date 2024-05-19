import axios from "axios";

export function getList(query){
    console.log(query);
    return axios.get("http://localhost:5000/api/assignment_information/getList?page="+query.page+"&size="+query.size);
}

// 发布
export function toPublic(id){
    return axios.get("http://localhost:5000/api/assignment_information/public?id="+id)
}
