package com.money.more.data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Describe：
 * @Author：luna
 * @Date：2021/11/29:15:53
 */
@Data
public class UserBean extends BasicsRequest{
    @ApiModelProperty("手机号")
    private String mobile ;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("确认密码")
    private String passwordAgain;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("验证码")
    private String verifyCode;

    @ApiModelProperty("qq")
    private String qq;

    @ApiModelProperty("邮箱")
    private String eMail;

    @ApiModelProperty("用户类型：1-商户2-兼职用户3-系统用户")
    private Integer userType;



}
