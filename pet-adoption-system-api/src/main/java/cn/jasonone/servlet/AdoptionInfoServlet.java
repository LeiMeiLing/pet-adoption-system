package cn.jasonone.servlet;

import cn.jasonone.bean.AdoptInfo;
import cn.jasonone.service.AdoptInfoService;
import cn.jasonone.service.impl.AdoptInfoServiceImpl;
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

@WebServlet("/adoption/*")
public class AdoptionInfoServlet extends HttpServlet {
    private AdoptInfoService adoptInfoService=new AdoptInfoServiceImpl();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        adoptInfoService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        // 去除contextPath
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/adoption/add":
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
        adoptInfoService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());

        switch (requestURI) {
            case "/adoption/update":
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
        adoptInfoService.setSqlSession(sqlSession);

        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        Gson gson = new Gson();
        switch (requestURI) {
            case "/adoption/findAll":
                List<AdoptInfo> adoptInfos = adoptInfoService.findAll();
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "查找成功");
                result.put("data", adoptInfos);
                resp.getWriter().write(gson.toJson(result));
                sqlSession.commit();
                break;
            case "/adoption/find":
                AdoptInfo application = gson.fromJson(req.getReader(),AdoptInfo.class);
                AdoptInfo adoptInfo = adoptInfoService.selectById(application.getId());
                Map<String, Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "查找成功");
                result1.put("data", adoptInfo);
                resp.getWriter().write(gson.toJson(result1));
                sqlSession.commit();
                break;
            case "/adoption/findName":
                String username = req.getParameter("username");
                List<AdoptInfo> name = adoptInfoService.findName(username);
                Map<String, Object> result2 = new HashMap<>();
                result2.put("code", 200);
                result2.put("msg", "查找成功");
                result2.put("data", name);
                resp.getWriter().write(gson.toJson(result2));
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        AdoptInfo adoptInfo = gson.fromJson(req.getReader(), AdoptInfo.class);

        adoptInfoService.add(adoptInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "提交申请成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        AdoptInfo adoptInfo = gson.fromJson(req.getReader(), AdoptInfo.class);
        adoptInfoService.updateStatus(adoptInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));
    }
}
