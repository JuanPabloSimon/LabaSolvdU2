package com.solvd.musichall.parsers.factory;

public enum ParserTypes {
    JAXB(".xml"), JACKSON(".json"), SAX(".xml");

    private String extension;

    ParserTypes(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }
}
