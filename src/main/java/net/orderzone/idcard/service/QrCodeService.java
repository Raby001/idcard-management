package net.orderzone.idcard.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QrCodeService {

    public BufferedImage generate(String text) throws Exception {

        QRCodeWriter writer =
                new QRCodeWriter();

        var matrix =
                writer.encode(
                        text,
                        BarcodeFormat.QR_CODE,
                        300,
                        300
                );

        BufferedImage image =
                new BufferedImage(
                        300,
                        300,
                        BufferedImage.TYPE_INT_RGB
                );

        for (int x = 0; x < 300; x++) {

            for (int y = 0; y < 300; y++) {

                image.setRGB(
                        x,
                        y,
                        matrix.get(x, y)
                                ? 0x000000
                                : 0xFFFFFF
                );
            }
        }

        return image;
    }
}