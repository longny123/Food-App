import {
    LOGIN_SUCCESS,
    FETCH_DATA_USER,
    LOGIN_FAILED,
    LOGOUT,
    GET_USER,
    SWITCH_PAGE,
    SWITCH_PAGE_HOME,
    SWITCH_PAGE_USER,
    SWITCH_PAGE_PRODUCT,
    GET_LIST_USER,
    GET_LIST_PRODUCT,
    UPDATE_USER_SUCCESS,
    DELETE_USER_SUCCESS, CREATE_USER
} from "../constants/Type"

const userInfoLocal = localStorage.getItem("User")

const initialState = {
    user: userInfoLocal ? JSON.parse(userInfoLocal) : [],
    listUser: [],
    loading: true,
    err: null,
    redirect: false,
    mess: null,
    products: [],
    userDetails: []
};

const UserReducer = (state = initialState, action) =>{
    switch (action.type){
        case FETCH_DATA_USER:
            return {...state, loading: false }
        case LOGIN_SUCCESS:
            return {...state, user: action.payload, redirect: true}
        case LOGIN_FAILED:
            return {...state, err: action.payload.err}
        case GET_USER:
            return {...state, user: action.payload}
        case SWITCH_PAGE_HOME:
            return {...state, page: action.payload.page}
        case SWITCH_PAGE_USER:
            return {...state, page: action.payload.page, listUser: action.payload}
        case SWITCH_PAGE_PRODUCT:
            return {...state, page: action.payload.page, products: action.payload}
        case GET_LIST_USER:
            return {...state, listUser: action.payload}
        case GET_LIST_PRODUCT:
            return {...state, products: action.payload}
        case UPDATE_USER_SUCCESS:
            return {...state, mess: action.payload}
        case DELETE_USER_SUCCESS:
            return {...state, mess: "User was deleted"}
        case CREATE_USER:
            return {...state, userDetails: action.payload}
        default:
            return state;
    }
}

export {UserReducer};