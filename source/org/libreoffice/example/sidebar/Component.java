package org.libreoffice.example.sidebar;

import com.sun.star.comp.loader.FactoryHelper;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.lang.XSingleServiceFactory;
import com.sun.star.registry.XRegistryKey;

/**
 * This class basically represents the extension. Its __writeRegistryServiceInfo() method registers
 * two services, the panel factory and the protocol handler, while its __getServiceFactory() method
 * is the factory method that creates objects of the two services.
 */
public class Component
{
    public static XSingleServiceFactory __getServiceFactory(final String sImplementationName,
            final XMultiServiceFactory xFactory, final XRegistryKey xKey)
    {
        if (sImplementationName.equals(SidebarFactory.class.getName()))
        {
            return FactoryHelper.getServiceFactory(SidebarFactory.class, SidebarFactory.SERVICE_NAME, xFactory, xKey);
        }

        return null;
    }

    public static boolean __writeRegistryServiceInfo(final XRegistryKey xKey)
    {
        boolean bResult = true;
        try
        {
            bResult &= FactoryHelper.writeRegistryServiceInfo(SidebarFactory.class.getName(),
                    SidebarFactory.SERVICE_NAME, xKey);
        } catch (java.lang.Exception e)
        {
            System.err.println(e);
        }

        return bResult;
    }
}
