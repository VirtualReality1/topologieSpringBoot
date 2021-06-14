package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Segment;
import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.Software;

import javax.validation.constraints.NotNull;

public class AddSegmentItemForm {
    @NotNull
    private int segmentId;

    @NotNull
    private int fieldId;

    private Iterable<Field> fields;
    private Segment segment;

    public AddSegmentItemForm(){ }

    public AddSegmentItemForm(Iterable <Field> fields, Segment segment) {
        this.fields = fields;
        this.segment = segment;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }
    public int getFieldId() {
        return fieldId;
    }
    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public Iterable<Field> getFields(){
        return fields;
    }

    public Segment getSegment(){
        return segment;
    }

}
