package com.example.demo.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Action.AccionDao;
import com.example.demo.Email.SendEmail;
import com.example.demo.Pdf.ExportPdfService;
import com.example.demo.Wrapper.BitacoraDataCount;
import com.example.demo.Wrapper.ChartDataBar;
import com.example.demo.Wrapper.DataQueryCharts;
import com.example.demo.Wrapper.Email;
import com.example.demo.Wrapper.MemoWrapper;
import com.example.demo.Wrapper.PdfMemo;
import com.example.demo.Wrapper.PersonWrapper;
import com.example.demo.Wrapper.QueryData;
import com.example.demo.dao.Memo.MemoDao;
import com.example.demo.dao.Person.PersonDao;
import com.example.demo.model.Accion;
import com.example.demo.model.Bitacora;
import com.example.demo.model.Memo;
import com.example.demo.model.Person;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT }, exposedHeaders = { "Content-Disposition" })
public class PersonController {

	int min = 1;
	int max = 3;

	Random random = new Random();

	int value = random.nextInt(max + min) + min;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private MemoDao memoDao;

	@Autowired
	private AccionDao accionDao;

	@Autowired
	private ExportPdfService exportPdfService;

	@Autowired
	private SendEmail sendEmail;

	@Autowired
	private JdbcTemplate jdbc;

	@RequestMapping(value = "api/person", method = RequestMethod.POST)
	private String CreateP(@RequestBody Person person) {
		int i = 1;
		Person newPerson = personDao.getPerson(person);
		if (newPerson == null) {
			Accion currentAccion = accionDao.getAccionById(i);
			Bitacora bita = new Bitacora(LocalDateTime.now(), value);
			currentAccion.getListB().add(bita);
			person.setFecha_Registro(LocalDate.now());
			person.setEstado(1);
			person.getListB().add(bita);
			accionDao.createBitacAccion(currentAccion);
			personDao.CreatePerson(person);
			return "OK";
		}else {
			return "El DNI ya se encuentra registrado";
		}
	}

	@RequestMapping(value = "api/search", method = RequestMethod.POST)
	private Person SearchP(@RequestBody Person person) {
		int i = 2;
		Person newPerson = personDao.getPerson(person);
		if (newPerson == null) {
			return null;
		}
		Accion currentAccion = accionDao.getAccionById(i);
		Bitacora bita = new Bitacora(LocalDateTime.now(), value);
		currentAccion.getListB().add(bita);
		newPerson.getListB().add(bita);
		accionDao.createBitacAccion(currentAccion);
		personDao.updatePerson(newPerson);
		return newPerson;
	}

	@RequestMapping(value = "api/update", method = RequestMethod.PUT)
	private void UpdateP(@RequestBody Person person) {
		int i = 3;
		Accion currentAccion = accionDao.getAccionById(i);
		Bitacora bita = new Bitacora(LocalDateTime.now(), value);
		currentAccion.getListB().add(bita);
		accionDao.createBitacAccion(currentAccion);
		System.out.print(person.getId_person());
		Person currentPerson = personDao.getPersonById(person.getId_person());
		currentPerson.setNombre(person.getNombre());
		currentPerson.setApellido_P(person.getApellido_P());
		currentPerson.setApellido_M(person.getApellido_M());
		currentPerson.setDni(person.getDni());
		currentPerson.setCorreo(person.getCorreo());
		currentPerson.getListB().add(bita);
		personDao.updatePerson(currentPerson);

	}

	@RequestMapping(value = "api/delete", method = RequestMethod.DELETE)
	private String removeP(@RequestBody Person person) {
		int i = 4;
		Accion currentAccion = accionDao.getAccionById(i);
		Bitacora bita = new Bitacora(LocalDateTime.now(), value);
		currentAccion.getListB().add(bita);
		accionDao.createBitacAccion(currentAccion);

		Person currentPerson = personDao.getPerson(person);
		currentPerson.setEstado(0);
		currentPerson.getListB().add(bita);
		personDao.removePerson(currentPerson);

		return "OK";
	}

