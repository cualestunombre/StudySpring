package com.example.demo.security.filter;

import com.example.demo.domain.entity.Resources;
import com.example.demo.domain.entity.Role;
import com.example.demo.service.ResourcesService;
import com.example.demo.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TerminalAuthorizationFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final ResourcesService resourcesService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (shouldSkipFilter(authentication)) {
            filterChain.doFilter(request, response);
            return;
        }

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        List<Resources> resources = getResourcesSortedByOrder();

        if (!isAuthorized(request, roles, resources)) {
            sendAccessDeniedResponse(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean shouldSkipFilter(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private List<Resources> getResourcesSortedByOrder() {
        return resourcesService.getResources()
                .stream()
                .sorted(Comparator.comparingInt(Resources::getOrderNum))
                .collect(Collectors.toList());
    }

    private boolean isAuthorized(HttpServletRequest request, List<String> roles, List<Resources> resources) {
        for (Resources resource : resources) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resource.getResourceName());
            if (requestMatcher.matches(request)) {
                // 접속하기 위해 필요한 권한
                // roles 는 내가 가진 권한
                Set<Role> resourceRoles = resource.getRoleSet();
                for (Role resourceRole : resourceRoles) {
                    for(String role : roles){
                        if(match(resourceRole,role))return true;
                    }
                }
                return false; // 권한 없음
            }
        }
        return true; // 매칭되는 리소스가 없음
    }
    private boolean match(Role resourceRole,String userRole){
        return resourceRole.getRoleName().equals(userRole);
    }

    private void sendAccessDeniedResponse(HttpServletResponse response) throws IOException {
        String staticHtmlPath = "/Users/woo/Desktop/code/StudySpring/Spring Security/realSecurity/src/main/resources/static/html/403.html";
        String htmlContent = readHtmlFile(staticHtmlPath);

        if (htmlContent != null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(htmlContent);
            out.flush();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String readHtmlFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
