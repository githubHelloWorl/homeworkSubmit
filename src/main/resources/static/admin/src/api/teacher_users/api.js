import axios from "axios";

// TODO
// 得到教师学院
export function getList(){
    return axios.get("http://localhost:5000/api/learning_resource/getNameList");
}

