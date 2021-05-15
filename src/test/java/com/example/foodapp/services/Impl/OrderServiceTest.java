package com.example.foodapp.services.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.foodapp.dtos.BurgerDto;
import com.example.foodapp.dtos.FillingDto;
import com.example.foodapp.dtos.FillingInBurgerDto;
import com.example.foodapp.models.entities.Filling;
import com.example.foodapp.models.entities.Order;
import com.example.foodapp.models.entities.OrderBurger;
import com.example.foodapp.models.entities.OrderBurgerFilling;
import com.example.foodapp.models.entities.UserEntity;
import com.example.foodapp.models.repository.FillingRepository;
import com.example.foodapp.models.repository.OrderBurgerFillingRepository;
import com.example.foodapp.models.repository.OrderBurgerRepository;
import com.example.foodapp.models.repository.OrderRepository;
import com.example.foodapp.models.repository.UserRepository;
import com.example.foodapp.services.OrderService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private FillingRepository fillingRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderBurgerRepository orderBurgerRepository;
    @Mock
    private OrderBurgerFillingRepository orderBurgerFillingRepository;
    @Mock
    private UserRepository userRepository;

    private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception{
        closeable.close();
    }

    //fillings
    @Test
    public void getFillingsTest(){
        List<Filling> fillings = new ArrayList<Filling>();
        fillings.add(new Filling("weed",3.0));
        fillings.add(new Filling("soup",2.0));
        fillings.add(new Filling("rice",3.0));
        
        when(fillingRepository.findAll()).thenReturn(fillings);
        List<Filling> testlist = orderService.getFillingList();
        assertEquals(fillings, testlist);
        verify(fillingRepository,times(1)).findAll();
    }

    @Test
    public void addFillingToMenuTest(){
        Filling pork = new Filling("pork", 1.0 );
        orderService.addFillingToMenu(pork);
        verify(fillingRepository).save(Mockito.any(Filling.class));
    }


    //order
    @Test
    public void createOrderTest(){
        UserEntity user = new UserEntity("id", "khoa", "phan", "test@test.test", "encryptedPassword");
        List<BurgerDto> burgers = new ArrayList<BurgerDto>();
        Set<FillingInBurgerDto> fillingDtos = new HashSet<FillingInBurgerDto>();
        fillingDtos.add(new FillingInBurgerDto(2L,2));
        fillingDtos.add(new FillingInBurgerDto(3L,2));
        burgers.add(new BurgerDto(fillingDtos,2));
        orderService.createOrder(user.getEmail(), burgers);
        verify(orderRepository,times(1)).save(Mockito.any(Order.class));
        verify(orderBurgerRepository,times(1)).save(Mockito.any(OrderBurger.class));
        verify(orderBurgerFillingRepository,times(2)).save(Mockito.any(OrderBurgerFilling.class));

    }


}
