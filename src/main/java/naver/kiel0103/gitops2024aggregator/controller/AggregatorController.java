package naver.kiel0103.gitops2024aggregator.controller;

import lombok.RequiredArgsConstructor;
import naver.kiel0103.gitops2024aggregator.client.PostClient;
import naver.kiel0103.gitops2024aggregator.client.UserClient;
import naver.kiel0103.gitops2024aggregator.client.dto.PostRequest;
import naver.kiel0103.gitops2024aggregator.client.dto.PostResponse;
import naver.kiel0103.gitops2024aggregator.client.dto.UserResponse;
import naver.kiel0103.gitops2024aggregator.controller.dto.PostCreateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AggregatorController {

    private final UserClient userClient;
    private final PostClient postClient;

    @GetMapping("/users")
    public ResponseEntity<Collection<UserResponse>> getUsers() {
        Map<Long, UserResponse> response = userClient.getUsers();
        return ResponseEntity.ok(response.values());
    }

    @PostMapping("/post")
    public ResponseEntity<PostResponse> create(@RequestBody PostCreateRequest request) {
        Optional<UserResponse> userOptional = userClient.getUser(
                request.getUsername(), request.getPassword()
        );
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        PostResponse post = postClient.post(PostRequest.builder()
                .authorId(userOptional.get().getId())
                .content(request.getContent())
                .build());
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(post);
    }
}
