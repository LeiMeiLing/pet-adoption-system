package cn.jasonone.servlet;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.PetInfo;
import cn.jasonone.service.GoodsInfoService;
import cn.jasonone.service.PetInfoService;
import cn.jasonone.service.impl.GoodsInfoServiceImpl;
import cn.jasonone.service.impl.PetInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/pet/*")
public class PetInfoServlet extends HttpServlet {
   PetInfoService petInfo= new PetInfoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        petInfo.setSqlSession(sqlSession);
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        int pageNum = 1;
        int pageSize =10;
        if (page != null){
            pageNum = Integer.parseInt(page);
        }
        if (limit != null){
            pageSize = Integer.parseInt(limit);
        }
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        Gson gson = new Gson();
        switch (requestURI) {
            case "/pet/findAll":
                PageInfo<PetInfo> pets= petInfo.findAllPet(pageNum,pageSize);
                Map<String,Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "获取成功");
                result.put("data",pets);
                resp.getWriter().write(gson.toJson(result));
                break;
            case "/pet/findSome":
                PageInfo<PetInfo> some = findSome(req, resp, pageNum, pageSize,petInfo);
                Map<String,Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "获取成功");
                result1.put("data",some);
                resp.getWriter().write(gson.toJson(result1));
                break;
            default:
                super.doPut(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        PetInfo petInfo1 = gson.fromJson(req.getReader(), PetInfo.class);
        petInfo.update(petInfo1);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","修改成功");
        resp.getWriter().write(gson.toJson(map));

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        PetInfo petInfo1 = gson.fromJson(req.getReader(), PetInfo.class);
        petInfo.add(petInfo1);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","添加成功");
        resp.getWriter().write(gson.toJson(map));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        PetInfo petInfo1 = gson.fromJson(req.getReader(), PetInfo.class);
        petInfo.deletePet((long)(petInfo1.getPetId()));
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","删除成功");
        resp.getWriter().write(gson.toJson(map));
    }
    private PageInfo<PetInfo> findSome(HttpServletRequest req, HttpServletResponse resp, Integer pageNum, Integer pageSize, PetInfoService petInfos) throws IOException{
        Gson gson = new Gson();
        String petnames = req.getParameter("petName");
        String petstatus = req.getParameter("petStatus");
        PetInfo petInfo = new PetInfo();
        petInfo.setPetName(petnames);
        petInfo.setPetStatus(petstatus);
        return petInfos.selectNameOrType(pageNum,pageSize,petInfo);

    }
}
