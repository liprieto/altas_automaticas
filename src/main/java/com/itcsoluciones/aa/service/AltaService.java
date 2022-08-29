package com.itcsoluciones.aa.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.itcsoluciones.aa.read.ReadExcel;



@Service
public class AltaService {
	
	// Metodo para guardar archivo OSDE en directorio para almacenamiento y lectura

	public boolean guardarDirectorio1(String fileName) {
		System.out.println(fileName);
		boolean b = false;
		ReadExcel readExcel = new ReadExcel();
		List<ReadExcel> list = null;
		try {
			list = readExcel.getDataFromExcel(fileName);
			System.out.println(list);
		} catch (Exception e) {

		}
		for (ReadExcel archivoOsde : list) {
			System.out.println(archivoOsde);
			b = true;
		}
		return b;
	}
}
