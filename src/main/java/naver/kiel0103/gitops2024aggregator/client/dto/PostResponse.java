package naver.kiel0103.gitops2024aggregator.client.dto;

import lombok.Getter;

@Getter
public class PostResponse {

    private Long id;
    private Long authorId;
    private String content;
}
