package m2.info.entities;

import java.util.HashMap;

public class EvalMap extends HashMap<EvalType, Integer> {

    public EvalMap() {
        for (EvalType type : EvalType.values())
            this.put(type, 0);
    }

}
