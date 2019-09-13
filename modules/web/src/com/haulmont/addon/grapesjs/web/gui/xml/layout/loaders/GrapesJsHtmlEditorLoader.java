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

import com.haulmont.addon.grapesjs.web.gui.components.*;
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
        loadBlocks(resultComponent, element);
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
            GjsPlugin plugin = new GjsPlugin();
            if (pluginEl.attribute("name") != null) {
                String pluginCode = pluginEl.attribute("name").getText();
                GjsPluginsRepository pluginsRepository = AppBeans.get(GjsPluginsRepository.class);
                plugin = pluginsRepository.getPlugin(pluginCode);
                if (plugin == null) {
                    throw new RuntimeException("Unknown grapes js plugin " + pluginCode);
                }
            }
            Element nameEl = pluginEl.element("name");
            if (nameEl != null) {
                plugin.setName(nameEl.getTextTrim());
            }
            if (plugin.getName() == null) {
                throw new RuntimeException("Grapes js plugin name should not be empty");
            }
            plugin.setOptions(getPluginOptions(pluginEl));
            plugins.add(plugin);

        }

        component.addPlugins(plugins);
    }

    private void loadBlocks(GrapesJsHtmlEditor component, Element element) {
        List<Element> pluginsEls = element.elements("block");
        List<GjsBlock> blocks = new ArrayList<>();
        for (Element blockEl : pluginsEls) {
            GjsBlock block = new GjsBlock();
            if (blockEl.attribute("name") != null) {
                String pluginCode = blockEl.attribute("name").getText();
                GjsBlocksRepository pluginsRepository = AppBeans.get(GjsBlocksRepository.class);
                block = pluginsRepository.getBlock(pluginCode);
                if (block == null) {
                    throw new RuntimeException("Unknown grapes js block " + pluginCode);
                }
            }
            Element nameEl = blockEl.element("name");
            if (nameEl != null) {
                block.setName(nameEl.getTextTrim());
            }
            if (block.getName() == null) {
                throw new RuntimeException("Grapes js block name should not be empty");
            }
            Element labelEl = blockEl.element("label");
            if (labelEl != null) {
                block.setLabel(labelEl.getTextTrim());
            }
            Element categoryEl = blockEl.element("category");
            if (categoryEl != null) {
                block.setCategory(categoryEl.getTextTrim());
            }
            Element attributesEl = blockEl.element("attributes");
            if (attributesEl != null) {
                block.setAttributes(attributesEl.getTextTrim());
            }
            block.setContent(getBlockContent(blockEl));
            blocks.add(block);

        }

        component.addBlocks(blocks);
    }

    private String getBlockContent(Element blockEl) {
        String options = null;
        Element optionsEl = blockEl.element("content");
        if (optionsEl != null) {
            options = optionsEl.getTextTrim();
        }
        Element optionsPathEl = blockEl.element("contentPath");
        if (optionsPathEl != null) {
            String optionsPath = optionsPathEl.getTextTrim();
            if (StringUtils.isNotBlank(optionsPath)) {
                options = loadFromPath(optionsPath);
            }
        }
        return options;
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
                options = loadFromPath(optionsPath);
            }
        }
        return options;
    }

    protected String loadFromPath(String path) {
        return AppBeans.get(Resources.class).getResourceAsString(path);
    }
}
