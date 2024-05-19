import axios from "axios";

export function getList(){
    return axios.get("http://localhost:5000/api/homework_correction/getNameList");
}