package software.ulpgc.kata4.application.usecases;

import software.ulpgc.kata4.application.domain.User;
import software.ulpgc.kata4.application.ports.UserStore;

import java.util.Optional;

public class GetUserUseCase {
    private final UserStore userStore;
    public GetUserUseCase(UserStore userStore) {
        this.userStore = userStore;
    }

    public Optional<User> execute(String username) {
        return userStore.getUser(username);
    }
}
