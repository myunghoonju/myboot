package admin.domain.post;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static admin.domain.post.QPost.post;

import admin.web.dto.PostResponseDto;

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

    @Override
    public Long batchAgeUpdate(List<Long> ages) {
        return queryFactory.update(post)
                           .set(post.content, MARK_FAKE_STORY)
                           .where(post.age.in(ages))
                           .execute();
    }

    @Override
    public List<PostResponseDto> posts() {
        return queryFactory.select(defaultColumn())
                           .from(post)
                           .groupBy(post.title, post.id)
                           .orderBy(post.title.desc(), post.id.desc())
                           .fetch();
    }

    private QBean<PostResponseDto> defaultColumn() {
        return Projections.fields(
                PostResponseDto.class,
                post.id,
                post.author,
                post.title);
    }
}
