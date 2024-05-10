package tyut.selab.recruitservice.controller;

import tyut.selab.bookservice.utils.Result;
import tyut.selab.recruitservice.service.impl.RegistrationServiceImpl;
import tyut.selab.recruitservice.service.RegistrationService;
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




}
