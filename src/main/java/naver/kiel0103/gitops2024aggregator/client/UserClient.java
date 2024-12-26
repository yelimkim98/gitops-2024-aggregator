package naver.kiel0103.gitops2024aggregator.client;

import lombok.RequiredArgsConstructor;
import naver.kiel0103.gitops2024aggregator.client.dto.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    @Value("${domains.user}")
    private String userUrl;

    public Map<Long, UserResponse> getUsers() {
        Map<Long, UserResponse> response = restTemplate.getForObject(userUrl + "/users", Map.class);

        if (response == null) {
            return Collections.emptyMap();
        }
        return response;
    }

    public Optional<UserResponse> getUser(String username, String password) {
        String url = String.format("%s/user?username=%s&pw=%s", userUrl, username, password);
        UserResponse response = restTemplate.getForObject(url, UserResponse.class);

        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response);
    }
}
