package com.tcp.tcp.vo.send.vo;

import com.tcp.bean.MUpgradeBlacklist;
import com.tcp.tcp.base.message.BaseMessageBody;
import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

/**
 *  0x0115 运营平台下发/更新⿊名单
 *
 * @author yorking
 */
public class SendBlacklistVo extends MUpgradeBlacklist implements BaseMessageBody {

    /**
     * ftp地址长度
     */
    @Data(order = 1, byteType = DataByteTypEnum.Number)
    private Integer ftpUrlLength;

    /**
     * 黑名单ftp地址
     */
    @Data(order = 2, byteType = DataByteTypEnum.ASCII, byteLenAttr = "ftpUrlLength")
    private String ftpUrl;

    /**
     * ftp帐号
     */
    @Data(order = 3, byteType = DataByteTypEnum.ASCII, byteLen = 8)
    private String ftpAccount;

    /**
     * ftp密码
     */
    @Data(order = 4, byteType = DataByteTypEnum.ASCII, byteLen = 8)
    private String ftpPassword;

    public Integer getFtpUrlLength() {
        return ftpUrlLength;
    }

    public void setFtpUrlLength(Integer ftpUrlLength) {
        this.ftpUrlLength = ftpUrlLength;
    }

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    public String getFtpAccount() {
        return ftpAccount;
    }

    public void setFtpAccount(String ftpAccount) {
        this.ftpAccount = ftpAccount;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }
}
