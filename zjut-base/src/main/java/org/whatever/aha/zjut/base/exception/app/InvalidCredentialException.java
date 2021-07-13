package org.whatever.aha.zjut.base.exception.app;

import org.whatever.aha.zjut.base.consts.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;

public class InvalidCredentialException extends AppException {

    public InvalidCredentialException() {
        super(ErrorCode.INVALID_CREDENTIAL, null);
    }
}
