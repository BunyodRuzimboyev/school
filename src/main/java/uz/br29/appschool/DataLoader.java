package uz.br29.appschool;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.br29.appschool.enums.Role;
import uz.br29.appschool.enums.UserType;
import uz.br29.appschool.security.User;
import uz.br29.appschool.security.UserRepository;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            UUID uuid = UUID.randomUUID();
            User superAdmin = User.builder()
                    .username("superadmin")
                    .password(passwordEncoder.encode("123456"))
                    .role(Role.SUPER_ADMIN)
                    .userType(UserType.NOBODY)
                    .firstName("")
                    .lastName("")
                    .middleName("")
                    .imageId(uuid)
                    .identificationId(uuid)
                    .addressId(uuid)
                    .birthDate(LocalDate.now())
                    .phone("+99888 888 88 88")
                    .build();
            userRepository.save(superAdmin);
            System.err.println("Super admin has created");

        } catch (Exception exception) {
//            throw new RuntimeException("Error within data loader");
        }
    }
}
