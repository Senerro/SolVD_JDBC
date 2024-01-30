package com.solvd.repairService.helpers.parsers;

import com.solvd.repairService.views.ValidationView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.Scanner;

public class StaxStreamProcessor implements AutoCloseable {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(StaxStreamProcessor.class);
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    public StaxStreamProcessor(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                LOGGER.error("reader wasn't closed");
            }
        }
    }
}
