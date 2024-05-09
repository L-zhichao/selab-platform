package tyut.selab.taskservice.controller;

import tyut.selab.bookservice.utils.Result;
import tyut.selab.taskservice.service.TaskManagerService;
import tyut.selab.taskservice.service.impl.TaskManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskController",urlPatterns = {})
public class TaskController extends HttpServlet {
    private TaskManagerService taskManagerService = new TaskManagerServiceImpl();
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
     *  查询任务信息（动态查询）
     *  传入 taskId & publisher(发布者) & groupName(任务小组) & taskName & status [可以都传，也可以传入单个参数 | 调用前先判断参数是都为空(是否有效) | 为空跳过此参数 ]
     * @return
     */
    private Result queryOne(HttpServletRequest request,HttpServletResponse response){
        Integer taskId = Integer.valueOf(request.getAttribute("taskId").toString());
        Integer status = Integer.valueOf(request.getAttribute("status").toString());
        String publisher = request.getAttribute("publisher").toString();
        String updater = request.getAttribute("updater").toString();
        String groupName = request.getAttribute("groupName").toString();
        String taskName = request.getAttribute("taskName").toString();

        return null;
    }

    /**
     *  查询所有书籍(分页查询)
     *  param: cur[当前页数] size[每页数量]
     * @param request
     * @param response
     * @return
     */
    private Result list(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**  删除书籍
     *   param: bookId
     * @param request
     * @param response
     * @return
     */
    private Result delect(HttpServletRequest request,HttpServletResponse response){return null;}


}
