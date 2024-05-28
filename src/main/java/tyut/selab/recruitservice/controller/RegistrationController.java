package tyut.selab.recruitservice.controller;

import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.impl.RegistrationServiceImpl;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.utils.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.service.impl.RegistrationServiceImpl;
import tyut.selab.utils.Result;

import java.io.IOException;
import java.util.List;

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
        return null;
    }

    /**
     *   通过面试者Name查询报名表信息(支持模糊查询)
     * param intervieweesName
     * @return
     */
    private Result selectRegistrationByUserName(HttpServletRequest request,HttpServletResponse response){
        return null;
    }


    /**
     *  通过意向部门查询报名表
     *  param: departId cur size
     * @return
     */
    private Result intentDepartment(HttpServletRequest request,HttpServletResponse response){
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
