package com.heliustech.orderitem.service;

import com.heliustech.orderitem.config.Utility;
import com.heliustech.orderitem.dao.ItemRepository;
import com.heliustech.orderitem.dao.OrderInfoRepository;
import com.heliustech.orderitem.entity.Item;
import com.heliustech.orderitem.dto.ItemRequestDTO;
import com.heliustech.orderitem.entity.OrderItemInfo;
import com.heliustech.orderitem.dto.OrderRequestDTO;
import com.heliustech.orderitem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<OrderItemInfo> getAllOrdersInfo() {
        return orderInfoRepository.findAll();
    }

    public List<OrderItemInfo> getOrderInfoByOrderId(String orderId) {
        List<OrderItemInfo> orderItemInfoList = null;
        try {
            if (!Utility.isEmpty(orderId)) {
                orderItemInfoList = orderInfoRepository.findOrderByOrderId(orderId);
                if(Utility.isEmpty(orderItemInfoList)){
                    throw new NoDataFoundException();
                }
                return orderItemInfoList;
            } else {
                throw new InvalidOrderException();
            }
        } catch (Exception e) {
            throw new OrderFetchException();
        }
    }

    public ResponseEntity<Map<String,OrderItemInfo>> saveOrder(OrderRequestDTO orderRequestDTO) {
        Map<String,OrderItemInfo> map = new HashMap<>();
        if (!Utility.isEmpty(orderRequestDTO)) {
            if(!Utility.isEmpty(orderRequestDTO.getOrderRequestList())){
                String orderId=Utility.generateOrderId();
                for (ItemRequestDTO itemRequestDTO : orderRequestDTO.getOrderRequestList()) {
                    Item item = null;
                    item = itemRepository.findByProductCode(itemRequestDTO.getItemCode());
                    if(Utility.isEmpty(item)){
                        throw new NoProductFoundException();
                    }
                    OrderItemInfo orderItemInfo = new OrderItemInfo();
                    if(!Utility.isEmpty(item)){
                        orderItemInfo.setId(""+Utility.getSeqValue());
                        orderItemInfo.setOrderId(orderId);
                        orderItemInfo.setItem(item);
                        orderItemInfo.setQuantity(itemRequestDTO.getQuantity());
                        orderItemInfo.setTotal(itemRequestDTO.getQuantity()*item.getPrice());
                        try{
                            orderInfoRepository.save(orderItemInfo);
                            map.put(item.getProductCode(),orderItemInfo);
                        }catch(Exception e){
                            throw new OrderFailedException();
                        }
                    }else{
                        map.put(item.getProductCode(),setNullOrderItemInfo(orderItemInfo));
                    }
                }
            }

            return new ResponseEntity<Map<String,OrderItemInfo>>(map,HttpStatus.CREATED);
        } else {
            throw new InvalidOrderException();
        }
    }


    public List<Item> getItems(){
        List<Item> items =  itemRepository.findAll();
        if(Utility.isEmpty(items)){
            throw new NoDataFoundException();
        }
        return items;
    }

    private OrderItemInfo setNullOrderItemInfo(OrderItemInfo orderItemInfo){
        orderItemInfo.setId(null);
        orderItemInfo.setOrderId(null);
        orderItemInfo.setItem(null);
        orderItemInfo.setQuantity(0);
        orderItemInfo.setTotal(0);
        return orderItemInfo;
    }
}
