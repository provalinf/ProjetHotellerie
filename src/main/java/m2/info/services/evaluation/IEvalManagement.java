package m2.info.services.evaluation;

import m2.info.models.Evaluation;

public interface IEvalManagement {

    boolean addEvaluation(Evaluation eval);
    boolean updateEvaluation(long id, Evaluation eval);
    boolean deleteEvaluation(long id);

}
