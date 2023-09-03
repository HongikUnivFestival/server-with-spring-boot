package com.hiufestainfo.global.constant;

public class StaticValue {
    /* 에러 코드 static 값 */
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int CONFLICT = 409;
    public static final int INTERNAL_SERVER_ERROR = 500;

    /* token type */
    public static final String ACCESS_TOKEN = "AccessToken";
    public static final String REFRESH_TOKEN = "RefreshToken";

    public static final String KAKAO_PUBLIC_KEYS = "kakaoPublicKeys";

    public static final String REFRESH_TOKEN_KEY = "refreshToken";
    public static final int REFRESH_TOKEN_EXPIRE_SEC = 1209600;

}
