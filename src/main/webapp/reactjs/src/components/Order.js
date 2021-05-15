import {ListGroup} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux'
import {deleteBurger,deleteAllOfOneKind} from '../redux/orderSlice'
import {postOrder} from '../service/orderApis'
import NavBar from "./NavBar";
import {BrowserRouter} from "react-router-dom";
import React from "react";


function OrderComp(){
    const burgers = useSelector(state => state.order.burgers)
    const dispatch = useDispatch()
    return <div>
        <NavBar/>
        <button onClick={()=>{
            postOrder(burgers).then(res=>alert(res)).catch(err=>alert(err))
            }}>Order now</button>
    <ListGroup>
        {burgers.map((burger,index) =>{
            return <ListGroup.Item key={index}>
                <ListGroup horizontal>{burger.filling.map(item=>{
                    return(
                    <ListGroup.Item key={item.id}>
                        <div>{item.name}</div>
                        <div>{item.quantity}</div>
                    </ListGroup.Item>
                    )
                })}</ListGroup>
                <div>Quantity: {burger.quantity}</div>
                <button onClick={()=>{dispatch(deleteBurger(index))}}>Remove 1 burger</button>
                <button onClick={()=>{dispatch(deleteAllOfOneKind(index))}}>Remove all these burgers</button>
            </ListGroup.Item>
        })}
    </ListGroup>
    </div>
}
export default OrderComp