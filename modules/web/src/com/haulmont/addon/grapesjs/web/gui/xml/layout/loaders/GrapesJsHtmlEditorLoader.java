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

import com.haulmont.addon.grapesjs.web.gui.components.GjsPlugin;
import com.haulmont.addon.grapesjs.web.gui.components.GjsPluginsRepository;
import com.haulmont.addon.grapesjs.web.gui.components.GrapesJsHtmlEditor;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.xml.layout.loaders.AbstractComponentLoader;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        loadPlugins(resultComponent, element);
    }

    private void loadDisabledBlocks(GrapesJsHtmlEditor component, Element element) {
        Element disabledBlocksEl = element.element("disabledBlocks");
        if (disabledBlocksEl != null) {
            String disabledBlocks = disabledBlocksEl.getTextTrim();
            component.setDisabledBlocks(StringUtils.isNotBlank(disabledBlocks) ? Arrays.asList(disabledBlocks.split(",")) : null);
        }
    }

    private void loadPlugins(GrapesJsHtmlEditor component, Element element) {
        List<Element> pluginsEls = element.elements("plugin");
        List<GjsPlugin> plugins = new ArrayList<>();
        for (Element pluginEl : pluginsEls) {
            if (pluginEl.attribute("name") != null) {
                String pluginCode = pluginEl.attribute("name").getText();
                GjsPluginsRepository pluginsRepository = AppBeans.get(GjsPluginsRepository.class);
                GjsPlugin plugin = pluginsRepository.getPlugin(pluginCode);
                if (plugin == null) {
                    throw new RuntimeException("Unknown grapes js plugin " + pluginCode);
                }
                String options = getPluginOptions(pluginEl);
                if (options != null) {
                    plugin = new GjsPlugin(plugin.getName(), options);
                }
                plugins.add(plugin);
            } else {
                String name = null;
                String options = null;
                Element nameEl = pluginEl.element("name");
                if (nameEl != null) {
                    name = nameEl.getTextTrim();
                }
                if (name == null) {
                    throw new RuntimeException("Grapes js plugin name should not be empty");
                }
                options = getPluginOptions(pluginEl);
                plugins.add(new GjsPlugin(name, options));
            }

        }

        component.addPlugins(plugins);
    }

    private String getPluginOptions(Element pluginEl) {
        String options = null;
        Element optionsEl = pluginEl.element("options");
        if (optionsEl != null) {
            options = optionsEl.getTextTrim();
        }
        Element optionsPathEl = pluginEl.element("optionsPath");
        if (optionsPathEl != null) {
            String optionsPath = optionsPathEl.getTextTrim();
            if (StringUtils.isNotBlank(optionsPath)) {
                options = loadOptionsFromPath(optionsPath);
            }
        }
        return options;
    }

    protected String loadOptionsFromPath(String path) {
        return AppBeans.get(Resources.class).getResourceAsString(path);
    }
}
