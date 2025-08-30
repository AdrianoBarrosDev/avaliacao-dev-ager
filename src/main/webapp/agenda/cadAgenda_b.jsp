<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title><s:text name="label.titulo.pagina.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">	
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
				
					<!-- Futuros Filtros de Agenda -->
				  	
				</div>				
			</div>

			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.id"/></th>
							<th><s:text name="label.nome"/></th>
							<th><s:text name="label.periodoDisponivel"/></th>
							<th class="text-end mt-5"><s:text name="label.acao"/></th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="agendas" >
							<tr>
								<td>${rowid}</td>
								<td>${nome}</td>
								<td>${periodoDisponivel.descricao}</td>
								<td class="text-end">
								
									<s:url action="editarAgendas" var="editar">
										<s:param name="agendaVo.rowid" value="rowid"></s:param>
									</s:url>

									<a href="${editar}" class="btn btn-warning text-white">
										<s:text name="label.editar"/>
									</a>

									<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmarExclusao" data-rowid="${rowid}">
										<s:text name="label.excluir"/>
									</a>
									
								</td>
							</tr>
						</s:iterator>
					</tbody>
					
					<tfoot class="table-secondary">
						<tr>
							<td colspan="4">
								<s:url action="novoAgendas" var="novo"/>
								
								<a href="${novo}" class="btn btn-success">
									<s:text name="label.novo"/>
								</a>
							</td>
						</tr>
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>
		
		<div  class="modal fade" id="confirmarExclusao" 
			data-bs-backdrop="static" data-bs-keyboard="false"
			tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.titulo"/></h5>
		        
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		      	<span><s:text name="label.modal.corpo"/></span>
		      </div>
		      
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close">
					<s:text name="label.nao"/>
				</a>
				
				<s:url action="excluirAgendas" var="excluir">
					<s:param name="agendaVo.rowid" value="rowid"></s:param>
				</s:url>
	        	
				<s:a href="%{excluir}" id="excluir" class="btn btn-primary" style="width: 75px;">
					<s:text name="label.sim"/>
				</s:a>
				
		      </div>
		    </div>		    
		  </div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script>
			
			var modalConfirmarExclusao = document.getElementById("confirmarExclusao");
			modalConfirmarExclusao.addEventListener("show.bs.modal", function (event) {
				
				var btnAcionado = event.relatedTarget;
				var btnConfirmar = modalConfirmarExclusao.querySelector("#excluir");
				btnConfirmar.setAttribute("href", "excluirAgendas.action?agendaVo.rowid=" + btnAcionado.getAttribute("data-rowid"));
				
			});
		
		</script>
	</body>
</html>