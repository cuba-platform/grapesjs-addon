package com.haulmont.addon.grapesjs.web.screens;

import com.haulmont.addon.grapesjs.web.gui.components.GrapesJsHtmlEditor;
import com.haulmont.cuba.gui.components.AbstractWindow;

import javax.inject.Inject;
import java.util.Map;

public class Screen extends AbstractWindow {

    @Inject
    private GrapesJsHtmlEditor htmlEditor;

    @Override
    public void init(Map<String, Object> params) {
        htmlEditor.setValue("<h1>HELLO</h1>");

        htmlEditor.addValueChangeListener(e->{
            showNotification((String) e.getValue(),NotificationType.TRAY_HTML);
        });
    }
}