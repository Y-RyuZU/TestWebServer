package com.github.ryuzu.TestWebServer.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FileOperationUtilities {
    private static final String[] units = {" B", " KB", " MB", " GB", " TB"};

    public static String getUnit(final long fileSize) {
        return units[convertToFileSize(fileSize).counter];
    }

    public static BigDecimal getFileSize(final long fileSize) {
        return convertToFileSize(fileSize).bd.stripTrailingZeros();
    }

    private static convertedFile convertToFileSize(final long fileSize) {

        double tempFileSize = fileSize;

        int counter = 0;
        for (; tempFileSize > 1024; counter++) {
            tempFileSize = tempFileSize / 1024;
        }

        BigDecimal bd = new BigDecimal(tempFileSize);
        BigDecimal bd2 = bd.setScale(2, RoundingMode.HALF_UP);

        return new convertedFile(bd2, counter);
    }

    private record convertedFile(BigDecimal bd, int counter) {}
}
