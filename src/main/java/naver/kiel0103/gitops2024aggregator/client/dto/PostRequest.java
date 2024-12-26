package naver.kiel0103.gitops2024aggregator.client.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostRequest {

    private Long authorId;
    private String content;
}
