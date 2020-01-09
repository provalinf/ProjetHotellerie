package m2.info.repositories;

import m2.info.models.Evaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalRepository extends CrudRepository<Evaluation, Long> {}
