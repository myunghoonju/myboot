package admin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import admin.domain.dsl.Member;
import admin.domain.dsl.Team;
import admin.domain.post.Post;
import admin.domain.post.PostRepository;
import admin.web.dto.PostListResponseDto;
import admin.web.dto.PostResponseDto;
import admin.web.dto.PostSaveRequestDto;
import admin.web.dto.PostUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    @Cacheable(cacheNames = "store", cacheManager = "redisCacheManager")
    public Map<String, List<Team>> test() {
        log.error("PostService.test() called");

        Team teamA = Team.builder()
                .id(1L)
                .name("teamA")
                .build();

        Member member = new Member("a", 1);

        Team teamB = Team.builder()
                .id(2L)
                .name("teamB")
                .build();

        List<Team> objects = new ArrayList<>();
        objects.add(teamA);
        objects.add(teamB);

        Map<String, List<Team>> mymap = new HashMap<>();
        mymap.put("store", objects);

        return mymap;
    }
}
