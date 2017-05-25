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
        <jsp:include page="menuAdmin.jsp" />
        <section >
            <div class="container">
                <div class="row">
                    <h2 class="text-center">Definir Jugadores</h2>
                    <form class="form-horizontal" action="/TorneoTenisMesa/admin/DefinirUsuariosTorneoCtrl" method="post" name="crearTorneo" id="crearTorneoForm">
                        <fieldset id="jug" class="col-md-5">
                            <div>
                                <div class="form-group">
                                    <select name="jugadores" class="form-control" id="jugadores" multiple>
                                        <c:forEach items="${jugadores}" var="jugador">
                                            <option value="${jugador.idUsuario}">${jugador.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <button type="button" id="agregarJugador" class="btn btn-success">Agregar Jugador</button>
                                    <button type="button" id="removerJugador" class="btn btn-success">Remover Jugador</button>
                                </div>
                                <div class="form-group">
                                    <select name="jugadoresSeleccionados" class="form-control" multiple id="jugadoresSeleccionados">
                                        </select>
                                </div>
                                <div class="form-group">
                                    <button type="button" id="confirmarJugadores" class="btn btn-success">Confirmar Jugadores</button>
                                </div>

                            </div>
                        </fieldset>
                        <div class="col-md-5 col-md-offset-2 hide"id="arbit">
                            <div class="form-group">
                                    <select name="arbitros" class="form-control"  id="arbitros" multiple>
                                        <c:forEach items="${arbitros}" var="arbitro">
                                            <option value="${arbitro.idUsuario}">${arbitro.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            <div class="form-group">
                                <button type="button" id="agregarArbitro" class="btn btn-success">Agregar Arbitro</button>
                                <button type="button" id="removerArbitro" class="btn btn-success">Remover Arbitro</button>
                            </div>
                            <div class="form-group">
                                <select name="arbitrosSeleccionados" class="form-control" id="arbitrosSeleccionados" multiple>
                                </select>
                            </div>
                            <div class="form-group">
                                <button type="button" id="confirmarArbitros" class="btn btn-success">Confirmar Arbitros</button>
                            </div>
                        </div>
                        <div id="listaJugadores"></div>
                        <div id="listaArbitros"></div>
                    </form>
                </div>
                <div class="btn-group" role="group">
                    <a id="volver" href="" class="btn btn-info">Volver</a>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript">
            var cantidadJugadores = ${cantidadJugadores};
            var cantidadMesas = ${cantidadMesas};
            
            $(document).ready(function (){
                $('#jugadores option').each(function (i,e){
                    if(i<cantidadJugadores){
                        $('#jugadoresSeleccionados').append($(e).clone());
                        $(e).remove();
                    }
                });
                $('#arbitros option').each(function (i,e){
                    if(i<cantidadMesas){
                        $('#arbitrosSeleccionados').append($(e).clone());
                        $(e).remove();
                    }
                });
                
            });
            
            $('#confirmarArbitros').click(function (){
                $('#listaJugadores').html('');
                $('#listaArbitros').html('');
                $('#jugadoresSeleccionados option').each(function (i,e){
                    var input = '<input name="jugadoresSelec[]" type="hidden" value="' + $(e).val() + '"/>';
                    $('#listaJugadores').append(input);
                });
                $('#arbitrosSeleccionados option').each(function (i,e){
                    var input = '<input name="arbitrosSelec[]" type="hidden" value="' + $(e).val() + '"/>';
                    $('#listaArbitros').append(input);
                });
                
                if($('#arbitrosSeleccionados option').length==0){
                    alert('debe seleccionar los arbitros del torneo');
                }else if($('#arbitrosSeleccionados option').length!=cantidadMesas){
                    alert('debe seleccionar '+cantidadMesas+' arbitros para el torneo');
                }else{
                    $('#crearTorneoForm').submit();
                }
            });
        $('#agregarJugador').click(function(e) {
            var selectedOpts = $('#jugadores option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }
            
            $('#jugadoresSeleccionados').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        $('#removerJugador').click(function(e) {
            var selectedOpts = $('#jugadoresSeleccionados option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }

            $('#jugadores').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        $('#agregarArbitro').click(function(e) {
            var selectedOpts = $('#arbitros option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }

            $('#arbitrosSeleccionados').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        $('#removerArbitro').click(function(e) {
            var selectedOpts = $('#arbitrosSeleccionados option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }

            $('#arbitros').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        
        
        $('#confirmarJugadores').click(function (e){
            
            if($('#jugadoresSeleccionados option').length!=cantidadJugadores){
                alert('debe seleccionar '+cantidadJugadores+' jugadores para el torneo');
            }else if($('#jugadoresSeleccionados option').length==0){
                alert('debe seleccionar los jugadores del torneo');
            }else{
                $('#arbit').removeClass('hide');
                $('#jug').attr('disabled','');
            }
        });
               
        </script>
    </body>
</html>