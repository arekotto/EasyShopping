package com.easydevs.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 02.01.2017.
 */
@Component("authorizationFilter")
public class AuthFilter extends OncePerRequestFilter{

    private final Logger log = LoggerFactory.getLogger(getClass());


    private final List<String> filteredUrls = new ArrayList<String>() {{
        add("/user/logout");
        add("/user/homepage");
        add("/user/edit");
        add("/user/save");
        add("/user/edit-password");
        add("/user/save-password");
        add("/product/createForm");
        add("/product/create");
        add("/product/edit");
        add("/product/save");
        add("/product/remove");
        add("/product/user");
        add("/purchase-invoice/view-all");
        add("/purchase-invoice/create");
//        add("/cart/view");
//        add("/cart/add");
//        add("/cart/remove");

    }};

    private final List<String> authenticationUrls = new ArrayList<String>() {{
        add("/user/create");
        add("/user/register");
        add("/user/login");

    }};

    @Autowired
    private RequestVerificationService requestVerificationService;

    @Autowired
    private MultipartResolver multipartResolver;

    /**
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        boolean excluded = !filteredUrls.contains(requestURI);
        if (multipartResolver.isMultipart(request)) {
            if (!(request instanceof MultipartHttpServletRequest)) {
                request = multipartResolver.resolveMultipart(request);
            }
        }
        boolean verified = requestVerificationService.verifyRequest(request);
        request.setAttribute("isRequestVerified", verified);


        if (verified && authenticationUrls.contains(requestURI)) {
            response.sendRedirect("/user/homepage");
        } else if(excluded || verified){
            filterChain.doFilter(request, response);
            log.warn("okkk");

        } else {
            log.warn("Invalid token!! URL: " + getUrl(request) + " , token: " + request.getParameter("token"));
            response.sendRedirect("/user/login");

        }
    }

    /**
     *
     * @param req
     * @return
     */
    private String getUrl(HttpServletRequest req) {
        String reqUrl = req.getRequestURL().toString();
        String queryString = req.getQueryString();

        if (queryString != null) {
            reqUrl += "?" + queryString;
        }
        return reqUrl;
    }
}
