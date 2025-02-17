package yapyap.popol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yapyap.popol.dao.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
