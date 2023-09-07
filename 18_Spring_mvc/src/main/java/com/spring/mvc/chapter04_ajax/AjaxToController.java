package com.spring.mvc.chapter04_ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mvc.dto.ProductDTO;

@Controller
@RequestMapping("/ajax")
public class AjaxToController {
	
	@GetMapping("/ajaxToController")
	public String ajaxToController() {
		return "chapter04_ajax/ajaxToController";
	}
	
	// 1. 단일 데이터 전송
	@GetMapping("/a2cEx01")
	public ResponseEntity<Object> a2cEx01(@RequestParam("productCd") String productCd) {
		System.out.println(productCd);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	// 2. DTO 전송
	@GetMapping("/a2cEx02")
	public ResponseEntity<Object> a2cEx02(@ModelAttribute ProductDTO productDTO)  {
		System.out.println(productDTO);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	// 3. map 전송
	@GetMapping("/a2cEx03")
	public ResponseEntity<Object> a2cEx03(@RequestParam Map<String,Object> map) {
		System.out.println(map);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	// 4. List<DTO> 전송
	@GetMapping("/a2cEx04")
	public ResponseEntity<Object> a2cEx04(@RequestParam Map<String,Object> paramList) throws JsonParseException, JsonMappingException, IOException {
	
		//1) 파라메타로 넘어온 데이터를 toString()메서드를 사용하여 문자열로 변환한다. 
		String json = paramList.get("paramList").toString();
		
		//2) ObjectMapper 객체를 생성한다.
		ObjectMapper mapper = new ObjectMapper();
		  
		//3) readValue(json , new TypeReference<타입>(){}) 메서드를 사용하여 JSON 객체를 자바의 객체로 매핑한다.
		List<ProductDTO> productList = mapper.readValue(json, new TypeReference<ArrayList<ProductDTO>>(){});
		for (ProductDTO productDTO : productList) {
			System.out.println(productDTO);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	

	// 5. List<Map> 전송
	@GetMapping("/a2cEx05")
	public ResponseEntity<Object> a2cEx05(@RequestParam Map<String,Object> paramList) throws JsonParseException, JsonMappingException, IOException {
	
		//1) 파라메타로 넘어온 데이터를 toString()메서드를 사용하여 문자열로 변환한다. 
		String json = paramList.get("paramList").toString();
		//2) ObjectMapper 객체를 생성한다.
		ObjectMapper mapper = new ObjectMapper();
		//3) readValue(json데이터 , new TypeReference<타입>(){}) 메서드를 사용하여 JSON 객체를 자바의 객체로 매핑한다.
		List<Map<String,Object>> mapList = mapper.readValue(json, new TypeReference<ArrayList<Map<String,Object>>>(){});
		for (Map<String, Object> map : mapList) {
			System.out.println(map);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
		
		
	}
	
}
