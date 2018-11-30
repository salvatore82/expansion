package it.sdeluca.expansion;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ExpansionInterceptor extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(ExpansionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("ExpandInterceptor.preHandle");
		String expand = request.getParameter("expand");
		if (expand != null) {
			LocalExpand.setExpands(Arrays.asList(expand.split(",")));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, //
			Object handler, ModelAndView modelAndView) throws Exception {
		log.debug("ExpandInterceptor.postHandle");
		if (LocalExpand.getExpands() != null && !LocalExpand.getExpands().isEmpty()) {
			LocalExpand.clean();
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
			Object handler, Exception ex) throws Exception {
		log.debug("ExpandInterceptor.afterCompletion");
		log.debug("Expansion are: ".concat(LocalExpand.getExpands()!=null ? LocalExpand.getExpands().toString() : ""));
	}

}