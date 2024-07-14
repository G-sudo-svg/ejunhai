package ;com.ejunhai.junhaimall.filter

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejunhai.junhaimall.system.model.SystemMgtUser;

public class SystemMgtFilter implements Filter {

    private String excludePatterns;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        this.excludePatterns = arg0.getInitParameter("excludePatterns");
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException {
        // 1.是否已排除过滤该请求
        String uri = ((HttpServletRequest) arg0).getRequestURI();
        if (excludePatterns.equalsIgnoreCase(uri)) {
            arg2.doFilter(arg0, arg1);
        } else {
            // 2.判断是否登录
            SystemMgtUser systemMgtUser = SystemSessionManager.get((HttpServletRequest) arg0);
            if (systemMgtUser == null) {
                ((HttpServletResponse) arg1).sendRedirect("/toLoginSystem.sc");
            } else if (!"superadmin".equals(systemMgtUser.getRole())) {
                // 限制普通用户访问优惠券和系统配置
                if (uri.contains("coupon") | uri.contains("couponScheme") ) {
                    ((HttpServletResponse) arg1).sendRedirect("/toForbidden.sc");
                } else {
                    arg2.doFilter(arg0, arg1);
                }
            } else {
                arg2.doFilter(arg0, arg1);
            }
            arg2.doFilter(arg0, arg1);
        }
    }

    @Override
    public void destroy() {
    }
}
