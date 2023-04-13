package cn.jasonone.servlet;

import cn.hutool.jwt.JWTUtil;
import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.ManagerInfo;
import cn.jasonone.bean.UserInfo;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.ManagerService;
import cn.jasonone.service.impl.ManagerServiceImpl;
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
@WebServlet("/manager/*")
public class ManageServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ManagerService managerService = new ManagerServiceImpl();
            String requestURI = req.getRequestURI();
            // 去除contextPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/manager/register":
                    register(req, resp);
                    break;
                default:
                    super.doPut(req, resp);
            }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        managerService.setSqlSession(sqlSession);
            String requestURI = req.getRequestURI();
            // 去除contextPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/manager/login":
                    login(req, resp);
                    break;
                default:
                    super.doPost(req, resp);
            }

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        Gson gson = new Gson();
        ManagerInfo managerInfo = gson.fromJson(req.getReader(), ManagerInfo.class);
        managerInfo=managerService.login(managerInfo);
        Map<String,Object> result = new HashMap<>();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(managerInfo == null){
            result.put("code", 400);
            result.put("msg", "用户名或密码错误");
        }else{
            // 创建token
            Map<String,Object> token = new HashMap<>();
            token.put("username", managerInfo.getUsername());
            token.put("id", managerInfo.getId());
            token.put("exp", System.currentTimeMillis()+1000*60*30);
            String jwtPassword = req.getServletContext().getInitParameter("jwt_password");
            String token1 = JWTUtil.createToken(token, jwtPassword.getBytes());

            result.put("code", 200);
            result.put("msg", "登录成功");
            result.put("data", managerInfo);
            result.put("token", token1);
        }
        resp.getWriter().write(gson.toJson(result));

    }
    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        ManagerService managerService = new ManagerServiceImpl();
        Gson gson = new Gson();
        ManagerInfo managerInfo= gson.fromJson(req.getReader(), ManagerInfo.class);

        managerService.register(managerInfo);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "注册成功");
        resp.getWriter().write(gson.toJson(result));
    }
}
