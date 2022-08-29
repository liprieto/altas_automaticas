package com.itcsoluciones.aa.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {
	
	private static String directorio1 = "/home/luisp/Documentos/prueba-carga/";
	
	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes)
			throws IOException {

		if (file == null || file.isEmpty()) {
			attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
			return "redirect:status";
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss:");
		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("user.home"));
		builder.append(File.separator);
		builder.append("prueba-carga");
		builder.append(File.separator);
		builder.append(dtf.format(LocalDateTime.now())+file.getOriginalFilename());

		byte[] fileBytes = file.getBytes();
		Path path = Paths.get(directorio1 + (dtf.format(LocalDateTime.now())+ file.getOriginalFilename()));
		Files.write(path, fileBytes);
		System.out.println(path); 
		
		attributes.addFlashAttribute("message", "Archivo cargado correctamente ["+builder.toString()+"]");

		return "redirect:/status";
	}
	
	@GetMapping("/status")
	public String status() {
		return "status";
	}

}
