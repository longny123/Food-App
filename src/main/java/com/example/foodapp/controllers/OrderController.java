package com.example.foodapp.controllers;

import java.util.List;

import com.example.foodapp.dtos.BurgerDto;
import com.example.foodapp.dtos.FillingDto;
import com.example.foodapp.dtos.OrderDto;
import com.example.foodapp.models.entities.Filling;
import com.example.foodapp.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders(Authentication authentication){
        return orderService.getOrders(authentication.getName());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createOrder(@RequestBody List<BurgerDto> burgers,Authentication authentication) throws Exception{

        
        orderService.createOrder(authentication.getName(), burgers);
        return "Order successfully";
    }

    @DeleteMapping(path = "/burger/{id}")
    public String deleteBurger(@PathVariable String id){
        orderService.deleteBurger(Long.parseLong(id));
        return "Burger deleted";
    }
    @PutMapping(path = "/burger/{id}")
    public String updateBurger(@RequestBody BurgerDto burgerDto,@PathVariable String id ){
        orderService.updateBurger(Long.parseLong(id), burgerDto);
        return "Burger updated";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteOrder(@PathVariable String id ){
        orderService.deleteOrder(id);
        return "Order deleted";
    } 

    @GetMapping(path="/filling", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Filling> getFillingList(){
        return orderService.getFillingList();
    }

    @PostMapping(path="/filling",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String addFillingToMenu(@RequestBody FillingDto fillingDto){
        
        orderService.addFillingToMenu(fillingDto);
        return "Filling Added";
    }
    @GetMapping(path = "/all")
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }
}
