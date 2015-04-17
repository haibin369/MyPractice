package org.haibin369.ejb.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Utils {
    private static final String INITIAL_CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";
    private static final String PROVIDER_URL = "jnp://localhost:1099";


    public static Context getContext() throws NamingException {
        Hashtable params = new Hashtable(2);
        params.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        params.put(Context.PROVIDER_URL, PROVIDER_URL);
        return new InitialContext(params);
    }
}
