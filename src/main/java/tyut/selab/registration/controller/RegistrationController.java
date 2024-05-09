package tyut.selab.registration.controller;

import tyut.selab.bookservice.utils.Result;
import tyut.selab.registration.service.impl.RegistrationServiceImpl;
import tyut.selab.registration.service.RegistrationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    private Result<Void> save(HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    private Result update(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  查询报名表信息（动态查询）
     *  传入 registrationId & intervieweesName(面试者Name)[可以都传，也可以传入单个参数 | 调用前先判断参数是都为空(是否有效) | 为空跳过此参数 ]
     * @return
     */
    private Result queryOne(HttpServletRequest request,HttpServletResponse response){
        Integer registrationId = Integer.valueOf(request.getAttribute("registrationId").toString());
        String intervieweesName = request.getAttribute("intervieweesName").toString();
        return null;
    }

    /**
     *  查询所有报名表(分页查询)
     *  param: cur[当前页数] size[每页数量]
     * @param request
     * @param response
     * @return
     */
    private Result list(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**  删除报名表
     *   param: bookId
     * @param request
     * @param response
     * @return
     */
    private Result delect(HttpServletRequest request,HttpServletResponse response){return null;}


}
