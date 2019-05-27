package com.ozone.mfls.server;

import com.ozone.mfls.beans.AccountWallet;
import com.ozone.mfls.mapper.AccountWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountWalletService {
    @Autowired
    private AccountWalletMapper  accountWalletMapper;
    public AccountWallet selectByOpenId(String openId) {
        return accountWalletMapper.selectByOpenId(openId);
    }

    public int updateAccountWallet(AccountWallet record) {
        return accountWalletMapper.updateAccountWallet(record);
    }
}
