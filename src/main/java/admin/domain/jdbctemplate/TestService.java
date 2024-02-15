package admin.domain.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
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

    public String post(long id, String pa) {
        Ans ans1 = new Ans();
        ans1.setId(id);
        String s = "select id from post partition(%s) where id = 11";
        String formatted = String.format(s, pa);
        Ans ans = template.queryForObject(formatted, EmptySqlParameterSource.INSTANCE, BeanPropertyRowMapper.newInstance(Ans.class));
        return ans.getId().toString();
    }

    // replaced by BeanPropertyRowMapper.newInstance()
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
    static class Ans {
        private Long id;
        private String p;
    }
}
