<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Torneo de tenis de mesa-Admin</title>
    </head>
    <body id="page-top" class="index background">
        <%--<jsp:include page="menuAdmin.jsp" />--%>
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <c:if test="${idUsuario>0}">
                            <div class="alert alert-success" id="msgCreacion">
                                Usuario Creado
                            </div>
                        </c:if>                        
                        <p>Seleccione el tipo de usuario a crear</p>
                        <form class="form-horizontal" action="/TorneoTenisMesa/ModificarPartidoCtrl" method="post" name="addUser" id='modificarPartidoForm' >
                            <div id="customer_fields" class="user_fields">
                            <div class="form-group">
                                <label for="idPartidoTorneo" class="col-sm-4 control-label"># Partido</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="idPartidoTorneo" value="${partido.idPartidoTorneo}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fechaHora" class="col-sm-4 control-label">Fecha-Hora</label>
                                <div class="col-sm-8">
                                    <div class="input-group datetimepicker">
                                        <input type="text" class="form-control" id="fechaHora" name="fechaHora" value="${partido.fechaHoraF}" onkeyup="valida(this,16)">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="button">
                                              <i class="fa fa-calendar" aria-hidden="true"></i>
                                            </button>
                                        </span>
                                        
                                    </div>
                                  </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="jugadores" class="col-sm-4 control-label">Jugadores</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="jugadores" value="${partido.idJugador1} - ${partido.idJugador2}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="resultados" class="col-sm-4 control-label">Resultados</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="resultados" value="${partido.resultado1} - ${partido.resultado2}" readonly>
                                </div>
                            </div>
                            <div class="form-group text-right">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <input type="hidden" name="idPartido" value="${partido.idPartido}" id="idPartido"/>
                                    <button id="crearUsuario" type="button" class="btn btn-success btn-lg">Modificar</button>
                                </div>
                            </div>
                            <div class="btn-group" role="group">
                                <a id="volver" href="" class="btn btn-info">Volver</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript">
            function valida(btn,tam)
            {
                
                var largo=btn.value.length

                if (largo > tam){
                    btn.value = btn.value.substring(0,tam-1)+"0"
                }
            }
            $('#crearUsuario').click(function (){
                var error =  false
                
                
                if($('#fechaHora').val().length!=16){
                    alert("La fecha esta incompleta");
                    error = true
                }
                                
                if(f.search(/[a-z]/g)!=-1){
                    alert("la fecha no debe contener letras");
                    error = true
                }
         
                if(!error){
                    $('#modificarPartidoForm').submit();
                }
                
             });
            
            
            
        </script>
    </body>
</html>