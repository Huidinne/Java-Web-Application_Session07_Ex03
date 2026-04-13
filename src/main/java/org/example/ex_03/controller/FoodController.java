package org.example.ex_03.controller;

import org.example.ex_03.model.Food;
import org.example.ex_03.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@GetMapping("/add")
	public String showForm(Model model) {
		model.addAttribute("food", new Food());
		return "add-food";
	}

	@PostMapping("/add")
	public String addFood(
			@RequestParam("name") String name,
			@RequestParam("price") double price,
			@RequestParam("category") String category,
			@RequestParam("image") MultipartFile file,
			Model model
	) {

		String result = foodService.addFood(name, price, category, file);

		if (!result.equals("OK")) {
			model.addAttribute("error", result);
			model.addAttribute("food", new Food());
			return "add-food";
		}

		model.addAttribute("success", "Thêm món ăn thành công!");
		model.addAttribute("food", new Food());

		return "add-food";
	}
}
