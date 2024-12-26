package naver.kiel0103.gitops2024aggregator.controller.dto;

import lombok.Getter;

@Getter
public class PostCreateRequest {

    private String username;
    private String password;
    private String content;
}
