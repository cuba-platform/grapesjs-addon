[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) //For opensource components under Apache 2.0 license
# GrapesJs HTML editor

## Overview

GrapesJs HTML editor component for CUBA application. Built on [GrapeJs](https://grapesjs.com/) javascript library with `webpage` preset.

![html-editor](img/editor.gif)

## Installation
To add the component to your project, the following steps should be taken:

1. Open your application in CUBA Studio.

2. Edit Project properties.

3. Click the plus button in the *App components* section of the *Main* tab.

4. Specify the coordinates of the component in the corresponding field as follows: group:name:version.
   Click *OK* to confirm the operation.

    * Artifact group: *<component group>*
    * Artifact name: *<component artifact name>*
    * Version: *add-on version*

        When specifying the component version, you should select the one, which is compatible with the platform version used
    in your project.

    | Platform Version | Component Version |
    |------------------|-------------------|
    | 6.10.x           | 0.1-SNAPSHOT      |

5. Extend application theme.

## Usage
To use the `grapesjs` component in your screen, you need to add the special scheme `http://schemas.haulmont.com/grapesjs/ui-component.xsd` in the XML descriptor of the screen. Then add a namespace like `grapesjs` for the schema. The schema contains information about the tag `grapesJsHtmlEditor`.

### Usage Example

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<window xmlns="http://schemas.haulmont.com/cuba/window.xsd"
        class="com.haulmont.example.web.SomeController"
        xmlns:et="http://schemas.haulmont.com/grapesjs/ui-component.xsd">
    ...
        <et:grapesJsHtmlEditor id="dashboardId"/>
    ...
```

`grapesjs` UI component provides `setValue(String value)` and `getValue()` methods to set and get HTML content for the component.

