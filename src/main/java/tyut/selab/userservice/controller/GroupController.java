package tyut.selab.userservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.utils.Result;



import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: GroupController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:04
 * @version: 1.0
 */

@WebServlet(name = "GroupController",urlPatterns = {"/group"})
public class GroupController extends HttpServlet {
    private GroupService groupService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if ("/delete".equals(path)) {
            System.out.println("hello doGet delete");
        } else if ("/queryAll".equals(path)) {
            System.out.println("hello doGet queryAll");
        }
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write("还是牛掰");
//        out.flush();
//        out.close();
//        super.doGet(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
        String path = req.getPathInfo();
        if ("/save".equals(path)) {
            save(req,resp);
        } else if ("/update".equals(path)) {
        }
        super.doPost(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print("测试");
        out.flush();
        out.close();
    }

    /**
     * 新增小组
     * param:GroupDto对象
     *
     * @param req
     * @param resp POST
     * @return
     */
    private Result save(HttpServletRequest req, HttpServletResponse resp) {
        GroupDto groupDto = null;
        groupDto.setGroupName(req.getParameter("groupName"));
        Integer insert = groupService.insert(groupDto);
        return Result.success(insert);
    }

    /**
     * 修改小组信息
     * param: GroupVo对象
     *
     * @param req
     * @param resp POST
     * @return
     */
    private Result update(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    /**
     * 删除小组信息
     * param: groupId
     *
     * @param req
     * @param resp GET
     * @return
     */
    private Result delete(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    /**
     * 查询所有小组信息
     * param: cur size
     *
     * @param request
     * @param response GET
     * @return list<GroupVo>
     */
    private Result queryAllGroup(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
