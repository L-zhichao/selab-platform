package tyut.selab.userservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.ServiceImpl.GroupServiceImpl;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.Result;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        } else if (methodName.equals("queryAll")) {
            try {
                Result result = queryAll(req, resp);
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
//        StringBuffer xmlData = new StringBuffer();
//        BufferedReader reader = req.getReader();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            xmlData.append(line);
//        }
        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            InputSource source = new InputSource(new StringReader(xmlData.toString()));
//            Document document = builder.parse(source);
//
//            // 获取 groupName 标签的参数值
//            Node groupNameNode = document.getElementsByTagName("groupName").item(0);
//            String groupName = groupNameNode.getTextContent();
            int insert = groupService.insert(groupDto);
            if (insert != 1) {
                return Result.success(insert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(400, "添加失败或没有权限");
    }

    /**
     * 修改小组信息
     * param: GroupVo对象
     *
     * @param req
     * @param resp POST
     * @return
     */
    public Result update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        // 设置字符编码
        req.setCharacterEncoding("UTF-8");
        // 获取参数值
        String jsonData = req.getReader().lines().collect(Collectors.joining());
        GroupVo groupVo = JSON.parseObject(jsonData, GroupVo.class);
//        StringBuffer xmlData = new StringBuffer();
//        BufferedReader reader = req.getReader();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            xmlData.append(line);
//        }
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            InputSource source = new InputSource(new StringReader(xmlData.toString()));
//            Document document = builder.parse(source);
//
//            // 获取 groupName 标签的参数值
//            Node groupNameNode = document.getElementsByTagName("groupName").item(0);
//            String groupName = groupNameNode.getTextContent();
//
//            Node groupIdNode = document.getElementsByTagName("groupId").item(0);
//            String groupId = groupIdNode.getTextContent();
//
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Node createTimeNode = document.getElementsByTagName("createTime").item(0);
//            String createTime = createTimeNode.getTextContent();
//            Date date = simpleDateFormat.parse(createTime);

            int update = groupService.update(groupVo);
            if (update == 0) {
                return Result.error(400, "修改失败或没有权限");
            }
            return Result.success(update);
    }
        /**
         * 删除小组信息
         * param: groupId
         *
         * @param req
         * @param resp GET
         * @return
         */
        public Result delete (HttpServletRequest req, HttpServletResponse resp){
            Integer groupId = Integer.valueOf(req.getParameter("groupId"));
            Integer delete = groupService.delete(groupId);
            if (delete == 1) {
                return Result.success(delete);
            } else {
                return Result.error(400, "不存在该用户或没有权限");
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
        public Result queryAll (HttpServletRequest request, HttpServletResponse response) throws Exception {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");

            //条件查询
            Integer cur = Integer.valueOf((request.getParameter("cur") == null) ? "1" : request.getParameter("cur"));
            Integer szie = Integer.valueOf((request.getParameter("szie") == null) ? "5" : request.getParameter("szie"));
            List<GroupVo> groupVos = groupService.selectAllGroup(cur, szie);
            if(groupVos == null){
                return Result.error(400,"查询失败，没有权限");
            }
            return Result.success(groupVos);
        }


    }




