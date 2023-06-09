package com.sdd.sddpartner.common.security.jwt.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdd.sddpartner.common.security.domain.CustomUser;
import com.sdd.sddpartner.common.security.jwt.constants.SecurityConstants;
import com.sdd.sddpartner.common.security.jwt.provider.JwtTokenProvider;
import com.sdd.sddpartner.prop.ShopProperties;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

//	private final JwtTokenProvider jwtTokenProvider;

    private ShopProperties shopProperties;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//    	String header = request.getHeader(SecurityConstants.TOKEN_HEADER);
//
//        if (isEmpty(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        Authentication authentication = jwtTokenProvider.getAuthentication(header);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean isEmpty(final CharSequence cs) {
//    	return cs == null || cs.length() == 0;
//    }

    public JwtRequestFilter(ShopProperties shopProperties) {
        this.shopProperties = shopProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.TOKEN_HEADER);

        if (isEmpty(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = this.getAuthentication(header);

        log.info("authentication = " + authentication);
        log.info("authentication.getPrincipal() = " + authentication.getPrincipal());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        if (isNotEmpty(tokenHeader)) {
            try {
                byte[] signingKey = this.shopProperties.getSecretKey().getBytes();

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(tokenHeader.replace("Bearer ", ""));

                Claims claims = parsedToken.getBody();

                String userId = (String)claims.get("uid");

                List<SimpleGrantedAuthority> authorities = ((List<?>) claims.get("rol"))
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());

                if (isNotEmpty(userId)) {
                    UserDetails userDetails = new CustomUser(userId, "", authorities);

                    return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", tokenHeader, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", tokenHeader, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", tokenHeader, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", tokenHeader, exception.getMessage());
            }
        }

        return null;
    }

    private boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    private boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
}
