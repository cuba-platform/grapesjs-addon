package com.haulmont.addon.grapesjs.web.toolkit.ui.grapejshtmleditorcomponent;

import com.vaadin.shared.ui.JavaScriptComponentState;

import java.util.ArrayList;
import java.util.List;

public class GrapeJsHtmlEditorComponentState extends JavaScriptComponentState {

    public String html;

    public List<String> disabledBlocks = new ArrayList<>();
}
