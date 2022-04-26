package uz.iSystem.market.bookmarket.orderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // |- DONE: create function -|
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.ok(orderItemService.create(orderItemDto));
    }


    // |- DONE: getAll function -|
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderItemService.getAll());
    }


    // |- DONE: getOne function -|
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderItemService.getOne(id));
    }


    // |- DONE: update function -|
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.ok(orderItemService.update(id, orderItemDto));
    }


    // |- DONE: delete function -|
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Integer id){
        orderItemService.delete(id);
        return ResponseEntity.ok("Ok, Order Item deleted!");
    }
}
