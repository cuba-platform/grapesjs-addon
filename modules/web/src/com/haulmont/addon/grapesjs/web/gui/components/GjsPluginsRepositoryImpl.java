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

package com.haulmont.addon.grapesjs.web.gui.components;

import com.haulmont.addon.grapesjs.web.gui.plugins.*;
import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component(GjsPluginsRepository.NAME)
public class GjsPluginsRepositoryImpl implements GjsPluginsRepository {

    protected Map<String, GjsPlugin> registeredPlugins = new HashMap<>();

    @EventListener
    protected void appStarted(AppContextStartedEvent event) {
        registerDefaultPlugins();
    }

    protected void registerDefaultPlugins() {
        registeredPlugins.put("ckeditor", new CKEditorGjsPlugin());
        registeredPlugins.put("customcode", new CustomCodeGjsPlugin());
        registeredPlugins.put("newsletter", new NewsletterGjsPlugin());
        registeredPlugins.put("postcss", new PostCssGjsPlugin());
        registeredPlugins.put("tabs", new TabsGjsPlugin());
        registeredPlugins.put("touch", new TouchGjsPlugin());
        registeredPlugins.put("webpage", new WebpageGjsPlugin());
        registeredPlugins.put("basicBlocks", new BasicBlocksGjsPlugin());
        registeredPlugins.put("flexBlocks", new FlexBlocksGjsPlugin());
        registeredPlugins.put("tuiImageEditor", new TuiImageEditorGjsPlugin());
        registeredPlugins.put("forms", new FormsGjsPlugin());
        registeredPlugins.put("styleFilter", new StyleFilterGjsPlugin());
        registeredPlugins.put("tooltip", new TooltipGjsPlugin());
    }

    @Override
    public GjsPlugin getPlugin(String pluginXsdCode) {
        return registeredPlugins.get(pluginXsdCode).clone();
    }

    @Override
    public void registerPlugin(String pluginXsdCode, GjsPlugin plugin) {
        registeredPlugins.put(pluginXsdCode, plugin.clone());
    }
}
