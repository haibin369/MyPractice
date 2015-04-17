package org.haibin369.jndi;

import javax.naming.*;
import java.util.Hashtable;

public class FirstJNDI {
    public static void main(String[] args) throws NamingException {
        Hashtable params = new Hashtable(2);
        params.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        params.put(Context.PROVIDER_URL, "file:/e:/");

        Context context = new InitialContext(params);
        Object file = context.lookup("snagit11.rar");
        System.out.println(file.getClass());

        Object dir = context.lookup("Training");
        System.out.println(dir.getClass());

        NamingEnumeration<Binding> namingEnum = context.listBindings("");
        while(namingEnum.hasMoreElements()){
            Binding binding = namingEnum.next();
            System.out.println(binding.getName() + ": " + binding.getObject());
        }
    }
}
