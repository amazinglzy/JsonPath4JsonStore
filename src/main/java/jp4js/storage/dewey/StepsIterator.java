package jp4js.storage.dewey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp4js.algebra.op.structure.StructureSteps;
import jp4js.utils.iter.Iter;

public class StepsIterator implements Iter<IndexNode> {
    private final StructureSteps steps;
    private ArrayList<IndexNode> data;
    private int cursor;

    public StepsIterator(StructureSteps steps, ArrayList<IndexNode> data) {
        this.steps = steps.copy();
        this.data = data;
        LocateCursor();
    }

    @Override
    public IndexNode read() {
        return this.data.get(this.cursor);
    }

    @Override
    public void next() {
        ++ this.cursor;
        while (valid()) {
            if (!matchSteps(this.steps, this.data.get(this.cursor).indexes)) {
                ++ this.cursor;
            } else {
                break;
            }
        }
    }

    @Override
    public boolean valid() {
        if (this.cursor >= this.data.size()) {
            return false;
        }

        if (this.steps.size() == 0) {
            return true;
        }

        return matchStep(this.steps.step(0), this.data.get(this.cursor).indexes.get(0));
    }

    private boolean matchStep(StructureSteps.Step step, int index) {
        if (step instanceof StructureSteps.PropertyStep) {
            return true;
        }

        StructureSteps.IndexStep is = (StructureSteps.IndexStep)step;
        if (is.from <= index && index < is.to) {
            return true;
        }

        return false;
    }

    private boolean matchSteps(StructureSteps steps, List<Integer> indexes) {
        Iterator<Integer> iter = indexes.iterator();
        for (int i = 0; i < steps.size(); ++ i) {
            if (!iter.hasNext()) {
                return false;
            }

            int index = iter.next();
            StructureSteps.Step step = steps.step(i);
            if (!matchStep(step, index)) {
                return false;
            }
        }

        return true;
    }

    private void LocateCursor() {
        if (steps.size() == 0) {
            this.cursor = 0;
            return;
        }

        if (steps.step(0) instanceof StructureSteps.PropertyStep) {
            this.cursor = 0;
        } else {
            StructureSteps.IndexStep is = (StructureSteps.IndexStep)steps.step(0);
            int left = -1, right = this.data.size();
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if ( is.from <= data.get(mid).indexes.get(0) ) {
                    right = mid;
                } else {
                    left = mid;
                }
            }

            this.cursor = right;
        }


        if (valid()) {
            if (!matchSteps(this.steps, this.data.get(this.cursor).indexes)) {
                next();
            }
        }
    }
}
