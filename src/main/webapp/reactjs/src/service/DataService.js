import axios from 'axios'

const axiosClient = axios.create()

// axiosClient.defaults.headers.common['Content-Type'] = "application/json"
// axiosClient.defaults.headers.common['Accept'] = "application/json"


let config = "";
if (localStorage.getItem("User")){
    config = {
        headers: {
            Authorization: JSON.parse(localStorage.User).Jwt,
        }
    }
}
else if (localStorage.getItem("User") && localStorage.getItem("switchPage") === "User"){
    config = {
        headers: {
            Authorization: JSON.parse(localStorage.User).Jwt,
        }
    }
}

export const getListUserData = (page = 1 ) =>{
    return axiosClient.get(`/users?page=${page}&limit=15`, config)
}
export const userLogin = ( email, password) =>{
    return axiosClient.post(`/users/login`,{email: email,password: password})
}
export const getUserData = () =>{
    return axiosClient.get(`/users/${JSON.parse(localStorage.User).UserId}`, config)
}
export const userLogout = () =>{
    return axiosClient.get(`/users/logout`)
}
export const editUser = (fName, lName, userId) =>{
    return axiosClient.put(`/users/${userId}`, {firstName: fName, lastName: lName}, config)
}

export const deleteUser = (userId) =>{
    return axiosClient.delete(`/users/${userId}`,config)
}

export const createUser = (fName, lName, email, password) => {
    return axiosClient.post(`/users`,{"firstName": fName,
        "lastName": lName,
        "email": email,
        "password": password})
}

