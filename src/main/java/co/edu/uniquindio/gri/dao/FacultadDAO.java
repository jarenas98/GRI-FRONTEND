package co.edu.uniquindio.gri.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniquindio.gri.model.Facultad;
import co.edu.uniquindio.gri.repository.FacultadRepository;

/**
 * Clase FacultadDAO.
 */
@Service
public class FacultadDAO {

	/** Repository para facultades. */
	@Autowired
	FacultadRepository facultadRepository;
	
	/**
	 * Obtiene todas las facultades.
	 *
	 * @return lista con todas las facultades.
	 */
	public List<Facultad> getAllFacultades(){
		return facultadRepository.findAll();
	}
	
	/**
	 * Obtiene una facultad especificada por id
	 *
	 * @param idFacultad el id de la facultad
	 * @return la facultad especificada por el idFacultad
	 */
	public Facultad getFacultadById(Long facultadId){
		return facultadRepository.findOne(facultadId);
	}
	
	/**
	 * Obtiene las estadísticas del sistema.
	 *
	 * @return lista con las estadísticas del sistema. 
	 */
	public List<BigInteger> getStats(){
		return facultadRepository.getStats();
	}
	
	/**
	 * Obtiene el resumen general de la facultad en números
	 * la lista en cada posición obtiene lo siguiente:
	 * 0 - Cantidad programas académicos
	 * 1 - Cantidad especializaciones
	 * 2 - Cantidad maestrías
	 * 3 - Cantidad doctorados
	 * 4 . Cantidad grupos de investigación
	 * 5 . Cantidad lineas de investigación
	 * 6 - Cantidad de investigadores
	 * @return lista con totales anteriores.
	 */
	public List<BigInteger> getResumenGeneral(Long facultadId){
		return facultadRepository.getResumenGeneral(facultadId);
	}
	
}
