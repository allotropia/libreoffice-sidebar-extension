/*-
 * Copyright (C) 2005 - 2021 Landeshauptstadt München
 * Copyright (C) 2021 allotropia software GmbH
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */

package org.libreoffice.example.sidebar;

import com.sun.star.awt.XWindow;
import com.sun.star.uno.XComponentContext;

import org.libreoffice.ext.unohelper.dialog.adapter.AbstractSidebarPanel;

/**
 * Sample sidebar panel
 */
public class MySidebarPanel extends AbstractSidebarPanel
{
    /**
     * Create the panel.
     *
     * @param context
     *            The context.
     * @param parentWindow
     *            The parent window.
     * @param resourceUrl
     *            The resource URL.
     */
    public MySidebarPanel(XComponentContext context, XWindow parentWindow, String resourceUrl)
    {
        super(resourceUrl);
        panel = new MySidebarContent(context, parentWindow);
    }
}
