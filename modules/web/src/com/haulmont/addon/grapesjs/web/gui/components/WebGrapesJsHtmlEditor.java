package com.haulmont.addon.grapesjs.web.gui.components;

import com.haulmont.addon.grapesjs.web.toolkit.ui.grapejshtmleditorcomponent.GrapeJsHtmlEditorComponent;
import com.haulmont.chile.core.model.utils.InstanceUtils;
import com.haulmont.cuba.gui.components.compatibility.ComponentValueListenerWrapper;
import com.haulmont.cuba.gui.data.ValueListener;
import com.haulmont.cuba.web.gui.components.WebAbstractComponent;

public class WebGrapesJsHtmlEditor extends WebAbstractComponent<GrapeJsHtmlEditorComponent> implements GrapesJsHtmlEditor {

    protected String prevValue;

    public WebGrapesJsHtmlEditor() {
        this.component = new GrapeJsHtmlEditorComponent();
        attachValueListener(this.component);
    }

    protected void attachValueListener(GrapeJsHtmlEditorComponent component) {
        component.setListener(vEvent -> {
            final String value = getValue();
            final String oldValue = prevValue;
            prevValue = value;

            if (!InstanceUtils.propertyValueEquals(oldValue, value)) {
                if (hasValidationError()) {
                    setValidationError(null);
                }

                ValueChangeEvent event = new ValueChangeEvent(this, oldValue, value);
                getEventRouter().fireEvent(ValueChangeListener.class, ValueChangeListener::valueChanged, event);
            }
        });
    }

    @Override
    public String getValue() {
        return component.getValue();
    }

    @Override
    public void setValue(Object value) {
        component.setValue(value != null ? value.toString() : null);
    }

    @Override
    public void addListener(ValueListener listener) {
        addValueChangeListener(new ComponentValueListenerWrapper(listener));
    }

    @Override
    public void removeListener(ValueListener listener) {
        removeValueChangeListener(new ComponentValueListenerWrapper(listener));
    }

    @Override
    public void addValueChangeListener(ValueChangeListener listener) {
        getEventRouter().addListener(ValueChangeListener.class, listener);
    }

    @Override
    public void removeValueChangeListener(ValueChangeListener listener) {
        getEventRouter().removeListener(ValueChangeListener.class, listener);
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public void setEditable(boolean editable) {

    }
}