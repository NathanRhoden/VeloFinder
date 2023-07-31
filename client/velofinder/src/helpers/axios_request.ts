import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.headers.post["Content-Type"] = 'application/json';

export const getAuthToken = () => {
    return window.localStorage.getItem("jwt");
};

export const setAuthHeader = (token : string) => { 
    window.localStorage.setItem("auth_Token", token);
};


export const request = (method: string, url: string, data: object) => {
    let headers = {};

    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = {
            "Authorization": `Bearer ${getAuthToken()}`
        }
    }

    return axios({
        method : method,
        url: url, 
        headers:headers, 
        data : data
        
    })

    
}
export const requestMultiPart = (method: string, url: string, data: object) => {
    let headers = {};

    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = {
            "Authorization": `Bearer ${getAuthToken()}`,
            "Content-Type": "multipart/form-data"
        }
    }

    return axios({
        method : method,
        url: url, 
        headers:headers, 
        data : data
        
    })

    
}

