package admin.web;

import admin.service.PostService;
import admin.web.dto.PostBatchUpdateRequestDto;
import admin.web.dto.PostResponseDto;
import admin.web.dto.PostSaveRequestDto;
import admin.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        return postService.update(id, postUpdateRequestDto);
    }

    @PutMapping("/api/v1/authors")
    public Long catchFakeStories(@RequestBody PostBatchUpdateRequestDto requestDto) {
        return postService.catchFakeStories(requestDto.getAuthors());
    }

    @PutMapping("/api/v1/ages")
    public Long catchFakeStoriesWithAge(@RequestBody PostBatchUpdateRequestDto requestDto) {
        return postService.catchFakeStoriesWithAge(requestDto.getAges());
    }

    @DeleteMapping("/api/v1/post/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/post/get/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
