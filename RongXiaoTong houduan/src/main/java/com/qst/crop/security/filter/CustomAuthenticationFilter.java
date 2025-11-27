package com.qst.crop.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qst.crop.security.entity.AuthenticationBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
/**
 * @author QST
 * @Description JSON格式的登录实现
 * @Date 2021-08-24
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        UsernamePasswordAuthenticationToken authRequest = null;
        if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                ||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            //use jackson to deserialize json
            ObjectMapper mapper = new ObjectMapper();
            //*UsernamePasswordAuthenticationToken authRequest = null;

            /**if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
                ObjectMapper mapper = new ObjectMapper();
                UsernamePasswordAuthenticationToken authRequest = null;**/
            try (InputStream is = request.getInputStream()) {
                    AuthenticationBean authenticationBean = mapper.readValue(is, AuthenticationBean.class);
                    authRequest = new UsernamePasswordAuthenticationToken(
                            authenticationBean.getUsername(), authenticationBean.getPassword());
            } catch (IOException e) {
                    e.printStackTrace();
                    authRequest = new UsernamePasswordAuthenticationToken(
                            "", "");
            } finally {
                    setDetails(request, authRequest);
                    //*return this.getAuthenticationManager().authenticate(authRequest);
            }
            if (authRequest == null) {
                authRequest = new UsernamePasswordAuthenticationToken("","");
            }


            return this.getAuthenticationManager().authenticate(authRequest);
            //transmit it to UsernamePasswordAuthenticationFilter
        }else {
            return super.attemptAuthentication(request, response);
        }
    }
}

