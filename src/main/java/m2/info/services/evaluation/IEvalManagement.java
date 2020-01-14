package m2.info.services.evaluation;

import m2.info.models.Evaluation;

public interface IEvalManagement {

    Evaluation getEvaluation(long id);
    void saveEvaluation(Evaluation eval);
    boolean deleteEvaluation(long id);

}
