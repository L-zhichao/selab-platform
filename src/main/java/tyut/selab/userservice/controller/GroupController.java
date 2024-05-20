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



/**
 * @className: GroupController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:04
 * @version: 1.0
 */

@WebServlet("/group/*")
public class GroupController extends HttpServlet {
    private GroupService groupService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        if(methodName.equals("delete")){
            delete(req,resp);
        } else if (methodName.equals("queryAllGroup")) {
            queryAllGroup(req,resp);
        }
//        PrintWriter out = resp.getWriter();
//        out.write("还是牛掰");
//        out.flush();
//        out.close();
//        super.doGet(req, resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        if(methodName.equals("save")){
            save(req,resp);
        } else if (methodName.equals("update")) {
            update(req,resp);
        }
        else{

        }


    }

    /**
     * 新增小组
     * param:GroupDto对象
     *
     * @param req
     * @param resp POST
     * @return
     */
    public Result save(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException{
        //设置请求体字符集
        req.setCharacterEncoding("UTF-8");
        System.out.println("hello save");
       GroupDto groupDto = null;
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
    public Result update(HttpServletRequest req, HttpServletResponse resp) {
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
    public Result delete(HttpServletRequest req, HttpServletResponse resp) {
        GroupDto groupDto = null;
        groupDto.setGroupName(req.getParameter("groupName"));
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
    public Result queryAllGroup(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /*public static String getString(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        char[] buf = new char[512];
        int len = 0;
        StringBuffer contentBuffer = new StringBuffer();
        while ((len = reader.read(buf)) != -1) {
            contentBuffer.append(buf, 0, len);
        }
        String content = contentBuffer.toString();
        if (content == null) {
            content = "";
        }
        return content;
    }*/
}



