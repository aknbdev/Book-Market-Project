package uz.iSystem.market.bookmarket.order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    // |- DONE: create function -|
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.create(orderDto));
    }


    // |- DONE: getAll function -|
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }


    // |- DONE: getOne function -|
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderService.getOne(id));
    }


    // |- DONE: update function -|
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody OrderDto orderDto){

        return ResponseEntity.ok(orderService.update(id, orderDto));
    }


    // |- DONE: delete function -|
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        orderService.delete(id);
        return ResponseEntity.ok("Ok, Order deleted");
    }

}
