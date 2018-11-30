package it.sdeluca.expansion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.sdeluca.expansion.dao.PersonDaoImpl;

public class LocalExpand {

	private static ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

	private static Logger log = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	private LocalExpand() {

	}

	public static ThreadLocal<List<String>> getThreadLocal() {
		return threadLocal;
	}

	public static void setExpands(List<String> expands) {
		threadLocal.set(expands);
		log.debug("Expansion are: ".concat(getExpands()!=null ? getExpands().toString() : ""));
	}

	public static List<String> getExpands() {
		return threadLocal.get();
	}

	public static void clean() {
		threadLocal.remove();
	}
}
