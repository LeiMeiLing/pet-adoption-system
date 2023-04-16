package cn.jasonone.servlet;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.ObjectMapper;
import cn.hutool.jwt.JWTUtil;
import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.UserInfo;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.UserInfoService;
import cn.jasonone.service.impl.UserInfoServiceImpl;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static cn.hutool.crypto.SecureUtil.md5;

@WebServlet("/user/*")
public class UserInfoServlet extends HttpServlet {
    private UserInfoService userInfoService = new UserInfoServiceImpl();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        userInfoService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        // 去除contextPath
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/user/register":
                register(req, resp);
                sqlSession.commit();
                break;
            case "/user/update":
                update(req, resp);
                sqlSession.commit();
                break;
            default:
                super.doPut(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        userInfoService.setSqlSession(sqlSession);
        String requestURI = req.getRequestURI();
        // 去除contextPath
        requestURI = requestURI.substring(req.getContextPath().length());
        switch (requestURI) {
            case "/user/login":
                login(req, resp);
                break;
            case "/user/changePassword":
                changePassword(req,resp);
                sqlSession.commit();
                break;
            default:
                super.doPost(req, resp);
        }


    }
        //修改密码
    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        userInfoService.setSqlSession(sqlSession);
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
        UserInfo userInfo1 = userInfoService.find(Long.valueOf(userInfo.getId()));
        String salt = RandomUtil.randomString(6);
        userInfo1.setSalt(salt);
        userInfo1.setPassword(salt+userInfo.getPassword()+salt);
        userInfoService.update(userInfo1);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();

        UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
        userInfo = userInfoService.login(userInfo);
        Map<String, Object> result = new HashMap<>();
        if (userInfo == null) {
            result.put("code", 400);
            result.put("msg", "用户名或密码错误");
        } else {
            // 创建token
            Map<String, Object> token = new HashMap<>();
            token.put("username", userInfo.getUsername());
            token.put("id", userInfo.getId());
            token.put("exp", System.currentTimeMillis() + 1000 * 60 * 30);
            String jwtPassword = req.getServletContext().getInitParameter("jwt_password");
            String token1 = JWTUtil.createToken(token, jwtPassword.getBytes());

            result.put("code", 200);
            result.put("msg", "登录成功");
            result.put("data", userInfo);
            result.put("token", token1);
        }
        resp.getWriter().write(gson.toJson(result));

    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
        userInfoService.register(userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "注册成功");
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        userInfoService.setSqlSession(sqlSession);
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

        String requestURI = req.getRequestURI();
        requestURI = requestURI.substring(req.getContextPath().length());
        Gson gson = new Gson();
        switch (requestURI) {
            case "/user/findAll":
                PageInfo<UserInfo> userInfo = userInfoService.userFindAll(pageNum, pageSize);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "获取成功");
                result.put("data", userInfo);
                gson.toJson(result,resp.getWriter());
                sqlSession.commit();
                break;
            case "/user/findSome":
                PageInfo<UserInfo> some = findSome(req, resp, pageNum, pageSize);
                Map<String, Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "获取成功");
                result1.put("data", some);
                gson.toJson(result1, resp.getWriter());
                sqlSession.commit();
                break;
            case "/user/find":
                find(req,resp);
                break;
            default:
                super.doPut(req, resp);
        }
    }
        public void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
            userInfoService.setSqlSession(sqlSession);
            String password = req.getParameter("password");
            String id = req.getParameter("id");
            Gson gson = new Gson();
            UserInfo userInfo = userInfoService.find((long)(Integer.parseInt(id)));
            System.out.println(userInfo);
            String password1 = userInfo.getPassword();
            String salt = userInfo.getSalt();
            Map<String, Object> result1 = new HashMap<>();

            if(password1.equals(md5(salt+password+salt))){
                result1.put("code", 200);
                result1.put("msg", "密码正确");
                result1.put("data",userInfo);
                resp.getWriter().write(gson.toJson(result1));
            }else {
                result1.put("msg", "密码错误");
                resp.getWriter().write(gson.toJson(result1));
            }


        }

    /*
    管理员界面删除用户信息
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        userInfoService.setSqlSession(sqlSession);
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
        userInfoService.delete((long) userInfo.getId());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        resp.getWriter().write(gson.toJson(result));
        sqlSession.commit();
    }

    /*
    管理员界面对用户信息进行更新
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        userInfoService.setSqlSession(sqlSession);
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
        userInfoService.update(userInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private PageInfo<UserInfo> findSome(HttpServletRequest req, HttpServletResponse resp, Integer pageNum, Integer pageSize) throws IOException {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        return userInfoService.selectNameOrType(pageNum, pageSize, userInfo);
    }
}
