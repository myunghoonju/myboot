package admin.service;

import admin.domain.post.Post;
import admin.domain.post.PostRepository;
import admin.web.dto.PostResponseDto;
import admin.web.dto.PostSaveRequestDto;
import admin.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public PostResponseDto findById(Long id) {
        Post postEntity = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find user with the id = " + id));
        return new PostResponseDto(postEntity);
    }
}
