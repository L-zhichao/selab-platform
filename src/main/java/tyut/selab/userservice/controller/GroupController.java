package tyut.selab.userservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.ServiceImpl.GroupServiceImpl;
import tyut.selab.utils.Result;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;


/**
 * @className: GroupController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:04
 * @version: 1.0
 */

@WebServlet("/group/*")
public class GroupController extends HttpServlet {
    private GroupService groupService = new GroupServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取接口
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        if (methodName.equals("delete")) {
            //执行接口方法
            try {
                Result delete = delete(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", delete.getCode());
                jsonObject.put("msg", delete.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (methodName.equals("queryAllGroup")) {
            queryAllGroup(req, resp);
        }
//        PrintWriter out = resp.getWriter();
//        out.write("还是牛掰");
//        out.flush();
//        out.close();
//        super.doGet(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //super.doPost(req, resp);
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        if (methodName.equals("save")) {
            try {
                Result save = save(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", save.getCode());
                jsonObject.put("msg", save.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (methodName.equals("update")) {
            update(req, resp);
        } else {

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
    public Result save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //设置请求体字符集
        req.setCharacterEncoding("UTF-8");
        //获取json格式对应数据，必须名字相同
        String jsonData = req.getReader().lines().collect(Collectors.joining());
        GroupDto groupDto = JSON.parseObject(jsonData, GroupDto.class);
        int insert = groupService.insert(groupDto);
        if(insert == 1){
            return Result.error(400,"添加失败");
        }
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
        GroupDto groupDto = new GroupDto(req.getParameter("groupName"));
        Integer delete = groupService.delete(groupDto);
        if(delete == 0){
            return Result.success(delete);
        }
        else {
            return Result.error(400,"删除失败");
        }

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



