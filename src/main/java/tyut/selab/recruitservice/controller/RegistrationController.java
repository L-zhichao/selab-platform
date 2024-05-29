package tyut.selab.recruitservice.controller;
import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.Authenticator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet (name = "RegistrationController",urlPatterns = {"/registration/insertRegistration","/registration/updateRegistration","/registration/queryMyRecruit"})
public class RegistrationController extends HttpServlet {
    private RegistrationService registrationService = new RegistrationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        System.out.println(userId);
        String requestURI = req.getRequestURI();
        String []split =requestURI.split("/");
        String methodName =split[split.length-1];
        if(methodName.equals("queryMyRecruit")){
            Result<RegistrationVo> result = queryMy(req,resp,userId);
            Map res = new HashMap<>();
            res.put("code",result.getCode());
            res.put("data",result.getData());
            result.setMsg("");
            res.put("msg",result.getMsg().toString());
            resp.getWriter().write(JSON.toJSONString(res));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        while((line=reader.readLine())!=null){
            jsonRequest.append(line);
        }
        String requestBody = jsonRequest.toString();
        String requestURI = req.getRequestURI();
        String []split =requestURI.split("/");
        String methodName =split[split.length-1];
        System.out.println(requestBody);
        if(methodName.equals("insertRegistration")){
            RegistrationDto registrationDto = JSONObject.parseObject(requestBody,RegistrationDto.class);
            System.out.println(registrationDto);
            Result<RegistrationDto> result = save(req,resp,registrationDto);
            Map res = new HashMap<>();
            res.put("code",result.getCode());
            result.setMsg("Success");
            res.put("msg",result.getMsg().toString());
            resp.getWriter().write(JSON.toJSONString(res));
        } else if (methodName.equals("updateRegistration")) {
            System.out.println(JSON.parseObject(requestBody,RegistrationVo.class));
            RegistrationVo registrationVo = JSON.parseObject(requestBody,RegistrationVo.class);
            Result<RegistrationVo> result = update(req,resp,registrationVo);
            Map res = new HashMap<>();
            res.put("code",result.getCode());
            result.setMsg("Success");
            res.put("msg",result.getMsg().toString());
            resp.getWriter().write(JSON.toJSONString(res));
        }
    }

    /** 新增报名表
     *  param: registrationDto
     * @param request
     * @param response
     * @return
     */
    private Result save(HttpServletRequest request,HttpServletResponse response,RegistrationDto registrationDto){
       try{registrationService.insertRegistration(registrationDto);
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
    private Result update(HttpServletRequest request,HttpServletResponse response,RegistrationVo registrationVo){
        try{registrationService.updateRegistration(registrationVo);
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
    private Result queryMy(HttpServletRequest request,HttpServletResponse response,Integer userId){
        try{RegistrationVo registrationVo =registrationService.queryMyRecruit(userId);
            RegistrationVo [] data = {registrationVo};
            Result<RegistrationVo[]> result = new Result<>(200,data);
            return result;
        }catch (QueryMyException e){
            Result<RegistrationVo[]> result = new Result<>(204,new RegistrationVo[]{});
            return result;
        }
    }
}
