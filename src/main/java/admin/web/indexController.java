package admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import admin.service.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {

	private final PostService postService;

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
}
