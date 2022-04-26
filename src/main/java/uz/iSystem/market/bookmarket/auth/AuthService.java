package uz.iSystem.market.bookmarket.auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.iSystem.market.bookmarket.auth.mail.MailSenderService;
import uz.iSystem.market.bookmarket.exception.ApiRequestException;
import uz.iSystem.market.bookmarket.user.User;
import uz.iSystem.market.bookmarket.user.UserDto;
import uz.iSystem.market.bookmarket.user.UserRepository;
import uz.iSystem.market.bookmarket.user.userRole.UserRole;
import uz.iSystem.market.bookmarket.user.userRole.UserRoleService;
import uz.iSystem.market.bookmarket.util.JwtTokenUtil;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Value("${content.text}")
    private String contentText;

    @Value("${mail.send.address}")
    private String address;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRoleService userRoleService;
    private final MailSenderService mailSenderService;

    public AuthService(UserRepository userRepository,
                       JwtTokenUtil jwtTokenUtil,
                       UserRoleService userRoleService,
                       MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRoleService = userRoleService;
        this.mailSenderService = mailSenderService;
    }

    public void register(RegistrationDto dto){

        if (!dto.getPassword().equals(dto.getCheckPassword())){
            throw new ApiRequestException("Password invalid!");
        }

        User user = new User();
        user.setContact(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setPassword(PasswordService.generateMD5(dto.getPassword()));
        user.setStatus("NOT_ACTIVE");
        user.setCreated_date(LocalDateTime.now());
        UserRole userRole = userRoleService.getEntityByName("ROLE_USER");
        user.setUserRoleId(userRole.getId());
        userRepository.save(user);

        String link = address + jwtTokenUtil.generateToken(user);
        String content = contentText + " " + link;

        try{
            mailSenderService.sendMail(content, dto.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            userRepository.delete(user);
        }
    }

    public User verification(String token){

        Optional<User> optionalUser = userRepository.getByIdAndDeleted_dateIsNull(Integer.valueOf(jwtTokenUtil.getId(token)));

        if (optionalUser.isEmpty()){
            throw new ApiRequestException("Verification failed!");
        }
        User user = optionalUser.get();
        user.setStatus("ACTIVE");
        userRepository.save(user);
        return user;
    }

    public UserDto auth(AuthDto dto){

        String phone = dto.getContact();
        String password = PasswordService.generateMD5(dto.getPassword());

        Optional<User> optionalUser = userRepository.authorize(phone, password);

        if (optionalUser.isEmpty()){
            throw new ApiRequestException("User not found!");
        }
        User user = optionalUser.get();
        UserDto userDto = new UserDto();
        userDto.setContact(user.getContact());
        userDto.setToken(jwtTokenUtil.generateToken(user));
        return userDto;
    }
}
