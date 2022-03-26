package es.neifi.controlfitAPI.rest.model.gimnasio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "gimnasio")
public class GimnasioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	//@OneToOne(mappedBy = "gyms")
	@Column(name = "id_gimnasio")
	private int gymId;
	private String nombre;
	private String ciudad;
	private String direccion;
	private int codigo_postal;
	private String provincia;
	private String pais;
	
	@JsonIgnore
	private List <Integer> clientes;

	public int gymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}


}
