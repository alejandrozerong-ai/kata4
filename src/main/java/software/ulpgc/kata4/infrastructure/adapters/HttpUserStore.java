package software.ulpgc.kata4.infrastructure.adapters;

import com.google.gson.Gson;
import software.ulpgc.kata4.application.domain.User;
import software.ulpgc.kata4.application.dtos.UserDto;
import software.ulpgc.kata4.application.ports.UserStore;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class HttpUserStore implements UserStore {

    private final String baseUrl;
    private final HttpClient client;
    private final Gson gson;

    public HttpUserStore(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @Override
    public Optional<User> get(String name) {
        try {
            String url = baseUrl + "?name=" + name;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("[HTTP Error] No success with status code " + response.statusCode());
                return Optional.empty();
            }

            UserDto dto = gson.fromJson(response.body(), UserDto.class);
            if (dto == null || dto.name == null) {
                System.err.println("[DATA Error]  No valid service response.");
                return Optional.empty();
            }

            return Optional.of(new User(dto.name, dto.age, dto.gender != null ? dto.gender : "unknown"));

        } catch (Exception e) {
            System.err.println("[NETWORK Error] Could not connect to service " + e.getMessage());
            return Optional.empty();
        }
    }

}
