package co.edu.uniquindio.gri.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniquindio.gri.dao.PertenenciaDAO;
import co.edu.uniquindio.gri.model.Investigador;
import co.edu.uniquindio.gri.model.Pertenencia;

@Service
public class Util {

	@Autowired
	PertenenciaDAO pertenenciaDAO;

	public static final String PERTENENCIA_INDEFINIDO = "INDEFINIDO";
	public static final String PERTENENCIA_DOCENTE_PLANTA = "DOCENTE PLANTA";
	public static final String PERTENENCIA_DOCENTE_CATEDRATICO = "DOCENTE CATEDRÁTICO";
	public static final String PERTENENCIA_DOCENTE_OCASIONAL = "DOCENTE OCASIONAL";
	public static final String PERTENENCIA_ADMINISTRATIVO = "ADMINISTRATIVO";
	public static final String PERTENENCIA_EXTERNO = "INVESTIGADOR EXTERNO";
	public static final String PERTENENCIA_ESTUDIANTE = "ESTUDIANTE INVESTIGADOR";

	/**
	 * constructor de la clase Util
	 */
	public Util() {

	}

	/**
	 * Metodo que permite convertir las cadena a estilo camel, con cada primera leta
	 * en mayuscula
	 * 
	 * @param texto a convertir
	 * @return texto en camel
	 */
	public String convertToTitleCaseIteratingChars(String text) {
		if (text == null || text.isEmpty()) {
			return text;
		}

		StringBuilder converted = new StringBuilder();

		boolean convertNext = true;
		for (char ch : text.toCharArray()) {
			if (Character.isSpaceChar(ch)) {
				convertNext = true;
			} else if (convertNext) {
				ch = Character.toTitleCase(ch);
				convertNext = false;
			} else {
				ch = Character.toLowerCase(ch);
			}
			converted.append(ch);
		}

		return converted.toString();
	}

	/**
	 * permite agregarle la pertenencia a los investigadors
	 * 
	 * @param investigadores
	 * @return
	 */
	public List<Investigador> agregarPertenenciaInves(List<Investigador> investigadores) {

		for (Investigador investigador : investigadores) {
			Pertenencia pertenecia_investigador = pertenenciaDAO.getPertenenciaByIdInves(investigador.getId());

			if (pertenecia_investigador != null) {

				if (pertenecia_investigador.getPertenencia().contains("CATED")) {

					investigador.setPertenencia("DOCENTE CATEDRÁTICO");

				} else {
					investigador.setPertenencia(pertenecia_investigador.getPertenencia());
				}
			} else {

				investigador.setPertenencia("INDEFINIDO");

			}

		}

		return investigadores;

	}

	/**
	 * Metodo que permite extraer de una lista los investigadores dependiendo de la
	 * pertenencia que se necesite
	 * 
	 * @param investigadores lista de investigadores
	 * @param pertenencia    tipo de pertenencia que se requiere
	 * @return una lista con los investigadores de dicha pertenencia
	 */
	public List<Investigador> seleccionarInvestigadoresPertenencia(List<Investigador> investigadores,
			String pertenencia) {

		List<Investigador> investigadores_resultantes = new ArrayList<Investigador>();

		for (Investigador investigador : investigadores) {

			if (investigador.getPertenencia().equals(pertenencia))
				investigadores_resultantes.add(investigador);

		}

		return investigadores_resultantes;

	}

}
