package fatec.api.pixel.horaextra.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.model.Cliente;
import fatec.api.pixel.horaextra.repository.ClienteRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class RelatorioService {

	@Autowired
	private ClienteRepository courseRepo;

	public void generateExcel(HttpServletResponse response) throws Exception {

		List<Cliente> courses = courseRepo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Extrato Horas");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Price");

		int dataRowIndex = 1;

		for (Cliente course : courses) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(course.getCnpj());
			dataRow.createCell(1).setCellValue(course.getRazaoSocial());
			dataRow.createCell(2).setCellValue(course.getId());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}
}
