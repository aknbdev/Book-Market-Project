package uz.iSystem.market.bookmarket.user;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.iSystem.market.bookmarket.exception.ApiRequestException;
import uz.iSystem.market.bookmarket.user.userRole.UserRoleService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    public UserService(ModelMapper mapper,
                       UserRepository userRepository, UserRoleService userRoleService) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    public UserDto create(UserDto userDto) {
        checkUniqueFields(userDto.getEmail(), userDto.getContact());
        User user = mapper.map(userDto, User.class);
        user.setCreated_date(LocalDateTime.now());
        userRepository.save(user);
        userDto.setStatus(user.getStatus());
        setPasswordNull(userDto);
        return userDto;
    }

    public List<UserDto> getAll() {

        List<UserDto> userDtoList = userRepository
                .getAllByDeletedDateIsNull()
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        if (!userDtoList.isEmpty()){

            for (UserDto userDto : userDtoList){
                setPasswordNull(userDto);
                setUserRole(userDto);
            }
            return userDtoList;
        }
        throw new ApiRequestException("Users not found!");
    }

    public UserDto getOne(Integer id) {

        UserDto userDto = mapper.map(getEntity(id), UserDto.class);
        setPasswordNull(userDto);
        setUserRole(userDto);
        return userDto;
    }

    public UserDto update(Integer id, UserDto userDto) {

        User user = getEntity(id);
        User user1 = mapper.map(userDto, User.class);
        user1.setId(id);
        user1.setCreated_date(user.getCreated_date());
        user1.setUpdated_date(LocalDateTime.now());
        userRepository.save(user1);
        userDto = mapper.map(user1, UserDto.class);
        setPasswordNull(userDto);
        setUserRole(userDto);
        return userDto;
    }

    public void delete(Integer id) {

        User user = getEntity(id);
        user.setDeleted_date(LocalDateTime.now());
        userRepository.save(user);
    }


    // |- Secondary functions -|


    public void checkUniqueFields(String email, String contact){
        // DONE: check all of this fields
        Optional<User> optionalUser = userRepository.getByEmailOrContactAndDeleted_dateIsNull(email, contact);
        if (optionalUser.isPresent()){
            throw new ApiRequestException("User already exist");
        }
    }

    public void setUserRole(UserDto userDto){
        userDto.setUserRole(userRoleService.getOne(userDto.getUserRoleId()));
    }

    public User getEntity(Integer id){
        Optional<User> optionalUser = userRepository.getByIdAndDeleted_dateIsNull(id);
        if (optionalUser.isEmpty()){
            throw new ApiRequestException("User not found");
        }
        return optionalUser.get();
    }

    public User getByPhone(String phone){

        Optional<User> optionalUser = userRepository.getByPhoneAndDeletedAtIsNull(phone);

        if (optionalUser.isEmpty()){
            throw new ApiRequestException("User not found with this Phone");
        }
        return optionalUser.get();
    }

    public void setPasswordNull(UserDto userDto){
        userDto.setPassword(null);
    }

}