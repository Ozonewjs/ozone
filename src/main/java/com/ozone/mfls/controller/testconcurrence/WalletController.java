package com.ozone.mfls.controller.testconcurrence;

import com.ozone.mfls.beans.AccountWallet;
import com.ozone.mfls.server.AccountWalletService;
import com.ozone.mfls.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
/**
 * @ClassName WalletController
<<<<<<< Updated upstream
 * @Description 钱包账户操作
 * @Author Ozone
 * @Date 2019/5/26 0026 16:17
=======
 * @Author Ozone
 * @Description
 * @Date 2019/5/27 15:36
>>>>>>> Stashed changes
 * @Version 1.0
 **/
@RestController
public class WalletController {
    private Logger logger = Logger.getLogger(WalletController.class);
    @Autowired
    private AccountWalletService accountWalletService;
    @RequestMapping(value = "/walleroptimisticlock", method = RequestMethod.POST)
    public String walleroptimisticlock(HttpServletRequest request) {

        String result = "";

        try {
            /* 用户唯一编号*/
            String openId = request.getParameter("openId") == null ? null
                    : request.getParameter("openId").trim();
            /* 1:代表增加，2：代表减少*/
            String openType = request.getParameter("openType") == null ? null
                    : request.getParameter("openType").trim();
            /* 金额*/
            String amount = request.getParameter("amount") == null ? null
                    : request.getParameter("amount").trim();

            if (StringUtils.isEmpty(openId)) {
                logger.info("openId为空");
                return "openId is null";
            }
            if (StringUtils.isEmpty(openType)) {
                return "openType is null";
            }
            if (StringUtils.isEmpty(amount)) {
                return "amount is null";
            }
            AccountWallet wallet = accountWalletService.selectByOpenId(openId);

            // 用户操作金额
            BigDecimal cash = BigDecimal.valueOf(Double.parseDouble(amount));
            cash.doubleValue();
            cash.floatValue();
            if (Integer.parseInt(openType) == 1) {
                wallet.setUserAmount(wallet.getUserAmount().add(cash));
            } else if (Integer.parseInt(openType) == 2) {
                wallet.setUserAmount(wallet.getUserAmount().subtract(cash));
            }

            int target = accountWalletService.updateAccountWallet(wallet);
            System.out.println("修改用户金额是否：" + (target == 1 ? "成功" : "失败"));

        } catch (Exception e) {
            result = e.getMessage();
            return result;
        }

        return "success";
    }

}
