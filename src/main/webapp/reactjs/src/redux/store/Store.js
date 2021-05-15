import { createStore, applyMiddleware, compose, combineReducers } from "redux";
import thunk from "redux-thunk";
import {UserReducer} from "../reducers/UserReducer"
import orderReducer from "../orderSlice";

const initialState = {};
const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(
    combineReducers({
        user: UserReducer,
        order : orderReducer
    }),
    initialState,
    composeEnhancer(applyMiddleware(thunk))
);
export default store;