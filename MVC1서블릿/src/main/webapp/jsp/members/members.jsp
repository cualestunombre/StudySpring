<%@ page import ="java.util.List" %>
<%@ page import ="hello.demo.domain.Member"%>
<%@ page import ="hello.demo.repository.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();

%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <a href="new-form.jsp">등록</a>
    <table>
        <thead>
            <th>id</th>
            <th>age</th>
            <th>name</th>ç
        </thead>
        <tbody>
        <%
            for(Member member :members){
                out.write("<tr>");
                out.write("<td>"+member.getId()+"</td>");
                out.write("<td>"+member.getAge()+"</td>");
                out.write("<td>"+member.getName()+"</td>");
                out.write("</tr>");
            }
        %>
        </tbody>
    </table>
</body>
</html>