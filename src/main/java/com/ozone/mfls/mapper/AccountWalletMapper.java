package com.ozone.mfls.mapper;

import com.ozone.mfls.beans.AccountWallet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountWalletMapper {
    AccountWallet selectByOpenId(String openId);

    int updateAccountWallet(AccountWallet record);

}
