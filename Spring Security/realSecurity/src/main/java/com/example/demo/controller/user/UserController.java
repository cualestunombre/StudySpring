package com.example.demo.controller.user;

import com.example.demo.domain.entity.Account;
import com.example.demo.domain.dto.AccountDto;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value="/mypage")
	public String myPage() throws Exception {
		return "user/mypage";
	}

	@GetMapping("/login")
	public String loginPage(Model model, @RequestParam(value = "exception",required = false)String exception){
		model.addAttribute("exception",exception);
		return "user/login/login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request,response,auth);
		}
		return "redirect:/";
	}
	@GetMapping("/denied")
	public String denied(@RequestParam(value = "exception",defaultValue = "인가 실패")String exception,Model model,Authentication authentication){
		model.addAttribute("exception",exception);
		model.addAttribute("username",authentication.getName());
		return "user/login/denied";
	}
	@GetMapping("/users")
	public String createUser(){
		return "user/login/register";
	}

	@PostMapping("/users")
	public String createUser(AccountDto accountDto){
		Account account = Account.createAccount(accountDto);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		userService.createUser(account);

		return "redirect:/";
	}
}
