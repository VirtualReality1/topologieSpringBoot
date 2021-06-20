package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Segment;
import com.springboot.topologie.models.Software;

import javax.validation.constraints.NotNull;

public class AddFieldItemForm {
    @NotNull
    private Long fieldId;

    @NotNull
    private Long segmentId;

    private Iterable<Segment> segments;
    private Field field;

    public AddFieldItemForm(){ }

    public AddFieldItemForm(Iterable <Segment> segments, Field field) {
        this.segments = segments;
        this.field = field;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
    public Long getSegmentId() {
        return segmentId;
    }
    public void setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
    }

    public Iterable<Segment> getSegments(){
        return segments;
    }

    public Field getField(){
        return field;
    }

}