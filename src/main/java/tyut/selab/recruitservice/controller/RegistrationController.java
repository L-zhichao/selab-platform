package tyut.selab.recruitservice.controller;

import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.impl.RegistrationServiceImpl;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RegistrationController",urlPatterns = {})
public class RegistrationController extends HttpServlet {
    private RegistrationService RegistrationService = new RegistrationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
        return null;
    }

    /**
     *   通过 registrationId 查询报名表信息
     * param registrationId 报名表id
     * @return
     */
    private Result selectRegistrationById(HttpServletRequest request,HttpServletResponse response){
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));
        RegistrationVo rev = RegistrationService.selectRegistrationById(registrationId);
        if(rev != null){
//            int grade = 0;
//            switch (rev.getClassroom().substring(0,2)){
//                case "23":
//                    grade = 1;
//                    break;
//                case "22":
//                    grade = 2;
//                    break;
//                case "21":
//                    grade = 3;
//                    break;
//                case "20":
//                    grade = 4;
//                default:
//                    try{
//                    }catch(Exception e){
//                        e.printStackTrace();
//                        System.out.println("年级输入有误");
//                    }
//
//            }

            Map data = new HashMap();
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
            Map user = new HashMap();
            user.put("code",200);
            user.put("data",data);
            user.put("msg","string");
        }
        return null;
    }

    /**
     *   通过面试者Name查询报名表信息(支持模糊查询)
     * param intervieweesName
     * @return
     */
    private Result selectRegistrationByUserName(HttpServletRequest request,HttpServletResponse response){
        String intervieweesName = request.getParameter("intervieweesName");
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<RegistrationVo> registrationVos = RegistrationService.selectByIntervieweesName(cur, size, intervieweesName);
        return null;
    }


    /**
     *  通过意向部门查询报名表
     *  param: departId cur size
     * @return
     */
    private Result intentDepartment(HttpServletRequest request,HttpServletResponse response){
        String intentDepartment = request.getParameter("intentDepartment");
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<RegistrationVo> registrationVos = RegistrationService.selectByIntentDepartment(cur, size, Integer.valueOf(intentDepartment));
        return null;
    }

    /**
     *  通过年级查询报名表
     * param gradeId cur size
     * @return
     */
    private Result selectByGradeId(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  查询本人提交报名表 【userId通过后端获取】
     * @return
     */
    private Result queryMy(HttpServletRequest request,HttpServletResponse response){
        return null;
    }


}
