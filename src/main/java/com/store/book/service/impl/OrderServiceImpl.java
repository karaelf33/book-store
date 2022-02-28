package com.store.book.service.impl;

import com.store.book.dto.CartDto;
import com.store.book.dto.CartItemDto;
import com.store.book.model.Customer;
import com.store.book.model.Order;
import com.store.book.model.OrderItem;
import com.store.book.repository.OrderItemsRepository;
import com.store.book.repository.OrderRepository;
import com.store.book.service.CartService;
import com.store.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Override
    public void placeOrder(Customer customer, String sessionId) {
        // first let get cart items for the user
        CartDto cartDto = cartService.listCartItems(customer);

        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setSessionId(sessionId);
        newOrder.setCustomer(customer);
        newOrder.setTotalPrice(cartDto.getTotalCost());
        orderRepository.save(newOrder);

        addOrderToOrderItem(cartItemDtoList, newOrder);
        //
        cartService.deleteUserCartItems(customer);
    }

    private void addOrderToOrderItem(List<CartItemDto> cartItemDtoList, Order newOrder) {
        for (CartItemDto cartItemDto : cartItemDtoList) {
            // create orderItem and save each one
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getBook().getPrice());
            orderItem.setBook(cartItemDto.getBook());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrders(newOrder);
            // add to order item list
            orderItemsRepository.save(orderItem);
        }
    }

    @Override
    public List<Order> listCustomerOrders(Integer customerId){
        return orderRepository.findAllByCustomer(customerId);

    }
}
