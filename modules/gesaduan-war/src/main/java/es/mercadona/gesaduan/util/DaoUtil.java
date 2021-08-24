package es.mercadona.gesaduan.util;

public final class DaoUtil {
	
	private DaoUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String getParameterResultString(Object key) {
		
		String value = null;
				
		try {
			value = String.valueOf(key);
			
			if(value.equals("null")) {
				value = null;
			}
			
		} catch (Exception e) {
			value = null;
		}
		return value;
	}
	
	public static Double getParameterResultDouble(Object key) {
		Double value = null;
		try {
			value = Double.parseDouble(String.valueOf(key));
		} catch (Exception e) {
			value = null;
		}
		return value;
	}
	
	public static Integer getParameterResultInteger(Object key) {
		Integer value = null;
		try {
			value = Integer.parseInt(String.valueOf(key));
		} catch (Exception e) {
			value = null;
		}
		return value;
	}
	
	public static Long getParameterResultLong(Object key) {
		Long value = null;
		try {
			value = Long.parseLong(String.valueOf(key));
		} catch (Exception e) {
			value = null;
		}
		return value;
	}

}
