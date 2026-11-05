package software.ulpgc.kata4;

import software.ulpgc.kata4.application.usecases.GetUserUseCase;
import software.ulpgc.kata4.infrastructure.adapters.HttpUserStore;

public class Main {
    public static void main(String[] args) {
        String baseUrl = "https://api.agify.io";
        GetUserUseCase useCase = new GetUserUseCase(new HttpUserStore(baseUrl));

        System.out.println("=== 1. Test with correct a response ===");
        useCase.execute("Carlos").ifPresentOrElse(
                user -> System.out.println("User succesfully obtained -> " + user),
                () -> System.out.println("Couldn't obtain user.")
        );

        System.out.println("=== 2. Test with error controled response ===");
        GetUserUseCase useCase2 = new GetUserUseCase(new HttpUserStore("https://api-invalid-12345.io"));
        useCase2.execute("Carlos");
    }
}
