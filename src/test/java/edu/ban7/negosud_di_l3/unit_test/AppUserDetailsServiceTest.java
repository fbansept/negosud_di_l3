package edu.ban7.negosud_di_l3.unit_test;

import edu.ban7.negosud_di_l3.mock.MockUtilisateurDao;
import edu.ban7.negosud_di_l3.security.AppUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsServiceTest {

    @Test
    void callLoadUserByUsernameWithExistingUser_shouldReturnUserDetails() {
        AppUserDetailsService service = new AppUserDetailsService(new MockUtilisateurDao());
        Assertions.assertNotNull(service.loadUserByUsername("a@a.com"));
    }

    @Test
    void callLoadUserByUsernameWithNotExistingUser_shouldThrowException() {
        AppUserDetailsService service = new AppUserDetailsService(new MockUtilisateurDao());
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> service.loadUserByUsername("z@z.com"));
    }

}
