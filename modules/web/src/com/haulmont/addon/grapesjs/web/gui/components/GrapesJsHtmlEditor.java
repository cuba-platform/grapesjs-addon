package com.haulmont.addon.grapesjs.web.gui.components;

import com.haulmont.cuba.gui.components.Component;

import java.util.Collection;

public interface GrapesJsHtmlEditor extends Component, Component.HasValue {
    String NAME = "grapesJsHtmlEditor";

    void setDisabledBlocks(Collection<String> disabledBlocks);

    Collection<String> getDisabledBlocks();
}