package fatec.api.pixel.horaextra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.api.pixel.horaextra.service.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("relatorio")
public class RelatorioController {

	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=extrato_hora.xls";

		response.setHeader(headerKey, headerValue);
		
		relatorioService.generateExcel(response);
		
		response.flushBuffer();
	}

}
