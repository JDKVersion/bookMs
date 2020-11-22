package cn.test.bookms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.test.bookms.entity.MsMembers;
import cn.test.bookms.service.MsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.bookms.entity.MsAdmin;
import cn.test.bookms.service.MsAdminService;
import cn.test.bookms.util.Message;




@Controller
public class LoginController {

	@Autowired
	private MsAdminService msAdminService;

	@Autowired
	private MsMemberService msMemberService;

	@RequestMapping(value = "/login")
	public String toLogin(HttpSession httpSession, Model model, HttpServletRequest request) {
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		Map<String,String> map = new HashMap<String,String>();
		map.put("adminNumber", number);
		map.put("adminPwd", password);
		MsAdmin msAdmin = msAdminService.selectAdmin(map);
		MsMembers member =msMemberService.selectByNo(number,password);
		if(msAdmin != null) {
			httpSession.setAttribute("msAdmin", msAdmin);
			//httpSession.setMaxInactiveInterval(1800);  //默认存在半个小时  设置回话存在时长 秒单位
			httpSession.setAttribute("imgPath", Message.IMG_PATH);
			return "redirect:/showBook";
		}else if(member !=null){
			httpSession.setAttribute("member", member);
			return "redirect:/showmemberrecords";
		}else {
			model.addAttribute("Login_error", Message.LOGIN_FAILED_MSG);
			return "login";
		}

	}

	@RequestMapping(value = "/register")
	public String toRegister(){
		return "addMember";
	}

	/**
	 * 安全退出
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String adminLogout(HttpSession httpSession) {
		MsAdmin msAdmin = (MsAdmin) httpSession.getAttribute("msAdmin");  //从sesion中获取MsAdmin对象
		MsMembers member = (MsMembers) httpSession.getAttribute("member");
		if(msAdmin !=null) {  
			httpSession.removeAttribute("msAdmin");  //移除
			return "login";   //重定向到登登录界面
		}
		if(member !=null) {
			httpSession.removeAttribute("member");  //移除
			return "login";   //重定向到登登录界面
		}
		return "login";
	}
	
}
