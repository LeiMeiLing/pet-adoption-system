package cn.jasonone.servlet;

import cn.jasonone.bean.Comment;
import cn.jasonone.service.CommentService;
import cn.jasonone.service.impl.CommentServiceImpl;
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

@WebServlet("/comment/*")
public class CommentServlet extends HttpServlet {
    CommentService commentService = new CommentServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        commentService.setSqlSession(sqlSession);
        Gson gson = new Gson();
        String issueId = req.getParameter("issueId");
        String commentId = req.getParameter("commentId");
        String content = req.getParameter("content");
        String commentName = req.getParameter("commentName");
        Comment comment = new Comment();
        comment.setIssueId(Integer.valueOf(issueId));
        comment.setCommentId(Integer.valueOf(commentId));
        comment.setContent(content);
        comment.setCommentName(commentName);
        commentService.addComment(comment);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "添加成功");
        gson.toJson(result,resp.getWriter());
        sqlSession.commit();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        commentService.setSqlSession(sqlSession);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();

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
        switch (requestURI) {
            case "/comment/findAll":
                String issueId = req.getParameter("issueId");
                PageInfo<Comment> all = commentService.findAll(Long.valueOf(issueId),pageNum,pageSize);
                Map<String,Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "查询成功");
                result.put("data",all);
                gson.toJson(result,resp.getWriter());
                sqlSession.commit();
                break;
            case "/comment/findSome":
                String issueId1 = req.getParameter("issueId");
                String commentName = req.getParameter("commentName");
                String content = req.getParameter("content");
                Comment comment = new Comment();
                comment.setIssueId(Integer.valueOf(issueId1));
                comment.setCommentName(commentName);
                comment.setContent(content);
                PageInfo<Comment> byUsernameOrComment = commentService.findByUsernameOrComment(comment,pageNum,pageSize);
                Map<String,Object> result1 = new HashMap<>();
                result1.put("code", 200);
                result1.put("msg", "查询成功");
                result1.put("data",byUsernameOrComment);
                gson.toJson(result1,resp.getWriter());
                sqlSession.commit();
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        commentService.setSqlSession(sqlSession);
        Gson gson = new Gson();
        String id = req.getParameter("id");
        commentService.deleteComment(Long.valueOf(id));
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        gson.toJson(result,resp.getWriter());
        sqlSession.commit();
    }
}
