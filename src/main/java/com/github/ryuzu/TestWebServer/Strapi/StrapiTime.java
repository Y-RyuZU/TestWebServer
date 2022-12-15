package com.github.ryuzu.TestWebServer.Strapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@Getter
@AllArgsConstructor
public class StrapiTime {
    Date createAt;
    Date updateAt;
    Date publishedAt;
}
