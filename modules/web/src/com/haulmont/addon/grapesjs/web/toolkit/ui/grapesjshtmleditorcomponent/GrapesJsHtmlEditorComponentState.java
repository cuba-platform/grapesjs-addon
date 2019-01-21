package com.haulmont.addon.grapesjs.web.toolkit.ui.grapesjshtmleditorcomponent;

import com.vaadin.shared.ui.JavaScriptComponentState;

import java.util.ArrayList;
import java.util.List;

public class GrapesJsHtmlEditorComponentState extends JavaScriptComponentState {

    public List<String> disabledBlocks = new ArrayList<>();

    public String html;
}
