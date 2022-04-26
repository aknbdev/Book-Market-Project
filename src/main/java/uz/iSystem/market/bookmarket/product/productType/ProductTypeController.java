package uz.iSystem.market.bookmarket.product.productType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-type")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    // |- DONE: create function -|
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductTypeDto productTypeDto){
        return ResponseEntity.ok(productTypeService.create(productTypeDto));
    }


    // |- DONE: getAll function -|
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productTypeService.getAll());
    }


    // |- DONE: getOne function -|
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productTypeService.getOne(id));
    }


    // |- DONE: update function -|
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody ProductTypeDto productTypeDto){
        return ResponseEntity.ok(productTypeService.update(id, productTypeDto));
    }


    // |- DONE: delete function -|
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        productTypeService.delete(id);
        return ResponseEntity.ok("Ok, Product Type deleted!");
    }
}
