package com.jeffrey.util.office;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** poi操作excel转换成html
 * @author Jeffrey.Liu
 * @date 2018-06-26 10:31
 **/
public class POIExcelToHtml {
    public static String excelToHtml(String SourcePath, String targetPath) {

        InputStream is = null;
        String htmlExcel = null;
        try {
            File sourcefile = new File(SourcePath);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            if (wb instanceof XSSFWorkbook) {
                XSSFWorkbook xWb = (XSSFWorkbook) wb;
                htmlExcel = POIExcelToHtml.getExcelInfo(xWb);
                FileUtils.writeFile(htmlExcel, targetPath);
            } else if (wb instanceof HSSFWorkbook) {
                HSSFWorkbook hWb = (HSSFWorkbook) wb;
                htmlExcel = POIExcelToHtml.getExcelInfo(hWb);
                FileUtils.writeFile(htmlExcel, targetPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return htmlExcel;
    }

    private static String getExcelInfo(Workbook wb) {
        StringBuffer sb = new StringBuffer();
        int sheetCounts = wb.getNumberOfSheets();
        // sb.append("<!DOCTYPE html> <html> <head> <meta charset='utf-8'
        // /><title>HTML实例</title></head> ");

        for (int i = 0; i < sheetCounts; i++) {
            // 获取第一个Sheet的内容
            Sheet sheet = wb.getSheetAt(i);
            int lastRowNum = sheet.getLastRowNum();
            Map<String, String> map[] = getRowSpanColSpanMap(sheet);
            // sb.append("<br><br>");
            sb.append(sheet.getSheetName());
            sb.append("<table style='border-collapse:collapse;' width='100%'>");
            // 兼容
            Row row = null;
            // 兼容
            Cell cell = null;
            for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
                row = sheet.getRow(rowNum);
                if (row == null) {
                    sb.append("<tr><td > &nbsp;</td></tr>");
                    continue;
                }
                sb.append("<tr>");
                int lastColNum = row.getLastCellNum();
                for (int colNum = 0; colNum < lastColNum; colNum++) {
                    cell = row.getCell(colNum);
                    // 特殊情况 空白的单元格会返回null
                    if (cell == null) {
                        sb.append("<td>&nbsp;</td>");
                        continue;
                    }

                    String stringValue = getCellValue(cell);
                    if (map[0].containsKey(rowNum + "," + colNum)) {
                        String pointString = map[0].get(rowNum + "," + colNum);
                        map[0].remove(rowNum + "," + colNum);
                        int bottomeRow = Integer.valueOf(pointString.split(",")[0]);
                        int bottomeCol = Integer.valueOf(pointString.split(",")[1]);
                        int rowSpan = bottomeRow - rowNum + 1;
                        int colSpan = bottomeCol - colNum + 1;
                        sb.append("<td rowspan= '" + rowSpan + "' colspan= '" + colSpan + "' ");
                    } else if (map[1].containsKey(rowNum + "," + colNum)) {
                        map[1].remove(rowNum + "," + colNum);
                        continue;
                    } else {
                        sb.append("<td ");
                    }
                    // 处理单元格样式
                    dealExcelStyle(wb, sheet, cell, sb);
                    sb.append(">");
                    if (stringValue == null || "".equals(stringValue.trim())) {
                        sb.append(" &nbsp; ");
                    } else {
                        // 将ascii码为160的空格转换为html下的空格（&nbsp;）
                        sb.append(stringValue.replace(String.valueOf((char) 160), "&nbsp;"));
                    }
                    sb.append("</td>");
                }
                sb.append("</tr>");
            }
            sb.append("</table>");
        }

        // sb.append("</body></html> ");
        return sb.toString();
    }

    private static Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {
        Map<String, String> map0 = new HashMap<String, String>();
        Map<String, String> map1 = new HashMap<String, String>();
        int mergedNum = sheet.getNumMergedRegions();
        CellRangeAddress range = null;
        for (int i = 0; i < mergedNum; i++) {
            range = sheet.getMergedRegion(i);
            int topRow = range.getFirstRow();
            int topCol = range.getFirstColumn();
            int bottomRow = range.getLastRow();
            int bottomCol = range.getLastColumn();
            map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);
            // System.out.println(topRow + "," + topCol + "," + bottomRow + ","
            // + bottomCol);
            int tempRow = topRow;
            while (tempRow <= bottomRow) {
                int tempCol = topCol;
                while (tempCol <= bottomCol) {
                    map1.put(tempRow + "," + tempCol, "");
                    tempCol++;
                }
                tempRow++;
            }
            map1.remove(topRow + "," + topCol);
        }
        Map[] map = { map0, map1 };
        return map;
    }

    /**
     * 200 * 获取表格单元格Cell内容 201 * @param cell 202 * @return 203
     */
    private static String getCellValue(Cell cell) {
        String result = new String();
        CellType cellTypeEnum = cell.getCellTypeEnum();
        //numeric包含数字和日期格式
        if (cellTypeEnum.equals(CellType.NUMERIC)){
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = null;
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("hh:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                        .getBuiltinFormat("yyyy-MM-dd HH:mm:ss")) {
                    // 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                Date date = cell.getDateCellValue();
                result = sdf.format(date);
            } else if (cell.getCellStyle().getDataFormat() == 58) {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                double value = cell.getNumericCellValue();
                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                result = sdf.format(date);
            } else {
                double value = cell.getNumericCellValue();
                CellStyle style = cell.getCellStyle();
                DecimalFormat format = new DecimalFormat();
                String temp = style.getDataFormatString();
                // 单元格设置成常规
                if ("General".equals(temp)) {
                    format.applyPattern("#");
                }
                result = format.format(value);
            }
        }else if (cellTypeEnum.equals(CellType.STRING)){
            result = cell.getRichStringCellValue().toString();
        }else if (cellTypeEnum.equals(CellType.BLANK)){
            result = "";
        }else{
            result = "";
        }

        return result;
    }

    /**
     * 251 * 处理表格样式 252 * @param wb 253 * @param sheet 254 * @param cell 255
     * * @param sb 256
     */
    private static void dealExcelStyle(Workbook wb, Sheet sheet, Cell cell, StringBuffer sb) {

        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle != null) {
            HorizontalAlignment alignmentEnum = cellStyle.getAlignmentEnum();
            // 单元格内容的水平对齐方式
            sb.append("align='" + convertAlignToHtml(alignmentEnum) + "' ");
            VerticalAlignment verticalAlignmentEnum = cellStyle.getVerticalAlignmentEnum();
            // 单元格中内容的垂直排列方式
            sb.append("valign='" + convertVerticalAlignToHtml(verticalAlignmentEnum) + "' ");

            if (wb instanceof XSSFWorkbook) {
                XSSFFont xf = ((XSSFCellStyle) cellStyle).getFont();
                short boldWeight=0;
                if (xf.getBold()){
                    boldWeight = 700;
                }
                String fontfamily = xf.getFontName();
                int underline = xf.getUnderline();
                boolean Italic = xf.getItalic();

                sb.append("style='");
                if (underline >= 1) {
                    // 字体型号
                    sb.append("text-decoration:underline;");
                }
                if (Italic) {
                    // 字体型号
                    sb.append("font-style: italic;");
                }
                // 字体型号
                sb.append("font-family:" + fontfamily + ";");
                // 字体加粗
                sb.append("font-weight:" + boldWeight + ";");
                // 字体大小
                sb.append("font-size: " + xf.getFontHeight() / 2 + "%;");
                int columnWidth = sheet.getColumnWidth(cell.getColumnIndex());
                sb.append("width:" + columnWidth + "px;");

                XSSFColor xc = xf.getXSSFColor();
                if (xc != null && !"".equals(xc)) {
                    // 字体颜色
                    sb.append("color:#" + xc.getARGBHex().substring(2) + ";");
                }

                XSSFColor bgColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
                if (bgColor != null && !"".equals(bgColor)) {
                    // 背景颜色
                    sb.append("background-color:#" + bgColor.getARGBHex().substring(2) + ";");
                }
                sb.append(getBorderStyle(0, cellStyle.getBorderTopEnum().getCode(),
                        ((XSSFCellStyle) cellStyle).getTopBorderXSSFColor()));
                sb.append(getBorderStyle(1, cellStyle.getBorderRightEnum().getCode(),
                        ((XSSFCellStyle) cellStyle).getRightBorderXSSFColor()));
                sb.append(getBorderStyle(2, cellStyle.getBorderBottomEnum().getCode(),
                        ((XSSFCellStyle) cellStyle).getBottomBorderXSSFColor()));
                sb.append(getBorderStyle(3, cellStyle.getBorderLeftEnum().getCode(),
                        ((XSSFCellStyle) cellStyle).getLeftBorderXSSFColor()));

            } else if (wb instanceof HSSFWorkbook) {

                HSSFFont hf = ((HSSFCellStyle) cellStyle).getFont(wb);
                short fontColor = hf.getColor();
                short boldWeight=0;
                if (hf.getBold()){
                    boldWeight = 700;
                }
                String fontfamily = hf.getFontName();
                int underline = hf.getUnderline();
                boolean Italic = hf.getItalic();

                sb.append("style='");
                if (underline >= 1) {
                    // 字体型号
                    sb.append("text-decoration:underline;");
                }
                if (Italic) {
                    // 字体型号
                    sb.append("font-style: italic;");
                }
                // 类HSSFPalette用于求的颜色的国际标准形式
                HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette();
                HSSFColor hc = palette.getColor(fontColor);
                // 字体加粗
                sb.append("font-weight:" + boldWeight + ";");
                // 字体型号
                sb.append("font-family:" + fontfamily + ";");
                // 字体大小
                sb.append("font-size: " + hf.getFontHeight() / 2 + "%;");
                String fontColorStr = convertToStardColor(hc);
                if (fontColorStr != null && !"".equals(fontColorStr.trim())) {
                    // 字体颜色
                    sb.append("color:" + fontColorStr + ";");
                }
                int columnWidth = sheet.getColumnWidth(cell.getColumnIndex());
                sb.append("width:" + columnWidth + "px;");
                short bgColor = cellStyle.getFillForegroundColor();
                hc = palette.getColor(bgColor);
                String bgColorStr = convertToStardColor(hc);
                if (bgColorStr != null && !"".equals(bgColorStr.trim())) {
                    // 背景颜色
                    sb.append("background-color:" + bgColorStr + ";");
                }
                sb.append(getBorderStyle(palette, 0, cellStyle.getBorderTopEnum().getCode(), cellStyle.getTopBorderColor()));
                sb.append(getBorderStyle(palette, 1, cellStyle.getBorderRightEnum().getCode(), cellStyle.getRightBorderColor()));
                sb.append(getBorderStyle(palette, 3, cellStyle.getBorderLeftEnum().getCode(), cellStyle.getLeftBorderColor()));
                sb.append(getBorderStyle(palette, 2, cellStyle.getBorderBottomEnum().getCode(), cellStyle.getBottomBorderColor()));
            }

            sb.append("' ");
        }
    }

