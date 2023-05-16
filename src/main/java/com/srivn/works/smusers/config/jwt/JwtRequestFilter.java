package com.srivn.works.smusers.config.jwt;

import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.services.UserAdminService;
import com.srivn.works.smusers.util.AppMsg;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserAdminService userAdminService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            final String requestTokenHeader = request.getHeader("Authorization");

            String username = null;
            String jwtToken = null;
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);

                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }

            // Once we get the token validate it.
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userAdminService.loadUserByUserName(username);

                // if token is valid configure Spring Security to manually set
                // authentication
                try {
                    if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // After setting the Authentication in the context, we specify
                        // that the current user is authenticated. So it passes the
                        // Spring Security Configurations successfully.
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                } catch (Exception e) {
                    throw new SMException(AppMsg.Err.ERR_AUTH_0033.getCode(), AppMsg.Err.ERR_AUTH_0033.getMsg());
                }

            }
            chain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
            throw new SMException(AppMsg.Err.ERR_AUTH_0031.getCode(), AppMsg.Err.ERR_AUTH_0031.getMsg());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
            throw new SMException(AppMsg.Err.ERR_AUTH_0032.getCode(), AppMsg.Err.ERR_AUTH_0032.getMsg());
        } catch (Exception e) {
            System.out.println("Bad Request");
            throw new SMException(AppMsg.Err.ERR_AUTH_0033.getCode(), AppMsg.Err.ERR_AUTH_0033.getMsg());
        }
    }

}