package m2.info.services.evaluation;

import m2.info.models.Evaluation;
import m2.info.repositories.EvalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvalManagement implements IEvalManagement {

    @Autowired private EvalRepository repository;

    @Override
    public boolean addEvaluation(Evaluation eval) {
        if (!repository.exists(eval.getId())) {
            repository.save(eval);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEvaluation(long id, Evaluation eval) {
        if (deleteEvaluation(id)) {
            addEvaluation(eval);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEvaluation(long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return true;
        }
        return false;
    }
}
