package admin.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import admin.config.auth.custom.annotation.LoginUser;
import admin.config.auth.dto.SessionUser;
import admin.domain.dsl.Tes;
import admin.domain.jdbctemplate.TestService;
import admin.service.PostService;
import admin.web.dto.PostListResponseDto;
import admin.web.dto.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class indexController {

	private final PostService postService;
	private final TestService testService;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser sessionUser) {
		return testService.post(1);
	}

	@GetMapping("/a")
	public ResponseEntity<String> index2() {
		Tes tes = postService.test2();
		return ResponseEntity.ok().body(tes.toString());
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
