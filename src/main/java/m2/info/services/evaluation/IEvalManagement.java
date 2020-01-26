package m2.info.services.evaluation;

import m2.info.models.Evaluation;

public interface IEvalManagement {

    void saveEvaluation(Evaluation eval);
    void deleteEvaluation(long id);
    Evaluation getEvaluation(long id);
    Iterable<Evaluation> getAllEvaluations();

}
