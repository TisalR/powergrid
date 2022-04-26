package feedback;

import feedback.Feedbackmodel;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Items")
public class Feedbackservice
{
	Feedbackmodel itemObj = new Feedbackmodel();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		 return itemObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("issueName") String issName,
							@FormParam("issueStatus") String status,
							@FormParam("Date") String date,
							@FormParam("Description") String desc,
							@FormParam("Location") String location)
	{
	 String output = itemObj.insertItem(issName, status, date, desc, location);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String Issue_id = itemObject.get("Issue_id").getAsString();
	 String Issue_name = itemObject.get("Issue_name").getAsString();
	 String Issue_Status = itemObject.get("Issue_Status").getAsString();
	 String Date = itemObject.get("Date").getAsString();
	 String Description = itemObject.get("Description").getAsString();
	 String Location = itemObject.get("Location").getAsString();
	 String output = itemObj.updateItem(Issue_id, Issue_name, Issue_Status, Date, Description, Location);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String Issue_id = doc.select("Issue_id").text();
	 String output = itemObj.deleteItem(Issue_id);
	return output;
	}
	
}

