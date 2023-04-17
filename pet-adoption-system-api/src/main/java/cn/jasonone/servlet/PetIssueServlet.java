package cn.jasonone.servlet;

import cn.jasonone.bean.PetIssue;
import cn.jasonone.service.PetIssueService;
import cn.jasonone.service.impl.PetIssueServiceImpl;
import com.github.pagehelper.PageInfo;
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

@WebServlet("/petIssue/*")
public class PetIssueServlet extends HttpServlet {
    PetIssueService petIssueService = new PetIssueServiceImpl();
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
        petIssueService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        int pageNum = 1;
        int pageSize = 10;
        if (page != null) {
            pageNum = Integer.parseInt(page);
        }
        if (limit != null) {
            pageSize = Integer.parseInt(limit);
        }
        switch (requestURI) {
            case "/petIssue/findAll":
                find(req,resp,pageNum,pageSize);
                sqlSession.commit();
                break;
            case "/petIssue/findMy":
                findMy(req,resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petIssueService.setSqlSession(sqlSession);
        PetIssue petIssue = gson.fromJson(req.getReader(), PetIssue.class);
        petIssueService.add(petIssue);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "添加成功");
        gson.toJson(result,resp.getWriter());
        sqlSession.commit();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petIssueService.setSqlSession(sqlSession);
        PetIssue petIssue = gson.fromJson(req.getReader(), PetIssue.class);
        petIssueService.del(petIssue.getId());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        gson.toJson(result,resp.getWriter());
        sqlSession.commit();
    }

    private void find(HttpServletRequest req, HttpServletResponse resp,int pageNum , int pageSize) throws IOException {
        PageInfo<PetIssue> all = petIssueService.findAll(pageNum,pageSize);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "获取成功");
        result.put("data",all);
        resp.getWriter().write(gson.toJson(result));
    }
    private void findMy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        List<PetIssue> my = petIssueService.findMy(Long.valueOf(id));
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "获取成功");
        result.put("data",my);
        resp.getWriter().write(gson.toJson(result));
    }
}





















