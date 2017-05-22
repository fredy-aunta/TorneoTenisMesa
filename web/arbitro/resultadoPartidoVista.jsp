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
                        <c:if test="${subidos == true}">
                            <div class="alert alert-success" id="msgCreacion">
                                Resultados subidos
                            </div>
                        </c:if>                        
                        <p>Ingrese los resultados del partido</p>
                        <form class="form-horizontal" action="/TorneoTenisMesa/IngresarResultadoPartido" method="post" name="addUser" id='resultadoPartidoForm' >
                            <div id="customer_fields" class="user_fields">
                            <div class="form-group">
                                <label for="idPartidoTorneo" class="col-sm-4 control-label"># Partido</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="idPartidoTorneo" value="${partido.idPartidoTorneo}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="jugadores" class="col-sm-4 control-label">Resultado jugador 1 (${partido.idJugador1})</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="jugadores" name="resultado1" value="${partido.resultado1}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="jugadores" class="col-sm-4 control-label">Resultado jugador 2 (${partido.idJugador2})</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="jugadores" name="resultado2" value="${partido.resultado2}">
                                </div>
                            </div>
                            <div class="form-group text-right">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <input type="hidden" name="idPartido" value="${partido.idPartido}" id="idPartido"/>
                                    <input type="hidden" name="idJugador1" value="${partido.idJugador1}" id="idJugador1"/>
                                    <input type="hidden" name="idJugador2" value="${partido.idJugador2}" id="idJugador2"/>
                                    <button id="subirResultados" type="button" class="btn btn-success btn-lg">Subir</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript">
            $('#subirResultados').click(function (){
                var error =  false
                if(!error){
                    $('#resultadoPartidoForm').submit();
                }
             });
        </script>
    </body>
</html>