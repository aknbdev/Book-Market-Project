package uz.iSystem.market.bookmarket.user;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // |- DONE: create function -|
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.create(userDto));
    }


    // |- DONE: getAll function -|
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }


    // |- DONE: getOne function -|
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.getOne(id));
    }


    // |- DONE: update function -|
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.update(id, userDto));
    }


    // |- DONE: delete function -|
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        userService.delete(id);
        return ResponseEntity.ok("Ok, User deleted!");
    }
}
