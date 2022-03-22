package admin.domain.dsl;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class TeamTwo implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament
    @Column(name = "team_id")
    private Long id;
    private String name;
    private int age;

/*    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();*/

    public TeamTwo(String name) {
        this.name = name;
    }

    @Builder
    public TeamTwo(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
