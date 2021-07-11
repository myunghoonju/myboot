package admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import admin.service.PostService;
import admin.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {

	private final PostService postService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postService.findAllDESC());
		return "index";
	}
	@GetMapping("/post/update/{id}")
	public String updatePost(@PathVariable Long id, Model model) {
		PostResponseDto dto = postService.findById(id);
		model.addAttribute("post", dto);
		return "update-post";
	}
}
