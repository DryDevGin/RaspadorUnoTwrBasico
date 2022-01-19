package proyectoWeb;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {
	
	//agrega el url de la pagina que vas a raspar
public static final String url = "";
	
	
	public static void main (String args[]) {
		
		// Compruebo si me da un 200 al hacer la petición
		if (getStatusConnectionCode(url) == 200) {
			
			// Obtengo el HTML de la web en un objeto Document
			Document document = getHtmlDocument(url);
			
			//agregas el rango de clases donde vas a raspar en el dom
			// Busco todas las historias de meneame que estan dentro de: 
			Elements entradas = document.select("").not("");
			System.out.println("Número de entradas : "+entradas.size()+"\n");
			
			// Paseas cada una de las clases que vas a raspar
			for (Element elem : entradas) {
				String likes = elem.getElementsByClass("").toString();
				String visto = elem.getElementsByClass("").toString();
				String nuevosSeguidores = elem.getElementsByClass("").text();
				
				System.out.println(likes+"\n"+visto+"\n"+nuevosSeguidores+"\n\n");
				
				// Con el metodo "text()" se obtiene el contenido que hay dentro de las etiquetas HTML
				// Con el metodo "toString()" se obtiene todo el HTML con etiquetas incluidas
			}
				
		}else
			System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(url));
		
	}
	
	
	/**
	 * Con esta metodo se comprueba el Status code de la respuesta que se recibe al hacer la peticion
	 * EJM:
	 * 		200 OK					300 Multiple Choices
	 * 		301 Moved Permanently	305 Use Proxy
	 * 		400 Bad Request			403 Forbidden
	 * 		404 Not Found			500 Internal Server Error
	 * 		502 Bad Gateway			503 Service Unavailable
	 */
	public static int getStatusConnectionCode(String url) {
		
		Response response = null;
		
		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			System.out.println("Excepcion al obtener el Status Code: " + ex.getMessage());
		}
		return response.statusCode();
	}
	
	
	/**
	 * Con este metodo devuelvo un objeto de la clase Document con el contenido del
	 * HTML de la web que me permitira parsearlo con los métodos de la librelia JSoup
	 */
	public static Document getHtmlDocument(String url) {

		Document doc = null;

		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepcion al obtener el HTML de la pagina" + ex.getMessage());
		}

		return doc;

	}
	
	
}
