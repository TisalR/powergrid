package bill;

import bill.Billmodel;
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
public class Billservice
{
	Billmodel itemObj = new Billmodel();
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
	public String insertItem(@FormParam("Customer_Name") String Customer_Name,
							@FormParam("Customer_Account") String Customer_Account,
							@FormParam("Date") String Date,
							@FormParam("Units_Used") String Units_Used,
							@FormParam("Amount") String Amount)
	{
	 String output = itemObj.insertItem(Customer_Name, Customer_Account, Date, Units_Used, Amount);
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
	 String Bill_ID = itemObject.get("Bill_ID").getAsString();
	 String Customer_Name = itemObject.get("Customer_Name").getAsString();
	 String Customer_Account = itemObject.get("Customer_Account").getAsString();
	 String Date = itemObject.get("Date").getAsString();
	 String Units_Used = itemObject.get("Units_Used").getAsString();
	 String Amount = itemObject.get("Amount").getAsString();
	 String output = itemObj.updateItem(Bill_ID, Customer_Name, Customer_Account, Date, Units_Used, Amount);
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
	 String Bill_ID = doc.select("Bill_ID").text();
	 String output = itemObj.deleteItem(Bill_ID);
	return output;
	}
	
}

