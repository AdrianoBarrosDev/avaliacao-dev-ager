<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
		<link rel="stylesheet" href="/avaliacao/css/navCustomizada.css" />
		<link rel="stylesheet" href="/avaliacao/css/global.css" />
	</head>
	<body>
	
		<div class="background-container justify-content-end align-items-center">
		    <img src="/avaliacao/imagens/LogoBackground.png" class="background-image" style="height: 120%"/>
		</div>
		
		<div class="d-flex justify-content-start aling-items-start w-100" style="position: relative; z-index: 1;">
			
			<nav class="sidebar">
		
				<img class="logoNavSoc" src="/avaliacao/imagens/LogoSocNav.png" />
			
				<div>
					
					<a href="todosFuncionarios.action" class="desativado gap-3">
						<img src="/avaliacao/imagens/FuncionariosIcon.png" />
						Funcionários
					</a>
					<a href="todosAgendas.action" class="desativado gap-3">
						<img src="/avaliacao/imagens/AgendasIcon.png" />
						Agendas
					</a>
					<a href="todosCompromissos.action" class="ativado gap-3">
						<img src="/avaliacao/imagens/CompromissosIcon.png" />
						Compromissos
					</a>
				</div>
				
			</nav>
			
			<div class="container-fluid p-5" style="margin-left: 260px;">
			
				<h1 class="mt-4">Compromissos</h1>
				
				<div class="d-flex justify-content-between align-items-start mb-4 w-100">
				
					<s:form action="/filtrarCompromissos.action" cssClass="w-75">
						
						<div class="row d-flex justify-content-start align-items-center gap-3">
						
							<div class="input-group d-flex align-items-center col-6 w-auto">
							    <span class="input-group-text">
							        <strong><s:text name="label.buscar.por"/></strong>
							    </span>
							
							    <s:select  
							    	id="opcoesCombo"
							        cssClass="form-select opcoesFiltrar"
							        name="filtrar.opcoesCombo" 
							        list="listaOpcoesCombo"
							        headerKey=""  
							        headerValue="Escolha..." 
							        listKey="%{codigo}" 
							        listValue="%{descricao}"
							        value="filtrar.opcoesCombo.codigo"
							        required="true"							
							    />
							</div>
						
							<div class="input-group col-6">
								<s:textfield cssClass="form-control inputPesquisar" id="inputPesquisa" name="filtrar.valorBusca" required="true" />
								<button class="btnPesquisar" type="submit">
									<img src="/avaliacao/imagens/PesquisarIcon.png" />
								</button>
							</div>
							
						</div>
						
					</s:form>
					
					<div>
						<button id="btnLimpar" class="btnTransparente border-0 w-auto" onclick="document.location.href='todosCompromissos.action'">
							<s:text name="LIMPAR"/>
						</button>
					</div>
					
				</div>
				
				<div class="tabela-wrapper mb-5">
					<table class="tabela">
						<thead>
							<tr>
								<th class="colunaId"><s:text name="label.id"/></th>
								<th style="width: 10vw"><s:text name="label.codigoFuncionario"/></th>
								<th style="width: 12vw"><s:text name="label.nomeFuncionario"/></th>
								<th style="width: 10vw"><s:text name="label.codigoAgenda"/></th>
								<th style="width: 14vw"><s:text name="label.nomeAgenda"/></th>
								<th style="width: 8vw"><s:text name="label.data"/></th>
								<th><s:text name="label.horario"/></th>
								<th class="text-end mt-5"><s:text name="label.acao"/></th>
							</tr>
						</thead>
					</table>
					
					<div class="tabela-scroll-body">
						
						<table class="tabela">
							<tbody>
								<s:iterator value="compromissos" >
									<tr>
										<td class="colunaId">${rowid}</td>
										<td style="width: 10vw">${codigoFuncionario}</td>
										<td style="width: 12vw">${nomeFuncionario}</td>
										<td style="width: 10vw">${codigoAgenda}</td>
										<td style="width: 14vw">${nomeAgenda}</td>
										<td style="width: 8vw">${data}</td>
										<td>${horario}</td>
										<td>
											<div class="d-flex justify-content-end align-items-center gap-2">
												
												<s:url action="editarCompromissos" var="editar">
													<s:param name="compromissoVo.rowid" value="rowid"></s:param>
												</s:url>
			
												<a href="${editar}" class="btnAcao">
													<img class="imgAcao" src="/avaliacao/imagens/EditarIcon.png" />
												</a>
			
												<a href="#" class="btnAcao" data-bs-toggle="modal" data-bs-target="#confirmarExclusao" data-rowid="${rowid}">
													<img class="imgAcao" src="/avaliacao/imagens/DeletarIcon.png" />
												</a>
												
											</div>
										</td>
									</tr>
								</s:iterator>
							</tbody>
										
						</table>
						
					</div>
					
				</div>
				
				<div class="d-flex justify-content-between align-items-center">
					
					<s:url action="salvarCompromissos" var="salvar"/>
									
					<a href="${salvar}" class="btnNovo">
						<s:text name="label.novo"/>
					</a>
					
					<a href="#" class="btnTransparente" data-bs-toggle="modal" data-bs-target="#inserirDatasRelatorio">
						<s:text name="label.gerarRelatorio"/>
					</a>
				
				</div>
				
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
				
				<s:url action="excluirCompromissos" var="excluir">
					<s:param name="compromissoVo.rowid" value="rowid"></s:param>
				</s:url>
	        	
				<s:a href="%{excluir}" id="excluir" class="btn btn-primary" style="width: 75px;">
					<s:text name="label.sim"/>
				</s:a>
				
		      </div>
		    </div>		    
		  </div>
		</div>
		
		<div  class="modal fade" id="inserirDatasRelatorio" 
			data-bs-backdrop="static" data-bs-keyboard="false"
			tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.tituloRelatorioCompromissos"/></h5>
		        
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body d-flex justify-content-around align-items-center">
		      
		      		<div class="d-flex justify-content-around align-items-center flex-column col-5">
		      			<label for="dataInicial" class="col-form-label text-center">
							<s:text name="label.dataInicial" />
						</label>
	
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dataInicial" name="dataInicial">					
						</div>
		      		</div>
		      
      				<div class="d-flex justify-content-around align-items-center flex-column col-5">
      				
	      				<label for="dataFinal" class="col-form-label text-center">
							<s:text name="label.dataFinal" />
						</label>
	
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dataFinal" name="dataFinal">				
						</div>
      				
      				</div>

		      </div>
		      
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close">
					<s:text name="label.cancelar"/>
				</a>
	        	
				<a href="#" id="novoRelatorio" class="btn btn-primary" style="width: 125px;">
					<s:text name="label.continuar"/>
				</a>
				
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
				btnConfirmar.setAttribute("href", "excluirCompromissos.action?compromissoVo.rowid=" + btnAcionado.getAttribute("data-rowid"));
				
			});
		
		</script>
		
		<script>
			
			document.getElementById("novoRelatorio").addEventListener("click", function() {

				const dataInicial = document.getElementById("dataInicial").value;
				const dataFinal = document.getElementById("dataFinal").value;
				
				if (!dataInicial || !dataFinal) {
					alert("As datas não podem estar vazias");
					return;
				}
				
				if(dataInicial > dataFinal) {
					alert("A data inicial deve ser menor que a data final");
					return;
				}
				
				const url = "novoRelatorioCompromisso.action?dataInicial=" + dataInicial + "&dataFinal=" + dataFinal;
				window.open(url, "_blank");
				
			});
		
		</script>
		
		<script>
		
			document.addEventListener('DOMContentLoaded', function () {
			    const opcoesCombo = document.getElementById('opcoesCombo');
			    const inputPesquisa = document.getElementById('inputPesquisa');
			    
			    function atualizarCampoPesquisa() {
			        if (opcoesCombo.value === '6') {
			            inputPesquisa.setAttribute("type", "date");
			        } else if (opcoesCombo.value === '7') {
			            inputPesquisa.setAttribute("type", "time");
			        } else {
			            inputPesquisa.setAttribute("type", "text");
			        }
			    }
			    
			    opcoesCombo.addEventListener('change', function () {
			        inputPesquisa.value = "";
			        atualizarCampoPesquisa();
			    });

			    atualizarCampoPesquisa();
			    
			});
			
		</script>
		
	</body>
</html>