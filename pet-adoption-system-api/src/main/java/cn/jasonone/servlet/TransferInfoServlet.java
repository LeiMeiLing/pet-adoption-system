package cn.jasonone.servlet;

import cn.jasonone.bean.PetInfo;
import cn.jasonone.bean.TransferInfo;
import cn.jasonone.service.TransferInfoService;
import cn.jasonone.service.impl.TransferInfoServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/transferInfo/*")
public class TransferInfoServlet extends HttpServlet {
    private TransferInfoService transferInfoService=new TransferInfoServiceImpl();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            // 是否显示值为null的字段
            .serializeNulls()
            // 是否格式化json
            .setPrettyPrinting()
            .create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        transferInfoService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/transferInfo/findAll":
                List<TransferInfo> transferInfos= transferInfoService.findAll();
                Map<String,Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "获取成功");
                result.put("data",transferInfos);
                resp.getWriter().write(gson.toJson(result));
                sqlSession.commit();
                break;
            case "/transferInfo/selectStatus":
                List<TransferInfo> transferInfos1 = transferInfoService.selectStatus();
                Map<String,Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "获取成功");
                result1.put("data",transferInfos1);
                resp.getWriter().write(gson.toJson(result1));
                sqlSession.commit();
                break;
            case"/transferInfo/findName":
                String transferName = req.getParameter("transferName");
                List<TransferInfo> all = transferInfoService.findAll(transferName);
                Map<String,Object> result2 = new HashMap<>();
                result2.put("code", 200);
                result2.put("msg", "获取成功");
                result2.put("data",all);
                resp.getWriter().write(gson.toJson(result2));
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        transferInfoService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/transferInfo/add":
                add(req, resp);
                sqlSession.commit();
                break;
            case "/transferInfo/update":
                updateStatus(req,resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        transferInfoService.setSqlSession(sqlSession);
        TransferInfo transferInfo = gson.fromJson(req.getReader(), TransferInfo.class);
        transferInfoService.update(transferInfo);
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","修改成功");
        resp.getWriter().write(gson.toJson(map));
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        transferInfoService.setSqlSession(sqlSession);
        TransferInfo transferInfo = gson.fromJson(req.getReader(), TransferInfo.class);
        transferInfoService.add(transferInfo);
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","发布成功");
        resp.getWriter().write(gson.toJson(map));
    }
}
