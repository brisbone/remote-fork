package de.uwxy.lirc;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Uli Wiegand on 11.01.18.
 */

public class ReadXml {

    private static final String FIRST = "//device[@manufacturer='";
    private static final String TAG = "ReadXml";
    private static final String SECOND = "']/button[@android='";
    private static final String LAST = "']/text()";
    private Context context;

    public ReadXml(Context myContext) {
        this.context = myContext;
    }
    public String readRemoteXml(String device, String deviceData) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("remote.xml");

        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        //try {

            //XPathExpression expr = xPath.compile(FIRST + device + SECOND + deviceData + LAST);
            //String name;
            String name = (FIRST + device + SECOND + deviceData + LAST);
            //name = (String) expr.evaluate(document, XPathConstants.STRING);
            Log.e(TAG, "readRemoteXml: " + name );
            inputStream.close();
/*
        } catch (XPathExpressionException e) {
        e.printStackTrace();
        }*/
        return name;
    }
}
