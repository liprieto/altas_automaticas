package com.itcsoluciones.aa.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ReadExcel {

	public List<ReadExcel> getDataFromExcel(String fileName) throws IOException, EncryptedDocumentException, InvalidFormatException {

		Workbook workbook = WorkbookFactory.create(new FileInputStream("/home/luisp/Documentos/prueba-carga/" + fileName));

		Sheet sheet = workbook.getSheetAt(0);
		// System.out.println("Cantidad de hojas: " + sheet);
		
		String base="";
		
		int filas = sheet.getPhysicalNumberOfRows();
		System.out.println("Cantidad de filas: " + filas);
		int columnas = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Cantidad de columnas: " + columnas);
		String matriz[][] = new String[filas][columnas];
		Cell cell;
		for (int i = 1; i < filas; i++) {
			// Archivo original
			// for (int i = 1; i < rows; i++) {
			for (int j = 1; j < columnas; j++) {

				// cell = sheet.getRow(i).getCell(j);
				if ((cell = sheet.getRow(i).getCell(j)) != null) {
					DataFormatter formatter = new DataFormatter();
					String cellContents = formatter.formatCellValue(cell);
					matriz[i][j] = cellContents;
					System.out.println(matriz[i][j]);

					// Validacion de datos del prestador:
					
				}
				if(j==2) {
					String filial = (matriz[i][2]);
					if(filial != null) {
						base = "";
						
						switch (filial) {
						case "60":
							base = "SIFOSOSDEMETRO";
							break;
						case "11":
							base = "SIFOSOSDEMENDOZA";
							break;
						case "2":
							base = "SIFOSOSDECORDOBA";
							break;
						default:
							base = "SIFOSOSDENACIONAL";
							break;
						}
						System.out.println("Corresponde a: " + base);
					}
				}
				
			}
		}
		
		
		
		DataFormatter dataFormatter = new DataFormatter();

		List<ReadExcel> cellList = new ArrayList<>();

		Iterator<Row> rowIterator = sheet.iterator();

		int rowNumber = 0;
		
		
		
		
		/*
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();

			rowNumber++;
			if (rowNumber > 1) {
				AltaOsde altaOsde = new AltaOsde();

				altaOsde.setOperador(dataFormatter.formatCellValue(row.getCell(1)));
				altaOsde.setFilial(dataFormatter.formatCellValue(row.getCell(2)));
				altaOsde.setDelegacion(dataFormatter.formatCellValue(row.getCell(3)));
				altaOsde.setPos(dataFormatter.formatCellValue(row.getCell(4)));
				altaOsde.setCodigo_prestador(dataFormatter.formatCellValue(row.getCell(5)));
				altaOsde.setNombre(dataFormatter.formatCellValue(row.getCell(6)));
				altaOsde.setEspecialidad(dataFormatter.formatCellValue(row.getCell(7)));
				altaOsde.setCuit_prestador(dataFormatter.formatCellValue(row.getCell(9)));
				altaOsde.setCalle(dataFormatter.formatCellValue(row.getCell(10)));
				altaOsde.setNumero_domicilio(dataFormatter.formatCellValue(row.getCell(11)));
				altaOsde.setPiso(dataFormatter.formatCellValue(row.getCell(12)));
				altaOsde.setDepartamento(dataFormatter.formatCellValue(row.getCell(13)));
				altaOsde.setLocalidad(dataFormatter.formatCellValue(row.getCell(15)));
				altaOsde.setProvincia(dataFormatter.formatCellValue(row.getCell(16)));
				altaOsde.setTelefono(dataFormatter.formatCellValue(row.getCell(28)));

				cellList.add(altaOsde);

			}

		}*/

		workbook.close();
		// System.out.println(listemp);
		return cellList;
	}



}
