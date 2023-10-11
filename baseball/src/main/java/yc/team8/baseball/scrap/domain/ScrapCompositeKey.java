package yc.team8.baseball.scrap.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class ScrapCompositeKey implements Serializable {
    private Long userId;
    private Long postId;

    public ScrapCompositeKey(){}
    public ScrapCompositeKey(long userId, long postId){
        this.userId = userId;
        this.postId = postId;
    }
}

//  https://www.baeldung.com/jpa-many-to-many
//  https://www.baeldung.com/jpa-entities-serializable
//  https://www.baeldung.com/jpa-composite-primary-keys

/* composite key -> equals()랑 hashCode() 구현 필요...?
  - - -
* import java.io.Serializable;
import java.util.Objects;

public class MyCompositeKey implements Serializable {
    private Long id1;
    private String id2;

    public MyCompositeKey() {
    }

    public MyCompositeKey(Long id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    // Implement equals and hashCode based on all components
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCompositeKey that = (MyCompositeKey) o;
        return Objects.equals(id1, that.id1) &&
               Objects.equals(id2, that.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }

    // Getters and setters

    // Override toString() for custom representation if needed
    @Override
    public String toString() {
        return "MyCompositeKey{" +
               "id1=" + id1 +
               ", id2='" + id2 + '\'' +
               '}';
    }
}
 */