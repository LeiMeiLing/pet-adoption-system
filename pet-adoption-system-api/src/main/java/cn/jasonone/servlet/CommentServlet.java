package cn.jasonone.servlet;

import cn.jasonone.bean.Comment;
import cn.jasonone.service.CommentService;
import cn.jasonone.service.impl.CommentServiceImpl;
import com.google.gson.Gson;
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

@WebServlet("/comment")
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
        Gson gson = new Gson();
        String issueId = req.getParameter("issueId");
        List<Comment> all = commentService.findAll(Long.valueOf(issueId));
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data",all);
        gson.toJson(result,resp.getWriter());


    }
}
