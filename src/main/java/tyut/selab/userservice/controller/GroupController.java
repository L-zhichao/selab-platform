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
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.Result;

import java.io.IOException;
import java.util.List;
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
    private UserVo userVo = new UserVo();

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
            try {
                Result result = queryAllGroup(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", result.getCode());
                jsonObject.put("msg", result.getMsg());
                jsonObject.put("data", result.getData());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
            try {
                Result update = update(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", update.getCode());
                jsonObject.put("msg", update.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
        if (insert == 1) {
            return Result.error(400, "添加失败");
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
    public Result update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置字符编码
        req.setCharacterEncoding("UTF-8");
        // 获取参数值
        String jsonData = req.getReader().lines().collect(Collectors.joining());
        GroupVo groupVo = JSON.parseObject(jsonData, GroupVo.class);
        int insert = groupService.update(groupVo);
        if (insert == 0) {
            return Result.error(400, "添加失败");
        }
        return Result.success(insert);


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
        Integer groupId = Integer.valueOf(req.getParameter("groupId"));
        Integer delete = groupService.delete(groupId);
        if (delete == 0) {
            return Result.success(delete);
        } else {
            return Result.error(400, "删除失败");
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
    public Result queryAllGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //条件查询
        Integer cur = Integer.valueOf((request.getParameter("cur") == null) ? "1" : request.getParameter("cur"));
        Integer szie = Integer.valueOf((request.getParameter("szie") == null) ? "5" : request.getParameter("szie"));
        List<GroupVo> groupVos = groupService.selectAllGroup(cur, szie);
        return Result.success(groupVos);
    }


}



