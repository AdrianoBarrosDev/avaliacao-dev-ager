<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title><s:text name="label.titulo.pagina"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
		<link rel="stylesheet" href="/avaliacao/css/navCustomizada.css" />
		<link rel="stylesheet" href="/avaliacao/css/global.css" />
	</head>
	<body>
	
		<div class="d-flex justify-content-start aling-items-start w-100">
		
			<nav class="sidebar">
			
				<img src="/avaliacao/imagens/LogoSocNav.png" />
			
				<div>
					<a href="todosFuncionarios.action" class="ativado gap-3">
						<img src="/avaliacao/imagens/FuncionariosIcon.png" />
						Funcionários
					</a>
					<a href="todosAgendas.action" class="desativado gap-3">
						<img src="/avaliacao/imagens/AgendasIcon.png" />
						Agendas
					</a>
					<a href="todosCompromissos.action" class="desativado gap-3">
						<img src="/avaliacao/imagens/CompromissosIcon.png" />
						Compromissos
					</a>
				</div>
				
			</nav>

			<div class="container-fluid p-5" style="margin-left: 260px;">
				
				<h1 class="mt-5">Novo Funcionário</h1>
			
				<s:form action="/novoFuncionarios.action">
	
					<div class="card mt-5">
						
						<div class="card-body">
							<div class="row align-items-center">
								<label for="id" class="col-sm-1 col-form-label text-center">
									Código:
								</label>	
	
								<div class="col-sm-2">
									<s:textfield cssClass="form-control" id="id" name="funcionarioVo.rowid" readonly="true"/>							
								</div>	
							</div>
							
							<div class="row align-items-center mt-3">
								<label for="nome" class="col-sm-1 col-form-label text-center">
									Nome:
								</label>	
	
								<div class="col-sm-5">
									<s:textfield cssClass="form-control" id="nome" name="funcionarioVo.nome"/>							
								</div>	
							</div>
						</div>
	
						<div class="card-footer">
							<div class="form-row">
								<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
								<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
							</div>
						</div>
					</div>
				</s:form>			
			</div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>