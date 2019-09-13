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


public interface GjsPluginsRepository {

    String NAME = "grapesJs_GjsPluginsRepository";

    /**
     * Retrieve registered GrapesJs plugin
     *
     * @param pluginXsdCode plugin coe from xml screen descriptor
     * @return GrapesJs plugin descriptor
     */
    GjsPlugin getPlugin(String pluginXsdCode);

    /**
     * Register custom GrapesJs plugin which can be used in xml configuration
     *
     * @param pluginXsdCode plugin coe from xml screen descriptor
     * @param plugin GrapesJs plugin descriptor with name and options
     */
    void registerPlugin(String pluginXsdCode, GjsPlugin plugin);

}
