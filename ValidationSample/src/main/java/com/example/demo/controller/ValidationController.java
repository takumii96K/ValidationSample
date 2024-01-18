package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.CalcForm;

@Controller
public class ValidationController {
	/**「form-backing beanの初期化」*/
	@ModelAttribute
	public CalcForm setUpForm() {
		return new CalcForm();
	}

	/**入力画面を表示する*/
	@GetMapping("show")
	public String showView() {
		return "entry";
	}


	@PostMapping("calc")
	public String confirmView(@Validated CalcForm form, BindingResult bindingResult, Model model) {
		//入力された場合
		if(bindingResult.hasErrors()) {
			//入力画面へ
			return "entry";
		}
		//加算実行
		Integer result = form.getLeftNum() + form.getRightNum();
		//Modelに格納する
		model.addAttribute("result", result);
		return "confirm";
	}
}
