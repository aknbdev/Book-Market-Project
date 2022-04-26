package uz.iSystem.market.bookmarket.orderItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.iSystem.market.bookmarket.exception.ApiRequestException;
import uz.iSystem.market.bookmarket.order.OrderService;
import uz.iSystem.market.bookmarket.product.ProductService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderService orderService;
    private final ProductService productService;
    private final ModelMapper mapper;
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderService orderService,
                            ProductService productService,
                            ModelMapper mapper,
                            OrderItemRepository orderItemRepository) {
        this.orderService = orderService;
        this.productService = productService;
        this.mapper = mapper;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItemDto create(OrderItemDto orderItemDto) {

        orderService.getEntity(orderItemDto.getOrderId());
        productService.getEntity(orderItemDto.getProductId());
        OrderItem orderItem = mapper.map(orderItemDto, OrderItem.class);
        orderItem.setCreated_date(LocalDateTime.now());
        orderItem.setStatus("Active");
        orderItemRepository.save(orderItem);
        orderItemDto = mapper.map(orderItem, OrderItemDto.class);
        setFields(orderItemDto);
        return orderItemDto;
    }

    public List<OrderItemDto> getAll() {

        List<OrderItemDto> orderItemDtoList = orderItemRepository
                .getAllByDeleted_dateIsNull()
                .stream()
                .map(orderItem -> mapper.map(orderItem, OrderItemDto.class))
                .collect(Collectors.toList());

        if (!orderItemDtoList.isEmpty()){

            for (OrderItemDto orderItemDto : orderItemDtoList){
                setFields(orderItemDto);
            }
            return orderItemDtoList;
        }
        throw new ApiRequestException("Order Items not found!");
    }

    public OrderItemDto getOne(Integer id) {

        OrderItemDto orderItemDto = mapper.map(getEntity(id), OrderItemDto.class);
        setFields(orderItemDto);
        return orderItemDto;
    }

    public OrderItemDto update(Integer id, OrderItemDto orderItemDto) {

        OrderItem orderItem = getEntity(id);
        OrderItem orderItem1 = mapper.map(orderItemDto, OrderItem.class);
        orderItem1.setCreated_date(orderItem.getCreated_date());
        orderItem1.setStatus(orderItem.getStatus());
        orderItem1.setUpdated_date(LocalDateTime.now());
        orderItemRepository.save(orderItem1);
        orderItemDto = mapper.map(orderItem1, OrderItemDto.class);
        setFields(orderItemDto);
        return orderItemDto;
    }

    public void delete(Integer id) {

        OrderItem orderItem = getEntity(id);
        orderItem.setDeleted_date(LocalDateTime.now());
        orderItemRepository.save(orderItem);
    }



    // |- Secondary functions -|

    private OrderItem getEntity(Integer id) {
        Optional<OrderItem> orderItem = orderItemRepository.getByIdAndDeleted_dateIsNull(id);

        if (orderItem.isEmpty()){
            throw new ApiRequestException("Order Item not found!");
        }
        return orderItem.get();
    }

    public void setFields(OrderItemDto orderItemDto){
        orderItemDto.setOrder(orderService.getOne(orderItemDto.getOrderId()));
        orderItemDto.setProduct(productService.getOne(orderItemDto.getProductId()));
    }

}
