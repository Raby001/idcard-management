package net.orderzone.idcard.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import net.orderzone.idcard.model.Profile;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generate(Profile profile)
            throws Exception {

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        PdfWriter writer =
                new PdfWriter(output);

        com.itextpdf.kernel.pdf.PdfDocument pdf =
                new com.itextpdf.kernel.pdf.PdfDocument(writer);

        Document document =
                new Document(pdf);

        document.add(
                new Paragraph("ITC ID CARD")
        );

        document.add(
                new Paragraph("-----------------------")
        );

        document.add(
                new Paragraph(
                        "Name: " +
                        profile.getFullName()
                )
        );

        document.add(
                new Paragraph(
                        "Type: " +
                        profile.getType()
                )
        );

        document.add(
                new Paragraph(
                        "Department: " +
                        profile.getDepartment()
                )
        );

        document.add(
                new Paragraph(
                        "Email: " +
                        profile.getEmail()
                )
        );

        document.add(
                new Paragraph(
                        "Registration: " +
                        profile.getRegistrationNumber()
                )
        );

        document.add(
                new Paragraph(
                        "Issue Date: " +
                        profile.getIssueDate()
                )
        );

        document.add(
                new Paragraph(
                        "Expiry Date: " +
                        profile.getExpiryDate()
                )
        );

        document.close();

        return output.toByteArray();
    }
}