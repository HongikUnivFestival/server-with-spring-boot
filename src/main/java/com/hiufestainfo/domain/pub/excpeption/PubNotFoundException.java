package com.hiufestainfo.domain.pub.excpeption;

import com.hiufestainfo.domain.example.exception.ExampleErrorCode;
import com.hiufestainfo.global.exception.base.BaseException;

public class PubNotFoundException  extends BaseException {
    public PubNotFoundException() {
        super(PubErrorCode.PUB_NOT_FOUND);
    }
}
