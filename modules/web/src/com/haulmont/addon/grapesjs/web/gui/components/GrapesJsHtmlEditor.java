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

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.HasValue;

import java.util.Collection;

/**
 * The GrapesJs HTML editor component
 */
public interface GrapesJsHtmlEditor extends Component, Component.BelongToFrame, HasValue<String> {
    String NAME = "grapesJsHtmlEditor";

    void setDisabledBlocks(Collection<String> disabledBlocks);

    Collection<String> getDisabledBlocks();

    void setPlugins(Collection<GjsPlugin> plugins);

    void addPlugins(Collection<GjsPlugin> plugins);

    void removePlugins(Collection<GjsPlugin> plugins);

    Collection<GjsPlugin> getPlugins();

    Collection<GjsBlock> getCustomBlocks();

    void setCustomBlocks(Collection<GjsBlock> blocks);

    void addBlocks(Collection<GjsBlock> blocks);

    void removeCustomBlocks(Collection<GjsBlock> blocks);

}