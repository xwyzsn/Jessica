package jessca.study.security;

import jessca.study.common.Result;
import cn.hutool.json.JSONUtil;
import jessca.study.jwtUtils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

   @Autowired
    JwtUtils jwtUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        String jwt =jwtUtils.generateToken(authentication.getName());
        httpServletResponse.setHeader(jwtUtils.getHeader(),jwt);
        Result result = Result.succ("success");
        servletOutputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
