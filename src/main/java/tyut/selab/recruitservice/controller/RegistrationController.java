package tyut.selab.recruitservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.Authenticator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.InsertException;
import tyut.selab.recruitservice.service.QueryMyException;
import tyut.selab.recruitservice.service.UpdateException;
import tyut.selab.recruitservice.service.impl.RegistrationServiceImpl;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.utils.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import tyut.selab.utils.PageUtil;

@WebServlet("/registration/*")
public class RegistrationController extends HttpServlet {
    private RegistrationService RegistrationService = new RegistrationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName =split[split.length-1];
        Result result = null;
        if ("selectList".equals(methodName)) {
            result = selectList(req, resp);
        }else if ("selectRegistrationById".equals(methodName)) {
            result = selectRegistrationById(req,resp);
        } else if ("selectByUserName".equals(methodName)) {
            result = selectRegistrationByUserName(req,resp);
        } else if ("selectByDepart".equals(methodName)) {
            result = intentDepartment(req,resp);
        }else if ("selectByGradeId".equals(methodName)) {
            result = selectByGradeId(req,resp);
        }else if ("queryMyRecruit".equals(methodName)) {
//            result = queryMy(req,resp, Integer.valueOf(req.getParameter("userId")));
            result = queryMy(req,resp);
        }else if("insertRegistration".equals(methodName)){
            result = save(req,resp);
            result.setMsg("Success");
            resp.getWriter().write(JSON.toJSONString(result));
        } else if ("updateRegistration".equals(methodName)) {
            result = update(req,resp);
            result.setMsg("Success");
            resp.getWriter().write(JSON.toJSONString(result));
        }else {
            result = new Result(404,null);
            result.setMsg("填入地址无效！");
        }
        writeJson(resp,result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    /**
     * 将数据转换成json格式并响应
     * @param response
     * @param obj
     */
    public static void  writeJson(HttpServletResponse response, Object obj) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            String json = JSON.toJSONString(obj);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException var) {
            throw new RuntimeException(var);
        }
    }

    private String toJdbc(HttpServletRequest request){

        try {
            BufferedReader reader = null;
            reader = request.getReader();
            StringBuilder jsonRequest = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                jsonRequest.append(line);
            }

            return jsonRequest.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** 新增报名表
     *  param: registrationDto
     * @param request
     * @param response
     * @return
     */
    private Result save(HttpServletRequest request, HttpServletResponse response){
        String requestBody = toJdbc(request);
        System.out.println(requestBody);
        RegistrationDto registrationDto = JSONObject.parseObject(requestBody,RegistrationDto.class);
        System.out.println(registrationDto);
       try{RegistrationService.insertRegistration(registrationDto);
           Result<RegistrationDto> result = new Result<>(200,registrationDto);
           return result;
       }catch(InsertException e){
           System.out.println("插入失败");
           Result<RegistrationDto> result = new Result<>(303,registrationDto);
           return result;
       }
    }

    /**
     *   修改报名表信息(管理员操作)
     * param: registrationVo
     * @return
     */
    private Result update(HttpServletRequest request,HttpServletResponse response){
        String requestBody = toJdbc(request);
        System.out.println(JSON.parseObject(requestBody,RegistrationVo.class));
        RegistrationVo registrationVo = JSON.parseObject(requestBody,RegistrationVo.class);
        try{RegistrationService.updateRegistration(registrationVo);
            Result<RegistrationVo> result = new Result<>(200,registrationVo);
            return result;
        }catch(UpdateException e){
            System.out.println("修改失败");
            Result<RegistrationVo> result = new Result<>(303,registrationVo);
            return result;
        }
    }


    /**
     *  分页查询所有报名表
     *  param: cur size
     * @return
     */
    private Result<List<RegistrationVo>> selectList(HttpServletRequest request,HttpServletResponse response){
        //权限方法
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        // 在第一次查询的时候返回total数据说明数据量大小
        PageUtil<RegistrationVo> registrationVos = RegistrationService.selectList(cur, size);
        return getListResult(registrationVos.getData());
    }

    /**
     *   通过 registrationId 查询报名表信息
     * param registrationId 报名表id
     * @return
     */
    private Result<RegistrationVo> selectRegistrationById(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<RegistrationVo> objectResult = new Result<>(500001, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        // id为非自然数 或不是数字
        if(registrationId < 1||!isNumer(String.valueOf(registrationId))){
            Result<RegistrationVo> objectResult = new Result<>(502, null);
            objectResult.setMsg("输入数据有误");
            return objectResult;
        }
        RegistrationVo rev = RegistrationService.selectRegistrationById(registrationId);
        if(rev.getId() == null || rev.getId() == 0){
            Result<RegistrationVo> objectResult = new Result<>(501, null);
            objectResult.setMsg("未找到对应对象");
            return objectResult;
        }
        Result<RegistrationVo> objectResult = new Result<>(200, rev);
        objectResult.setMsg("string");
        return objectResult;
    }

    /**
     *   通过面试者Name查询报名表信息(支持模糊查询)
     * param intervieweesName
     * @return
     */
    private Result<List<RegistrationVo>> selectRegistrationByUserName(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String intervieweesName = request.getParameter("intervieweesName");
        if(!isChineseString(intervieweesName)){
            Result<List<RegistrationVo>> objectResult = new Result<>(502, null);
            objectResult.setMsg("输入数据有误");
            return objectResult;
        }
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        PageUtil<RegistrationVo> registrationVos = RegistrationService.selectByIntervieweesName(cur, size, intervieweesName);
        return getListResult(registrationVos.getData());
    }


    /**
     *  通过意向部门查询报名表
     *  param: departId cur size
     * @return
     */
    private Result<List<RegistrationVo>> intentDepartment(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String intentDepartment = request.getParameter("intentDepartment");
        if(Integer.parseInt(intentDepartment) != 1 && Integer.parseInt(intentDepartment) != 2 && Integer.parseInt(intentDepartment) != 3 && Integer.parseInt(intentDepartment) != 4){
            Result<List<RegistrationVo>> objectResult = new Result<>(502, null);
            objectResult.setMsg("输入数据有误");
            return objectResult;
        }
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        PageUtil<RegistrationVo> registrationVos = RegistrationService.selectByIntentDepartment(Integer.valueOf(intentDepartment),cur, size);
        return getListResult(registrationVos.getData());
    }

    /**
     *  通过年级查询报名表
     * param gradeId cur size
     * @return
     */
    private Result<List<RegistrationVo>> selectByGradeId(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String grade = request.getParameter("grade");
        //输入的数据必须等于1，2，3，4
        if(Integer.parseInt(grade) != 1 && Integer.parseInt(grade) != 2 && Integer.parseInt(grade) != 3 && Integer.parseInt(grade) != 4){
            Result<List<RegistrationVo>> objectResult = new Result<>(502, null);
            objectResult.setMsg("输入数据有误");
            return objectResult;
        }
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        PageUtil<RegistrationVo> registrationVos = RegistrationService.selectByGradeId(Integer.valueOf(grade),cur, size);
        return getListResult(registrationVos.getData());
    }

    /**
     *  查询本人提交报名表 【userId通过后端获取】
     * @return
     */
    private Result queryMy(HttpServletRequest request,HttpServletResponse response){
        Integer registrationId = Integer.valueOf(request.getParameter("userId"));
        // id为非自然数 或不是数字
        if(registrationId < 1||!isNumer(String.valueOf(registrationId))){
            Result<RegistrationVo> objectResult = new Result<>(502, null);
            objectResult.setMsg("输入数据有误");
            return objectResult;
        }
        try{RegistrationVo registrationVo =RegistrationService.queryMyRecruit(registrationId);
            RegistrationVo [] data = {registrationVo};
            Result<RegistrationVo[]> result = new Result<>(200,data);
            return result;
        }catch (QueryMyException e){
            Result<RegistrationVo[]> result = new Result<>(204,new RegistrationVo[]{});
            return result;
        }
    }
    private Result<List<RegistrationVo>> getListResult(List<RegistrationVo> registrationVos) {
        if(registrationVos.isEmpty()){
            Result<List<RegistrationVo>> objectResult = new Result<>(501, null);
            objectResult.setMsg("未找到对应对象");
            return objectResult;
        }
        Result<List<RegistrationVo>> objectResult = new Result<>(200, registrationVos);
        objectResult.setMsg("string");
        return objectResult;
    }
    public static boolean isNumer(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isChineseString(String str) {
        String regex = "^[\\u4e00-\\u9fa5]+$";
        String newStr = str.replaceAll("[\\pP\\p{Punct}\\s]+", "");
        return newStr.matches(regex);
    }

}
