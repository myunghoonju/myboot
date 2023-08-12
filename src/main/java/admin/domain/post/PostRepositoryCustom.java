package admin.domain.post;

import java.util.List;

public interface PostRepositoryCustom {

    Long batchUpdate(List<String> dtos);
}
