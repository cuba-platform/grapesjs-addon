package com.haulmont.addon.grapesjs.web.gui.xml.layout.loaders;

import com.haulmont.addon.grapesjs.web.gui.components.GrapesJsHtmlEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.xml.layout.loaders.AbstractComponentLoader;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import java.util.Arrays;

public class GrapesJsHtmlEditorLoader extends AbstractComponentLoader<GrapesJsHtmlEditor> {
    @Override
    public void createComponent() {
        resultComponent = factory.createComponent(GrapesJsHtmlEditor.class);
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
