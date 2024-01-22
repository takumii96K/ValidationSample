package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.CalcForm;
import com.example.demo.validator.CalcValidator;

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
	/**インジェクション*/
	@Autowired
	CalcValidator calcValidator;
	
	/**相関チェック登録*/
	@InitBinder("calcForm") //Modelに格納されるローワーのチェックしたいクラス名（識別名）を指定する。
	//※指定しない場合全てのオブジェクトが対象になり、対象外の場合例外になる。
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(calcValidator);
		//webDataBinderインターフェースのaddメソッドでバリテーション機能を相関チェックとして登録する
	}
}
