package cn.jasonone.servlet;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.PetInfo;
import cn.jasonone.service.GoodsInfoService;
import cn.jasonone.service.impl.GoodsInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/petstore/*")
public class GoodsServlet extends HttpServlet {
    GoodsInfoService gs = new GoodsInfoServiceImpl();
    Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        PageInfo<GoodsInfo> goods= gs.findAll(pageNum,pageSize);
        Gson gson = new Gson();
        gson.toJson(goods,resp.getWriter());
    }

    /**
     * 添加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/petstore/add":
                add(req, resp);
                break;
            case "/petstore/update":
                update(req, resp);
                break;

            default:
                super.doPut(req, resp);
        }
    }

    /**
     * 删除商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PetInfo pet = gson.fromJson(req.getReader(), PetInfo.class);
        gs.delete((long)pet.getPetId());
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void add(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        GoodsInfo goods = gson.fromJson(req.getReader(), GoodsInfo.class);
        gs.add(goods);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "添加成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        GoodsInfo goods = gson.fromJson(req.getReader(), GoodsInfo.class);
        gs.update(goods);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));
    }
}
