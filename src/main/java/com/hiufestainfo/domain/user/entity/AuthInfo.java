package com.hiufestainfo.domain.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static com.hiufestainfo.domain.user.entity.AccountStatus.MEMBER;
import static com.hiufestainfo.domain.user.entity.Role.GUEST;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class AuthInfo {

    @Enumerated(EnumType.STRING)
    private LoginType loginType;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static AuthInfo authInfoForSignUp(LoginType loginType, String email) {
        return AuthInfo.builder()
                .loginType(loginType)
                .email(email)
                .accountStatus(MEMBER)
                .role(GUEST)
                .build();
    }
}
