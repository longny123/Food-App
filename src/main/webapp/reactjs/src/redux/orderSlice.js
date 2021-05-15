import {createSlice} from '@reduxjs/toolkit'


function compareBurger(bur1, bur2){
    for (let i =0; i<bur1.length; i++){
        if(bur1[i].id !== bur2[i].id || bur1[i].quantity !== bur2[i].quantity){
            return false 
        }    
        
    }
    return true 
}

export const fillingSlice= createSlice({
    name: 'order',
    initialState:{
        burgers:[

        ]
    },
    reducers:{
        addBurger: (state,{payload}) =>{
            var check = false
            state.burgers.forEach(burger => {
                if(compareBurger(burger.filling, payload )){
                    burger.quantity++
                    check = true
                }
            });
            if(!check){
                const b = {
                    filling: payload ,
                    quantity: 1
                }
                state.burgers.push(b)
            }
        },
        deleteBurger:(state,{payload}) =>{
            state.burgers[payload].quantity--
            state.burgers = state.burgers.filter(burger => burger.quantity>0)
        } ,
        deleteAllOfOneKind:(state,{payload})=>{
            state.burgers.splice(payload, 1)
        },
        deleteAll:(state)=>{
            state.burgers.splice(0)
        }
    }
    
})
// Action creators are generated for each case reducer function
export const {addBurger, deleteBurger,deleteAllOfOneKind,deleteAll} = fillingSlice.actions
export default fillingSlice.reducer