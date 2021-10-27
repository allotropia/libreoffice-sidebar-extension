# LibreOffice Sidebar Extension

This repository contains some boilerplate code and config you need to get started to build your own LibreOffice Sidebar Extension.

You can use this project as a starting point to write your own Sidebar extension for LibreOffice.

This project was based on the [LibreOffice Starter Extension](https://github.com/smehrbrodt/libreoffice-starter-extension/)

## Get started

1. Install [LibreOffice](http://www.libreoffice.org/download) & the [LibreOffice SDK](http://www.libreoffice.org/download) (5.0 or greater)
2. Install [Eclipse](http://www.eclipse.org/) IDE for Java Developers & the [LOEclipse plugin](https://marketplace.eclipse.org/content/loeclipse)
3. [Download](https://github.com/allotropia/libreoffice-sidebar-extension/archive/master.zip) this project & unzip it
4. Import the project in Eclipse (File->Import->Existing Projects into Workspace)
5. Let Eclipse know the paths to LibreOffice & the SDK (Project->Properties->LibreOffice Properties)
6. Setup Run Configuration
    * Go to Run->Run Configurations
    * Create a new run configuration of the type "LibreOffice Application"
    * Select the project
    * Run!
    * *Hint: Show the error log to view the output of the run configuration (Window->Show View->Error Log)*
7. The extension will be installed in LibreOffice (see Tools->Extension Manager)
8. Select the new Sidebar Tab with the icon "A1"

## Development Hints
* To debug the Java code, just stick a breakpoint anywhere in Eclipse and start your run configuration in debug mode.
* If you add non-code files (or an external .jar) to your extension, you need to mention them in [package.properties](https://github.com/smehrbrodt/libreoffice-sidebar-extension/blob/master/package.properties), else they won't be included in the packaged extension.
* Now go on customizing the extension to your needs. Some helpful links:
  * [OpenOffice Wiki](https://wiki.openoffice.org/wiki/Extensions_development)
  * [API Reference](http://api.libreoffice.org/docs/idl/ref/index.html)
  * [Example extensions](http://api.libreoffice.org/examples/examples.html#Java_examples)
