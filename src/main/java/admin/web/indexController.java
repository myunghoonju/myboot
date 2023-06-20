package admin.web;

import admin.config.auth.custom.annotation.LoginUser;
import admin.config.auth.dto.SessionUser;
import admin.domain.dsl.Team;
import admin.service.PostService;
import admin.web.dto.PostListResponseDto;
import admin.web.dto.PostResponseDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class indexController {

	private final PostService postService;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser sessionUser) {
		return "index";
	}

	@PostMapping("/a")
	public ResponseEntity<Map<String, List<Team>>> index2() {
		Map<String, List<Team>> test = postService.test();
		log.error("test: {}", test.get("store"));

		return ResponseEntity.ok().body(test);
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

	@PostMapping("/myboot")
	public TestModel fromPractice(@RequestBody TestModel testModel) {
		System.out.println(testModel.toString());

		return testModel;
	}

	@ToString
	@Getter
	@Setter
	@NoArgsConstructor
	static class TestModel {
		private String key;
		private String  val;

		public TestModel(String key, String val) {
			this.key = key;
			this.val = val;
		}
	}
}
