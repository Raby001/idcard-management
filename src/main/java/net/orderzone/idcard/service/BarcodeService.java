package net.orderzone.idcard.service;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class BarcodeService {

    public BufferedImage generateCode128(String text) throws Exception {

        Code128Bean bean = new Code128Bean();

        bean.setModuleWidth(0.2);

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(
                        out,
                        "image/png",
                        300,
                        BufferedImage.TYPE_BYTE_BINARY,
                        false,
                        0
                );

        bean.generateBarcode(
                canvas,
                text
        );

        canvas.finish();

        return javax.imageio.ImageIO.read(
                new java.io.ByteArrayInputStream(
                        out.toByteArray()
                )
        );
    }
}