package tyut.selab.taskservice.myutils;

/**
 * ClassName: XmlParser
 * Package: tyut.selab.taskservice.myutils
 * Description:
 *
 * @Author LLLLB
 * @Create 2024/5/21 22:00
 * @Version 1.0
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import tyut.selab.taskservice.view.TaskReportVo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlParser {
    public static TaskReportVo parseXml(Reader reader) throws Exception {
        // 创建一个新的DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 使用DocumentBuilder解析XML
        Document document = builder.parse(new InputSource(reader));
        // 获取根元素
        Element root = document.getDocumentElement();
        // 获取<data>元素
        NodeList dataList = root.getElementsByTagName("data");
        Element dataElement = (Element) dataList.item(0);
        // 从<data>元素中获取数据并设置到TaskReportVo对象中
        TaskReportVo taskReportVo = new TaskReportVo();
        taskReportVo.setReportId(Integer.parseInt(dataElement.getElementsByTagName("reportId").item(0).getTextContent()));
        taskReportVo.setTaskId(Integer.parseInt(dataElement.getElementsByTagName("taskId").item(0).getTextContent()));
        taskReportVo.setUserName(dataElement.getElementsByTagName("userName").item(0).getTextContent());
        taskReportVo.setReportStatus(Integer.parseInt(dataElement.getElementsByTagName("reportStatus").item(0).getTextContent()));
        taskReportVo.setDetails(dataElement.getElementsByTagName("details").item(0).getTextContent());
        // 解析日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reportTime = dateFormat.parse(dataElement.getElementsByTagName("reportTime").item(0).getTextContent());
        taskReportVo.setReportTime(reportTime);
        return taskReportVo;
    }
}