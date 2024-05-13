package tyut.selab.userservice.controller;

import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.service.UserService;
import tyut.selab.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: UserController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 23:31
 * @version: 1.0
 */

public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private Result queryByUserId(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  查询小组所有用户
     *
     *  param: cur size groupId  roleId 两个中有一个不为空或全为空，全为空则查询全部
     * @param request
     * @param response
     * @return list<User>
     */
    private Result query(HttpServletRequest request,HttpServletResponse response){
        String method = request.getMethod();





        return null;
    }

    /**
     *  通过id查询用户信息 [id在路径后面]  " queryById/2  (id = 2)
     * @param request
     * @param response
     * @return
     */
    private Result queryById(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  增加用户
     *  param: UserDto
     * @param request
     * @param response
     * @return
     */
    private Result save(HttpServletRequest request,HttpServletResponse response){return null;}

    /**
     *  修改用户信息
     *  param: UserVo对象
     * @param request
     * @param response
     * @return
     */
    private Result update(HttpServletRequest request, HttpServletResponse response){return null;}

    /**
     *  注销用户
     *  param: 要删除用户的userId
     * @param request
     * @param response
     * @return
     */
    private Result logout(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  修改用户权限
     *  param: UserRoleDto
     * @param request
     * @param response
     * @return
     */
    private Result updateRole(HttpServletRequest request, HttpServletResponse response){return null;}

}
