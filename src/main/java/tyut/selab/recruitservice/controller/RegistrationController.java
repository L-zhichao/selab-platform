package tyut.selab.recruitservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.*;
import tyut.selab.recruitservice.service.impl.RegistrationServiceImpl;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.utils.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import tyut.selab.utils.PageUtil;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.xml.transform.Templates;

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
            result = queryMy(req,resp);
        }else if("insertRegistration".equals(methodName)){
            result = save(req,resp);
        } else if ("updateRegistration".equals(methodName)) {
            result = update(req,resp);
        }else {
            result = new Result(404,null);
            result.setMsg("填入地址无效");
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
        if(registrationDto == null){
            Result<RegistrationDto> result = new Result<>(500005,registrationDto);
            result.setMsg("registrationDto不能为空");
            return result;
        }
        int row = dtoIsNull(registrationDto);
        if(row == 9){
            Result<RegistrationDto> result = new Result<>(500003,registrationDto);
            result.setMsg("你的数据没有成功传入");
            return result;
        }
        if(row > 0){
            Result<RegistrationDto> result = new Result<>(200,registrationDto);
            result.setMsg("你有部分数据未传入");
            return result;
        }
        System.out.println(registrationDto);
       try{RegistrationService.insertRegistration(registrationDto);
           Result<RegistrationDto> result = new Result<>(200,registrationDto);
           result.setMsg("成功");
           return result;
       }catch(InsertException e){
           System.out.println("插入失败");
           Result<RegistrationDto> result = new Result<>(500003,null);
           result.setMsg("修改或插入数据失败");
           return result;
       }
    }

    /**
     * 判断RegistrationDto中是否有空值，并修改
     * 返回为空的数量
     * @param registrationDto
     * @return
     */
    private static int dtoIsNull(RegistrationDto registrationDto){
        int row = 0;
        if (registrationDto.getIntentDepartment() == null){
            registrationDto.setIntentDepartment(0);
            row++;
        }
        if (registrationDto.getInterviewTime() == null){
            registrationDto.setInterviewTime(new Date());
            row++;
        }
        if (registrationDto.getClassroom() == null){
            registrationDto.setClassroom("0");
            row++;
        }
        if (registrationDto.getEmail() == null){
            registrationDto.setEmail("0");
            row++;
        }
        if (registrationDto.getPhone() == null){
            registrationDto.setPhone("0");
            row++;
        }
        if (registrationDto.getIntroduce() == null){
            registrationDto.setIntroduce("0");
            row++;
        }
        if (registrationDto.getPurpose() == null){
            registrationDto.setPurpose("0");
            row++;
        }
        if (registrationDto.getRemark() == null){
            registrationDto.setRemark("0");
            row++;
        }
        if (registrationDto.getGrade() == null){
            registrationDto.setGrade(0);
            row++;
        }
        return row;
    }
    /**
     *   修改报名表信息(管理员操作)
     * param: registrationVo
     * @return
     */
    private Result update(HttpServletRequest request,HttpServletResponse response){
        //权限方法
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500002, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String requestBody = toJdbc(request);
        System.out.println(JSON.parseObject(requestBody,RegistrationVo.class));
        RegistrationVo registrationVo = JSON.parseObject(requestBody,RegistrationVo.class);
        if(registrationVo == null){
            Result<RegistrationVo> result = new Result<>(500005,registrationVo);
            result.setMsg("registrationVo不能为空");
            return result;
        }
        int row = voIsNull(registrationVo);
        if(row == 10){
            Result<RegistrationVo> result = new Result<>(500003,registrationVo);
            result.setMsg("你的数据没有成功传入");
            return result;
        }
        if(row > 0){
            Result<RegistrationVo> result = new Result<>(200,registrationVo);
            result.setMsg("你有部分数据未传入");
            return result;
        }
        try{RegistrationService.updateRegistration(registrationVo);
            Result<RegistrationVo> result = new Result<>(200,registrationVo);
            result.setMsg("成功");
            return result;
        }catch(UpdateException e){
            Result<RegistrationVo> result = new Result<>(500003,null);
            result.setMsg("修改或插入数据失败");
            return result;
        }
    }

    /**
     * 判读registrationVo中的值是否为空，并修改
     * 返回为空行数
     * @param registrationVo
     * @return
     */
    private static int voIsNull(RegistrationVo registrationVo){
        int row = 0;
        if(registrationVo.getId() == null){
            row++;
            registrationVo.setId(1);
        }
        if(registrationVo.getEmail() == null){
            row++;
            registrationVo.setEmail("0");
        }
        if(registrationVo.getPhone() == null){
            row++;
            registrationVo.setPhone("0");
        }
        if(registrationVo.getIntentDepartment() == null){
            row++;
            registrationVo.setIntentDepartment(0);
        }
        if(registrationVo.getClassroom() == null){
            row++;
            registrationVo.setClassroom("0");
        }
        if(registrationVo.getInterviewTime() == null){
            row++;
            registrationVo.setInterviewTime(new Date());
        }
        if(registrationVo.getIntroduce() == null){
            row++;
            registrationVo.setIntroduce("0");
        }
        if(registrationVo.getPurpose() == null){
            row++;
            registrationVo.setPurpose("0");
        }
        if(registrationVo.getRemark() == null){
            row++;
            registrationVo.setRemark("0");
        }
        if(registrationVo.getGrade() == null){
            row++;
            registrationVo.setGrade(0);
        }
        return row;
    }

    /**
     *  分页查询所有报名表
     *  param: cur size
     * @return
     */
    private Result<List<RegistrationVo>> selectList(HttpServletRequest request,HttpServletResponse response){
        //权限方法
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500002, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }

        String cur = request.getParameter("cur");
        String size = request.getParameter("size");
        if(cur == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("cur不能为null");
            return objectResult;
        }
        if(size == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("cur不能为null");
            return objectResult;
        }
        // 在第一次查询的时候返回total数据说明数据量大小
        PageUtil<RegistrationVo> registrationVos = null;
        try {
            registrationVos = RegistrationService.selectList(Integer.valueOf(cur), Integer.valueOf(size));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(registrationVos == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
            objectResult.setMsg("未找到查询数据");
            return objectResult;
        }
        return getListResult(registrationVos.getData());
    }

    /**
     *   通过 registrationId 查询报名表信息
     * param registrationId 报名表id
     * @return
     */
    private Result<RegistrationVo> selectRegistrationById(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<RegistrationVo> objectResult = new Result<>(500002, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String registrationId = request.getParameter("registrationId");
        if(registrationId == null){
            Result<RegistrationVo> objectResult = new Result<>(500005, null);
            objectResult.setMsg("registrationId不能为null");
            return objectResult;
        }

        // id为非自然数 或不是数字
        if(Integer.parseInt(registrationId) < 1 || !isNumer(registrationId)){
            Result<RegistrationVo> objectResult = new Result<>(500004, null);
            objectResult.setMsg("输入数据违法或不规范");
            return objectResult;
        }
        RegistrationVo rev = null;
        try {
            rev = RegistrationService.selectRegistrationById(Integer.parseInt(registrationId));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(rev == null){
            Result<RegistrationVo> objectResult = new Result<>(500001, null);
            objectResult.setMsg("未找到查询数据");
            return objectResult;
        }
        Result<RegistrationVo> objectResult = new Result<>(200, rev);
        objectResult.setMsg("查询成功");
        return objectResult;
    }

    /**
     *   通过面试者Name查询报名表信息(支持模糊查询)
     * param intervieweesName
     * @return
     */
    private Result<List<RegistrationVo>> selectRegistrationByUserName(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500002, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String intervieweesName = request.getParameter("intervieweesName");
        if(intervieweesName == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("intervieweesName不能为null");
            return objectResult;
        }
        if(!isChineseString(intervieweesName)){
            Result<List<RegistrationVo>> objectResult = new Result<>(500004, null);
            objectResult.setMsg("输入数据违法或不规范");
            return objectResult;
        }
        String cur = request.getParameter("cur");
        String size = request.getParameter("size");
        if(cur == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("cur不能为null");
            return objectResult;
        }
        if(size == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("size不能为null");
            return objectResult;
        }
        PageUtil<RegistrationVo> registrationVos = null;
        try {
            registrationVos = RegistrationService.selectByIntervieweesName(Integer.valueOf(cur), Integer.valueOf(size), intervieweesName);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(registrationVos == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
            objectResult.setMsg("未找到查询数据");
            return objectResult;
        }
        return getListResult(registrationVos.getData());
    }


    /**
     *  通过意向部门查询报名表
     *  param: departId cur size
     * @return
     */
    private Result<List<RegistrationVo>> intentDepartment(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500002, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String intentDepartment = request.getParameter("intentDepartment");
        if(intentDepartment == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("intentDepartment不能为null");
            return objectResult;
        }
        if(Integer.parseInt(intentDepartment) != 1 && Integer.parseInt(intentDepartment) != 2
                && Integer.parseInt(intentDepartment) != 3 && Integer.parseInt(intentDepartment) != 4){
            Result<List<RegistrationVo>> objectResult = new Result<>(500004, null);
            objectResult.setMsg("输入数据违法或不规范");
            return objectResult;
        }
        String cur = request.getParameter("cur");
        String size = request.getParameter("size");
        if(cur == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("cur不能为null");
            return objectResult;
        }
        if(size == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("size不能为null");
            return objectResult;
        }
        PageUtil<RegistrationVo> registrationVos = null;
        try {
            registrationVos = RegistrationService.selectByIntentDepartment(Integer.valueOf(intentDepartment),Integer.valueOf(cur), Integer.valueOf(size));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(registrationVos == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
            objectResult.setMsg("未找到查询数据");
            return objectResult;
        }
        return getListResult(registrationVos.getData());
    }

    /**
     *  通过年级查询报名表
     * param gradeId cur size
     * @return
     */
    private Result<List<RegistrationVo>> selectByGradeId(HttpServletRequest request,HttpServletResponse response){
//        if (SecurityUtil.getUser().getRoleId() != 0 || SecurityUtil.getUser().getRoleId() != 1){
//            Result<List<RegistrationVo>> objectResult = new Result<>(500002, null);
//            objectResult.setMsg("权限不足");
//            return objectResult;
//        }
        String grade = request.getParameter("grade");
        if(grade == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("grade不能为null");
            return objectResult;
        }
        //输入的数据必须等于1，2，3，4
        if(Integer.parseInt(grade) != 1 && Integer.parseInt(grade) != 2 && Integer.parseInt(grade) != 3 && Integer.parseInt(grade) != 4){
            Result<List<RegistrationVo>> objectResult = new Result<>(500004, null);
            objectResult.setMsg("输入数据违法或不规范");
            return objectResult;
        }
        String cur = request.getParameter("cur");
        String size = request.getParameter("size");
        if(cur == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("cur不能为null");
            return objectResult;
        }
        if(size == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500005, null);
            objectResult.setMsg("size不能为null");
            return objectResult;
        }
        PageUtil<RegistrationVo> registrationVos = null;
        try {
            registrationVos = RegistrationService.selectByGradeId(Integer.valueOf(grade),Integer.valueOf(cur), Integer.valueOf(size));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(registrationVos == null){
            Result<List<RegistrationVo>> objectResult = new Result<>(500001, null);
            objectResult.setMsg("未找到查询数据");
            return objectResult;
        }
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
            Result<RegistrationVo> objectResult = new Result<>(500004, null);
            objectResult.setMsg("输入数据违法或不规范");
            return objectResult;
        }
        try{RegistrationVo registrationVo =RegistrationService.queryMyRecruit(registrationId);
            RegistrationVo [] data = {registrationVo};
            Result<RegistrationVo[]> result = new Result<>(200,data);
            result.setMsg("查询成功");
            return result;
        }catch (QueryMyException e){
            Result<RegistrationVo[]> result = new Result<>(500001,null);
            result.setMsg("未找到查询数据");
            return result;
        }
    }
    private Result<List<RegistrationVo>> getListResult(List<RegistrationVo> registrationVos) {
        Result<List<RegistrationVo>> objectResult = new Result<>(200, registrationVos);
        objectResult.setMsg("查询成功");
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
