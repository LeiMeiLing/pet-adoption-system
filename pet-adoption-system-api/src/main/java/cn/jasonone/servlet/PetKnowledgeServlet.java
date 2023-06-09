package cn.jasonone.servlet;

import cn.jasonone.bean.PetKnowledge;
import cn.jasonone.bean.UserInfo;
import cn.jasonone.service.PetKnowledgeService;
import cn.jasonone.service.impl.PetKnowledgeServiceImpl;
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

@WebServlet("/petKnowledge/*")
public class PetKnowledgeServlet  extends HttpServlet {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            // 是否显示值为null的字段
            .serializeNulls()
            // 是否格式化json
            .setPrettyPrinting()
            .create();
    private PetKnowledgeService petKnowledgeService=new PetKnowledgeServiceImpl();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petKnowledgeService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/petKnowledge/insert":
                insert(req, resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }

    }
    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PetKnowledge petKnowledge = gson.fromJson(req.getReader(), PetKnowledge.class);
        petKnowledgeService.add(petKnowledge);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "添加成功");
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petKnowledgeService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/petKnowledge/findAll":
                findAll(req, resp);
                sqlSession.commit();
                break;
            case "/petKnowledge/selectByType":
                selectByType(req, resp);
                sqlSession.commit();
                break;
            case "/petKnowledge/selectByTime":
                selectByTime(req, resp);
                sqlSession.commit();
                break;
            case "/petKnowledge/createMessages":
                createMessages(req, resp);
                sqlSession.commit();
                break;
            default:
                super.doGet(req, resp);
        }
    }
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<PetKnowledge> petKnowledges = petKnowledgeService.findAll();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "查找成功");
        result.put("data", petKnowledges);
        resp.getWriter().write(gson.toJson(result));
    }
    private void selectByType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String essayType = req.getParameter("essayType");
        List<PetKnowledge> petKnowledges = petKnowledgeService.selectByType(essayType);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "查找成功");
        result.put("data", petKnowledges);
        resp.getWriter().write(gson.toJson(result));
    }
    private void selectByTime(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<PetKnowledge> petKnowledges = petKnowledgeService.selectByTime();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "查找成功");
        result.put("data", petKnowledges);
        resp.getWriter().write(gson.toJson(result));
    }
    private void createMessages(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<PetKnowledge> petKnowledges = petKnowledgeService.createMessages();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "生成成功");
        result.put("data", petKnowledges);
        resp.getWriter().write(gson.toJson(result));
    }
}
