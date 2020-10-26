package jp4js.algebra;

import java.util.List;
import java.util.LinkedList;

import jp4js.algebra.tpl.NestedData;
import jp4js.algebra.tpl.ListTemplate;
import jp4js.algebra.tpl.DHeader;

import jp4js.algebra.tpl.AtomicTemplate;
import jp4js.algebra.tpl.AtomicValue;
import jp4js.algebra.tpl.Template;
import jp4js.algebra.tpl.ListTuple;
import jp4js.algebra.tpl.Tuple;
import jp4js.utils.Utils;


public class TplValidator {
    private DHeader template;
    private List<NestedData> documentSet;
    private boolean valid;

    public TplValidator(DHeader template, NestedData documentSet) {
        this.template = template;
        this.documentSet = new LinkedList<>() {{add(documentSet);}};
    }

    public TplValidator(DHeader template, List<NestedData> documentSet) {
        this.template = template;
        this.documentSet = documentSet;
    }

    public boolean isValid() {
        return this.valid;
    }

    public DHeader header() {
        return this.template;
    }

    public List<NestedData> body() {
        return this.documentSet;
    }

    public void match() {
        this.valid = tryMatch(this.template, this.documentSet);
    }

    private boolean tryMatch(DHeader header, List<NestedData> documentSet) {
        Utils.isTrue(documentSet != null, "documentset must not be null");
        if (documentSet.size() == 0) {
            return true;
        }
        if (header == null) {
            return false;
        }
        for (NestedData item: documentSet) {
            if (!tryMatch(header, item)) {
                return false;
            }
        }
        
        return true;
    }

    private boolean tryMatch(DHeader header, NestedData document) {
        if (header instanceof ListTemplate) {
            if (!matchList((ListTemplate)header, document)) {
                return false;
            }
        }

        if (header instanceof Template) {
            if (!matchComplex((Template)header, document)) {
                return false;
            }
        }

        if (header instanceof AtomicTemplate) {
            if (!matchAtomic((AtomicTemplate)header, document)) {
                return false;
            }
        }
        return true;

    }

    private boolean matchList(ListTemplate tpl, NestedData documentSet) {
        if (!(documentSet instanceof ListTuple)) {
            return false;
        }
        ListTuple data = (ListTuple)documentSet;
        for (NestedData item: data) {
            if (!tryMatch(tpl.getHeader(), item)) {
                return false;
            }
        }
        return true;
    }


    private boolean matchAtomic(AtomicTemplate template, NestedData documentSet) {
        if (!(documentSet instanceof AtomicValue)) {
            return false;
        }
        return true;
    }

    private boolean matchComplex(Template template, NestedData documentSet) {
        if (!(documentSet instanceof Tuple)) {
            return false;
        }

        Tuple t = (Tuple)documentSet;
        if (template.size() != t.size()) {
            return false;
        }

        for (String fieldname: template) {
            if (!(tryMatch(template.get(fieldname), t.get(template.index(fieldname))))) {
                return false;
            }
        }
        return true;
    }
}