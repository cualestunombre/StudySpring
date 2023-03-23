<%@ page import="hello.demo.domain.Member"%>
<%@ page import="hello.demo.repository.MemberRepository"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
    MemberRepository memberRepository  = MemberRepository.getInstance();
    String username = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username,age);
    memberRepository.save(member);
    response.setStatus(302);
    response.setHeader("Location","http://localhost:8080/jsp/members/members.jsp");
%>