package com.Arc.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.Arc.entity.Document;
import com.Arc.repo.DocumentRepository;



@Controller
public class HomeController {
	
	Document doc;
	byte[] data;
	
	
	@Autowired
	private DocumentRepository repo;
	
	
	@RequestMapping("/")
	public String displayUpload()
	{
		return "DocumentFile";
	}	
	
	@RequestMapping("/upload")
	public String uploadData(@RequestParam ("document") MultipartFile multipartFile ,
			@RequestParam ("id") int id, ModelMap theModel)
	{
		Document document= new Document();
		document.setId(id);
		document.setName(multipartFile.getOriginalFilename());
		
		try { 
			document.setData(multipartFile.getBytes());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		repo.save(document);
		
		List<Document> list= repo.findAll();
		
		theModel.addAttribute("doc", list);
	
		return "DocumentFile";
	}
	
	@RequestMapping("/download")
	public StreamingResponseBody download (@RequestParam("id") int id, HttpServletResponse response)
	{
	  Optional<Document> document= repo.findById(id);
	  
	  if (document.isPresent()) {
	
		  doc = document.get();
		  data= doc.getData();
		  response.setHeader("Content-Disposition", "attachment; filename=downloaded.jpeg");
	}
	  
	  
		
		return OutputStream -> {
			OutputStream.write(data);
		};
	}	
	

}
