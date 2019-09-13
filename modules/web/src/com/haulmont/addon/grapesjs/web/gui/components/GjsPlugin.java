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

import java.io.Serializable;

/**
 * Grapes JS plugin
 */
public class GjsPlugin implements Serializable, Cloneable {

    /**
     * Plugin name
     */
    protected String name;

    /**
     * Plugin options
     */
    protected String options;

    public GjsPlugin() {
    }

    public GjsPlugin(String name, String options) {
        this.name = name;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public String getOptions() {
        return options;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public GjsPlugin clone() {
        GjsPlugin clone = new GjsPlugin();
        clone.setName(this.name);
        clone.setOptions(this.options);
        return clone;
    }
}
