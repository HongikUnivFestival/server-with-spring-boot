package com.hiufestainfo.domain.user.entity;

import com.hiufestainfo.domain.user.exception.NotSupportedLoginTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginType {
    KAKAO("kakao"),
    GOOGLE("google");

    private final String value;

    public static LoginType fromValue(String value) {
        for (LoginType type : LoginType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new NotSupportedLoginTypeException();
    }
}
