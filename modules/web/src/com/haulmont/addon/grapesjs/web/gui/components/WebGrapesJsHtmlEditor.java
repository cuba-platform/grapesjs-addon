package com.haulmont.addon.grapesjs.web.gui.components;

import com.haulmont.addon.grapesjs.web.toolkit.ui.grapejshtmleditorcomponent.GrapeJsHtmlEditorComponent;
import com.haulmont.bali.events.Subscription;
import com.haulmont.chile.core.model.utils.InstanceUtils;
import com.haulmont.cuba.web.gui.components.WebAbstractComponent;

import java.util.function.Consumer;

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
                getEventHub().publish(ValueChangeEvent.class, event);
            }
        });
    }

    @Override
    public String getValue() {
        return component.getValue();
    }

    @Override
    public void setValue(String value) {
        component.setValue(value);
    }

    @Override
    public Subscription addValueChangeListener(Consumer<ValueChangeEvent<String>> listener) {
        return getEventHub().subscribe(ValueChangeEvent.class, (Consumer) listener);
    }

    @Override
    public void removeValueChangeListener(Consumer<ValueChangeEvent<String>> listener) {
        unsubscribe(ValueChangeEvent.class, (Consumer) listener);
    }
}