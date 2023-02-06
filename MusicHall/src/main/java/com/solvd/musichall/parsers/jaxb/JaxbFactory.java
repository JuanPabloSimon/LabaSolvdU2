package com.solvd.musichall.parsers.jaxb;

import com.solvd.musichall.parsers.factory.IParse;
import com.solvd.musichall.parsers.factory.ParserTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class JaxbFactory implements IParse {
    private static final Logger LOGGER = LogManager.getLogger(JaxbFactory.class);
    private final String extension = ParserTypes.JAXB.getExtension();

    @Override
    public void marshalling(Object o, File file) {
        try {
            JAXBContext content = JAXBContext.newInstance(o.getClass());
            Marshaller m = content.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(o, file);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Object unmarshalling(Class c, File file) {
        try {
            JAXBContext content = JAXBContext.newInstance(c);
            Unmarshaller u = content.createUnmarshaller();
            Object o = c.getConstructor().newInstance();
            o = (Object) u.unmarshal(file);
            return o;
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String getExtension() {
        return this.extension;
    }
}
