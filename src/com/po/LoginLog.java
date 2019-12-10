package com.po;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/09/20
 */
@Component("loginLog")
public class LoginLog {

    /**
     * 主键
     **/
    private int logiLogId;

    /**
     * 日志名称
     **/
    private String logName;

    /**
     * 管理员id
     **/
    private int userId;

    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 是否执行成功
     **/
    private String succeed;
    /**
     * 具体消息
     **/
    private String message;

    /**
     * 登录ip
     **/
    private String ipAddress;

    public int getLogiLogId() {
        return logiLogId;
    }

    public void setLogiLogId(int logiLogId) {
        this.logiLogId = logiLogId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }



    @Override
    public String toString() {
        return "LoginLog{" +
                "logiLogId=" + logiLogId +
                ", logName='" + logName + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", succeed='" + succeed + '\'' +
                ", message='" + message + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