    /**
     * 330 * 单元格内容的水平对齐方式 331 * @param alignment 332 * @return 333
     */
    private static String convertAlignToHtml(HorizontalAlignment alignmentEnum) {

        String align;
        if (alignmentEnum.equals(HorizontalAlignment.CENTER)){
            align = "center";
        }else if(alignmentEnum.equals(HorizontalAlignment.LEFT)){
            align = "left";
        }else if(alignmentEnum.equals(HorizontalAlignment.RIGHT)){
            align = "right";
        }else {
            align = "left";
        }

        return align;
    }

    /**
     * 354 * 单元格中内容的垂直排列方式 355 * @param verticalAlignment 356 * @return 357
     */
    private static String convertVerticalAlignToHtml(VerticalAlignment alignmentEnum) {

        String valign;
        if (alignmentEnum.equals(VerticalAlignment.BOTTOM)){
            valign = "bottom";
        }else if (alignmentEnum.equals(VerticalAlignment.CENTER)){
            valign = "center";
        }else if (alignmentEnum.equals(VerticalAlignment.TOP)){
            valign = "top";
        }else {
            valign = "middle";
        }
        return valign;
    }

    private static String convertToStardColor(HSSFColor hc) {

        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            if (HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex() == hc.getIndex()) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                sb.append(fillWithZero(Integer.toHexString(hc.getTriplet()[i])));
            }
        }

        return sb.toString();
    }

    private static String fillWithZero(String str) {
        if (str != null && str.length() < 2) {
            return "0" + str;
        }
        return str;
    }

    static String[] bordesr = { "border-top:", "border-right:", "border-bottom:", "border-left:" };
    static String[] borderStyles = { "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ",
            "solid ", "solid", "solid", "solid", "solid", "solid" };

    private static String getBorderStyle(HSSFPalette palette, int b, short s, short t) {
        if (s == 0) {
            return bordesr[b] + borderStyles[s] + "#d0d7e5 1px;";
        }
        String borderColorStr = convertToStardColor(palette.getColor(t));
        borderColorStr = borderColorStr == null || borderColorStr.length() < 1 ? "#000000" : borderColorStr;
        return bordesr[b] + borderStyles[s] + borderColorStr + " 1px;";

    }

    private static String getBorderStyle(int b, short s, XSSFColor xc) {
        if (s == 0) {
            return bordesr[b] + borderStyles[s] + "#d0d7e5 1px;";
        }
        if (xc != null && !"".equals(xc)) {
            String borderColorStr = xc.getARGBHex();
            borderColorStr = borderColorStr == null || borderColorStr.length() < 1 ? "#000000"
                    : borderColorStr.substring(2);
            return bordesr[b] + borderStyles[s] + borderColorStr + " 1px;";
        }
        return "";
    }
}
