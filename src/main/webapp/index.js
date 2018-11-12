$(document).ready(function() {
	  
    $('#add-phone').usPhoneFormat({
        format: '(xxx) xxx-xxxx',
    });

	var dataTable = $('#dataTable');
	fetchAll();
	$('#add-button-submit').on('click', Save);
	$('#edit-button-submit').on('click', Edit);
	$('#btnCancel').on('click', function() {
		dialog.close();
	});
});

$(document).ajaxStart(function(){
    $('#loading').show();
 }).ajaxStop(function(){
    $('#loading').hide();
 });

function Save() {
	
    $('#edit-phone').usPhoneFormat({
        format: '(xxx) xxx-xxxx',
    });
    
	var user = {
		name : $('#add-name').val(),
		surname : $('#add-surname').val(),
		phoneNumber : $('#add-phone').val()
	};
	if (user.name != "" && user.surname != "") {

		var userJSON = JSON.stringify(user);
		$.ajax({
			url : '/SpringWebMVC/save',
			context : document.body,
			data : userJSON,
			contentType : 'application/json',
			method : 'POST'
		}).done(function() {
			dataTable.reload();
		}).fail(function(error) {
			alert('Failed to save.' + error);
			dialog.close();
		});
	}
}

function fetchAll() {

	var actionsHtml = "<a href='#editUserModal' class='btnEdit' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Edit'>&#xE254;</i></a>"
			+ "<a href='#deleteUserModal' class='btnDelete' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i></a>";

	$.ajax({
		url : '/SpringWebMVC/getAllUsers',
		context : document.body,
		method : 'GET'
	}).done(
			function(data) {
				for (var i = 0; i < data.length; i++) {
					var item = data[i];
					$('#dataTable').append(
							"<tr><td style='display: none;'>" + item.id
									+ "</td><td>" + "</td><td>" + item.name
									+ "</td><td>" + item.surname + "</td><td>"
									+ item.phoneNumber + "</td><td>"
									+ actionsHtml + "</td></tr>");
					$(".btnEdit").bind("click", RowEdit);
					$(".btnDelete").bind("click", RowDelete);
				}
			}).fail(function(error) {
		alert('Failed to fetch.');
		dialog.close();
	});
}

function RowEdit() {

	var par = $(this).closest('tr').get(0).children; // tr elements
	var tdId = par[0].innerHTML;
	var tdName = par[2].innerHTML;
	var tdSurname = par[3].innerHTML;
	var tdPhone = par[4].innerHTML;

	$('#edit-id').val(tdId);
	$('#edit-name').val(tdName);
	$('#edit-surname').val(tdSurname);
	$('#edit-phone').val(tdPhone);
	
    
    $('#edit-phone').usPhoneFormat({
        format: '(xxx) xxx-xxxx',
    });
}

function RowDelete() {

	var par = $(this).closest('tr').get(0).children; // tr elements
	var tdId = par[0].innerHTML;

	$('#edit-button-submit').bind("click", Delete(tdId));

}

function Edit() {

	var user = {
		id : $('#edit-id').val(),
		name : $('#edit-name').val(),
		surname : $('#edit-surname').val(),
		phoneNumber : $('#edit-phone').val()
	};
	if (user.name != "" && user.surname != "") {
		var userJSON = JSON.stringify(user);
		$.ajax({
			url : '/SpringWebMVC/save',
			context : document.body,
			data : userJSON,
			contentType : 'application/json',
			method : 'POST'
		}).done(function() {
			dataTable.reload();
		}).fail(function(error) {
			alert('Failed to save.');
			dialog.close();
		});
	}
}

function Delete(id) {

	$.ajax({
		url : '/SpringWebMVC/delete',
		context : document.body,
		data : id,
		contentType : 'application/json',
		method : 'POST'
	}).done(function() {
		dataTable.reload();
	}).fail(function(error) {
		alert('Failed to delete');
		dialog.close();
	});

}
