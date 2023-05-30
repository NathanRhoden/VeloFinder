import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.headers.post["Content-Type"] = 'application/json';

export const getAuthToken = () => {
    return window.localStorage.getItem("auth_Token");
};

export const setAuthHeader = (token : string) => { 
    window.localStorage.setItem("auth_Token", token);
};


export const request = (method: string, url: string, data: object) => {
    let headers = {};

    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = {"Authorization":  `Bearer ${getAuthToken()}`}
    }

    return axios({
        method : method,
        url: url, 
        headers:headers, 
        data : data
        
    })

}