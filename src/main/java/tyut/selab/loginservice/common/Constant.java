package tyut.selab.loginservice.common;

public interface Constant {

    /**
     *  对于同一邮箱注册的最大用户数量
     */
    public static final Integer MAX_SAME_MMAIL_REGISTER_NUM = 3;
    Integer ACCOUNT_FORMAT_ERROR = 50101;//账号输入格式问题
    Integer PASSWORD_FORMAT_ERROR = 50102;//密码输入格式问题
    Integer PHONENUM_FORMAT_ERROR = 50103;//电话号码格式错误
    Integer VERIFY_INFO_ERROR = 50104;//输入验证码错误
    Integer MAIL_FORMAT_ERROR_CODE = 50105;//邮箱格式错误
    Integer ACCOUNT_OR_PASSWORD_ERROR = 50106;//账号或密码格式错误
    Integer FAILED_SEND_ERROR_CODE = 50002;//验证码发送失败
    Integer STATUS_CODE_INNSER_ERROR = 50001;//服务器内部异常问题
    Integer STATUS_CODE_NON_TOKEN = 505;
    String HEAD = "平台注册验证码信息";
    String TYPE = "QQ邮箱注册平台";
    String VERIFICATION_HTML_TEXT = "<!DOCTYPE html>   " +
            "<html>   " +
            "<head>   " +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />   " +
            "    <title></title>   " +
            "    <meta charset=\"utf-8\" />   " +
            "   " +
            "</head>   " +
            "<body>   " +
            "    <div class=\"qmbox qm_con_body_content qqmail_webmail_only\" id=\"mailContentContainer\" style=\"\">   " +
            "        <style type=\"text/css\">   " +
            "            .qmbox body {   " +
            "                margin: 0;   " +
            "                padding: 0;   " +
            "                background: #fff;   " +
            "                font-family: \"Verdana, Arial, Helvetica, sans-serif\";   " +
            "                font-size: 14px;   " +
            "                line-height: 24px;   " +
            "            }   " +
            "   " +
            "            .qmbox div, .qmbox p, .qmbox span, .qmbox img {   " +
            "                margin: 0;   " +
            "                padding: 0;   " +
            "            }   " +
            "   " +
            "            .qmbox img {   " +
            "                border: none;   " +
            "            }   " +
            "   " +
            "            .qmbox .contaner {   " +
            "                margin: 0 auto;   " +
            "            }   " +
            "   " +
            "            .qmbox .title {   " +
            "                margin: 0 auto;   " +
            "                background: url() #CCC repeat-x;   " +
            "                height: 30px;   " +
            "                text-align: center;   " +
            "                font-weight: bold;   " +
            "                padding-top: 12px;   " +
            "                font-size: 16px;   " +
            "            }   " +
            "   " +
            "            .qmbox .content {   " +
            "                margin: 4px;   " +
            "            }   " +
            "   " +
            "            .qmbox .biaoti {   " +
            "                padding: 6px;   " +
            "                color: #000;   " +
            "            }   " +
            "   " +
            "            .qmbox .xtop, .qmbox .xbottom {   " +
            "                display: block;   " +
            "                font-size: 1px;   " +
            "            }   " +
            "   " +
            "            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {   " +
            "                display: block;   " +
            "                overflow: hidden;   " +
            "            }   " +
            "   " +
            "            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3 {   " +
            "                height: 1px;   " +
            "            }   " +
            "   " +
            "            .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {   " +
            "                border-left: 1px solid #BCBCBC;   " +
            "                border-right: 1px solid #BCBCBC;   " +
            "            }   " +
            "   " +
            "            .qmbox .xb1 {   " +
            "                margin: 0 5px;   " +
            "                background: #BCBCBC;   " +
            "            }   " +
            "   " +
            "            .qmbox .xb2 {   " +
            "                margin: 0 3px;   " +
            "                border-width: 0 2px;   " +
            "            }   " +
            "   " +
            "            .qmbox .xb3 {   " +
            "                margin: 0 2px;   " +
            "            }   " +
            "   " +
            "            .qmbox .xb4 {   " +
            "                height: 2px;   " +
            "                margin: 0 1px;   " +
            "            }   " +
            "   " +
            "            .qmbox .xboxcontent {   " +
            "                display: block;   " +
            "                border: 0 solid #BCBCBC;   " +
            "                border-width: 0 1px;   " +
            "            }   " +
            "   " +
            "            .qmbox .line {   " +
            "                margin-top: 6px;   " +
            "                border-top: 1px dashed #B9B9B9;   " +
            "                padding: 4px;   " +
            "            }   " +
            "   " +
            "            .qmbox .neirong {   " +
            "                padding: 6px;   " +
            "                color: #666666;   " +
            "            }   " +
            "   " +
            "            .qmbox .foot {   " +
            "                padding: 6px;   " +
            "                color: #777;   " +
            "            }   " +
            "   " +
            "            .qmbox .font_darkblue {   " +
            "                color: #006699;   " +
            "                font-weight: bold;   " +
            "            }   " +
            "   " +
            "            .qmbox .font_lightblue {   " +
            "                color: #008BD1;   " +
            "                font-weight: bold;   " +
            "            }   " +
            "   " +
            "            .qmbox .font_gray {   " +
            "                color: #888;   " +
            "                font-size: 12px;   " +
            "            }   " +
            "        </style>   " +
            "        <div class=\"contaner\">   " +
            "            <div class=\"title\">%s</div>   " +
            "            <div class=\"content\">   " +
            "                <p class=\"biaoti\"><b>亲爱的用户，你好！</b></p>   " +
            "                <b class=\"xtop\"><b class=\"xb1\"></b><b class=\"xb2\"></b><b class=\"xb3\"></b><b class=\"xb4\"></b></b>   " +
            "                <div class=\"xboxcontent\">   " +
            "                    <div class=\"neirong\">   " +
            "                        <p><b>请核对你的用户名：</b><span id=\"userName\" class=\"font_darkblue\">%s</span></p>   " +
            "                        <p><b>%s的验证码：</b><span class=\"font_lightblue\"><span id=\"yzm\" data=\"$(captcha)\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">%s</span></span><br><span class=\"font_gray\">(请输入该验证码完成注册，验证码60秒内有效！)</span></p>   " +
            "                        <div class=\"line\">如果你未申请%s服务，请忽略该邮件。</div>   " +
            "                    </div>   " +
            "                </div>   " +
            "                <b class=\"xbottom\"><b class=\"xb4\"></b><b class=\"xb3\"></b><b class=\"xb2\"></b><b class=\"xb1\"></b></b>   " +
            "            </div>   " +
            "        </div>   " +
            "        <style type=\"text/css\">   " +
            "            .qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {   " +
            "                display: none !important;   " +
            "            }   " +
            "        </style>   " +
            "    </div>   " +
            "</body>   " +
            "</html>";
}
