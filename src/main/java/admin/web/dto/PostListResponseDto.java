package admin.web.dto;

import java.time.LocalDateTime;

import admin.domain.post.Post;
import lombok.Getter;

@Getter
public class PostListResponseDto {

	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedDate;

	public PostListResponseDto(Post entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.modifiedDate = entity.getModifiedDate();
	}
}
