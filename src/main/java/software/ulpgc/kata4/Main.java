package software.ulpgc.kata4;

import software.ulpgc.kata4.application.usecases.GetUserUseCase;
import software.ulpgc.kata4.infrastructure.adapters.HttpUserStore;

public class Main {
    public static void main(String[] args) {

        String baseUrl = "https://api.agify.io";
        GetUserUseCase useCase = new GetUserUseCase(new HttpUserStore(baseUrl));

        System.out.println("=== 1. Test with correct response ===");
        useCase.execute("michael").ifPresentOrElse(
                user -> System.out.println("User succesfully obtained -> " + user),
                () -> System.out.println("Could not obtain user.")
        );

        System.out.println("\n=== 2. Test with error controled (Unavailable Service / no valid URL) ===");
        GetUserUseCase faultyUseCase = new GetUserUseCase(new HttpUserStore("https://api-invalid-12345.io"));
        faultyUseCase.execute("michael");
    }
}
