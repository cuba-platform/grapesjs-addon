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

import com.haulmont.addon.grapesjs.web.gui.components.GrapesJsNewsletterHtmlEditor;
import org.dom4j.Element;

public class GrapesJsNewsletterHtmlEditorLoader extends GrapesJsHtmlEditorLoader {
    @Override
    public void createComponent() {
        resultComponent = factory.create(GrapesJsNewsletterHtmlEditor.class);
        loadId(resultComponent, element);
    }

    @Override
    public void loadComponent() {
        super.loadComponent();
        loadInlineCss((GrapesJsNewsletterHtmlEditor) resultComponent, element);
    }

    private void loadInlineCss(GrapesJsNewsletterHtmlEditor component, Element element) {
        if (element.attribute("inlineCss") != null) {
            component.setInlineCss(Boolean.valueOf(element.attribute("inlineCss").getValue()));
        } else {
            component.setInlineCss(true);
        }
    }
}
