package uz.iSystem.market.bookmarket.security;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.iSystem.market.bookmarket.user.User;
import java.util.Collection;
import java.util.List;

@Component @AllArgsConstructor @NoArgsConstructor
public class CustomUserDetail implements UserDetails {

    private Integer id;
    private String contact;
    private String password;
    private String status;
    private List<GrantedAuthority> authorityList;

    public CustomUserDetail(User user){
        this.id = user.getId();
        this.contact = user.getContact();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.authorityList = List.of(new SimpleGrantedAuthority(user.getUserRole().getName()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return contact;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (password.equals("ACTIVE")){
            return true;
        }
        return false;
    }

    public Integer getId(){
        return id;
    }

    @Override
    public String toString() {
        return "CustomUserDetail{" +
                "password='" + password + '\'' +
                ", phone='" + contact + '\'' +
                ", status=" + status +
                ", authorityList=" + authorityList +
                '}';
    }
}
