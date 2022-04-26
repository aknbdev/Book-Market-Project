package uz.iSystem.market.bookmarket.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // |- DONE: create function -|
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.create(productDto));
    }


    // |- DONE: getAll function -|
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }


    // |- DONE: getOne function -|
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productService.getOne(id));
    }


    // |- DONE: update function -|
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.update(id, productDto));
    }


    // |- DONE: delete function -|
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return ResponseEntity.ok("Ok, Product deleted!");
    }
}
