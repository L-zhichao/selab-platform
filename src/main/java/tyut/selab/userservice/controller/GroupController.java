package tyut.selab.userservice.controller;

import tyut.selab.userservice.service.GroupService;
import tyut.selab.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: GroupController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:04
 * @version: 1.0
 */

@WebServlet("/group")
public class GroupController extends HttpServlet {
    private GroupService groupService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取请求参数

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     *  新增小组
     *  param:GroupDto对象
     * @param req
     * @param resp
     * POST
     * @return
     */

    private Result save(HttpServletRequest req, HttpServletResponse resp){


        return null;}

    /**
     *  修改小组信息
     *  param: GroupVo对象
     * @param req
     * @param resp
     * POST
     * @return
     */
    private Result update(HttpServletRequest req, HttpServletResponse resp){return null;}

    /**
     *  删除小组信息
     *  param: groupId
     * @param req
     * @param resp
     * GET
     * @return
     */
    private Result delete(HttpServletRequest req, HttpServletResponse resp){return null;}

    /**
     * 查询所有小组信息
     *  param: cur size
     * @param request
     * @param response
     * GET
     * @return list<GroupVo>
     */
    private Result queryAllGroup(HttpServletRequest request,HttpServletResponse response){return null;}
}
