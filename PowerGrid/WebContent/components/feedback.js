$(document).ready(function() 
{ 
	if ($("#alertSuccess").text().trim() == "") 
	 { 
	 	$("#alertSuccess").hide(); 
	 } 
	 $("#alertError").hide(); 
});

//Save
$(document).on("click", "#btnSave", function(event) 
{ 
 	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide();
	 
	 // Form validation-------------------
	var status = validateItemForm(); 
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 }

	// If valid------------------------
 	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "feedbackAPI", 
		 type : type, 
		 data : $("#formItem").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onItemSaveComplete(response.responseText, status); 
	 	 } 
	 }); 

});

$(document).on("click", ".btnUpdate", function(event) 
{ 
 	 $("#hidItemIDSave").val($(this).data("itemid")); 
	 $("#Issue_name").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#Issue_Status").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#Date").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#Description").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#Location").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
	 $.ajax( 
	 { 
		 url : "feedbackAPI", 
		 type : "DELETE", 
		 data : "Issue_ID=" + $(this).data("itemid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onItemDeleteComplete(response.responseText, status); 
		 } 
	 }); 
});


// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
	// NAME
	if ($("#Issue_name").val().trim() == "") 
	 { 
	 	return "Insert Issue Name."; 
	 } 
	// STATUS-------------------------------
	if ($("#Issue_Status").val().trim() == "") 
	 { 
	 	return "Insert Issue status."; 
	 } 
	// DATE------------------------
	if ($("#Date").val().trim() == "") 
	 { 
	 	return "Insert Issue Date."; 
	 } 
	return true; 
}

function onItemSaveComplete(response, status) 
{ 
	var resultSet = JSON.parse(response); 
	
	if (resultSet.status.trim() == "success") 
	{ 
	 	$("#alertSuccess").text("Successfully saved."); 
	 	$("#alertSuccess").show(); 
		$("#divItemsGrid").html(resultSet.data); 
	} else if (resultSet.status.trim() == "error") 
	{ 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
	}
	else if (status == "error") 
	{ 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	} else
	{ 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	}
	$("#hidItemIDSave").val(""); 
	$("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status) 
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	 } else if (status == "error") 
	 { 
		 $("#alertError").text("Error while deleting."); 
		 $("#alertError").show(); 
	 } else
	 { 
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 } 
}