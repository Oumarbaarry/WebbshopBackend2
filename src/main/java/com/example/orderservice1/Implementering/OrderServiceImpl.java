package com.example.orderservice1.Implementering;
import com.example.orderservice1.models.CreateOrder;
import com.example.orderservice1.models.Items;
import com.example.orderservice1.models.Orders;
import com.example.orderservice1.repository.OrderRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class OrderServiceImpl {

   // private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepo orderRepository;

    RestTemplate restTemplate = new RestTemplate();

    /*@Value("${customerservice.url}")
    String customerUrl;

    @Value("${itemservice.url}")
    String itemUrl;*/

    public OrderServiceImpl(OrderRepo orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public ResponseEntity<Object> OrderById(long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Order not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> getAllOrdersWithItems() {
        List<Orders> orders = orderRepository.findAll();
        List<Map<String, Object>> ordersList = new ArrayList<>();
        for (Orders order : orders) {
            List<Long> itemIds = order.getItems();
            List<Items> items = new ArrayList<>();
            for (Long itemId : itemIds) {
                ResponseEntity<Items> response = restTemplate.getForEntity("http://localhost:8082/items/" + itemId, Items.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    items.add(response.getBody());
                }
            }
            Map<String, Object> orderData = new HashMap<>();
            orderData.put("order", order);
            orderData.put("items", items);
            ordersList.add(orderData);
        }
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    public ResponseEntity<Object> buyItem(CreateOrder createOrder) {

        boolean customerExists = CheckIfCustomerExist(createOrder.getCustomerId());
        if (!customerExists) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Customer not found"), HttpStatus.NOT_FOUND);
        }


        List<Long> itemIds = createOrder.getItemIds();
        List<Items> items = new ArrayList<>();
        List<String> notFoundItems = new ArrayList<>();
        for (Long itemId : itemIds) {
            ResponseEntity<Items> response = restTemplate.getForEntity("http://localhost:8082/items/" + itemId, Items.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                items.add(response.getBody());
            } else {
                notFoundItems.add("Product not found with id: " + itemId);
            }
        }
        if (!notFoundItems.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("error", notFoundItems), HttpStatus.NOT_FOUND);
        }


        Orders order = new Orders(createOrder.getOrderDate(), createOrder.getCustomerId(), createOrder.getItemIds());
        orderRepository.save(order);


        Map<String, Object> responseData = new HashMap<>();
        responseData.put("order", order);
        responseData.put("items", items);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    private boolean CheckIfCustomerExist(long customerId) {
        try {
            ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity("http://localhost:8081/customers/" + customerId, Boolean.class);
            return Boolean.TRUE.equals(responseEntity.getBody());
        } catch (Exception ex) {
            return false;
        }
    }
}