	@RequestMapping(value = "api/memo/send", method = RequestMethod.POST)
	private void sendM(@RequestBody Email email) throws MessagingException {
		int i = 5;
		int numCorrelactivo = numerosCorrelactivos();
		Accion currentAccion = accionDao.getAccionById(i);
		Bitacora bita = new Bitacora(LocalDateTime.now(), value);
		currentAccion.getListB().add(bita);
		accionDao.createBitacAccion(currentAccion);
		Person currentPerson = personDao.getPersonById(email.getId_p());
		String attchment = "C:\\Users\\Anghelo\\Downloads\\memorandum.pdf";
		Memo memo = new Memo();
		memo.setAsunto(email.getSubject());
		memo.setRazon(email.getMemo());
		memo.setFecha_RegistroM(LocalDate.now());
		memo.setN_memo(numCorrelactivo);
		memo.setEstado(1);
		memo.setUser_id(value);
		memo.getListB().add(bita);
		currentPerson.getMemos().add(memo);
		personDao.updatePerson(currentPerson);

		sendEmail.sendEmail(currentPerson.getCorreo(), email.getEmailS(), email.getEmailC(), attchment);
		File file = new File("C:\\Users\\Anghelo\\Downloads\\memorandum.pdf");
		file.delete();

	}

	@RequestMapping(value = "api/memo/search", method = RequestMethod.POST)
	private List<Memo> searchM(@RequestBody Person person) {

		int i = 6;
		Accion currentAccion = accionDao.getAccionById(i);
		Bitacora bita = new Bitacora(LocalDateTime.now(), value);
		currentAccion.getListB().add(bita);
		accionDao.createBitacAccion(currentAccion);
		Person currentPerson = personDao.getPerson(person);
		currentPerson.getListB().add(bita);
		personDao.updatePerson(currentPerson);
		return currentPerson.getMemos().stream().filter(memo -> memo.getEstado() == 1)
				.sorted(Comparator.comparingInt(Memo::getN_memo)).collect(Collectors.toList());

	}

	@RequestMapping(value = "api/memo/update", method = RequestMethod.PUT)
	private void updateM(@RequestBody Email email) throws MessagingException {
		int i = 7;
		String attchment = "C:\\Users\\Anghelo\\Downloads\\memorandum.pdf";
		Accion currentAccion = accionDao.getAccionById(i);
		Bitacora bita = new Bitacora(LocalDateTime.now(), value);
		currentAccion.getListB().add(bita);
		accionDao.createBitacAccion(currentAccion);
		Person currentPerson = personDao.getPersonById(email.getId_p());
		List<Memo> memos = currentPerson.getMemos().stream().filter(memo -> memo.getId() == email.getId_m()).collect(Collectors.toList());
		Memo memo = memos.get(0);
		memo.setId(email.getId_m());
		memo.setAsunto(email.getSubject());
		memo.setRazon(email.getMemo());
		memo.getListB().add(bita);
		personDao.updatePerson(currentPerson);
		sendEmail.sendEmail(currentPerson.getCorreo(), email.getEmailS(), email.getEmailC(), attchment);
		File file = new File("C:\\Users\\Anghelo\\Downloads\\memorandum.pdf");
		file.delete();
	}

	@RequestMapping(value = "api/memo/delete", method = RequestMethod.DELETE)
	private void deleteM(@RequestBody Email email) {
		int i = 8;
		Accion currentAccion = accionDao.getAccionById(i);
		Bitacora bita = new Bitacora(LocalDateTime.now(), value);
		currentAccion.getListB().add(bita);
		accionDao.createBitacAccion(currentAccion);
		Person currentPerson = personDao.getPersonById(email.getId_p());
		List<Memo> memos = currentPerson.getMemos().stream().filter(memo -> memo.getId() == email.getId_m()).collect(Collectors.toList());
		Memo memo = memos.get(0);
		memo.setEstado(0);
		memo.getListB().add(bita);
		personDao.updatePerson(currentPerson);
	}

