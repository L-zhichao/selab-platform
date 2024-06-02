package tyut.selab.taskservice.dao;
import org.junit.jupiter.api.Test;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.domain.TaskReport;

import java.util.Date;

public class TestTaskReportDao {

    TaskReportDao taskReportDao=new TaskReportDaoImpl();
    @Test
    public void insert(){
      ///  TaskReport taskReport=new TaskReport(1,1,0,"汇报测试",new Date());
//
//        Integer insert = taskReportDao.insert(taskReport);
//        System.out.println(insert);
    }
}
