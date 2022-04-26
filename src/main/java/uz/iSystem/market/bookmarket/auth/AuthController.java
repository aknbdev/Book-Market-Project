package uz.iSystem.market.bookmarket.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto dto){
        return ResponseEntity.ok(authService.auth(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDto dto){
        authService.register(dto);
        return ResponseEntity.ok("Check your email, and confirm!");
    }

    @GetMapping("/validation/{token}")
    public ResponseEntity<?> validation(@PathVariable("token") String token){
        return ResponseEntity.ok(authService.verification(token));
    }
}
