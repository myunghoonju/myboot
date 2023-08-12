package admin.domain.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static admin.domain.post.QPost.post;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    public static final String MARK_FAKE_STORY = "fake";

    private final JPAQueryFactory queryFactory;

    public PostRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Long batchUpdate(List<String> authors) {
        return queryFactory.update(post)
                           .set(post.content, MARK_FAKE_STORY)
                           .where(post.author.in(authors))
                           .execute();
    }
}
