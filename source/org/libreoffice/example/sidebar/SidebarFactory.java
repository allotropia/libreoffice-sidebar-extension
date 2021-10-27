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
import com.sun.star.beans.PropertyValue;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.ui.XUIElement;
import com.sun.star.uno.XComponentContext;

import de.muenchen.allg.dialog.adapter.AbstractSidebarFactory;
import de.muenchen.allg.afid.UNO;

/**
 * Factory for the the sidebar. It's mentioned in Factories.xcu.
 */
public class SidebarFactory extends AbstractSidebarFactory
{

    public static final String SERVICE_NAME = "org.libreoffice.example.sidebar.MySidebarFactory";

    /**
     * Create the sidebar
     *
     * @param context
     *            The context of the bar.
     */
    public SidebarFactory(XComponentContext context)
    {
        super(SERVICE_NAME, context);
    }

    @Override
    public XUIElement createUIElement(String resourceUrl, PropertyValue[] arguments) throws NoSuchElementException
    {
        if (!resourceUrl.startsWith("private:resource/toolpanel/MySidebarFactory"))
        {
            throw new NoSuchElementException(resourceUrl, this);
        }

        XWindow parentWindow = null;
        for (int i = 0; i < arguments.length; i++)
        {
            if (arguments[i].Name.equals("ParentWindow"))
            {
                parentWindow = UNO.XWindow(arguments[i].Value);
                break;
            }
        }

        return new MySidebarPanel(context, parentWindow, resourceUrl);
    }
}
