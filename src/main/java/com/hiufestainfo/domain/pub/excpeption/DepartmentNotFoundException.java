package com.hiufestainfo.domain.pub.excpeption;

import com.hiufestainfo.global.exception.base.BaseException;

public class DepartmentNotFoundException extends BaseException {
    public DepartmentNotFoundException(){super(DepartmentErrorCode.DEPARTMENT_NOT_FOUND);};
}
