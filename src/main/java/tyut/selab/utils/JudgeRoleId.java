package tyut.selab.utils;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;

public class JudgeRoleId {

    //userlocal为空，请求测试报错

    public static Integer GetJudgeRoleId() {
        SecurityUtil securityUtil = new SecurityUtil();
        UserLocal userLocal = securityUtil.getUser();
        //1超管，2管理，3用户
        return userLocal.getRoleId();
    }

}
