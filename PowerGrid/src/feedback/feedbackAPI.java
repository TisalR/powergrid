package feedback;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


@WebServlet("/ItemsAPI")
public class feedbackAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Feedbackmodel itemObj = new Feedbackmodel();
    
    public feedbackAPI() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = itemObj.insertItem(request.getParameter("Issue_name"), 
				 request.getParameter("Issue_Status"), 
				request.getParameter("Date"), 
				request.getParameter("Description")); 
		
		response.getWriter().write(output); 
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		 try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 
			 scanner.close(); 
			 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
			 { 
				 String[] p = param.split("="); 
				 map.put(p[0], p[1]); 
			 } 
		 } 
		catch (Exception e) 
		{ 
    	} 
		return map; 
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = itemObj.updateItem(paras.get("hidItemIDSave").toString(), 
										    paras.get("Issue_name").toString(), 
											paras.get("Issue_Status").toString(), 
											paras.get("Date").toString(), 
											paras.get("Description").toString());
		
		response.getWriter().write(output); 

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		
		String output = itemObj.deleteItem(paras.get("itemID").toString()); 
		
		response.getWriter().write(output); 
	}

}
