package org.whatever.aha.zjut.base.exception.app;

import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;

/**
 * @author GuiYi Yang
 */
public class InvalidVerifyingCodeException extends AppException {
    public InvalidVerifyingCodeException() {
        super(ErrorCode.INVALID_VERIFYING_CODE, null);
    }
}
