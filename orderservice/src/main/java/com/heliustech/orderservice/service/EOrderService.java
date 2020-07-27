package com.heliustech.orderservice.service;

import com.heliustech.orderservice.dto.*;
import com.heliustech.orderservice.dao.EOrderServiceRepository;
import com.heliustech.orderservice.config.Utility;
import com.heliustech.orderservice.dao.ECustomerRepository;
import com.heliustech.orderservice.entity.*;
import com.heliustech.orderservice.exception.CustomerNotFoundException;
import com.heliustech.orderservice.exception.InvalidOrderException;
import com.heliustech.orderservice.exception.OrderFailedException;
import com.heliustech.orderservice.exception.OrderNotFoundException;
import com.heliustech.orderservice.proxy.EOrderItemProxy;
import com.zaxxer.hikari.util.UtilityElf;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EOrderService {
    @Autowired
    private EOrderServiceRepository eOrderServiceRepository;

    @Autowired
    private ECustomerRepository eCustomerRepository;

    @Autowired
    private EOrderItemProxy eOrderItemProxy;

    public List<CustomerOrder> getAllOrders(){
        return eOrderServiceRepository.findAll();
    }

    public List<OrderInfo> getOrdersByOrderId(String orderId) {
        List<OrderInfo>  orders = getOrdersInfoByOrderId(orderId);
        if(Utility.isEmpty(orders)){
            throw new OrderNotFoundException();
        }
        return orders;
    }

    public List<CustomerOrder> getOrdersByCustomerId(String custId) {
        return eOrderServiceRepository.findOrderServiceByCustomerId(custId);
    }

    public List<Order> getOrderInfo(){
        return eOrderItemProxy.getAllOrderedItems();
    }

    public List<OrderInfo> getAllOrdersDetailedInfo(){
        return getAllOrdersInfo();
    }

    private List<OrderInfo> getAllOrdersInfo(){
        List<CustomerOrder> customerOrderInfo = eOrderServiceRepository.findAll();
        System.out.println("size 1- "+customerOrderInfo.size());
        List<OrderInfo> listOfOrderInfo = customerOrderInfo.stream()
                .filter(customerOrder -> !Utility.isEmpty(customerOrder))
                .map(customerOrder -> new OrderInfo(customerOrder.getId(),customerOrder.getOrderId(),0,0,
                        customerOrder.getOrderDate(),new ArrayList<Item>(),customerOrder.getCustomer())).collect(Collectors.toList());
        System.out.println("size 2- "+listOfOrderInfo.size());
        listOfOrderInfo = listOfOrderInfo.stream().map(orderInfo -> {
            System.out.println("order getItems "+orderInfo.getItems());
            List<Order> ordersList = eOrderItemProxy.getOrder(orderInfo.getOrderId());
            System.out.println("order getItems "+ordersList.size());
            if(!Utility.isEmpty(ordersList))
            {
                for(Order order:ordersList){
                    orderInfo.getItems().add(order.getItem());
                    double total = orderInfo.getTotal()+order.getTotal();
                    int quantity = orderInfo.getQuantity()+order.getQuantity();
                    orderInfo.setQuantity(quantity);
                    orderInfo.setTotal(total);
                }
            }
            return orderInfo;
        }).collect(Collectors.toList());
        System.out.println("size -3 "+listOfOrderInfo.size());
        return listOfOrderInfo;
    }

    public ResponseEntity<CustomerOrderResponse> saveCustomerOrder(CustomerOrderRequest customerOrderRequest) {
        Customer customer =  customerOrderRequest.getCustomer();
        long isCustomerExisted=-1;
        if(!Utility.isEmpty(customer.getEmail()))
        isCustomerExisted = eCustomerRepository.countByEmail(customer.getEmail());
        if(isCustomerExisted==0){
            //dummyvalue
            customer.setId("100");
            customer=eCustomerRepository.save(customer);
        }
        if(!Utility.isEmpty(customerOrderRequest.getItmes())){
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setOrderRequestList(customerOrderRequest.getItmes());
            ResponseEntity<Map<String,Order>> responseEntity = null;
            responseEntity =  eOrderItemProxy.saveOrder(orderRequest);
            if(Utility.isEmpty(responseEntity))
                throw new OrderFailedException();

            Map<String,Order> results = responseEntity.getBody();
            if (!Utility.isEmpty(results)){
                String orderId = getOrderId(results);
                Double totalAmount = calculateOrderAmount(results);
                boolean saved = saveCustomerOrder(customer,orderId,totalAmount);
                if(saved){
                    CustomerOrderResponse response = new CustomerOrderResponse();
                    response.setAmount(totalAmount);
                    response.setOrderId(orderId);
                    response.setMessage("Order posted successfully");
                    response.setOrderStatus("SUCCESS");
                    response.setCustomer(customer);
                    response.setOrderDetails(results);
                    return new ResponseEntity<CustomerOrderResponse>(response,HttpStatus.ACCEPTED);
                }
            }else{
                throw new OrderFailedException();
            }
        }else{
            throw new InvalidOrderException();
        }

        throw new OrderFailedException();
    }

    private boolean saveCustomerOrder(Customer customer,String orderId, Double totalAmount){
        CustomerOrder cOrder = new CustomerOrder();
        cOrder.setCustomer(customer);
        cOrder.setId("123");
        cOrder.setOrderDate(Utility.getDate());
        cOrder.setOrderId(orderId);
        cOrder.setTotal(totalAmount);
        try {
            cOrder = eOrderServiceRepository.save(cOrder);
            return true;
        }catch (Exception e){
            throw new OrderFailedException();
        }
    }

    private Double calculateOrderAmount(Map<String,Order> orderInfo){
        Double amount=0d;
        if(!Utility.isEmpty(orderInfo)){
            amount = orderInfo.values().stream().mapToDouble(oInfo->oInfo.getTotal()).sum();
        }
        return amount;
    }

    private String getOrderId(Map<String,Order> orderInfo){
        Collection<Order> orders =orderInfo.values();
        String orderId = "";
        for(Order o:orders){
            orderId = o.getOrderId();
            if(!Utility.isEmpty(orderId))
                return orderId;
        }
        if(Utility.isEmpty(orderId))
            throw new OrderNotFoundException();
        return orderId;
    }

    private List<OrderInfo> getOrdersInfoByOrderId(String strOrderId){
        List<CustomerOrder> customerOrderInfo = eOrderServiceRepository.findOrderServiceByOrderId(strOrderId);
        if(Utility.isEmpty(customerOrderInfo)){
            throw new OrderNotFoundException();
        }
        List<OrderInfo> listOfOrderInfo = customerOrderInfo.stream()
                .filter(customerOrder -> !Utility.isEmpty(customerOrder))
                .map(customerOrder -> new OrderInfo(customerOrder.getId(),customerOrder.getOrderId(),0,0,
                        customerOrder.getOrderDate(),new ArrayList<Item>(),customerOrder.getCustomer())).collect(Collectors.toList());

        listOfOrderInfo = listOfOrderInfo.stream().map(orderInfo -> {
            List<Order> ordersList = eOrderItemProxy.getOrder(orderInfo.getOrderId());
            System.out.println("order getItems "+ordersList.size());
            if(!Utility.isEmpty(ordersList))
            {
                for(Order order:ordersList){
                    orderInfo.getItems().add(order.getItem());
                    double total = orderInfo.getTotal()+order.getTotal();
                    int quantity = orderInfo.getQuantity()+order.getQuantity();
                    orderInfo.setQuantity(quantity);
                    orderInfo.setTotal(total);
                }
            }
            return orderInfo;
        }).collect(Collectors.toList());
        if(Utility.isEmpty(listOfOrderInfo)){
            throw new OrderNotFoundException();
        }
        return listOfOrderInfo;
    }


}
