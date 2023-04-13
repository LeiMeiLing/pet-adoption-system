package cn.jasonone.servlet;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.service.GoodsInfoService;
import cn.jasonone.service.impl.GoodsInfoServiceImpl;
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

@WebServlet("/petstore/*")
public class GoodsServlet extends HttpServlet {
    GoodsInfoService gs = new GoodsInfoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        gs.setSqlSession(sqlSession);
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
            case "/petstore/findAll":
                PageInfo<GoodsInfo> goods= gs.findAll(pageNum,pageSize);
                Map<String,Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "获取成功");
                result.put("data",goods);
                resp.getWriter().write(gson.toJson(result));
                break;
            case "/petstore/findSome":
                PageInfo<GoodsInfo> some = findSome(req, resp, pageNum, pageSize,gs);
                Map<String,Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "获取成功");
                result1.put("data",some);
                resp.getWriter().write(gson.toJson(result1));
                break;
            case "/petstore/findById":
                GoodsInfo goodsInfo = findId(req, resp, gs);
                Map<String,Object> rs = new HashMap<>();
                rs.put("code", 200);
                rs.put("msg", "获取成功");
                rs.put("data",goodsInfo);
                resp.getWriter().write(gson.toJson(rs));
                break;
            default:
                super.doPut(req, resp);
        }
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
        Gson gson = new Gson();

        GoodsInfo goods = gson.fromJson(req.getReader(), GoodsInfo.class);
        gs.delete((long)goods.getId());
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void add(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        GoodsInfo goods = gson.fromJson(req.getReader(), GoodsInfo.class);
        gs.add(goods);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "添加成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        GoodsInfo goods = gson.fromJson(req.getReader(), GoodsInfo.class);
        gs.update(goods);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private PageInfo<GoodsInfo> findSome(HttpServletRequest req, HttpServletResponse resp, Integer pageNum, Integer pageSize, GoodsInfoService gs) throws IOException{
        Gson gson = new Gson();
        String goodsname = req.getParameter("goodsname");
        String goodsType = req.getParameter("goodsType");
        GoodsInfo goods = new GoodsInfo();
        goods.setGoodsname(goodsname);
        goods.setGoodsType(goodsType);
        return gs.selectNameOrType(pageNum,pageSize,goods);
    }
    private GoodsInfo findId(HttpServletRequest req, HttpServletResponse resp, GoodsInfoService gs){
        Gson gson = new Gson();
        String str = req.getParameter("goodsId");
        int id = Integer.parseInt(str);
        return gs.selectById(id);
    }
}
