package com.haulmont.addon.grapesjs.web.toolkit.ui.grapejshtmleditorcomponent;

import com.haulmont.cuba.web.sys.WebJarResource;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import elemental.json.impl.JreJsonString;


@WebJarResource({
        "grapesjs:dist/grapes.min.js",
        "grapesjs:dist/css/grapes.min.css",
        "grapesjs-ckeditor:min/ckeditor.js",
        "toastr:build/toastr.min.js",
        "toastr:build/toastr.min.css",
        "grapesjs-plugin-ckeditor:dist/grapesjs-plugin-ckeditor.min.js",
        "grapesjs-custom-code:dist/grapesjs-custom-code.min.js",
        "grapesjs-tabs:dist/grapesjs-tabs.min.js",
        "grapesjs-touch:dist/grapesjs-touch.min.js",
        "grapesjs-parser-postcss:dist/grapesjs-parser-postcss.min.js",
        "grapesjs-preset-webpage:dist/grapesjs-preset-webpage.min.js",
        "grapesjs-preset-webpage:dist/grapesjs-preset-webpage.min.css"
})
@JavaScript({"grapejshtmleditorcomponent-connector.js"})
public class GrapeJsHtmlEditorComponent extends AbstractJavaScriptComponent {

    public GrapeJsHtmlEditorComponent() {
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
    protected GrapeJsHtmlEditorComponentState getState() {
        return (GrapeJsHtmlEditorComponentState) super.getState();
    }

    @Override
    protected GrapeJsHtmlEditorComponentState getState(boolean markAsDirty) {
        return (GrapeJsHtmlEditorComponentState) super.getState(markAsDirty);
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

}