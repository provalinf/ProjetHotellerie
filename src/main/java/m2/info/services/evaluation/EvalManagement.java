package m2.info.services.evaluation;

import m2.info.models.Evaluation;
import m2.info.repositories.EvalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvalManagement implements IEvalManagement {

    @Autowired private EvalRepository repository;

    @Override
    public Evaluation getEvaluation(long id) { return repository.findOne(id); }

    @Override
    public void saveEvaluation(Evaluation eval) { repository.save(eval); }

    @Override
    public boolean deleteEvaluation(long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return true;
        }
        return false;
    }
}
