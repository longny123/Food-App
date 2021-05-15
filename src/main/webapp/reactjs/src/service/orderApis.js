import axios from 'axios'

const USER_API_URL = 'http://localhost:8080'
const axiosClient = axios.create(
    {baseURL : USER_API_URL}
)

export function getFillings(){
    return axios.get("/order/filling",{ headers: {
        'Authorization': authHeader() 
      }})
    .then(res => {
        return res.data
    })
}

export function getOrders(){
    return axios.get("/order",{ headers: {
        'Authorization': authHeader() 
      }})
    .then(res=>{
        return res.data
    })
}

export function getAllOrders(){
    return axios.get("/order/all",{ headers: {
            'Authorization': authHeader()
        }})
        .then(res=>{
            return res.data
        })
}

export function postOrder(order){
    return axios.post("/order",order,{ headers: {
        'Authorization': authHeader() 
      }})
    .then(res=>{
        return res.data
    })
}
export function deleteOrder(id){
    return axios.delete('/order/'+id,{ headers: {
        'Authorization': authHeader() 
      }}).then(
        res=>res.data
    )
}

function authHeader(){
    return JSON.parse( localStorage.getItem('User')).Jwt;
}