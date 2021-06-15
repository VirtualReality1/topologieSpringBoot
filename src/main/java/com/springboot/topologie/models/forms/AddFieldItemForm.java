package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Segment;
import com.springboot.topologie.models.Software;

import javax.validation.constraints.NotNull;

public class AddFieldItemForm {
    @NotNull
    private int fieldId;

    @NotNull
    private int segmentId;

    private Iterable<Segment> segments;
    private Field field;

    public AddFieldItemForm(){ }

    public AddFieldItemForm(Iterable <Segment> segments, Field field) {
        this.segments = segments;
        this.field = field;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }
    public int getSegmentId() {
        return segmentId;
    }
    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public Iterable<Segment> getSegments(){
        return segments;
    }

    public Field getField(){
        return field;
    }

}