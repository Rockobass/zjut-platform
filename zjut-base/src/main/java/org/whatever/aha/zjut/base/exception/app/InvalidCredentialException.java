package org.whatever.aha.zjut.base.exception.app;

import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;

/**
 * @author GuiYi Yang
 */
public class InvalidCredentialException extends AppException {

    public InvalidCredentialException() {
        super(ErrorCode.INVALID_CREDENTIAL, null);
    }
}
