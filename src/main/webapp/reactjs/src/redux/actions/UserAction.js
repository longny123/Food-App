import {
    LOGIN_SUCCESS,
    LOGIN_FAILED,
    FETCH_DATA_USER,
    GET_USER, GET_LIST_USER, GET_LIST_PRODUCT, UPDATE_USER_SUCCESS, DELETE_USER_SUCCESS, CREATE_USER
} from "../constants/Type"
import {getListUserData, getUserData, userLogin, editUser, deleteUser , createUser} from "../../service/DataService";
import {getAllOrders} from "../../service/orderApis";

export const fetchUser = (user) => async (dispatch) => {
    dispatch({
        type: FETCH_DATA_USER,
    });
    userLogin(user.email, user.password).then(
        res =>{
            dispatch({
                type: LOGIN_SUCCESS,
                payload: res.data
            })
        localStorage.setItem("User",JSON.stringify(res.data));
        }
    ).catch(
        err =>
            dispatch({
                type:LOGIN_FAILED,
                payload: err
            })
    )
};

export const getUser = () => async (dispatch) =>{
    getUserData().then(res =>{
        dispatch({
            type: GET_USER,
            payload: res.data
        })
    })
}

export const getListUser = () => async (dispatch) =>{
    getListUserData(1).then(res =>{
        dispatch({
            type: GET_LIST_USER,
            payload: res.data
        })
    })
}
export const getListProduct = () => async (dispatch) =>{
        await getAllOrders().then(res =>{
            dispatch({
                type: GET_LIST_PRODUCT,
                payload: res
        })
    })
}

export const updateUser = (fName, lName, userId) => async (dispatch) =>{
    await editUser(fName, lName, userId).then(res => {
        dispatch({
            type: UPDATE_USER_SUCCESS,
            payload: res.data
        })
    })
}

export const deleteUserWithId = (userId) => async (dispatch) =>{
    await deleteUser(userId).then(res => {
        dispatch({
            type: DELETE_USER_SUCCESS,
        })
    })
}

export const createNewUser = (fName, lName, email, password) => (dispatch) =>{
    createUser(fName,lName,email,password).then(
        res => {
            dispatch({
                type: CREATE_USER,
                payload: res.data
            })
        }
    )
}
// export const switchPage= () => async (dispatch) =>{
//     if(JSON.parse(localStorage.getItem("switchPage")) === "Home"){
//         dispatch({
//             type: SWITCH_PAGE_HOME,
//             payload: JSON.parse(localStorage.getItem("switchPage"))
//         })
//     } else if (JSON.parse(localStorage.getItem("switchPage")) === "User"){
//         getListUserData().then( res => {
//             dispatch({
//                 type: SWITCH_PAGE_USER,
//                 payload: {
//                     page: JSON.parse(localStorage.getItem("switchPage")),
//                     listUser: res.data
//                 }
//             })
//         })
//     }else if (JSON.parse(localStorage.getItem("switchPage")) === "Product"){
//         getListUserData().then( res => {
//             dispatch({
//                 type: SWITCH_PAGE_PRODUCT,
//                 payload: {
//                     page: JSON.parse(localStorage.getItem("switchPage")),
//                     products: res.data
//                 }
//             })
//         })
//     }
// }



