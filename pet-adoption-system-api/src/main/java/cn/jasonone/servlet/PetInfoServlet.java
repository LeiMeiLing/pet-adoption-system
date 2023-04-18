package cn.jasonone.servlet;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.PetInfo;
import cn.jasonone.bean.PetIssue;
import cn.jasonone.service.GoodsInfoService;
import cn.jasonone.service.PetInfoService;
import cn.jasonone.service.impl.GoodsInfoServiceImpl;
import cn.jasonone.service.impl.PetInfoServiceImpl;
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

@WebServlet("/pet/*")
public class PetInfoServlet extends HttpServlet {
   PetInfoService petInfo= new PetInfoServiceImpl();
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
        petInfo.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        int pageNum = 1;
        int pageSize = 3;
        if (page != null) {
            pageNum = Integer.parseInt(page);
        }
        if (limit != null) {
            pageSize = Integer.parseInt(limit);
        }
        switch (requestURI) {
            case "/pet/findAll":
                List<PetInfo> pets= petInfo.findAllPet();
                Map<String,Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "获取成功");
                result.put("data",pets);
                resp.getWriter().write(gson.toJson(result));
                sqlSession.commit();
                break;
            case "/pet/findSome":
                List<PetInfo> some = findSome(req, resp);
                Map<String,Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "获取成功");
                result1.put("data",some);
                resp.getWriter().write(gson.toJson(result1));
                sqlSession.commit();
                break;
            case "/pet/find":
                find(req,resp,pageNum,pageSize);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petInfo.setSqlSession(sqlSession);

        PetInfo petInfo1 = gson.fromJson(req.getReader(), PetInfo.class);
        petInfo.update(petInfo1);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","修改成功");
        resp.getWriter().write(gson.toJson(map));
        sqlSession.commit();

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petInfo.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/pet/add":
                add(req, resp);
                sqlSession.commit();
                sqlSession.close();
                break;
            case "/pet/update":
                update(req, resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PetInfo petInfo1 = gson.fromJson(req.getReader(), PetInfo.class);
        System.out.println(petInfo1);
        petInfo.add(petInfo1);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","添加成功");
        resp.getWriter().write(gson.toJson(map));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petInfo.setSqlSession(sqlSession);

        PetInfo petInfo1 = gson.fromJson(req.getReader(), PetInfo.class);
        petInfo.deletePet((long)(petInfo1.getPetId()));
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","删除成功");
        resp.getWriter().write(gson.toJson(map));
        sqlSession.commit();

    }
    private List<PetInfo> findSome(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String petName = req.getParameter("petName");
        String variety = req.getParameter("variety");
        String petSex = req.getParameter("petSex");
        String petStatus = req.getParameter("petStatus");
        PetInfo petInfo1 = new PetInfo();
        petInfo1.setPetName(petName);
        petInfo1.setPetStatus(petStatus);
        petInfo1.setPetSex(petSex);
        petInfo1.setVariety(variety);
        return petInfo.selectNameOrType(petInfo1);

    }
    private void find(HttpServletRequest req, HttpServletResponse resp,int pageNum , int pageSize) throws IOException {
        PageInfo<PetInfo> all = petInfo.findAll(pageNum,pageSize);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "获取成功");
        result.put("data",all);
        resp.getWriter().write(gson.toJson(result));
    }
}
