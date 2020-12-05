package pl.coderslab.gov_app;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.gov_app.councilman.Councilman;
import pl.coderslab.gov_app.councilman.CouncilmanService;
import pl.coderslab.gov_app.superadmin.SuperAdmin;
import pl.coderslab.gov_app.superadmin.SuperAdminService;

@Service
@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final SuperAdminService superAdminService;
    private final CouncilmanService councilmanService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{

        SuperAdmin superAdmin = superAdminService.findByEmail(s);

        if (superAdmin != null) {
            return new LoginUserDetails(superAdmin.getEmail(), superAdmin.getPassword(), superAdmin.getRole());
        } else {
            Councilman councilman = councilmanService.getByEmail(s);
            if (councilman != null) {
                return new LoginUserDetails(councilman.getEmail(), councilman.getPassword(), councilman.getRole(), councilman.getIsDelete());
            }
        } throw new UsernameNotFoundException("Nie znaleziono użytkownika!");
    }

}
