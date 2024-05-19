import axios from 'axios'

export function updateSubmission(id){
    return axios.get("http://localhost:5000/api/homework_submission/updateSubmissionStatus?id="+id)
}