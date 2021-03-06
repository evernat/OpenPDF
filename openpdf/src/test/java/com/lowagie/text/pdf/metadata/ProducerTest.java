package com.lowagie.text.pdf.metadata;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class ProducerTest {

    private static final String PRODUCER = "Producer";

    @Test
    public void changeProducerLineTest() throws IOException {
        String expected = "New Producer.";

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.addProducer(expected);
        document.open();
        document.add(new Paragraph("Hello World!"));
        document.close();

        byte[] pdfBytes = baos.toByteArray();
        baos.close();

        PdfReader reader = new PdfReader(new ByteArrayInputStream(pdfBytes));

        Map<String, String> infoDictionary = reader.getInfo();
        String actual = infoDictionary.get(PRODUCER);

        Assert.assertEquals(expected, actual);

        reader.close();
    }
}