package cn.jasonone.servlet;

import cn.jasonone.bean.ShoppingCart;
import cn.jasonone.service.GoodsInfoService;
import cn.jasonone.service.ShoppingCartService;
import cn.jasonone.service.impl.ShoppingCartServiceImpl;
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
import java.util.Map;

@WebServlet("/shoppingCart/*")
public class ShoppingCartServlet extends HttpServlet {
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
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
        shoppingCartService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI){
            case "/shoppingCart/MyCart":
                PageInfo<ShoppingCart> shoppingCartPageInfo = selectMyCart(req, resp, shoppingCartService);
                Map<String,Object> result = new HashMap<>();
                result.put("code",200);
                result.put("msg", "获取成功");
                result.put("data",shoppingCartPageInfo);
                resp.getWriter().write(gson.toJson(result));
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        shoppingCartService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        // 去除contextPath
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/shoppingCart/join":
                join(req, resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    private void join(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        ShoppingCart shoppingCart = gson.fromJson(req.getReader(), ShoppingCart.class);
        shoppingCartService.join(shoppingCart);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "添加成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private PageInfo<ShoppingCart> selectMyCart(HttpServletRequest req, HttpServletResponse resp,ShoppingCartService shoppingCartService){
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        return shoppingCartService.selectMyCart(shoppingCart);
    }
}
