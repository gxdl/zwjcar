package com.gxdl.controller;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxdl.common.ActiverUser;
import com.gxdl.common.ResultObj;
import com.gxdl.common.WebUtils;
import com.gxdl.domain.Loginfo;
import com.gxdl.service.LoginfoService;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

/**
 * <p>
 *  登陆前端控制器
 * </p>
 *
 *
 */
@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	private LoginfoService loginfoService;
	
	@RequestMapping("login")

	public ResultObj login(String loginname,String pwd,String code,HttpSession session) {
		Object codeSession =  session.getAttribute("code");
		System.out.println("codesession:"+codeSession);
		System.out.println("code:"+code);
		if (code!=null && code.equals(codeSession)) {
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token=new UsernamePasswordToken(loginname, pwd);
			try {
				subject.login(token);
				ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
				WebUtils.getSession().setAttribute("user", activerUser.getUser());
				
				//记录登录日志
				
				Loginfo entity = new Loginfo();
				entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
				entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
				
				entity.setLogintime(new Date());
				loginfoService.save(entity);
				
				return ResultObj.LOGIN_SUCCESS;
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return ResultObj.LOGIN_ERROR_PASS;
			}
		}else {
			return new ResultObj(-1,"验证码错误");
		}
		
		
	}
	
	@RequestMapping("getCode")
	public void getCode(HttpServletResponse resp,HttpSession session) throws IOException {
		
		System.out.println(resp.getStatus());
		
		//定义图形验证码的长、宽、验证码字符数、干扰线宽度
		CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 5);
		//得到code
		String code = captcha.getCode();
		System.out.println(code);
		//放到session
		session.setAttribute("code", code);
		System.out.println(session);
		//输出流
		ServletOutputStream outputStream = resp.getOutputStream();
		System.out.println(outputStream);
		//读写输出流
		captcha.write(outputStream);
		//关闭输出流
		outputStream.close();
	}

	
	
}

