import React, {Component, useEffect, useState} from 'react';
import { getFillings } from '../service/orderApis';
import {useDispatch} from 'react-redux'
import {addBurger} from '../redux/orderSlice'


const ButtonSection = () =>{
        const [filling,setFilling] = useState([])
        const [burger, setBurger] = useState([])
        var dispatch = useDispatch()
        useEffect(()=>{
                getFillings().then(items =>{
                        setFilling(items)
                        setBurger(items.map(item =>( {id: item.id,name: item.name, quantity: 0} )))
                }).catch(err => console.log(err))
        },[])
        const changeBurgerFilling = (id, amount)=>{
                const nBurger = burger.map(item =>{
                        let tmp = item.quantity
                        if(item.id === id){
                                tmp += amount
                                if(tmp < 0 ) tmp=0
                        }
                        return ({id: item.id,name:item.name, quantity: tmp} )
                })
                setBurger(nBurger)
        }
        const dispatchBurger =()=>{
                let check_empty = true
                burger.forEach(item =>{
                        if(item.quantity>0){
                            check_empty = false
                        }
                })
                if(check_empty) return 
                dispatch(addBurger(burger))
        }

        return (
            <div className="div__content">

                    {filling.map(item=><div key={item.id} className="d-flex align-items-center">{item.name}: <i className="far fa-plus-square fa-2x" onClick={()=>changeBurgerFilling(item.id,1)}></i>
                            <div>{burger.length > 0 ?burger.filter(x=>x.id===item.id)[0].quantity:0}</div>
                            <i class="far fa-minus-square fa-2x" onClick={()=>changeBurgerFilling(item.id,-1)}></i>
                            <div>Price: {item.price}</div>
                            </div>)}

                    <button onClick={()=>dispatchBurger()}>addBurger</button> 


            </div>
        );
}

export default ButtonSection;