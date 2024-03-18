package admin.web.dto;

import admin.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PostResponseDto {

	private Long id;
	private String title;
	private String content;
	private String author;

	public PostResponseDto(Post postEntity) {
		this.id = postEntity.getId();
		this.title = postEntity.getTitle();
		this.content = postEntity.getContent();
		this.author = postEntity.getAuthor();
	}
}
