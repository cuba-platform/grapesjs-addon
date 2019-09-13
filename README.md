<p>
    <a href="http://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat" alt="license" title=""></a>
    <a href="https://travis-ci.org/cuba-platform/grapesjs-addon"><img src="https://travis-ci.org/cuba-platform/grapesjs-addon.svg?branch=master" alt="Build Status" title=""></a>
</p>

- [1. Overview](#overview)
- [2. Installation](#installaton)
  - [2.1. Adding the Repository and the Component in CUBA Studio](#adding-studio)
  - [2.2. Adding Editor in the Screen](#adding-screen)
- [3. Using Editor](#using-editor)
  - [3.1. The Blocks Tab](#blocks)
  - [3.2. The Style Manager Tab](#style)
  - [3.3. The Trait Manager Tab](#trait)
  - [3.4. The Layers Tab](#layers)
  - [3.5. The Top Panel Buttons](#buttons)
  - [3.6. UI components](#components)
  - [3.7. Custom blocks](#custom-blocks)

# 1.Overview <a name="overview"></a>

The add-on provides a visual HTML editor based on a [GrapesJs](https://grapesjs.com/) JavaScript library with the extensive set of HTML elements. It allows building HTML templates without any knowledge of coding. All you need is to drag an element into the canvas. The wide range of options enables independent styling of any element inside the canvas.

Key features:
- Wide variety of built-in HTML elements.
- Viewing representation for different devices.
- Using CSS properties.
- Downloading/uploading HTML code.

![html-editor](img/editor.gif)

See [sample application](https://github.com/cuba-platform/grapesjs-addon-demo), using this component.

# 2. Installation <a name="installation"></a>

To use the component you need to install it in your project and then add the editor to the screen. The complete add-ons installation guide see in [CUBA Platform documentation](https://doc.cuba-platform.com/manual-latest/app_components_usage.html).

## 2.1. Adding the Repository and the Component in CUBA Studio <a name="adding-studio"></a>

To install the component in your project do the following steps:

1. Open your application in CUBA Studio.

2. Click *Edit* in the *Project properties* panel.

3. On the *App components* panel click the *Plus* button next to *Custom components*.

4. Paste the add-on coordinates in the corresponding field as follows: *group:name:version*.

  - Artifact group: *com.haulmont.addon.grapesjs*;
  - Artifact name: *grapesjs-global*;
  - Version: *add-on version*.

 When specifying the component version, you should select the one, which is compatible with the platform version used in your project.

| Platform Version | Add-on Version |
|------------------|----------------|
| 7.1.x            | 0.3.0          |
| 7.0.x            | 0.2.0          |
| 6.10.x           | 0.1.8          |


5. Click *OK* to save the project properties.

6. Then extend application theme, if necessary. Expand *Manage theme* and select *Create theme extension*.

## 2.2. Adding Editor in the Screen <a name="adding-screen"></a>

To use the `GrapesJS` component in your screen, you need to add the special scheme `http://schemas.haulmont.com/grapesjs/ui-component.xsd` in the XML descriptor of the screen and then add a namespace like `grapesjs` for the schema. The schema contains information about the `grapesJsHtmlEditor` tag.

Look at the example of usage:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<window xmlns="http://schemas.haulmont.com/cuba/window.xsd"
        class="com.haulmont.example.web.SomeController"
        xmlns:et="http://schemas.haulmont.com/grapesjs/ui-component.xsd">
    ...
        <et:grapesJsHtmlEditor id="htmlEditor">
            <et:disabledBlocks>
                 //comma separated panel names which should be disabled, for example "map,tabs"
            </et:disabledBlocks>
        </et:grapesJsHtmlEditor>
    ...
```

`grapesjs` UI component provides `setValue(String value)` and `getValue()` methods to set and get HTML content for the component.

# 3. Using Editor <a name="using-editor"></a>

You can add, set and delete elements from the canvas. In addition, you can import and export HTML and CSS code. Here is a description of setting panels of the editor.

## 3.1. The Blocks Tab <a name="blocks"></a>

After opening the editor in your project you can see the *Blocks* tab. The following elements are enabled for adding:

in the *Basic* section
 - *Columns* (1,2 or 3)
 - *Text*
 - *Image*
 - *Video*
 - *Map*
 - *Link block*
 - *Text section*

in the *Extra* section
 - *Navbar*
 - *Tabs*
 - *Custom code*


in the *Forms* section
 - *Form*
 - *Input*
 - *Text area*
 - *Select*
 - *Button*
 - *Label*
 - *Checkbox*
 - *Radio*

![editor-block-manager](img/editor-block-manager.png)

Move elements on the canvas to add.

## 3.2. The Style Manager Tab <a name="style"></a>

You can set the properties of the elements. Select the element and go to the *Style Manager* tab.

![editor-style-manager](img/editor-style-manager.png)


## 3.3. The Trait Manager Tab <a name="trait"></a>

You can define parameters of an element. Select the element and go to the *Trait Manager* tab.

![editor-trait-manager](img/editor-trait-manager.png)

## 3.4. The Layers Tab <a name="layers"></a>

To watch the list of elements on the canvas go to the *Layers* tab. You can hide elements on the canvas while editing.

![editor-layers](img/editor-layers.png)

 ## 3.5. The Top Panel Buttons <a name="buttons"></a>

 The following buttons on the *Top Panel* are available:
- the *Show borders* button
- the *Preview* button
- the *Full-screen* button
- the *Export* button
- the *Undo* button
- the *Redo* button
- the *Import* button
- the *Clear canvas* button

and buttons for changing the screen width.

![editor-top-panel-buttons](img/editor-top-panel-buttons.png)

 ## 3.6. UI components <a name="components"></a>

GrapesJs addon provides following UI components:
- `grapesJsHtmlEditor` - base html editor without any applied plugins
- `grapesJsWebpageHtmlEditor` - html editor suitable for webpage development with applied `webpage, customcode` plugins
- `grapesJsNewsletterHtmlEditor` - html editor suitable for newletter development with applied `newsletter, customcode` plugins

`grapesJsNewsletterHtmlEditor` component has additional `inlineCss`. If enabled then css classes will be inlined in HTML.

UI components can be extended with plugins using `plugin` tag.
A plugin can be selected from a list of predefined plugins or can be configured as a new plugin.

Default available plugins:
- `basicBlocks` - this plugin contains some basic blocks for the GrapesJS editor ([plugin documentation](https://github.com/artf/grapesjs-blocks-basic))
- `ckeditor` - this plugin replaces the default Rich Text Editor with the one from CKEditor ([plugin documentation](https://github.com/artf/grapesjs-plugin-ckeditor))
- `customcode` - this plugin adds the possibility to embed custom code ([plugin documentation](https://github.com/artf/grapesjs-custom-code))
- `flexBlocks` -this plugin adds the Flexbox block which allows creating easily flexible and responsive columns ([plugin documentation](https://github.com/artf/grapesjs-blocks-flexbox))
- `forms` - this plugin adds some basic form components and blocks to help working with forms easier ([plugin documentation](https://github.com/artf/grapesjs-plugin-forms))
- `newsletter` - this preset configures GrapesJS to be used as a Newsletter Builder with some unique features and blocks composed specifically for being rendered correctly inside all major email clients ([plugin documentation](https://github.com/artf/grapesjs-preset-newsletter))
- `postcss` - this plugin enables custom CSS parser via PostCSS ([plugin documentation](https://github.com/artf/grapesjs-parser-postcss))
- `styleFilter` - add filter type input to the Style Manager in GrapesJS ([plugin documentation](https://github.com/artf/grapesjs-style-filter))
- `tabs` - simple tabs component plugin for GrapesJS ([plugin documentation](https://github.com/artf/grapesjs-tabs))
- `tooltip` - simple, CSS only, tooltip component for GrapesJS ([plugin documentation](https://github.com/artf/grapesjs-tooltip))
- `touch` - this plugin enables touch support for the GrapesJS editor ([plugin documentation](https://github.com/artf/grapesjs-touch))
- `tuiImageEditor` - add the [TOAST UI Image Editor](https://ui.toast.com/tui-image-editor/) on Image Components in GrapesJS ([plugin documentation](https://github.com/artf/grapesjs-tui-image-editor))
- `webpage` - this preset configures GrapesJS to be used as a Webpage Builder ([plugin documentation](https://github.com/artf/grapesjs-preset-webpage))

Custom project plugins can be registered via `com.haulmont.addon.grapesjs.web.gui.components.GjsPluginsRepository` class.

Example:
```
<et:grapesJsNewsletterHtmlEditor 
        id="templateEditor"
        inlineCss="true"
        height="100%" width="100%">
    <et:disabledBlocks>
        map,tabs
     </et:disabledBlocks>
    <et:plugin name="ckeditor">
        <!-- path to plugin configuration-->
        <et:optionsPath>/com/haulmont/addon/grapesjs/web/gui/plugins/gjs-plugin-ckeditor.js</et:optionsPath>
    </et:plugin>
    <et:plugin name="forms"/>
    <et:plugin name="flexBlocks"/>
    <et:plugin name="tuiImageEditor"/>
    <et:plugin name="customcode"/>
    <et:plugin name="postcss"/>
    <et:plugin name="touch">
        <et:options>
            <![CDATA[
                ... custom plugin settings ...
            ]]>
        </et:options>
    </et:plugin>
    <et:plugin name="styleFilter"/>
    <et:plugin>
        <et:name>customPlugin</et:name>
        <et:options>
            <![CDATA[
                ... custom plugin settings ...
            ]]>
        </et:options>
    </et:plugin>
</et:grapesJsNewsletterHtmlEditor>
```

 ## 3.7. Custom blocks <a name="custom-blocks"></a>
 
 Custom blocks can be added to the component using `block` tag with the following parameters:
 - *name* - unique block id
 - *label* - name of the block
 - *category* - group the block inside a catgegory
 - *content* - HTML content
 - *contentPath* - path to HTML content 
 - *attributes* - block attributes
 
 Block example:
 ```
<et:block>
	<et:name>h1-block</et:name>
	<et:label>Heading</et:label>
	<et:category>Basic</et:category>
	<et:content>
		<![CDATA[
            <h1>Put your title here</h1>
        ]]>
	</et:content>
	<et:attributes>
		<![CDATA[
            {
               title: 'Insert h1 block',
               class:'fa fa-th'
            }
        ]]>
	</et:attributes>
</et:block>
```

 Custom project blocks can be registered via `com.haulmont.addon.grapesjs.web.gui.components.GjsBlocksRepository` class. 
 Registered blocks can be added to UI component by `name` attribute. Example `<et:block name="custom block name"/>`.
 
 Please use `class:'fa <fa-icon>'` in block attributes to use Font Awesome icon.