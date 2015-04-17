package org.haibin369.jms.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JMSUtils {
    private static final String INITIAL_CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";
    private static final String PROVIDER_URL = "jnp://localhost:1099";

    private static Context context;

    static{
        try {
            Hashtable params = new Hashtable(2);
            params.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            params.put(Context.PROVIDER_URL, PROVIDER_URL);
            context = new InitialContext(params);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static <T> T lookupJNDI(String jndiName) throws NamingException {
        return (T) context.lookup(jndiName);
    }

}
