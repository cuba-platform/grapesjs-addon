package com.haulmont.addon.grapesjs.web.toolkit.ui.grapesjshtmleditorcomponent;

import com.haulmont.cuba.web.widgets.WebJarResource;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import elemental.json.impl.JreJsonString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@WebJarResource({
        "jquery:jquery.min.js",
        "grapesjs:dist/grapes.min.js",
        "grapesjs:dist/css/grapes.min.css",
        "grapesjs-plugin-ckeditor:dist/grapesjs-plugin-ckeditor.min.js",
        "grapesjs-custom-code:dist/grapesjs-custom-code.min.js",
        "grapesjs-tabs:dist/grapesjs-tabs.min.js",
        "grapesjs-touch:dist/grapesjs-touch.min.js",
        "grapesjs-parser-postcss:dist/grapesjs-parser-postcss.min.js",
        "grapesjs-preset-webpage:dist/grapesjs-preset-webpage.min.js",
        "grapesjs-preset-webpage:dist/grapesjs-preset-webpage.min.css"
})
@JavaScript({"vaadin://ckeditor/ckeditor.js",
        "grapesjshtmleditorcomponent-connector.js"})
@StyleSheet({"vaadin://styles/grapesjs-style.css"})
public class GrapesJsHtmlEditorComponent extends AbstractJavaScriptComponent {

    public GrapesJsHtmlEditorComponent() {
        addFunction("valueChanged", arguments -> {
            JreJsonString data = arguments.get(0);
            String value = data.getString();
            getState(false).html = value;
            if (listener != null) {
                listener.valueChanged(value);
            }
        });

        addFunction("fileUploaded", arguments -> {
            String fileName = arguments.getString(0);
            String fileBase64 = arguments.getString(1);

            if (fileUploadListener != null) {
                fileUploadListener.fileUploaded(fileName, fileBase64);
            }
        });
    }

    @Override
    protected GrapesJsHtmlEditorComponentState getState() {
        return (GrapesJsHtmlEditorComponentState) super.getState();
    }

    @Override
    protected GrapesJsHtmlEditorComponentState getState(boolean markAsDirty) {
        return (GrapesJsHtmlEditorComponentState) super.getState(markAsDirty);
    }

    public String getHtml() {
        return getState(false).html;
    }


    public interface ValueChangeListener {
        void valueChanged(String value);
    }

    public interface FileUploadListener {
        void fileUploaded(String name, String fileBase64);
    }

    private ValueChangeListener listener;

    private FileUploadListener fileUploadListener;

    public void setValue(String value) {
        getState().html = value;
    }

    public String getValue() {
        return getState(false).html;
    }

    public ValueChangeListener getListener() {
        return listener;
    }

    public void setListener(ValueChangeListener listener) {
        this.listener = listener;
    }

    public FileUploadListener getFileUploadListener() {
        return fileUploadListener;
    }

    public void setFileUploadListener(FileUploadListener fileUploadListener) {
        this.fileUploadListener = fileUploadListener;
    }

    public void setDisabledBlocks(Collection<String> disabledBlocks) {
        getState(false).disabledBlocks = disabledBlocks != null ? new ArrayList<>(disabledBlocks) : Collections.emptyList();
    }

}