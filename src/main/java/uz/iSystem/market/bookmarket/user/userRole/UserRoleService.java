package uz.iSystem.market.bookmarket.user.userRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.iSystem.market.bookmarket.exception.ApiRequestException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private final ModelMapper mapper;
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(ModelMapper mapper,
                           UserRoleRepository userRoleRepository) {
        this.mapper = mapper;
        this.userRoleRepository = userRoleRepository;
    }

    public void create(UserRoleDto userRoleDto) {
        UserRole userRole = mapper.map(userRoleDto, UserRole.class);
        userRole.setCreated_date(LocalDateTime.now());
        userRole.setStatus("Active");
        userRoleRepository.save(userRole);
    }

    public List<UserRoleDto> getAll() {
        List<UserRoleDto> userRoleDtoList = userRoleRepository.getAllByDeletedDateIsNull()
                .stream()
                .map(userRole -> mapper.map(userRole, UserRoleDto.class))
                .collect(Collectors.toList());

        if (userRoleDtoList.isEmpty()){
            throw new ApiRequestException("There is no user role!");
        }
        return userRoleDtoList;
    }

    public UserRoleDto getOne(Integer id) {
        return mapper.map(getEntity(id), UserRoleDto.class);
    }


    public void update(Integer id, UserRoleDto userRoleDto) {
        UserRole userRole = getEntity(id);
        UserRole userRole1 = mapper.map(userRoleDto, UserRole.class);
        userRole1.setId(userRole.getId());
        userRole1.setStatus(userRole.getStatus());
        userRole1.setCreated_date(userRole.getCreated_date());
        userRole1.setUpdated_date(userRole.getUpdated_date());
        userRoleRepository.save(userRole1);
    }

    public void delete(Integer id) {
        UserRole userRole = getEntity(id);
        userRole.setDeleted_date(LocalDateTime.now());
        userRoleRepository.save(userRole);
    }


    // |- Secondary functions -|

    public UserRole getEntity(Integer id){
        Optional<UserRole> userRole = userRoleRepository.findByIdAndDeleted_dateIsNull(id);

        if (userRole.isEmpty()){
            throw new ApiRequestException("UserRole not found!");
        }

        return userRole.get();
    }

    public UserRole getEntityByName(String name){
        Optional<UserRole> optionalUserRole = userRoleRepository.getBynameAndDeletedDateIsNull(name);

        if (optionalUserRole.isEmpty()){
            throw new ApiRequestException("USer Role not found!");
        }
        return optionalUserRole.get();
    }

}