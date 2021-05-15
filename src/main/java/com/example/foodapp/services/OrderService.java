package com.example.foodapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.foodapp.dtos.BurgerDto;
import com.example.foodapp.dtos.FillingDto;
import com.example.foodapp.dtos.FillingInBurgerDto;
import com.example.foodapp.dtos.OrderDto;
import com.example.foodapp.models.entities.*;
import com.example.foodapp.models.repository.FillingRepository;
import com.example.foodapp.models.repository.OrderBurgerFillingRepository;
import com.example.foodapp.models.repository.OrderBurgerRepository;
import com.example.foodapp.models.repository.OrderRepository;
import com.example.foodapp.models.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    
    @Autowired
    private FillingRepository fillingRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderBurgerRepository orderBurgerRepository;
    @Autowired
    private OrderBurgerFillingRepository orderBurgerFillingRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Filling> getFillingList(){
        List<Filling> fillings = new ArrayList<Filling>(fillingRepository.findAll());
        
        return fillings;
    }
    public String addFillingToMenu(Filling filling){
        fillingRepository.save(filling);
        return "Filling added";
    }

    public String deleteFillingToMenu(Long id){
        fillingRepository.deleteById(id);
        return "Filling deleted";
    }


    public void createOrder(String username, List<BurgerDto> burgers ){
        UserEntity user = userRepository.findByEmail(username);
        Order order = new Order(user, true, LocalDate.now());
        orderRepository.save(order);
        for (BurgerDto burger : burgers) {
            OrderBurger burger2 = new OrderBurger(burger.getQuantity(),order);
            orderBurgerRepository.save(burger2);
            for (FillingInBurgerDto filling : burger.getFillings()){
                Filling tmp = fillingRepository.getOne(filling.getId());
                OrderBurgerFilling fill = new OrderBurgerFilling(filling.getQuantity(),burger2,tmp);
                orderBurgerFillingRepository.save(fill);
            }
        }

    }
    
    public List<OrderDto> getOrders(String email){
        UserEntity user = userRepository.findByEmail(email);
        List<Order> orders = user.getOrders();
        return ordersToOrderDtos(orders);
    }
    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        System.out.println(orders.get(0).getUser().getEmail());
        return ordersToOrderDtos(orders);
    }
    public String deleteOrder(String id, String email) throws AccessDeniedException{
        Order order = orderRepository.findById(Long.parseLong(id)).get();
        Set<UserRole> roles = userRepository.findByEmail(email).getRoles();
        boolean admin= false;
        for (UserRole role : roles) {
            if (role.getRoleId().equals(1)) {
                admin = true;
                break;
            }
        }

        if(!order.getUser().getEmail().equals(email) && !admin ){
            throw new AccessDeniedException("You are not the onwer of this order");
        }

        orderRepository.deleteById(Long.parseLong(id));
        return "Success";
    }
    public String deleteBurger(Long burgerid ){
        orderBurgerRepository.deleteById(burgerid);
        return "Success";
    }

    public String updateBurger(Long id, BurgerDto burgerDto) {
        OrderBurger burger = new OrderBurger();
        BeanUtils.copyProperties(burgerDto,burger);
        burger.setId(id);
        orderBurgerRepository.save(burger);

        return "Success";
    
    }
    public String addFillingToMenu(FillingDto fillingDto){
        Filling filling = new Filling();
        BeanUtils.copyProperties(fillingDto, filling);
        fillingRepository.save(filling);
        return "Success";
    }
    private List<OrderDto> ordersToOrderDtos(List<Order> orders){
        List<OrderDto> orderDtos = new ArrayList<OrderDto>();
        for (Order order : orders){
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto );
            orderDto.setUserEmail(order.getUser().getEmail());
            orderDto.setBurgers(new HashSet<BurgerDto>());
            for(OrderBurger burger : order.getBurgers() ){
                BurgerDto burgerDto = new BurgerDto();
                BeanUtils.copyProperties(burger, burgerDto);
                burgerDto.setFillings(new HashSet<FillingInBurgerDto>());
                for(OrderBurgerFilling filling : burger.getFillings()){
                    FillingInBurgerDto fillingInBurgerDto = new FillingInBurgerDto();
                    BeanUtils.copyProperties(filling, fillingInBurgerDto);
                    burgerDto.getFillings().add(fillingInBurgerDto);
                }
                orderDto.getBurgers().add(burgerDto);
            }

            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
    public List<OrderBurger> getBurgers() {
        return orderBurgerRepository.findAll();
    }
}
