package admin.domain.post;

import java.util.List;

import admin.web.dto.PostResponseDto;

public interface PostRepositoryCustom {

    Long batchUpdate(List<String> dtos);

    Long batchAgeUpdate(List<Long> ages);

    List<PostResponseDto> posts();
}
