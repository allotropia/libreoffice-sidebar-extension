/*-
 * #%L
 * WollMux
 * %%
 * Copyright (C) 2005 - 2021 Landeshauptstadt München
 * %%
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
 * #L%
 */
package org.libreoffice.example.sidebar;

import java.awt.SystemColor;
import java.util.SortedMap;
import java.util.TreeMap;

import org.libreoffice.example.ui.GuiFactory;
import org.libreoffice.example.ui.layout.Layout;
import org.libreoffice.example.ui.layout.VerticalLayout;

import com.sun.star.accessibility.XAccessible;
import com.sun.star.awt.Rectangle;
import com.sun.star.awt.WindowEvent;
import com.sun.star.awt.XButton;
import com.sun.star.awt.XControl;
import com.sun.star.awt.XControlContainer;
import com.sun.star.awt.XTextComponent;
import com.sun.star.awt.XToolkit;
import com.sun.star.awt.XWindow;
import com.sun.star.awt.XWindowPeer;
import com.sun.star.lang.DisposedException;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lib.uno.helper.ComponentBase;
import com.sun.star.text.XTextDocument;
import com.sun.star.ui.LayoutSize;
import com.sun.star.ui.XSidebarPanel;
import com.sun.star.ui.XToolPanel;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import de.muenchen.allg.afid.UNO;
import de.muenchen.allg.afid.UnoHelperException;
import de.muenchen.allg.dialog.adapter.AbstractActionListener;
import de.muenchen.allg.dialog.adapter.AbstractWindowListener;

/**
 * Create the window for the sidebar panel
 */
public class MySidebarContent extends ComponentBase implements XToolPanel, XSidebarPanel
{
    /**
     * The component factory.
     */
    private XMultiComponentFactory xMCF;

    /**
     * The container of the controls.
     */
    private XControlContainer controlContainer;

    /**
     * The layout of the controls.
     */
    private Layout layout;

    /**
     * Create the sidebar.
     *
     * @param context
     *            The context of the sidebar.
     * @param parentWindow
     *            The parent window of the sidebar.
     */
    public MySidebarContent(XComponentContext context, XWindow parentWindow)
    {
        AbstractWindowListener windowAdapter = new AbstractWindowListener()
        {
            @Override
            public void windowResized(WindowEvent e)
            {
                layout.layout(parentWindow.getPosSize());
            }
        };
        parentWindow.addWindowListener(windowAdapter);
        layout = new VerticalLayout(5, 5, 5, 5, 5);

        xMCF = UnoRuntime.queryInterface(XMultiComponentFactory.class, context.getServiceManager());
        XWindowPeer parentWindowPeer = UNO.XWindowPeer(parentWindow);

        if (parentWindowPeer == null)
        {
            return;
        }

        XToolkit parentToolkit = parentWindowPeer.getToolkit();
        controlContainer = UNO
                .XControlContainer(GuiFactory.createControlContainer(xMCF, context, new Rectangle(0, 0, 0, 0), null));
        UNO.XControl(controlContainer).createPeer(parentToolkit, parentWindowPeer);

        // Add text field
        SortedMap<String, Object> props = new TreeMap<>();
        props.put("TextColor", SystemColor.textInactiveText.getRGB() & ~0xFF000000);
        props.put("Autocomplete", false);
        props.put("HideInactiveSelection", true);
        XTextComponent searchBox = UNO.XTextComponent(
                GuiFactory.createTextfield(xMCF, context, "", new Rectangle(0, 0, 100, 32), props, null));
        controlContainer.addControl("searchbox", UNO.XControl(searchBox));
        layout.addControl(UNO.XControl(searchBox));

        // Add button
        XControl button = GuiFactory.createButton(xMCF, context, "Insert", null, new Rectangle(0, 0, 100, 32), null);
        XButton xbutton = UNO.XButton(button);
        AbstractActionListener xButtonAction = event -> {
            String text = searchBox.getText();
            try
            {
                UNO.init(xMCF);
                XTextDocument doc = UNO.getCurrentTextDocument();
                doc.getText().setString(text);
            } catch (UnoHelperException e1)
            {
                e1.printStackTrace();
            }
        };
        xbutton.addActionListener(xButtonAction);
        controlContainer.addControl("button1", button);
        layout.addControl(button);
    }

    @Override
    public XAccessible createAccessible(XAccessible arg0)
    {
        return UNO.XAccessible(getWindow());
    }

    @Override
    public XWindow getWindow()
    {
        if (controlContainer == null)
        {
            throw new DisposedException("", this);
        }
        return UNO.XWindow(controlContainer);
    }

    @Override
    public LayoutSize getHeightForWidth(int width)
    {
        int height = layout.getHeightForWidth(width);
        return new LayoutSize(height, height, height);
    }

    @Override
    public int getMinimalWidth()
    {
        return 300;
    }
}
