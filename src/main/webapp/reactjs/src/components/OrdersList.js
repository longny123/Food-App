import { useEffect, useState } from 'react'
import {ListGroup} from 'react-bootstrap'
import {getOrders,deleteOrder} from '../service/orderApis'


function OrdersList(){
    const [orders, setOrders] = useState([])

    useEffect(()=>{
        getOrders().then(items =>{
            setOrders(items)
            console.log(items)
        }).catch(err => console.log(err))
    },[])

    return <ListGroup variant="primary">
        {orders.map(order=>{
            return <ListGroup.Item key={order.id}>
                <div>Start date: {order.startDate}</div>
                <div>Status: {order.status.toString()}</div>
                <button onClick={()=>{
                    deleteOrder(order.id).then(res=>alert(res)).catch(err=>alert(err))
                    getOrders().then(items =>{
                        setOrders(items)
                    }).catch(err => alert(err))
                    }}>Delete this order</button>
                <ListGroup >
                    {order.burgers.map(burger=>{
                        return <ListGroup.Item key={burger.id}>
                            <div>Quantity: {burger.quantity}</div>
                            <ListGroup horizontal>
                                {burger.filling.map(item=>{
                                    return <ListGroup.Item key={item.id}>
                                        <div>{item.filling.name}</div>
                                        <div>{item.quantity}</div>
                                    </ListGroup.Item>
                                })}
                            </ListGroup>
                        </ListGroup.Item>
                    })}
                </ListGroup>

            </ListGroup.Item>
        })}
    </ListGroup>
}

export default OrdersList