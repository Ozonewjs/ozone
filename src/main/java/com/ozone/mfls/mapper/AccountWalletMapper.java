package com.ozone.mfls.mapper;

import com.ozone.mfls.beans.AccountWallet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * @ClassName aQ
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/26 0026 16:17
 * @Version 1.0
 **/
@Mapper
@Repository
public interface AccountWalletMapper {
    /**
     * @Author Ozone
     * @Description 根据OpenId查询账户
     * @Date 16:29 2019/5/26 0026
     * @Param 支付密码开启状态
     * @return com.ozone.mfls.beans.AccountWallet
     **/
    AccountWallet selectByOpenId(String openId);
    /**
     * 更新账户
     * @Author Ozone
     * @Description 更新账户
     * @Date 16:31 2019/5/26 0026
     * @Param [record]账户数据
     * @return int
     **/
    int updateAccountWallet(AccountWallet record);

}
