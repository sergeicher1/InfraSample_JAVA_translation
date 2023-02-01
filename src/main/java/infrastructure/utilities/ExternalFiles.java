package infrastructure.utilities;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class ExternalFiles {

    public static class XML {

        public static String ReadData(String dataPath, String nodeName) {
            Allure.step("Read data from XML file");
            DocumentBuilder dBuilder;
            Document doc = null;
            File Fxmlfile = new File(dataPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            try {
                dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(Fxmlfile);
            } catch (Exception e) {
                System.out.println("Exception in reading XML file: " + e);
            }
            assert doc != null;
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName(nodeName).item(0).getTextContent();
        }
        //
        // Continue from here implementing as needed
        //
    }

    public static class PDF {
        public static String[] Read(String dataPath) throws IOException {
            Allure.step("Read PDF file");

            String[] lines = new String[0];
            try (PDDocument document = PDDocument.load(new File(dataPath))) {
                if (!document.isEncrypted()) {

                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);

                    PDFTextStripper tStripper = new PDFTextStripper();

                    String pdfFileInText = tStripper.getText(document);

                    // split by whitespace
                    lines = pdfFileInText.split("\\r?\\n");

                }
                return lines;

            }
        }
        //
        // Continue from here implementing as needed
        //
    }

    public static class Text {
        public static void Add(String filePath, String data) {
            Allure.step("Adds text to txt file, creates new if file doesn't exist");

            try {

                // Open given file in append mode by creating an
                // object of BufferedWriter class
                BufferedWriter out = new BufferedWriter(
                        new FileWriter(filePath, true));

                // Writing on output stream
                out.write("\n" + data);
                // Closing the connection
                out.close();
            }

            // Catch block to handle the exceptions
            catch (IOException e) {

                // Display message when exception occurs
                System.out.println("exception occurred" + e);
            }
        }

        public static String Read(String filePath) throws IOException {
            Allure.step("Reads text file");

            return Files.readString(Paths.get(filePath));
        }

        //
        // Continue from here implementing as needed
        //

    }
//
//    public static class Excel {
//
//        public static void Read() {
//            Allure.step("Read from excel file");
//
//            FileInputStream file = new FileInputStream(new File(fileLocation));
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet = workbook.getSheetAt(0);
//
//            Map<Integer, List<String>> data = new HashMap<>();
//            int i = 0;
//            for (Row row : sheet) {
//                data.put(i, new ArrayList<String>());
//                for (Cell cell : row) {
//                    switch (cell.getCellType()) {
//                        case STRING: ...break;
//                        case NUMERIC: ...break;
//                        case BOOLEAN: ...break;
//                        case FORMULA: ...break;
//                        default:
//                            data.get(new Integer(i)).add(" ");
//                    }
//                }
//                i++;
//            }
//            data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
//            if (DateUtil.isCellDateFormatted(cell)) {
//                data.get(i).add(cell.getDateCellValue() + "");
//            } else {
//                data.get(i).add(cell.getNumericCellValue() + "");
//            }
//            data.get(i).add(cell.getBooleanCellValue() + "");
//            data.get(i).add(cell.getCellFormula() + "");
//
//        }
//    }


}
