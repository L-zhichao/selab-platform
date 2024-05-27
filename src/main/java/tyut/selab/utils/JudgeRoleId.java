package tyut.selab.utils;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;

public class JudgeRoleId {

    //userlocal为空，请求测试报错

   /* public static Integer GetJudgeRoleId() {
        SecurityUtil securityUtil = new SecurityUtil();
        UserLocal userLocal = securityUtil.getUser();
        Integer key;
        //超级管理员
        if (userLocal.getRoleId().equals(1)) {
            key = 1;
        } else if (userLocal.getRoleId().equals(2)) {
            key = 2;
        } else {
            key = 3;
        }
        return key;
    }*/

}
