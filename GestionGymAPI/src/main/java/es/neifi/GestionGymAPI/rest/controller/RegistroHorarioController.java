package es.neifi.GestionGymAPI.rest.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.JsonMappingException;

import es.neifi.GestionGymAPI.rest.model.Cliente;
import es.neifi.GestionGymAPI.rest.DTO.InfoClienteDTO;
import es.neifi.GestionGymAPI.rest.DTO.CrearClienteDTO;
import es.neifi.GestionGymAPI.rest.DTO.converter.ClientDetailsDTOConverter;
import es.neifi.GestionGymAPI.rest.exceptions.ApiError;
import es.neifi.GestionGymAPI.rest.exceptions.ClienteNotFoundException;
import es.neifi.GestionGymAPI.rest.model.RegistroHorario;
import es.neifi.GestionGymAPI.rest.model.RegistroHorarioRepository;
import es.neifi.GestionGymAPI.services.ServerTime;
import es.neifi.GestionGymAPI.rest.model.ClienteRepository;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({ "/api" })
@RequiredArgsConstructor
public class RegistroHorarioController {

	private final RegistroHorarioRepository registroHorarioRepo;
	private final ClienteRepository clienteRepository;
	private final ClientDetailsDTOConverter clienteDetailsDTOConverter;
	private String dtEntrada = "";

	@GetMapping("/testHora")
	public void test() {
		String dt = DateTime.now().toString("HH:mm:ss");
		System.out.println(hourDiff(dt, "13:03:10"));
	}

	@GetMapping("/horario")
	public ResponseEntity<?> obtenerTodos() {
		List<RegistroHorario> registros = registroHorarioRepo.findAll();

		if (registros.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
//			List<ClientInfoDTO> dtoList = registros.stream().map(clienteDetailsDTOConverter::convertToDTO)
//					.collect(Collectors.toList());
			return ResponseEntity.ok(registros);
		}
	}
	

	/**
	 * 
	 * @param nuevo        Entidad registroHorario
	 * @param clientId     id del cliente al cual pertenece el registro
	 * @param tipoRegistro tipo de registro, entrada o salida
	 * @return
	 */
//	@JsonFormat(shape = Shape.NUMBER_INT, pattern = "dd/MM/yyyy hh:mm:ss")
//	LocalTime time = LocalTime.now();

	@PostMapping(path = "/horario", params = { "clientId", "tipoRegistro" })
	public ResponseEntity<?> nuevoRegistro(@RequestParam int clientId, @RequestParam String tipoRegistro) {

		if (tipoRegistro.contentEquals("e")) {
			dtEntrada = DateTime.now().toString("HH:mm:ss");

			registroHorarioRepo.insertEntry(dtEntrada, clientId, DateTime.now().getHourOfDay(),
					DateTime.now().getMonthOfYear(), DateTime.now().getYear());
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} else if (tipoRegistro.contentEquals("s")) {
			String dt = DateTime.now().toString("HH:mm:ss");
			System.out.println(dt);
			registroHorarioRepo.insertExit(dt, clientId, hourDiff(dtEntrada, dt), DateTime.now().getHourOfDay(),
					DateTime.now().getMonthOfYear(), DateTime.now().getYear());
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	public String hourDiff(String entry, String exit) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			Date start = format.parse(entry);
			Date end = format.parse(exit);

			DateTime startTime = new DateTime(start);
			DateTime exitTime = new DateTime(end);
			int hours = Hours.hoursBetween(startTime, exitTime).getHours() % 24;
			int minutes = Minutes.minutesBetween(startTime, exitTime).getMinutes() % 60;
			int seconds = Seconds.secondsBetween(startTime, exitTime).getSeconds() % 60;

			String difference = String.valueOf(hours + ":" + minutes + ":" + seconds);

			return difference;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "::";
	}

}
