import axios from "axios";

export function getList(param){
    return axios.get("http://localhost:5000/api/learning_resource/getList",{params:param});
}

export function getNameList(id){
    return axios.get("http://localhost:5000/api/learning_resource/getNameList?userId=" + id);
}

// TODO 学院名称
export function getListTestCollege(id){
    return axios.get("http://localhost:5000/api/learning_resource/get_list?teacher_users=" + id);
}

// TODO
export function getListTestCourse(id, college_name){
    return axios.get("http://localhost:5000/api/learning_resource/get_list?teacher_users=" + id + "&college_name=" + college_name);
}