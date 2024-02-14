package admin.domain.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class TestService {

    private final NamedParameterJdbcTemplate template;

    public TestService(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public String post(long id) {
        Ans ans1 = new Ans();
        ans1.setId(id);
        String s = """
                    select id from post \n
                    where id = :id 
                    limit 1
                   """;
        Ans ans = (Ans) template.queryForObject(s, new BeanPropertySqlParameterSource(ans1), new Row());
        return ans.getId().toString();
    }

    class Row implements RowMapper {
        @Override
        public Ans mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ans ans = new Ans();
            ans.setId(rs.getLong("id"));
            return ans;
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    class Ans {
        private Long id;
        private String p;
    }
}
