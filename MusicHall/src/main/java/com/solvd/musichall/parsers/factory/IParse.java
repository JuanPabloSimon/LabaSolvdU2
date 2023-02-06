package com.solvd.musichall.parsers.factory;

import java.io.File;

public interface IParse {

    public void marshalling(Object entity, File file);

    public Object unmarshalling(Class c, File file);

    public String getExtension();
}