	@RequestMapping(value = "api/pdf/create", method = RequestMethod.POST)
	public void downloadReceipt(@RequestBody PdfMemo pdfM, HttpServletResponse response)
			throws IOException, MessagingException {
		Map<String, Object> data = createTestData(pdfM);
		// Person person = personDao.getPersonById(pdfM.getId_p());
		
		ByteArrayInputStream exportedData = exportPdfService.exportReceiptPdf("template", data);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=memorandum.pdf");
		
		IOUtils.copy(exportedData, response.getOutputStream());

	}
	
	
	@RequestMapping(value = "api/pdf/report/person", method = RequestMethod.POST)
	public void downloadReportPerson(@RequestBody List<PersonWrapper> reportPersonWrapper, HttpServletResponse response)
			throws IOException, MessagingException {
		Map<String, Object> data = new HashMap<>();
		data.put("reportList", reportPersonWrapper);
		ByteArrayInputStream exportedData = exportPdfService.exportReceiptPdf("reportPerson", data);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=reportP.pdf");
		IOUtils.copy(exportedData, response.getOutputStream());

	}
	
	
	@RequestMapping(value = "api/pdf/report/memo", method = RequestMethod.POST)
	public void downloadReportMemo(@RequestBody List<MemoWrapper> reportMemoWrapper, HttpServletResponse response)
			throws IOException, MessagingException {
		Map<String, Object> data = new HashMap<>();
		data.put("reportList", reportMemoWrapper);
		ByteArrayInputStream exportedData = exportPdfService.exportReceiptPdf("reportMemo", data);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=reportM.pdf");
		IOUtils.copy(exportedData, response.getOutputStream());

	}

	@RequestMapping(value = "api/pdf/delete", method = RequestMethod.POST)
	public void deletePdfMemo() {
		File file = new File("C:\\Users\\Anghelo\\Downloads\\memorandum.pdf");
		file.delete();
	}

	@RequestMapping(value = "api/statistics/query/person", method = RequestMethod.POST)
	public List<PersonWrapper> getDataQueryPerson(@RequestBody QueryData queryData) {
		System.out.print(getQueryConditional(queryData));
		return jdbc.query(getQueryConditional(queryData), new BeanPropertyRowMapper<>(PersonWrapper.class));

	}
	
	@RequestMapping(value = "api/statistics/query/memo", method = RequestMethod.POST)
	public List<MemoWrapper> getDataQueryMemo(@RequestBody QueryData queryData) {
		System.out.print(getQueryConditional(queryData));
		return jdbc.query(getQueryConditional(queryData), new BeanPropertyRowMapper<>(MemoWrapper.class));

	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "api/chart/data", method = RequestMethod.GET)
	public DataQueryCharts getDataChart() {
		DataQueryCharts  dataChartQuery = new DataQueryCharts() ;
		HashMap<String, List<BitacoraDataCount>> ListDataHashChart = new HashMap<>();
		HashMap<String, List<ChartDataBar>> dataHashChart = new HashMap<>();
		ListDataHashChart.put("Person",jdbc.query(getBitaDataChartPerson(), new BeanPropertyRowMapper<>(BitacoraDataCount.class)));
		ListDataHashChart.put("Memo",jdbc.query(getBitaDataChartMemo(), new BeanPropertyRowMapper<>(BitacoraDataCount.class)));		
		dataHashChart.put("PersonaCreada",jdbc.query(getQueryDataChartPersonCreate(), new BeanPropertyRowMapper<>(ChartDataBar.class)));
		dataHashChart.put("PersonaEliminada",jdbc.query(getQueryDataChartPersonDelete(), new BeanPropertyRowMapper<>(ChartDataBar.class)));
		dataHashChart.put("MemoCreada",jdbc.query(getQueryDataChartMemoCreate(), new BeanPropertyRowMapper<>(ChartDataBar.class)));
		dataHashChart.put("MemoEliminada",jdbc.query(getQueryDataChartMemoDelete(), new BeanPropertyRowMapper<>(ChartDataBar.class)));
		dataChartQuery.setDataBarChart(dataHashChart);
		dataChartQuery.setDataBitacora(ListDataHashChart);
		return dataChartQuery;
	}

	

	
	
	private Map<String, Object> createTestData(PdfMemo pdfM) {
		Map<String, Object> data = new HashMap<>();

		Person person = personDao.getPersonById(pdfM.getId_p());
		int numCorrelactivo = numerosCorrelactivos();
		Memo memo = new Memo();
		memo.setAsunto(pdfM.getSubject());
		memo.setRazon(pdfM.getMemo());
		String dateS = transforDate();
		String NumCorr = transfCorrelactivo(numCorrelactivo);
		data.put("person", person);
		data.put("memo", memo);
		data.put("date", dateS);
		data.put("correlativo", NumCorr);

		return data;
	}

	private int numerosCorrelactivos() {
		int actives = 0;
		int numberMemo = memoDao.getNumberMemos();

		if (numberMemo != 0) {
			actives = numberMemo;
		} else {
			actives += memoDao.getMemosActive().size() + 1;
		}

		return actives;
	}

