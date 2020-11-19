//package com.net.tools.futurelabnetconnecttools.utils;
//
////
////import org.apache.commons.lang.BooleanUtils;
////import org.apache.commons.lang.CharUtils;
////import org.apache.commons.lang.StringUtils;
////import org.apache.poi.hssf.usermodel.*;
////import org.apache.poi.hssf.util.Region;
////import org.apache.poi.ss.usermodel.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ExcelUtil<T> {
//
//	private final static String EXCEL2003 = "xls";
//	private final static String EXCEL2007 = "xlsx";
//
//	private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
//
//	/**
//	 * 拼装单个obj
//	 */
//	public void exportExcelNew(String fileName, String columnNameStr, String columnFieldStr, List<T> dataList,
//			HttpServletResponse response) throws Exception {
//
//		String[] columnNames = URLDecoder.decode(columnNameStr, "UTF-8").split(",");
//		String[] columnFields = columnFieldStr.split(",");
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String pattern = sdf.format(new Date());
//		short rowHeight_x = 700;// 小单元格行高
//		// 声明一个工作薄
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		// 生成一个表格
//		HSSFSheet sheet = workbook.createSheet(fileName + pattern);
//		// 设置表格默认列宽度为15个字节
//		sheet.setDefaultColumnWidth((short) 15);
//		// 生成一个样式
//		HSSFCellStyle style = workbook.createCellStyle();
//
//		CellStyle css = workbook.createCellStyle();
//
//		DataFormat format = workbook.createDataFormat();
//
//		css.setDataFormat(format.getFormat("@"));
//
//		// 设置这些样式
//		// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index); //设置前景色
//		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		// 生成一个字体
//		HSSFFont font = workbook.createFont();
//		// font.setColor(HSSFColor.VIOLET.index);
//		font.setFontHeightInPoints((short) 10);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		// 把字体应用到当前的样式
//		style.setFont(font);
//		// 生成并设置另一个样式
//		HSSFCellStyle style2 = workbook.createCellStyle();
//		HSSFDataFormat format1 = workbook.createDataFormat();
//		style2.setDataFormat(format.getFormat("@"));
//
//		// style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index); //生成前景色
//		// style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		// 生成另一个字体
//		HSSFFont font2 = workbook.createFont();
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//		font2.setFontName("宋体"); // 设置字体
//		font2.setFontHeightInPoints((short) 10); // 设置字体大小
//		// 把字体应用到当前的样式
//		style2.setFont(font2);
//		HSSFCellStyle cellStyle_T = workbook.createCellStyle(); // 样式对象
//		cellStyle_T.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//		cellStyle_T.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
//		cellStyle_T.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		cellStyle_T.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
//		cellStyle_T.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
//		cellStyle_T.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
//		cellStyle_T.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // 设置颜色
//		HSSFFont font_t = workbook.createFont();// 设置字体
//		font_t.setFontName("Arial");
//		font_t.setFontHeightInPoints((short) 11);// 设置字体大小
//		font_t.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
//		cellStyle_T.setFont(font_t);
//		HSSFCellStyle cellStyle_C = workbook.createCellStyle(); // 样式对象
//		cellStyle_C.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//		cellStyle_C.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		cellStyle_C.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
//		cellStyle_C.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
//		cellStyle_C.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
//		HSSFFont font_c = workbook.createFont();// 设置字体
//		font_c.setFontName("Arial");
//		font_c.setFontHeightInPoints((short) 11);// 设置字体大小
//		cellStyle_C.setFont(font_c);
//		cellStyle_C.setWrapText(true);// 自动换行
//		// 声明一个画图的顶级管理器
//		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//		// 定义注释的大小和位置,详见文档
//		// HSSFComment comment = patriarch.createComment(new
//		// HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
//		// 设置注释内容
//		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//		// comment.setAuthor("leno");
//
//		// 产生表格标题行 第一行
//		int indexRow = 0;
//		HSSFRow row = sheet.createRow(indexRow);
//		row.setHeight((short) 1100);// 设置行高
//		HSSFCell cell0 = row.createCell(indexRow++);
//		cell0.setCellValue(new HSSFRichTextString(fileName));
//		cell0.setCellStyle(style);
//		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (columnNames.length - 1)));
//		HSSFRow rowLabel = sheet.createRow(1);
//		for (short i = 0; i < columnNames.length; i++) {
//			HSSFCell cell = rowLabel.createCell(i);
//			cell.setCellStyle(style);
//			HSSFRichTextString text = new HSSFRichTextString(columnNames[i]);
//			cell.setCellValue(text);
//		}
//		// 遍历集合数据，产生数据行
//		Iterator<T> it = dataList.iterator();
//		if (it != null) {
//			int index = 1;
//			// HSSFFont font3 = workbook.createFont();
//			while (it.hasNext()) {
//				index++;
//				row = sheet.createRow(index);
//				T t = (T) it.next();
//
//				for (short i = 0; i < columnFields.length; i++) {
//					HSSFCell cell = row.createCell(i);
//					cell.setCellStyle(style2);
//					String fieldName = columnFields[i];
//
//					try {
//
//						Object value = getValue(t, fieldName);
//
//						// 判断值的类型后进行强制类型转换
//						String textValue = null;
//
//						if (value instanceof String) {
//							textValue = value.toString();
//						} else if (value instanceof Integer) {
//							textValue = value.toString();
//						} else if (value instanceof Long) {
//							textValue = value.toString();
//						} else if (value instanceof BigInteger) {
//							textValue = value.toString();
//						} else if (value instanceof Float) {
//							textValue = value.toString();
//						} else if (value instanceof Double) {
//							textValue = value.toString();
//						} else if (String.valueOf(value).length() > 9L) {
////                            textValue = TimeUtils.unixToTime(Long.valueOf(String.valueOf(value)), "yyyy-MM-dd HH-mm-ss");
//						} else if (value instanceof Long) {
//							textValue = value.toString();
//						} else if (value instanceof BigDecimal) {
//							textValue = value.toString();
//						} else if (value instanceof Boolean) {
//							boolean bValue = (Boolean) value;
//							textValue = "是";
//							if (!bValue) {
//								textValue = "否";
//							}
//						} else if (value instanceof byte[]) {
//							// 有图片时，设置行高为60px;
//							row.setHeightInPoints(60);
//							// 设置图片所在列宽度为80px,注意这里单位的一个换算
//							sheet.setColumnWidth(i, (short) (35.7 * 80));
//							// sheet.autoSizeColumn(i);
//							byte[] bsValue = (byte[]) value;
//							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
//									index);
//							anchor.setAnchorType(2);
//							patriarch.createPicture(anchor,
//									workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
//						} else {
//							// 其它数据类型都当作字符串简单处理
//							if (value != null) {
//								textValue = value.toString();
//							}
//						}
//						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//						if (textValue != null) {
//							Pattern p = Pattern.compile("^//d+(//.//d+)?");
//							Matcher matcher = p.matcher(textValue);
//							if (matcher.matches()) {
//								// 是数字当作double处理
//								cell.setCellValue(Double.parseDouble(textValue));
//							} else {
//								HSSFRichTextString richString = new HSSFRichTextString(textValue);
//								// font3.setColor(HSSFColor.BLUE.index);
//								// richString.applyFont(font3);
//								cell.setCellValue(richString);
//							}
//						}
//					} catch (SecurityException e) {
//						e.printStackTrace();
//					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					} finally {
//						// 清理资源
//					}
//				}
//			}
//		}
//		// 输出流
//		OutputStream out = null;
//		try {
//			Date date = new Date();
//			SimpleDateFormat myFmt1 = new SimpleDateFormat("yy-MM- dd HH:mm");
//			String Filenames = myFmt1.format(date);
//
//			/*
//			 * response.setContentType("application/vnd.ms-excel; charset=utf-8");
//			 * response.setHeader("Content-Disposition", "attachment;filename=" +
//			 * java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
//			 * response.setCharacterEncoding("utf-8");
//			 */
//			response.setHeader("Content-disposition", "attachment; filename=" + Filenames + ".xls");
//			response.setContentType("application/vnd.ms-excel;charset=utf-8");
//			out = response.getOutputStream();
//			workbook.write(out);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			out.flush();
//			out.close();
//		}
//
//	}
//
//	public void exportExcelPlatformEvent(String fileName, String columnNameStr, String columnFieldStr, List<T> dataList,
//							   HttpServletResponse response) throws Exception {
//		String[] columnNames = URLDecoder.decode(columnNameStr, "UTF-8").split(",");
//		String[] columnFields = columnFieldStr.split(",");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String pattern = sdf.format(new Date());
//		short rowHeight_x = 700;// 小单元格行高
//		// 声明一个工作薄
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		// 生成一个表格
//		HSSFSheet sheet = workbook.createSheet(fileName + pattern);
//		// 设置表格默认列宽度为15个字节
//		sheet.setDefaultColumnWidth((short) 15);
//		// 生成一个样式
//		HSSFCellStyle style = workbook.createCellStyle();
//
//		CellStyle css = workbook.createCellStyle();
//
//		DataFormat format = workbook.createDataFormat();
//
//		css.setDataFormat(format.getFormat("@"));
//
//		// 设置这些样式
//		// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index); //设置前景色
//		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		// 生成一个字体
//		HSSFFont font = workbook.createFont();
//		// font.setColor(HSSFColor.VIOLET.index);
//		font.setFontHeightInPoints((short) 10);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		// 把字体应用到当前的样式
//		style.setFont(font);
//		// 生成并设置另一个样式
//		HSSFCellStyle style2 = workbook.createCellStyle();
//		HSSFDataFormat format1 = workbook.createDataFormat();
//		style2.setDataFormat(format.getFormat("@"));
//
//		// style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index); //生成前景色
//		// style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		// 生成另一个字体
//		HSSFFont font2 = workbook.createFont();
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//		font2.setFontName("宋体"); // 设置字体
//		font2.setFontHeightInPoints((short) 10); // 设置字体大小
//		// 把字体应用到当前的样式
//		style2.setFont(font2);
//		HSSFCellStyle cellStyle_T = workbook.createCellStyle(); // 样式对象
//		cellStyle_T.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//		cellStyle_T.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
//		cellStyle_T.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		cellStyle_T.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
//		cellStyle_T.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
//		cellStyle_T.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
//		cellStyle_T.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // 设置颜色
//		HSSFFont font_t = workbook.createFont();// 设置字体
//		font_t.setFontName("Arial");
//		font_t.setFontHeightInPoints((short) 11);// 设置字体大小
//		font_t.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
//		cellStyle_T.setFont(font_t);
//		HSSFCellStyle cellStyle_C = workbook.createCellStyle(); // 样式对象
//		cellStyle_C.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//		cellStyle_C.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		cellStyle_C.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
//		cellStyle_C.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
//		cellStyle_C.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
//		HSSFFont font_c = workbook.createFont();// 设置字体
//		font_c.setFontName("Arial");
//		font_c.setFontHeightInPoints((short) 11);// 设置字体大小
//		cellStyle_C.setFont(font_c);
//		cellStyle_C.setWrapText(true);// 自动换行
//		// 声明一个画图的顶级管理器
//		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//		// 定义注释的大小和位置,详见文档
//		// HSSFComment comment = patriarch.createComment(new
//		// HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
//		// 设置注释内容
//		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//		// comment.setAuthor("leno");
//
//		// 产生表格标题行 第一行
//		int indexRow = 0;
//		HSSFRow row = sheet.createRow(indexRow);
//		row.setHeight((short) 1100);// 设置行高
//		HSSFCell cell0 = row.createCell(indexRow++);
//		cell0.setCellValue(new HSSFRichTextString(fileName));
//		cell0.setCellStyle(style);
//		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (columnNames.length - 1)));
//		HSSFRow rowLabel = sheet.createRow(1);
//		for (short i = 0; i < columnNames.length; i++) {
//			HSSFCell cell = rowLabel.createCell(i);
//			cell.setCellStyle(style);
//			HSSFRichTextString text = new HSSFRichTextString(columnNames[i]);
//			cell.setCellValue(text);
//		}
//		// 遍历集合数据，产生数据行
//		Iterator<T> it = dataList.iterator();
//		if (it != null) {
//			int index = 1;
//			// HSSFFont font3 = workbook.createFont();
//			while (it.hasNext()) {
//				index++;
//				row = sheet.createRow(index);
//				T t = (T) it.next();
//
//				for (short i = 0; i < columnFields.length; i++) {
//					HSSFCell cell = row.createCell(i);
//					cell.setCellStyle(style2);
//					String fieldName = columnFields[i];
//
//					try {
//
//						Object value = getValue(t, fieldName);
//
//						// 判断值的类型后进行强制类型转换
//						String textValue = null;
//
//						if (value instanceof String) {
//							textValue = value.toString();
//						} else if (value instanceof Integer) {
//							textValue = value.toString();
//						} else if (value instanceof Long) {
//							textValue = value.toString();
//						} else if (value instanceof BigInteger) {
//							textValue = value.toString();
//						} else if (value instanceof Float) {
//							textValue = value.toString();
//						} else if (value instanceof Double) {
//							textValue = value.toString();
//						} else if (String.valueOf(value).length() > 9L) {
////                            textValue = TimeUtils.unixToTime(Long.valueOf(String.valueOf(value)), "yyyy-MM-dd HH-mm-ss");
//						} else if (value instanceof Long) {
//							textValue = value.toString();
//						} else if (value instanceof BigDecimal) {
//							textValue = value.toString();
//						} else if (value instanceof Boolean) {
//							boolean bValue = (Boolean) value;
//							textValue = "是";
//							if (!bValue) {
//								textValue = "否";
//							}
//						} else if (value instanceof byte[]) {
//							// 有图片时，设置行高为60px;
//							row.setHeightInPoints(60);
//							// 设置图片所在列宽度为80px,注意这里单位的一个换算
//							sheet.setColumnWidth(i, (short) (35.7 * 80));
//							// sheet.autoSizeColumn(i);
//							byte[] bsValue = (byte[]) value;
//							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
//									index);
//							anchor.setAnchorType(2);
//							patriarch.createPicture(anchor,
//									workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
//						} else {
//							// 其它数据类型都当作字符串简单处理
//							if (value != null) {
//								textValue = value.toString();
//							}
//						}
//						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//						if (textValue != null) {
//							Pattern p = Pattern.compile("^//d+(//.//d+)?");
//							Matcher matcher = p.matcher(textValue);
//							if (matcher.matches()) {
//								// 是数字当作double处理
//								cell.setCellValue(Double.parseDouble(textValue));
//							} else {
//								HSSFRichTextString richString = new HSSFRichTextString(textValue);
//								// font3.setColor(HSSFColor.BLUE.index);
//								// richString.applyFont(font3);
//								cell.setCellValue(richString);
//							}
//						}
//					} catch (SecurityException e) {
//						e.printStackTrace();
//					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					} finally {
//						// 清理资源
//					}
//				}
//			}
//		}
//		// 输出流
//		OutputStream out = null;
//		try {
//			fileName = URLEncoder.encode(fileName+".xls", "UTF-8");
//			response.setHeader("Content-disposition", "attachment;filename="+fileName+";"+"filename*=utf-8''"+fileName);
//			out = response.getOutputStream();
//			workbook.write(out);
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			out.flush();
//			out.close();
//		}
//	}
//
//	// 递归调用，获取多层包装类中的值
//	private Object getValue(Object obj, String colField) throws NoSuchFieldException, SecurityException,
//			IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
//		Object value = null;
//		if (colField.indexOf(".") > 0) {
//			String fieldName = colField.substring(0, colField.indexOf("."));
//			Field field = obj.getClass().getDeclaredField(fieldName);
//			Class<?> clazz = Class.forName(field.getType().getName());
//			field.setAccessible(true);
//			Object fieldObj = field.get(obj);
//			String nextField = colField.substring(colField.indexOf(".") + 1);
//			value = getValue(fieldObj, nextField);
//		} else {
//			Field field = obj.getClass().getDeclaredField(colField);
//			field.setAccessible(true);
//			value = field.get(obj);
//		}
//		return value;
//	}
//
//	public static void main(String[] args) {
//
//	}
//
//	/**
//	 * 生成表格带序号
//	 */
//	public void exportExcelSerialNumber(String fileName, String columnNameStr, String columnFieldStr, List<T> dataList,
//			HttpServletResponse response) throws Exception {
//
//		String[] columnNames = URLDecoder.decode(columnNameStr, "UTF-8").split(",");
//		String[] columnFields = columnFieldStr.split(",");
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String pattern = sdf.format(new Date());
//		short rowHeight_x = 700;// 小单元格行高
//		// 声明一个工作薄
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		// 生成一个表格
//		HSSFSheet sheet = workbook.createSheet(fileName + pattern);
//		// 设置表格默认列宽度为15个字节
//		sheet.setDefaultColumnWidth((short) 15);
//		// 生成一个样式
//		HSSFCellStyle style = workbook.createCellStyle();
//
//		CellStyle css = workbook.createCellStyle();
//
//		DataFormat format = workbook.createDataFormat();
//
//		css.setDataFormat(format.getFormat("@"));
//
//		// 设置这些样式
//		// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index); //设置前景色
//		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		// 生成一个字体
//		HSSFFont font = workbook.createFont();
//		// font.setColor(HSSFColor.VIOLET.index);
//		font.setFontHeightInPoints((short) 10);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		// 把字体应用到当前的样式
//		style.setFont(font);
//		// 生成并设置另一个样式
//		HSSFCellStyle style2 = workbook.createCellStyle();
//		HSSFDataFormat format1 = workbook.createDataFormat();
//		style2.setDataFormat(format.getFormat("@"));
//
//		// style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index); //生成前景色
//		// style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		// 生成另一个字体
//		HSSFFont font2 = workbook.createFont();
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//		font2.setFontName("宋体"); // 设置字体
//		font2.setFontHeightInPoints((short) 10); // 设置字体大小
//		// 把字体应用到当前的样式
//		style2.setFont(font2);
//		HSSFCellStyle cellStyle_T = workbook.createCellStyle(); // 样式对象
//		cellStyle_T.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//		cellStyle_T.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
//		cellStyle_T.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		cellStyle_T.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
//		cellStyle_T.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
//		cellStyle_T.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
//		cellStyle_T.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // 设置颜色
//		HSSFFont font_t = workbook.createFont();// 设置字体
//		font_t.setFontName("Arial");
//		font_t.setFontHeightInPoints((short) 11);// 设置字体大小
//		font_t.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
//		cellStyle_T.setFont(font_t);
//		HSSFCellStyle cellStyle_C = workbook.createCellStyle(); // 样式对象
//		cellStyle_C.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//		cellStyle_C.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		cellStyle_C.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
//		cellStyle_C.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
//		cellStyle_C.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
//		HSSFFont font_c = workbook.createFont();// 设置字体
//		font_c.setFontName("Arial");
//		font_c.setFontHeightInPoints((short) 11);// 设置字体大小
//		cellStyle_C.setFont(font_c);
//		cellStyle_C.setWrapText(true);// 自动换行
//		// 声明一个画图的顶级管理器
//		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//		// 定义注释的大小和位置,详见文档
//		// HSSFComment comment = patriarch.createComment(new
//		// HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
//		// 设置注释内容
//		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//		// comment.setAuthor("leno");
//
//		// 产生表格标题行 第一行
//		int indexRow = 0;
//		HSSFRow row = sheet.createRow(indexRow);
//		row.setHeight((short) 1100);// 设置行高
//		HSSFCell cell0 = row.createCell(indexRow++);
//		cell0.setCellValue(new HSSFRichTextString(fileName));
//		cell0.setCellStyle(style);
//		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (columnNames.length - 1)));
//		HSSFRow rowLabel = sheet.createRow(1);
//		for (short i = 0; i < columnNames.length; i++) {
//			HSSFCell cell = rowLabel.createCell(i);
//			cell.setCellStyle(style);
//			HSSFRichTextString text = new HSSFRichTextString(columnNames[i]);
//			cell.setCellValue(text);
//		}
//		// 遍历集合数据，产生数据行
//		Iterator<T> it = dataList.iterator();
//		if (it != null) {
//			int index = 1;
//			// HSSFFont font3 = workbook.createFont();
//			while (it.hasNext()) {
//				index++;
//				row = sheet.createRow(index);
//				T t = (T) it.next();
//
//				for (short i = 0; i < columnFields.length; i++) {
//					HSSFCell cell = row.createCell(i);
//					cell.setCellStyle(style2);
//					if (i == 0) {
//						cell.setCellValue(Double.parseDouble((index - 1) + ""));
//					} else {
//						String fieldName = columnFields[i];
//
//						try {
//
//							Object value = getValue(t, fieldName);
//
//							// 判断值的类型后进行强制类型转换
//							String textValue = null;
//
//							if (value instanceof String) {
//								textValue = value.toString();
//							} else if (value instanceof Integer) {
//								textValue = value.toString();
//							} else if (value instanceof Long) {
//								textValue = value.toString();
//							} else if (value instanceof BigInteger) {
//								textValue = value.toString();
//							} else if (value instanceof Float) {
//								textValue = value.toString();
//							} else if (value instanceof Double) {
//								textValue = value.toString();
//							} else if (String.valueOf(value).length() > 9L) {
////                            textValue = TimeUtils.unixToTime(Long.valueOf(String.valueOf(value)), "yyyy-MM-dd HH-mm-ss");
//							} else if (value instanceof Long) {
//								textValue = value.toString();
//							} else if (value instanceof BigDecimal) {
//								textValue = value.toString();
//							} else if (value instanceof Boolean) {
//								boolean bValue = (Boolean) value;
//								textValue = "是";
//								if (!bValue) {
//									textValue = "否";
//								}
//							} else if (value instanceof byte[]) {
//								// 有图片时，设置行高为60px;
//								row.setHeightInPoints(60);
//								// 设置图片所在列宽度为80px,注意这里单位的一个换算
//								sheet.setColumnWidth(i, (short) (35.7 * 80));
//								// sheet.autoSizeColumn(i);
//								byte[] bsValue = (byte[]) value;
//								HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index,
//										(short) 6, index);
//								anchor.setAnchorType(2);
//								patriarch.createPicture(anchor,
//										workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
//							} else {
//								// 其它数据类型都当作字符串简单处理
//								if (value != null) {
//									textValue = value.toString();
//								}
//							}
//							// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//							if (textValue != null) {
//								Pattern p = Pattern.compile("^//d+(//.//d+)?");
//								Matcher matcher = p.matcher(textValue);
//								if (matcher.matches()) {
//									// 是数字当作double处理
//									cell.setCellValue(Double.parseDouble(textValue));
//								} else {
//									HSSFRichTextString richString = new HSSFRichTextString(textValue);
//									// font3.setColor(HSSFColor.BLUE.index);
//									// richString.applyFont(font3);
//									cell.setCellValue(richString);
//								}
//							}
//						} catch (SecurityException e) {
//							e.printStackTrace();
//						} catch (IllegalArgumentException e) {
//							e.printStackTrace();
//						} catch (IllegalAccessException e) {
//							e.printStackTrace();
//						} finally {
//							// 清理资源
//						}
//					}
//
//				}
//			}
//		}
//		// 输出流
//		OutputStream out = null;
//		try {
//			Date date = new Date();
//			SimpleDateFormat myFmt1 = new SimpleDateFormat("yy-MM- dd HH:mm");
//			String Filenames = myFmt1.format(date);
//
//			/*
//			 * response.setContentType("application/vnd.ms-excel; charset=utf-8");
//			 * response.setHeader("Content-Disposition", "attachment;filename=" +
//			 * java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
//			 * response.setCharacterEncoding("utf-8");
//			 */
//			response.setHeader("Content-disposition", "attachment; filename=" + Filenames+fileName + ".xls");
//			response.setContentType("application/vnd.ms-excel;charset=utf-8");
//			out = response.getOutputStream();
//			workbook.write(out);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			out.flush();
//			out.close();
//		}
//
//	}
//
////	public static <T> List<T> readExcel(String path, Class<T> cls, MultipartFile file) {
////
////		String fileName = file.getOriginalFilename();
////		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
////			log.error("上传文件格式不正确");
////		}
////		List<T> dataList = new ArrayList<>();
////		Workbook workbook = null;
////		try {
////			InputStream is = file.getInputStream();
////			if (fileName.endsWith(EXCEL2007)) {
//////                FileInputStream is = new FileInputStream(new File(path));
////				workbook = new XSSFWorkbook(is);
////			}
////			if (fileName.endsWith(EXCEL2003)) {
//////                FileInputStream is = new FileInputStream(new File(path));
////				workbook = new HSSFWorkbook(is);
////			}
////			if (workbook != null) {
////				// 类映射 注解 value-->bean columns
////				Map<String, List<Field>> classMap = new HashMap<>();
////				List<Field> fields = Stream.of(cls.getDeclaredFields()).collect(Collectors.toList());
////				fields.forEach(field -> {
////					ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
////					if (annotation != null) {
////						String value = annotation.value();
////						if (StringUtils.isBlank(value)) {
////							return;// return起到的作用和continue是相同的 语法
////						}
////						if (!classMap.containsKey(value)) {
////							classMap.put(value, new ArrayList<>());
////						}
////						field.setAccessible(true);
////						classMap.get(value).add(field);
////					}
////				});
////				// 索引-->columns
////				Map<Integer, List<Field>> reflectionMap = new HashMap<>(16);
////				// 默认读取第一个sheet
////				Sheet sheet = workbook.getSheetAt(0);
////
////				boolean firstRow = true;
////				for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
////					Row row = sheet.getRow(i);
////					// 首行 提取注解
////					if (firstRow) {
////						for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
////							Cell cell = row.getCell(j);
////							String cellValue = getCellValue(cell);
////							if (classMap.containsKey(cellValue)) {
////								reflectionMap.put(j, classMap.get(cellValue));
////							}
////						}
////						firstRow = false;
////					} else {
////						// 忽略空白行
////						if (row == null) {
////							continue;
////						}
////						try {
////							T t = cls.newInstance();
////							// 判断是否为空白行
////							boolean allBlank = true;
////							for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
////								if (reflectionMap.containsKey(j)) {
////									Cell cell = row.getCell(j);
////									String cellValue = getCellValue(cell);
////									if (StringUtils.isNotBlank(cellValue)) {
////										allBlank = false;
////									}
////									List<Field> fieldList = reflectionMap.get(j);
////									fieldList.forEach(x -> {
////										try {
////											handleField(t, cellValue, x);
////										} catch (Exception e) {
////											log.error(String.format("reflect field:%s value:%s exception!", x.getName(),
////													cellValue), e);
////										}
////									});
////								}
////							}
////							if (!allBlank) {
////								dataList.add(t);
////							} else {
////								log.warn(String.format("row:%s is blank ignore!", i));
////							}
////						} catch (Exception e) {
////							log.error(String.format("parse row:%s exception!", i), e);
////						}
////					}
////				}
////			}
////			is.close();
////		} catch (Exception e) {
////			log.error(String.format("parse excel exception!"), e);
////		} finally {
////			if (workbook != null) {
////				try {
////
////				} catch (Exception e) {
////					log.error(String.format("parse excel exception!"), e);
////				}
////			}
////		}
////		return dataList;
////	}
//
//	private static <T> void handleField(T t, String value, Field field) throws Exception {
//		Class<?> type = field.getType();
//		if (type == null || type == void.class || StringUtils.isBlank(value)) {
//			return;
//		}
//		if (type == Object.class) {
//			field.set(t, value);
//			// 数字类型
//		} else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
//			if (type == int.class || type == Integer.class) {
//				field.set(t, NumberUtils.toInt(value));
//			} else if (type == long.class || type == Long.class) {
//				field.set(t, NumberUtils.toLong(value));
//			} else if (type == byte.class || type == Byte.class) {
//				field.set(t, NumberUtils.toByte(value));
//			} else if (type == short.class || type == Short.class) {
//				field.set(t, NumberUtils.toShort(value));
//			} else if (type == double.class || type == Double.class) {
//				field.set(t, NumberUtils.toDouble(value));
//			} else if (type == float.class || type == Float.class) {
//				field.set(t, NumberUtils.toFloat(value));
//			} else if (type == char.class || type == Character.class) {
//				field.set(t, CharUtils.toChar(value));
//			} else if (type == boolean.class) {
//				field.set(t, BooleanUtils.toBoolean(value));
//			} else if (type == BigDecimal.class) {
//				field.set(t, new BigDecimal(value));
//			}
//		} else if (type == Boolean.class) {
//			field.set(t, BooleanUtils.toBoolean(value));
//		} else if (type == Date.class) {
//			//
//			field.set(t, value);
//		} else if (type == String.class) {
//			field.set(t, value);
//		} else {
//			Constructor<?> constructor = type.getConstructor(String.class);
//			field.set(t, constructor.newInstance(value));
//		}
//	}
//
//	private static String getCellValue(Cell cell) {
//		if (cell == null) {
//			return "";
//		}
//		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//			if (DateUtil.isCellDateFormatted(cell)) {
//				return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
//			} else {
//				return new BigDecimal(cell.getNumericCellValue()).toString();
//			}
//		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
//			return StringUtils.trimToEmpty(cell.getStringCellValue());
//		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
//			return StringUtils.trimToEmpty(cell.getCellFormula());
//		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
//			return "";
//		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
//			return String.valueOf(cell.getBooleanCellValue());
//		} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
//			return "ERROR";
//		} else {
//			return cell.toString().trim();
//		}
//
//	}
//
//}