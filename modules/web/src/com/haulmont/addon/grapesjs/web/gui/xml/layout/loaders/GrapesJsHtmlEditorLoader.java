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

package com.haulmont.addon.grapesjs.web.gui.xml.layout.loaders;

import com.haulmont.addon.grapesjs.web.gui.components.GrapesJsHtmlEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.xml.layout.loaders.AbstractComponentLoader;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.Arrays;

public class GrapesJsHtmlEditorLoader extends AbstractComponentLoader<GrapesJsHtmlEditor> {
    @Override
    public void createComponent() {
        resultComponent = factory.create(GrapesJsHtmlEditor.class);
        loadId(resultComponent, element);
    }

    @Override
    public void loadComponent() {
        assignFrame(resultComponent);
        loadWidth(resultComponent, element, Component.AUTO_SIZE);
        loadHeight(resultComponent, element, Component.AUTO_SIZE);

        loadVisible(resultComponent, element);
        loadEnable(resultComponent, element);
        loadStyleName(resultComponent, element);

        loadDisabledBlocks(resultComponent, element);
    }

    private void loadDisabledBlocks(GrapesJsHtmlEditor component, Element element) {
        Element disabledBlocksEl = element.element("disabledBlocks");
        if (disabledBlocksEl != null) {
            String disabledBlocks = disabledBlocksEl.getTextTrim();
            component.setDisabledBlocks(StringUtils.isNotBlank(disabledBlocks) ? Arrays.asList(disabledBlocks.split(",")) : null);
        }
    }
}
