package software.ulpgc.kata4.application.ports;

import software.ulpgc.kata4.application.domain.User;

import java.util.Optional;

public interface UserStore {
    Optional<User> get(String username);
}