	private String transforDate() {
		LocalDate localDate = LocalDate.now();
		Locale spanishLocale = new Locale("es", "ES");
		String dateInSpanish = localDate.format(DateTimeFormatter.ofPattern("dd' de 'MMMM' del 'yyyy", spanishLocale));
		return dateInSpanish;
	}

	private String transfCorrelactivo(int numero) {
		Formatter obj = new Formatter();
		String numeroCeros = String.valueOf(obj.format("%05d", numero));
		return numeroCeros;

	}
	
	private String getBitaDataChartPerson() {
		String query = "select count(person.estado) as cantidad ,person.estado as estado\r\n"
				+ "from person\r\n"
				+ "where person.estado = '1' or person.estado = '0'\r\n"
				+ "group by person.estado ,person.estado" ;

		return query;

	}
	
	private String getBitaDataChartMemo() {
		String query = "select count(memos.estado_memo) as cantidad ,memos.estado_memo as estado\r\n"
				+ "from memos\r\n"
				+ "where memos.estado_memo = '1' or memos.estado_memo = '0'\r\n"
				+ "group by memos.estado_memo " ;

		return query;

	}
	
	private String getQueryDataChartPersonCreate() {
		String query = "select count(person.fecha_registro) as cantidad,to_char(person.fecha_registro, 'MM')as Mes,person.estado as estado  ,to_char(person.fecha_registro, 'YYYY') as Año\r\n"
				+ "from person\r\n"
				+ "where person.estado = 1\r\n"
				+ "group by to_char(person.fecha_registro, 'MM'),to_char(person.fecha_registro, 'YYYY'),person.estado\r\n"
				+ "order by to_char(person.fecha_registro, 'MM')" ;

		return query;

	}
	
	private String getQueryDataChartPersonDelete() {
		String query = "select count(person.fecha_registro) as cantidad,to_char(bitacora.fecha_hora, 'MM')as Mes,person.estado as estado  ,to_char(bitacora.fecha_hora, 'YYYY') as Año  \r\n"
				+ "from person\r\n"
				+ "inner join bitacora\r\n"
				+ "on person.id = bitacora.person_bita_id\r\n"
				+ "where person.estado = 0 and bitacora.accion_id = '4'\r\n"
				+ "group by to_char(bitacora.fecha_hora, 'MM'),to_char(bitacora.fecha_hora, 'YYYY'),person.estado\r\n"
				+ "order by to_char(bitacora.fecha_hora, 'MM')" ;

		return query;

	}
	
	private String getQueryDataChartMemoCreate() {
		String query = "select count(memos.fecha_registro) as cantidad,to_char(memos.fecha_registro, 'MM')as Mes,memos.estado_memo as estado  ,to_char(memos.fecha_registro, 'YYYY') as Año  \r\n"
				+ "from memos\r\n"
				+ "where memos.estado_memo = 1 \r\n"
				+ "group by to_char(memos.fecha_registro, 'MM'),to_char(memos.fecha_registro, 'YYYY'),memos.estado_memo\r\n"
				+ "order by to_char(memos.fecha_registro, 'MM')" ;

		return query;

	}
	
	private String getQueryDataChartMemoDelete() {
		String query = "select count(memos.fecha_registro) as cantidad,to_char(bitacora.fecha_hora, 'MM')as Mes,memos.estado_memo as estado  ,to_char(bitacora.fecha_hora, 'YYYY') as Año  \r\n"
				+ "from memos\r\n"
				+ "inner join bitacora\r\n"
				+ "on memos.id = bitacora.memo_bita_id\r\n"
				+ "where memos.estado_memo = 0 and bitacora.accion_id = '8'\r\n"
				+ "group by to_char(bitacora.fecha_hora, 'MM'),to_char(bitacora.fecha_hora, 'YYYY'),memos.estado_memo\r\n"
				+ "order by to_char(bitacora.fecha_hora, 'MM')" ;

		return query;

	}
	

	

	private String getQueryPerson(String condicionales) {
		String query = "select distinct person.nombre, person.apellido_m,person.apellido_p,person.dni,person.correo,\r\n"
				+ "person.estado,DATE(bitacora.fecha_hora) as fecha_accion \r\n"
				+ "from Person person\r\n" + "inner join bitacora\r\n" + "on person.id = bitacora.person_bita_id\r\n"
				+ "inner join accion\r\n" + "on bitacora.accion_id = accion.id\r\n" + "where " + condicionales;

		return query;

	}

