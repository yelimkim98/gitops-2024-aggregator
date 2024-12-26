package naver.kiel0103.gitops2024aggregator.client;

import lombok.RequiredArgsConstructor;
import naver.kiel0103.gitops2024aggregator.client.dto.PostRequest;
import naver.kiel0103.gitops2024aggregator.client.dto.PostResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PostClient {

    private final RestTemplate restTemplate;

    @Value("${domains.post}")
    private String postUrl;

    public PostResponse post(PostRequest postRequest) {
        return restTemplate.postForObject(postUrl + "/post", postRequest, PostResponse.class);
    }
}
