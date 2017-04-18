package iptv.cbor.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.jaxrs.cbor.CBORMediaTypes;


/**
 * 
 * @author bhawani.singh
 *
 */
@Path("cborclient")
public class CborClientREST {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(CborClientREST.class.getName());

	
	@GET
	@Path("hi")
	@Consumes({CBORMediaTypes.APPLICATION_JACKSON_CBOR})
	public void hi() throws IOException {
		LOGGER.info("CborClientREST.hi()");
		

			//Client client = ClientBuilder.newClient();
			//User response = client.target("http://127.0.0.1:8080/cbor/hi").request(CBORMediaTypes.APPLICATION_JACKSON_CBOR).get(User.class);
			//System.out.println(response);
			final ObjectMapper mapper = new ObjectMapper(new CBORFactory());
			try {
	            InputStream in = new URL("http://127.0.0.1:8080/cbor/hi").openStream();
	            User p = mapper.readValue(in, User.class);
	            System.out.println("CborClientREST.loadCache() user.getName() =  " + p.getName());
	            System.out.println("CborClientREST.loadCache() user.getAge() =  " + p.getAge());
	    		byte[] cborData = mapper.writeValueAsBytes(p);
	    		System.out.println("CborClientREST.loadCache() user.getName() =  " + cborData.toString());
	    		
	            in.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
	}
	
	
	
	
	
	
	@GET
	@Path("hi1")
	@Consumes({CBORMediaTypes.APPLICATION_JACKSON_CBOR})
	public void hi1() throws IOException {
		LOGGER.info("CborClientREST.hi()");
		
		//mapper.registerModule(new Jaxrs2TypesModule());
		User user = new User();
		
		System.out.println("NpvrREST.loadCache()");
		String url = "http://127.0.0.1:8080";
		String path = "/cbor/hi";		
		WebTarget channelService = ClientBuilder.newClient()
				.target(UriBuilder.fromUri(URI.create(url)).path(path).build());
		LOGGER.info("Uri = " + channelService.getUri().toString());
		//String val = channelService.request(CBORMediaTypes.APPLICATION_JACKSON_CBOR).accept("UTF-8").get(String.class);
		//String val = channelService.request(CBORMediaTypes.APPLICATION_JACKSON_CBOR).accept("UTF-8").get(String.class);
		User value = channelService.request().accept(CBORMediaTypes.APPLICATION_JACKSON_CBOR).get(User.class);	
		//Response response = channelService.request().get();
		//Object value = response.re
		//System.out.println(value);
		/*channelService.request(CBORMediaTypes.APPLICATION_JACKSON_CBOR)
		
		channelService.request().*/
		
		/*ByteArrayOutputStream out = new ByteArrayOutputStream();
        CBORGenerator gen = cborGenerator(out);
		*/
		
		
		/*BufferedReader br = new BufferedReader(
			    new InputStreamReader( 
			        (response.getEntity().getContent())
			    )
			);*/
		
		/*System.out.println("CborClientREST.loadCache() user.getName() =  " + value.getName());
		// and then read/write data as usual
		CBORFactory f = new CBORFactory();
		ObjectMapper mapper = new ObjectMapper(f);
		System.out.println("CborClientREST.hi() 11111111111 value = " + value);
		byte[] cborData = mapper.writeValueAsBytes(value);
		User otherValue = mapper.readValue(cborData, User.class);*/
		
		/*CBORFactory f = new CBORFactory();
		ObjectMapper mapper = new ObjectMapper(f);
		
		byte[] cborData;

		try {
			//cborData = mapper.writeValueAsBytes(user);
			
			///System.out.println("CborREST.hi()  cborData = " + cborData);
			
			System.out.println("CborClientREST.hi() val.getBytes() 222222222  =" + val.getBytes("UTF-8"));
			
			user = mapper.readValue(val.getBytes("UTF-8"), User.class);
			
			System.out.println("CborClientREST.hi()  user1.name" + user.name);
			
			
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		//System.out.println("CborClientREST.loadCache() user.getName() =  " + otherValue.getName());
		//return otherValue;
	}
	
	
	private static void loadCache(){
		System.out.println("NpvrREST.loadCache()");
		String url = "http://127.0.0.1:8080";
		String path = "/cbor/hi";		
		WebTarget channelService = ClientBuilder.newClient()
				.target(UriBuilder.fromUri(URI.create(url)).path(path).build());
		LOGGER.info("Uri = " + channelService.getUri().toString());
		User user = channelService.request().accept(CBORMediaTypes.APPLICATION_JACKSON_CBOR).get(User.class);	
			
		System.out.println("CborClientREST.loadCache() user.getName() =  " + user.getName());
				
	}

}