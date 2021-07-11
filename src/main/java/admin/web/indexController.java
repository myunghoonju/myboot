package admin.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import admin.config.auth.dto.SessionUser;
import admin.service.PostService;
import admin.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {

	private final PostService postService;
	private final HttpSession httpSession;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postService.findAllDESC());
		SessionUser user = (SessionUser)httpSession.getAttribute("user");
		if (user != null) {
			model.addAttribute("name", user.getName());
		}

		return "index";
	}

	@GetMapping("/post/save")
	public String savePost() {
		return "save-post";
	}

	@GetMapping("/post/update/{id}")
	public String updatePost(@PathVariable Long id, Model model) {
		PostResponseDto dto = postService.findById(id);
		model.addAttribute("post", dto);
		return "update-post";
	}
}
