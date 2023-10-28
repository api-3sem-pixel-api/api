package fatec.api.pixel.horaextra.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosRelatorio;
import fatec.api.pixel.horaextra.model.Cliente;
import fatec.api.pixel.horaextra.repository.ClienteRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class RelatorioService {

	@Autowired
	private CalculoVerbaService service;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void generateExcel(HttpServletResponse response) throws Exception {

		Date dataInicio = sdf.parse("01/10/2023");// temporario enquanto não tem o filtro no front
		Date dataFim = sdf.parse("30/10/2023");
		
		
		List<DadosRelatorio> dadosRelatorio = service.getDadosRelatorio(dataInicio, dataFim); 

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Extrato Horas");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("Nome");
		row.createCell(1).setCellValue("Verba");
		row.createCell(2).setCellValue("Quantidade Horas");

		int dataRowIndex = 1;

		for (DadosRelatorio dado : dadosRelatorio) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(dado.nome());
			dataRow.createCell(1).setCellValue(dado.verba());
			dataRow.createCell(2).setCellValue(dado.quantidadeHoras());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}
}
