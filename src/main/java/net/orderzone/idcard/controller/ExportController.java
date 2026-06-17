package net.orderzone.idcard.controller;

import lombok.RequiredArgsConstructor;
import net.orderzone.idcard.model.Profile;
import net.orderzone.idcard.service.BarcodeService;
import net.orderzone.idcard.service.PdfService;
import net.orderzone.idcard.service.ProfileService;
import net.orderzone.idcard.service.QrCodeService;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@RequiredArgsConstructor
public class ExportController {

    private final ProfileService profileService;
    private final PdfService pdfService;
    private final BarcodeService barcodeService;
    private final QrCodeService qrCodeService;

    @GetMapping("/export/pdf/{id}")
    public ResponseEntity<byte[]> exportPdf(
            @PathVariable Long id
    ) throws Exception {

        Profile profile =
                profileService
                        .getProfileById(id)
                        .orElseThrow();

        byte[] pdf =
                pdfService.generate(profile);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=idcard.pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(pdf);
    }

    @GetMapping("/export/batch")
    public String batchExport() {

        return "Generated "
                + profileService.getAllProfiles().size()
                + " ID Cards";
    }

    @GetMapping(
            value = "/qr/{id}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte[] qr(
            @PathVariable Long id
    ) throws Exception {

        Profile profile =
                profileService
                        .getProfileById(id)
                        .orElseThrow();

        BufferedImage image =
                qrCodeService.generate(
                        profile.getUuid()
                );

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        ImageIO.write(
                image,
                "png",
                out
        );

        return out.toByteArray();
    }

    @GetMapping(
            value = "/barcode/{id}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte[] barcode(
            @PathVariable Long id
    ) throws Exception {

        Profile profile =
                profileService
                        .getProfileById(id)
                        .orElseThrow();

        BufferedImage image =
                barcodeService.generateCode128(
                        profile.getRegistrationNumber()
                );

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        ImageIO.write(
                image,
                "png",
                out
        );

        return out.toByteArray();
    }
}