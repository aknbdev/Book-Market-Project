package uz.iSystem.market.bookmarket.order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.iSystem.market.bookmarket.exception.ApiRequestException;
import uz.iSystem.market.bookmarket.user.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final ModelMapper mapper;
    private final UserService userService;
    private final OrderRepository orderRepository;

    public OrderService(ModelMapper mapper, UserService userService,
                        OrderRepository orderRepository) {
        this.mapper = mapper;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    public OrderDto create(OrderDto orderDto) {

        userService.getEntity(orderDto.getUserId());
        Order order = mapper.map(orderDto, Order.class);
        order.setCreated_date(LocalDateTime.now());
        order.setStatus("Active");
        orderRepository.save(order);
        orderDto = mapper.map(order, OrderDto.class);
        setUser(orderDto);
        return orderDto;
    }

    public List<OrderDto> getAll() {

        List<OrderDto> orderDtoList = orderRepository.getAllByDeleted_dateIsNull()
                .stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
        if (!orderDtoList.isEmpty()){
            for (OrderDto orderDto : orderDtoList){
                setUser(orderDto);
            }
            return orderDtoList;
        }
        throw new ApiRequestException("Orders not found!");
    }

    public OrderDto getOne(Integer id) {

        OrderDto orderDto = mapper.map(getEntity(id), OrderDto.class);
        setUser(orderDto);
        return orderDto;
    }

    public OrderDto update(Integer id, OrderDto orderDto) {

        userService.getEntity(orderDto.getUserId());
        Order order = getEntity(id);
        Order order1 = mapper.map(orderDto, Order.class);
        order1.setId(id);
        order1.setCreated_date(order.getCreated_date());
        order1.setStatus(order.getStatus());
        order1.setUpdated_date(LocalDateTime.now());
        orderRepository.save(order1);
        orderDto = mapper.map(order1, OrderDto.class);
        setUser(orderDto);
        return orderDto;
    }

    public void delete(Integer id) {

        Order order = getEntity(id);
        order.setDeleted_date(LocalDateTime.now());
        orderRepository.save(order);
    }


    // |- Secondary functions -|

    public void setUser(OrderDto orderDto){
        orderDto.setUser(userService.getOne(orderDto.getUserId()));
    }

    public Order getEntity(Integer id){
        Optional<Order> optionalOrder = orderRepository.getByIdAndDeleted_dateIsNull(id);

        if (optionalOrder.isEmpty()){
            throw new ApiRequestException("Order not found!");
        }
        return optionalOrder.get();
    }

}