package com.haulmont.addon.grapesjs.web.gui.components;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.HasValue;

import java.util.Collection;

public interface GrapesJsHtmlEditor extends Component, Component.BelongToFrame, HasValue<String> {
    String NAME = "grapesJsHtmlEditor";

    void setDisabledBlocks(Collection<String> disabledBlocks);

    Collection<String> getDisabledBlocks();
}