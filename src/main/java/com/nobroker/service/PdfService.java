package com.nobroker.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nobroker.entity.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public byte[] generatePdfReport(List<User> userList) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        PdfPTable table = new PdfPTable(5); // 5 columns for ID, Name, Email, and Mobile
        addTableHeader(table);
        addRows(table, userList);

        document.add(table);
        document.close();
        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Mobile");
        table.addCell("Email is Verified");
    }

    private void addRows(PdfPTable table, List<User> userList) {
        for (User user : userList) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getName());
            table.addCell(user.getEmail());
            table.addCell(user.getMobile());
            table.addCell(String.valueOf(user.isEmailVerified()));

        }
    }
}
