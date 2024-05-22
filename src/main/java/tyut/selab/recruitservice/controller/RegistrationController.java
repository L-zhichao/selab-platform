package tyut.selab.recruitservice.controller;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.recruitservice.service.impl.RegistrationServiceImpl;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.utils.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/registration/*")
public class RegistrationController extends HttpServlet {
    private RegistrationService RegistrationService = new RegistrationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName =split[split.length-1];
        Result result = null;
        if("insertRegistration".equals(methodName)){
            result = save(req,resp);
        }else if("updateRegistration".equals(methodName)){
            result = update(req,resp);
        }else if ("selectList".equals(methodName)) {
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
     * 将service层传入的用户信息转换成map类型
     * @param rev
     * @return
     */
    private Map<String,Object> toData(RegistrationVo rev){
        Map<String,Object> data = new HashMap<>();
        data.put("id",rev.getId());
        data.put("interviewees",rev.getInterviewees());
        data.put("email",rev.getEmail());
        data.put("phone",rev.getPhone());
        data.put("intentDepartment",rev.getIntentDepartment());
        data.put("classroom",rev.getClassroom());
        data.put("interviewTime",rev.getInterviewTime());
        data.put("introduce",rev.getIntroduce());
        data.put("purpose",rev.getPurpose());
        data.put("remark",rev.getRemark());
        data.put("grade",rev.getGrade());
        Map<String,Object> user = new HashMap<>();
        user.put("code",200);
        user.put("data",data);
        user.put("msg","string");
        return data;
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


    /** 新增报名表
     *  param: registrationDto
     * @param request
     * @param response
     * @return
     */
    private Result save(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *   修改报名表信息(管理员操作)
     * param: registrationVo
     * @return
     */
    private Result update(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  分页查询所有报名表
     *  param: cur size
     * @return
     */
    private Result selectList(HttpServletRequest request,HttpServletResponse response){
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<RegistrationVo> registrationVos = RegistrationService.selectList(cur, size);
        if(null == registrationVos){
            return new Result<List<RegistrationVo>>(404,null);
        }
        return new Result<List<RegistrationVo>>(200,registrationVos);
    }

    /**
     *   通过 registrationId 查询报名表信息
     * param registrationId 报名表id
     */
    private Result<RegistrationVo> selectRegistrationById(HttpServletRequest request,HttpServletResponse response){
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));
        RegistrationVo rev = RegistrationService.selectRegistrationById(registrationId);
        if(null != rev){
            return new Result<RegistrationVo>(200,rev);
        }
        return new Result<RegistrationVo>(404,null);
    }

    /**
     *   通过面试者Name查询报名表信息(支持模糊查询)
     * param intervieweesName
     * @return
     */
    private Result<List<RegistrationVo>> selectRegistrationByUserName(HttpServletRequest request,HttpServletResponse response){
        String intervieweesName = request.getParameter("intervieweesName");
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<RegistrationVo> registrationVos = RegistrationService.selectByIntervieweesName(cur, size, intervieweesName);
        if(null == registrationVos){
            return new Result<List<RegistrationVo>>(404,null);
        }
        return new Result<List<RegistrationVo>>(200,registrationVos);
    }


    /**
     *  通过意向部门查询报名表
     *  param: departId cur size
     * @return
     */
    private Result<List<RegistrationVo>> intentDepartment(HttpServletRequest request,HttpServletResponse response){
        String intentDepartment = request.getParameter("intentDepartment");
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<RegistrationVo> registrationVos = RegistrationService.selectByIntentDepartment(cur, size, Integer.valueOf(intentDepartment));
        if(null == registrationVos){
            return new Result<List<RegistrationVo>>(404,null);
        }
        return new Result<List<RegistrationVo>>(200,registrationVos);
    }

    /**
     *  通过年级查询报名表
     * param gradeId cur size
     * @return
     */
    private Result<List<RegistrationVo>> selectByGradeId(HttpServletRequest request,HttpServletResponse response){
        String intentDepartment = request.getParameter("intentDepartment");
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<RegistrationVo> registrationVos = RegistrationService.selectByGradeId(cur, size, Integer.valueOf(intentDepartment));
        if(null == registrationVos){
            return new Result<List<RegistrationVo>>(404,null);
        }
        return new Result<List<RegistrationVo>>(200,registrationVos);
    }

    /**
     *  查询本人提交报名表 【userId通过后端获取】
     * @return
     */
    private Result queryMy(HttpServletRequest request,HttpServletResponse response){
       return null;
    }


}
