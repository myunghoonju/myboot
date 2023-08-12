package admin.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostBatchUpdateRequestDto {

	private List<String> authors;
}
