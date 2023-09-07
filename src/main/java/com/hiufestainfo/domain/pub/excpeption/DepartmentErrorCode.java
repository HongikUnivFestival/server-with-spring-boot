package com.hiufestainfo.domain.pub.excpeption;

import com.hiufestainfo.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hiufestainfo.global.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum DepartmentErrorCode implements BaseErrorCode {

    DEPARTMENT_NOT_FOUND(NOT_FOUND, "DEPARTMENT_404", "해당 과가 존재하지 않습니다");
    private final int httpStatus;
    private final String code;
    private final String message;

}
