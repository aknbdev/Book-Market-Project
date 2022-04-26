package uz.iSystem.market.bookmarket.user.userRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-role")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    // |- DONE: create function -|
     @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserRoleDto userRoleDto){
        userRoleService.create(userRoleDto);
        return ResponseEntity.ok("Ok, User Role created.");
     }


    // |- DONE: getAll function -|
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        List<UserRoleDto> userRoleDtoList = userRoleService.getAll();
        return ResponseEntity.ok(userRoleDtoList);
    }


    // |- DONE: getOne function -|
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id){
        UserRoleDto userRoleDto = userRoleService.getOne(id);
        return ResponseEntity.ok(userRoleDto);
    }


    // |- DONE: update function -|
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody UserRoleDto userRoleDto){
        userRoleService.update(id, userRoleDto);
        return ResponseEntity.ok("Ok, Updated!");
    }


    // |- DONE: delete function -|
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        userRoleService.delete(id);
        return ResponseEntity.ok("Ok, User Role deleted!");
    }
}
