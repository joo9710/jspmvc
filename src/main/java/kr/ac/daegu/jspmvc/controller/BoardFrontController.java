package kr.ac.daegu.jspmvc.controller;

import kr.ac.daegu.jspmvc.biz.BoardCmd;
import kr.ac.daegu.jspmvc.biz.BoardListCmd;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.bbs") // jspmvc/*.bbs 으로 들어오는 모든 요청을 처리하는 서블릿
public class BoardFrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    } //get요청으로들어오면

    @Override//post로 바로 보냄
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String cmdURI = requestURI.substring(contextPath.length());
        // log
        System.out.println("requestURI = " + requestURI);
        System.out.println("contextPath = " + contextPath);
        System.out.println("cmdURI = " + cmdURI);

        BoardCmd cmd = null;
        String viewPage = null;

        // 글 목록 조회 처리(/jspmvc/boardList.bbs)
        if(cmdURI.equals("/boardList.bbs")){
            cmd = new BoardListCmd();
            cmd.execute(request, response);
            viewPage = "view/boardList.jsp";
        } //어떻게 처리해줄지, 역할과 책임이 분리되는 장식


        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
        dispatcher.forward(request, response);

    }
}