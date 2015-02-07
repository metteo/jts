package com.vividsolutions.jtswebapp.client.test;

import java.util.HashMap;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.vividsolutions.jtswebapp.shared.test.Test;
import com.vividsolutions.jtswebapp.shared.test.TestCase;
import com.vividsolutions.jtswebapp.shared.test.TestRun;

public class TestReader {

	public TestRun read(String s) {

		Document doc = XMLParser.parse(s);
		TestRun tr = new TestRun();

		Element run = doc.getDocumentElement();

		NodeList runChildren = run.getChildNodes();
		for (int i = 0; i < runChildren.getLength(); i++) {
			Node runChild = runChildren.item(i);
			String name = runChild.getNodeName();

			switch (name) {
			case "desc":
				tr.description = trim(runChild.getChildNodes().item(0)
						.getNodeValue());
				break;
			case "precisionModel":
				tr.precisionModel = trim(((Element) runChild)
						.getAttribute("type"));
				break;
			case "resultMatcher":
				tr.resultMatcher = trim(runChild.getChildNodes().item(0)
						.getNodeValue());
				break;
			case "geometryOperation":
				tr.geometryOperation = trim(runChild.getChildNodes().item(0)
						.getNodeValue());
				break;
			case "case":
				TestCase tc = new TestCase();
				tr.testCases.add(tc);
				parseTestCase(runChild, tc);
				break;
			default:
				// sLogger.severe("-------" + name + "-------");
				break;
			}
		}

		return tr;
	}

	private void parseTestCase(Node case0, TestCase tc) {
		NodeList caseChildren = case0.getChildNodes();

		for (int i = 0; i < caseChildren.getLength(); i++) {
			Node caseChild = caseChildren.item(i);
			String name = caseChild.getNodeName();

			switch (name) {
			case "desc":
				tc.description = trim(caseChild.getChildNodes().item(0)
						.getNodeValue());
				break;
			case "a":
				tc.geometryA = trim(caseChild.getChildNodes().item(0)
						.getNodeValue());
				break;
			case "b":
				tc.geometryB = trim(caseChild.getChildNodes().item(0)
						.getNodeValue());
				break;
			case "test":
				Test t = new Test();
				tc.tests.add(t);
				parseTest(caseChild, t);
				break;
			default:
				// sLogger.severe("-------" + name + "-------");
				break;
			}
		}
	}

	private void parseTest(Node test, Test t) {
		Element testEl = (Element) test;

		HashMap<Integer, String> args = new HashMap<>();

		t.exception = testEl.getAttribute("expect");

		Element op = (Element) testEl.getElementsByTagName("op").item(0);

		NamedNodeMap attrs = op.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			String name = attr.getNodeName();

			switch (name) {
			case "name":
				t.operation = attr.getNodeValue();
				break;
			default:
				if (name.startsWith("arg")) {
					int argIdx = Integer.valueOf(name.replace("arg", ""));
					args.put(argIdx, attr.getNodeValue());
				} else {
					// sLogger.severe("-------" + name + "-------");
				}
				break;
			}
		}

		for (int i = 1; i <= args.size(); i++) {
			String argN = args.get(i);
			t.arguments.add(argN);
		}

		t.result = op.getChildNodes().item(0).getNodeValue();
	}

	private String trim(String s) {
		if (s == null) {
			return null;
		}

		return s.trim();
	}
}