	private String getQueryMemo( String condicionales) {
		String query = "select distinct memos.n_memo,person.correo,person.dni,\r\n"
				+ "memos.asunto,memos.estado_memo as estado,DATE(bitacora.fecha_hora) as fecha_accion\r\n"
				+ "from person\r\n"
				+ "inner join memos\r\n"
				+ "on memos.id_person = person.id\r\n"
				+ "inner join bitacora\r\n"
				+ "on memos.id = bitacora.memo_bita_id\r\n"
				+ "inner join accion\r\n"
				+ "on bitacora.accion_id = accion.id\r\n"
				+ "where " + condicionales;

		return query;

	}
	
	private String getQueryMemoSearch( String condicionales) {
		String query = "select distinct memos.n_memo,person.correo,person.dni,\r\n"
				+ "memos.asunto,memos.estado_memo as estado,DATE(bitacora.fecha_hora) as fecha_accion\r\n"
				+ "from bitacora\r\n"
				+ "inner join person\r\n"
				+ "on person.id = bitacora.person_bita_id\r\n"
				+ "inner join memos \r\n"
				+ "on person.id = memos.id_person\r\n"
				+ "where "+ condicionales ;

		return query;

	}
	

	private String getQueryDate(QueryData queryData) {
		
		String dateString = "AND ";
		System.out.print(queryData.getAction() != "1");
		if (queryData.getAction().equals("1")) {
			if (queryData.getTypeDate().equals("1")) {
				dateString += "(person.fecha_Registro = '" + queryData.getDateD() + "')";
			} else if (queryData.getTypeDate().equals("2")) {
				dateString += "(person.fecha_Registro BETWEEN '" + queryData.getDateR().get(0) + "' AND '"
						+ queryData.getDateR().get(1) + "')";
			}
		} else {
			if (queryData.getTypeDate().equals("1")) {
				dateString += "(DATE(bitacora.fecha_hora) = '" + queryData.getDateD() + "')";
			} else if (queryData.getTypeDate().equals("2")) {
				dateString += "(DATE(bitacora.fecha_hora) BETWEEN '" + queryData.getDateR().get(0) + "' AND '"
						+ queryData.getDateR().get(1) + "')";
			}
		}

		return dateString;
	}

	private String getQueryState(QueryData queryData) {
		String stateString = "AND ";
		if (queryData.getType().equals("person")) {
			
			stateString += "person.estado = '" + queryData.getCurrentState() + "'";
			
		} else {
			String condState = String.valueOf(Integer.parseInt(queryData.getAction()) + Integer.parseInt("4"));
			if (condState.equals("6")) {
				stateString += "memos.estado_memo = '" + queryData.getCurrentState() + "'";
			} else {

				stateString += "memos.estado_memo= '" + queryData.getCurrentState() + "'";
			}
		}


		return stateString;
	}
	
	private String getQueryBitacora(QueryData queryData) {
		String condAccion = "";
		String bitacora = "";
		if (queryData.getType().equals("person")) {
			bitacora = "bitacora.accion_id = '" + queryData.getAction() + "'";			
		} else {
			condAccion = String.valueOf(Integer.parseInt(queryData.getAction()) + Integer.parseInt("4"));
			bitacora = "bitacora.accion_id = '" + condAccion + "'";
		}
		return bitacora;
	}

	private String getQueryConditional(QueryData queryData) {
		String newQuery;
		String conditional = getQueryBitacora(queryData);
		conditional += getQueryDate(queryData);
		if (!queryData.getAction().equals("4") && !queryData.getAction().equals("8")) {
			if (!queryData.getCurrentState().equals("2")) {
				conditional += getQueryState(queryData);
			}
		}else if(queryData.getType().equals("memo")) {
			conditional += " AND memos.estado_memo= '0'";
		}
		
		if (queryData.getType().equals("person")) {
			
			 newQuery = getQueryPerson(conditional);
		}else {
			String condState = String.valueOf(Integer.parseInt(queryData.getAction()) + Integer.parseInt("4"));
			if (condState.equals("6")) {
				newQuery = getQueryMemoSearch(conditional);
			} else {

				newQuery = getQueryMemo(conditional);
			}
		}

		return newQuery;
	}

}
