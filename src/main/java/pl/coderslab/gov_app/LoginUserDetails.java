package pl.coderslab.gov_app;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.coderslab.gov_app.role.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class LoginUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private Boolean isDelete;
    private final Role role;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        this.authorities = grantedAuthorities;
    }

    public LoginUserDetails(String email, String password, Role role, Boolean isDelete) {
        this.email = email;
        this.password = password;
        this.role = role;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        this.authorities = grantedAuthorities;
        this.isDelete = isDelete;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

        if (isDelete != null) {
            return !isDelete;
        }
        return true;
    }
}
