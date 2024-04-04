import com.example.sona.Controller.OrderController;
import com.example.sona.Entity.LineItems;
import com.example.sona.Model.CustomerRequestPOJO;
import com.example.sona.Model.FoodOrderPOJO;
import com.example.sona.Model.LineItemsRequests;
import com.example.sona.Model.PlaceFoodOrderRequest;
import com.example.sona.Repository.OrderRepository;
import com.example.sona.Service.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //    private FoodOrderPOJO placeOrder(){
//        List<LineItems> lineItems = new ArrayList<>();
//        FoodOrderPOJO foodOrder = new FoodOrderPOJO();
//        foodOrder.setOrderId("123");
//        foodOrder.setOrderStatus("ACCEPTED");
//        foodOrder.setCustomerName("Shankar");
//        foodOrder.setLineItems(lineItems);
//        return foodOrder;
//
//    }
    @Test
    public void testPlaceOrder_Positive() {
        FoodOrderPOJO mockResponse = new FoodOrderPOJO();
        mockResponse.setOrderId("123");
        mockResponse.setOrderStatus("ACCEPTED");
        mockResponse.setCustomerName("Shankar");
        Mockito.when(orderService.placeOrder(any(PlaceFoodOrderRequest.class)))
                .thenReturn(mockResponse);

        PlaceFoodOrderRequest request = new PlaceFoodOrderRequest();

        CustomerRequestPOJO customerRequest = new CustomerRequestPOJO();
        customerRequest.setCustomerId("123");
        customerRequest.setCustomerName("Deepak");
        request.setCustomerRequest(customerRequest);

        List<LineItemsRequests> lineItemsRequests = new ArrayList<>();
        LineItemsRequests lineItem = new LineItemsRequests();
        lineItem.setItemTitle("Momo");
        lineItem.setPrice(14.99f);
        lineItemsRequests.add(lineItem);
        request.setLineItemsRequests(lineItemsRequests);

        //Calling Controller
        ResponseEntity<FoodOrderPOJO> responseEntity = orderController.placeOrder(request);

        //Asserting
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        Assert.assertEquals(mockResponse, responseEntity.getBody());

    }
}
