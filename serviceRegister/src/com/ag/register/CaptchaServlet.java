package com.ag.register;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;
/**
 * 
 * @author fb421
 * 添加验证码服务
 */
@WebServlet(urlPatterns="/captcha")
public class CaptchaServlet implements Servlet {
	
	private static final char[] chars={'0','1','2','3','4','5','6','7','8','9'};
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		  Captcha captcha = new Captcha.Builder(90, 50).addText(new DefaultTextProducer(4,chars)).addBackground(new GradiatedBackgroundProducer()).build();
		  ((HttpServletRequest)req).getSession(true).setAttribute("checkCode", captcha.getAnswer());
		  CaptchaServletUtil.writeImage((HttpServletResponse)res, captcha.getImage());
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
	}
	
	
}
