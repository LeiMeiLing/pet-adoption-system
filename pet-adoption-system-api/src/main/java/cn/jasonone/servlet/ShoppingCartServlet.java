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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            case "/shoppingCart/findSome":
                HashMap<String, Double>  some = findSome(req, resp);
                Map<String,Object> result1 = new HashMap<>();
                result1.put("code",200);
                result1.put("msg", "获取成功");
                result1.put("data",some);
                resp.getWriter().write(gson.toJson(result1));
                sqlSession.commit();
                break;
            case "/shoppingCart/update":
                updateShopping(req, resp);
                Map<String, Object> result2 = new HashMap<>();
                result2.put("code", 200);
                result2.put("msg", "修改成功");

                resp.getWriter().write(gson.toJson(result2));
                sqlSession.commit();
                break;

            default:
                super.doPut(req, resp);
        }
    }



    private void updateShopping(HttpServletRequest req, HttpServletResponse resp) {
        String shoppingCarts = req.getParameter("shoppingCart");
        System.out.println(shoppingCarts);
        String[] split = shoppingCarts.split(",");
            ShoppingCart shoppingCart = new ShoppingCart();
            for (int i1 = 0; i1 < (split.length)/2;i1++) {
                System.out.println(Integer.valueOf(split[2 * i1]));
                shoppingCart.setId(Integer.valueOf(split[2*i1]));
                shoppingCart.setQuantity(Integer.valueOf(split[2*i1+1]));
                shoppingCartService.update(shoppingCart);

            }



    }

    private HashMap<String, Double> findSome(HttpServletRequest req, HttpServletResponse resp) {
        String[] id = req.getParameterValues("id");
        ShoppingCart shoppingCart = new ShoppingCart();
        double totalPrice=0;
        HashMap<String, Double> stringShoppingCartHashMap = new HashMap<>();
        for (int i = 0; i <id.length ; i++) {
            shoppingCart.setId(Integer.valueOf(id[i]));
            ShoppingCart some = shoppingCartService.findSome(id[i]);
            totalPrice +=some.getUnitPrice()*some.getQuantity();
        }
        stringShoppingCartHashMap.put("totalPrice", totalPrice);
        return stringShoppingCartHashMap;

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        shoppingCartService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        // 去除contextPath
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/shoppingCart/deleteGoods":
                deleteGoods(req, resp);
                sqlSession.commit();
                break;
            case "/shoppingCart/delete":
                deleteAll(req,resp);
                HashMap<Object, Object> map = new HashMap<>();
                map.put("code",200);
                map.put("msg","删除成功");
                gson.toJson(map,resp.getWriter());
                sqlSession.commit();

                break;
            default:
                super.doPut(req, resp);
        }
    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ids = req.getParameter("ids");
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {
            shoppingCartService.delete(split[i]);
        }

    }

    private void deleteGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ShoppingCart shoppingCart = gson.fromJson(req.getReader(), ShoppingCart.class);
        shoppingCartService.deleteGoods(shoppingCart);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void join(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ShoppingCart shoppingCart = gson.fromJson(req.getReader(), ShoppingCart.class);
        System.out.println(shoppingCart);
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
