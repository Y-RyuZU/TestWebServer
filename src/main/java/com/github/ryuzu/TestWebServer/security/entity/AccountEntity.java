package com.github.ryuzu.TestWebServer.security.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class AccountEntity {
    String DisplayName;
    String HashedPassword;
    int Roles;
}
