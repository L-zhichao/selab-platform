package tyut.selab.userservice.controller;

import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.service.ServiceImpl.UserServiceImpl;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @className: UserController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 23:31
 * @version: 1.0
 */


@WebServlet("/user")
public class UserController extends HttpServlet {

    private UserServiceImpl userService;




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if("/query".equals(path)){
            query(req, resp);
            resp.getWriter().println("Handling /user/query path");
        }else if("/save".equals(path)){
            save(req, resp);
        }else if("update".equals(path)){
            update(req, resp);
        }else if("/queryById".equals(path)){
            queryById(req, resp);
        }
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    //是否还有用
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
        String groupId = request.getParameter("groupId");
        String roleId = request.getParameter("roleId");
        List<UserVo> usersByGroupId = userService.selectByGroupId(Integer.valueOf(groupId));





        return null;
    }

    /**
     *  通过id查询用户信息 [id在路径后面]  " queryById/2  (id = 2)
     * @param request
     * @param response
     * @return
     */
    private Result queryById(HttpServletRequest request,HttpServletResponse response){

        String userId = request.getParameter("id");
        UserVo userVo = userService.selectByUserId(Integer.valueOf(userId));

        Result<UserVo> result = new Result<>(null,null);
        return result.success(userVo);
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
