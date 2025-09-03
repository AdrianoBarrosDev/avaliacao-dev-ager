<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title><s:text name="label.titulo.pagina"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
		<link rel="stylesheet" href="/avaliacao/css/global.css" />
		<link rel="stylesheet" href="/avaliacao/css/relatorioCompromissos.css" />
	</head>
	<body>	
		<div class="container-fluid p-0">
		
			<div class="bannerRelatorio">
				<div class="d-flex justify-content-between align-items-center w-100 h-100 p-5">
					<h1>Relatório Compromissos</h1>
					<img src="/avaliacao/imagens/LogoSocRelatorio.png" />
				</div>
			</div>
			
			<div class="d-flex justify-content-between align-items-center w-100 p-5">
				
				<div class="d-flex justify-content-between align-items-center datas">
					
					<div class="d-flex justify-content-between align-items-center flex-column flex-lg-row gap-lg-3">
						<p><strong>Data Inicial:</strong></p>
						<p><s:property value="dataInicial"/></p>
					</div>
					
					<div class="d-flex justify-content-between align-items-center flex-column flex-lg-row gap-lg-3">
						<p><strong>Data Final:</strong></p>
						<p><s:property value="dataFinal"/></p></p>
					</div>
					 
				</div>
				<p><strong>Gerado em:</strong> <fmt:formatDate value="<%= new java.util.Date() %>" pattern="dd/MM/yyyy - HH:mm:ss"/></p>
				
			</div>
			
			<div class="ps-5 pe-5">
				<div class="line w-100" style="height: 1px; background-color: rgba(66, 148, 159, 0.4);"></div>
			</div>
			
			<div class="tabela-wrapper p-5">
					<table class="headerRelatorio tabela">
						<thead>
							<tr>
								<th><s:text name="label.id"/></th>
								<th><s:text name="label.codigoFuncionario"/></th>
								<th><s:text name="label.nomeFuncionario"/></th>
								<th><s:text name="label.codigoAgenda"/></th>
								<th><s:text name="label.nomeAgenda"/></th>
								<th><s:text name="label.data"/></th>
								<th><s:text name="label.horario"/></th>
							</tr>
						</thead>
					</table>
					
					<div>
						
						<table class="tabela">
							<tbody>
								<s:iterator value="compromissos" >
									<tr>
										<td>${rowid}</td>
										<td>${codigoFuncionario}</td>
										<td>${nomeFuncionario}</td>
										<td>${codigoAgenda}</td>
										<td>${nomeAgenda}</td>
										<td>${data}</td>
										<td>${horario}</td>
									</tr>
								</s:iterator>
							</tbody>	
						</table>
						
					</div>
					
				</div>

		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script>
			
			var modalConfirmarExclusao = document.getElementById("confirmarExclusao");
			modalConfirmarExclusao.addEventListener("show.bs.modal", function (event) {
				
				var btnAcionado = event.relatedTarget;
				var btnConfirmar = modalConfirmarExclusao.querySelector("#excluir");
				btnConfirmar.setAttribute("href", "excluirCompromissos.action?compromissoVo.rowid=" + btnAcionado.getAttribute("data-rowid"));
				
			});
		
		</script>
	</body>
</html>