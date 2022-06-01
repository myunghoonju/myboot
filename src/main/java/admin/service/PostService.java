package admin.service;

import admin.config.cache.annotation.Test2CacheGet;
import admin.config.cache.annotation.Test3CacheGet;
import admin.config.cache.annotation.Test4CacheGet;
import admin.domain.dsl.Member;
import admin.domain.dsl.Team;
import admin.domain.dsl.TeamTwo;
import admin.domain.dsl.Tes;
import admin.domain.dsl.TesTwo;
import admin.domain.post.Post;
import admin.domain.post.PostRepository;
import admin.web.dto.PostListResponseDto;
import admin.web.dto.PostResponseDto;
import admin.web.dto.PostSaveRequestDto;
import admin.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CacheManager cacheManager;

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


    public Map<String, List<Team>> test() {
        log.error("PostService.test() called evict tes");
        cacheManager.getCache("tes").evict("generatedTestKey");
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

    public static int cnt = 1;

    @Test2CacheGet
    public Tes test2() {
        cnt++;
        log.error("PostService.test2() called");
        Tes tes = new Tes();
        tes.setName("old tes");
        if (cnt > 3) {
            tes.setName("new tes");
        }

        return tes;
    }

    @Test3CacheGet
    public TesTwo test3() {
        log.error("PostService.test2() called");
        TesTwo tes = new TesTwo();
        tes.setNumber(1);

        return tes;
    }

    @Test4CacheGet
    public Map<String, List<TeamTwo>> test4() {
        log.error("PostService.test() called");

        TeamTwo teamA = TeamTwo.builder()
                .id(1L)
                .name("teamC")
                .age(1)
                .build();

        Member member = new Member("a", 1);

        TeamTwo teamB = TeamTwo.builder()
                .id(2L)
                .name("teamD")
                .age(2)
                .build();

        List<TeamTwo> objects = new ArrayList<>();
        objects.add(teamA);
        objects.add(teamB);

        Map<String, List<TeamTwo>> mymap = new HashMap<>();
        mymap.put("storeTwo", objects);

        return mymap;
    }
}
