package admin.web;

import admin.config.auth.custom.annotation.LoginUser;
import admin.config.auth.dto.SessionUser;
import admin.domain.dsl.Team;
import admin.service.PostService;
import admin.web.dto.PostListResponseDto;
import admin.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class indexController {

	private final PostService postService;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser sessionUser) {
		model.addAttribute("posts", postService.findAllDESC());
		SessionUser user = sessionUser;
		if (user != null) {
			model.addAttribute("name", user.getName());
		}

		Map<String, List<Team>> test = postService.test();
		List<Team> store = test.get("store");

		log.error("test: {}", test.get("store"));

		for (Team t : store) {
			log.error("name :: {}", t.getName());
		}

		return "index";
	}

	@GetMapping("/post/list")
	public ResponseEntity<List<PostListResponseDto>> getList() {
		return ResponseEntity.ok().body(postService.findAllDESC());
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
