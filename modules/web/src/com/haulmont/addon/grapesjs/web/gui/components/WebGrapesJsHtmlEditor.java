/*
 * Copyright (c) 2008-2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.addon.grapesjs.web.gui.components;

import com.haulmont.addon.grapesjs.web.toolkit.ui.grapesjshtmleditorcomponent.GrapesJsHtmlEditorComponent;
import com.haulmont.bali.events.Subscription;
import com.haulmont.chile.core.model.utils.InstanceUtils;
import com.haulmont.cuba.web.gui.components.WebAbstractComponent;

import java.util.Collection;
import java.util.function.Consumer;

public class WebGrapesJsHtmlEditor extends WebAbstractComponent<GrapesJsHtmlEditorComponent> implements GrapesJsHtmlEditor {

    protected String prevValue;
    protected Collection<String> disabledBlocks;

    public WebGrapesJsHtmlEditor() {
        this.component = new GrapesJsHtmlEditorComponent();
        attachValueListener(this.component);
    }

    protected void attachValueListener(GrapesJsHtmlEditorComponent component) {
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

    @Override
    public Collection<String> getDisabledBlocks() {
        return disabledBlocks;
    }

    @Override
    public void setDisabledBlocks(Collection<String> disabledBlocks) {
        this.disabledBlocks = disabledBlocks;
        component.setDisabledBlocks(disabledBlocks);
    }
}