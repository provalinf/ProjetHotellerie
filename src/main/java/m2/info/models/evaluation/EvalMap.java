package m2.info.models.evaluation;

import java.util.HashMap;

public class EvalMap extends HashMap<EvalType, Integer> {

    public EvalMap() {
        for (EvalType type : EvalType.values())
            this.put(type, 0);
    }

}
