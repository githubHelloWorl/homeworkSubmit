import axios from "axios";

export function getList(param){
    return axios.get("http://localhost:5000/api/learning_resource/getList",{params:param});
}

export function getNameList(id){
    return axios.get("http://localhost:5000/api/learning_resource/getNameList?userId=" + id);
}
