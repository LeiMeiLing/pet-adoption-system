package cn.jasonone.servlet;

import cn.jasonone.bean.TransferApplication;
import cn.jasonone.service.TransferApplicationService;
import cn.jasonone.service.impl.TransferApplicationServiceImpl;
import com.google.gson.Gson;
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

@WebServlet("/application/*")
public class ApplicationServlet extends HttpServlet {
    private TransferApplicationService applicationService=new TransferApplicationServiceImpl();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        applicationService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        // 去除contextPath
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/application/add":
                add(req, resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        applicationService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());

        switch (requestURI) {
            case "/application/update":
                updateStatus(req, resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        applicationService.setSqlSession(sqlSession);

        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        Gson gson = new Gson();
        switch (requestURI) {
            case "/application/findAll":
                List<TransferApplication> applications = applicationService.findAll();
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "获取成功");
                result.put("data", applications);
                resp.getWriter().write(gson.toJson(result));
                sqlSession.commit();
                break;
            case "/application/find":
                TransferApplication application = gson.fromJson(req.getReader(),TransferApplication.class);
                TransferApplication transferApplication = applicationService.selectById(application.getId());
                Map<String, Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "获取成功");
                result1.put("data", transferApplication);
                resp.getWriter().write(gson.toJson(result1));
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        TransferApplication application = gson.fromJson(req.getReader(), TransferApplication.class);

        applicationService.add(application);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "注册成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        TransferApplication application = gson.fromJson(req.getReader(), TransferApplication.class);
        applicationService.updateStatus(application);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));
    }

}
