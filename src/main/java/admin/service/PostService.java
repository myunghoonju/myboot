package admin.service;

import java.util.List;
import java.util.stream.Collectors;

import admin.domain.post.Post;
import admin.domain.post.PostRepository;
import admin.web.dto.PostListResponseDto;
import admin.web.dto.PostResponseDto;
import admin.web.dto.PostSaveRequestDto;
import admin.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find user with the id = " + id));
        post.update(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find user with the id = " + id));
        postRepository.delete(post);
    }

    @Transactional
    public PostResponseDto findById(Long id) {
        Post postEntity = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find user with the id = " + id));
        return new PostResponseDto(postEntity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDESC() {
        return postRepository.findAllDESC()
            .stream()
            .map(PostListResponseDto::new)
            .collect(Collectors.toList());
    }
}
