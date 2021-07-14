package org.whatever.aha.zjut.base.exception.app;

import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;

public class AccountBlockedException extends AppException {
    public AccountBlockedException(String accountId) {
        super(ErrorCode.ACCOUNT_BLOCKED, accountId);
    }
}
