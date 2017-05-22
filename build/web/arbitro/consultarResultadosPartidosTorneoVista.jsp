<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Torneo de tenis de mesa-Admin</title>
        <style type="text/css">
            .square {
            width: 20px;
            height: 10px;
            border: 1px solid black;
         }
         .square-red{
            background-color: red;
         }
         .square-green{
            background-color: green;
         }
        </style>
    </head>
    <body id="page-top" class="index background">
        <%--<jsp:include page="menuAdmin.jsp" />--%>
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        
                        <c:if test="${errorLogin!=null}">
                            <div class="alert alert-danger"><c:out value="${errorLogin}" /></div>
                        </c:if>
                            <div class="row">
                                <div class="col-md-4">
                                    <h3 class="">Partidos</h3>
                                </div>
<!--                                <div class="col-md-offset-4 col-md-4 text-right">
                                    <a class="btn btn-success btn-xs" href="/TorneoTenisMesa/admin/crearTorneoVista.jsp">Crear Torneo</a>
                                </div>-->
                            </div>
                        
                        <c:choose>
                            <c:when test="${partidos==null}">
                                No hay torneos
                            </c:when>
                            <c:otherwise>
                                <table class="table table-condensed">
                                    <thead>
                                        <tr>
                                            <th>Id Partido</th>
                                            <th>Fecha - hora</th>
                                            <th>Jugadores</th>
                                            <th>Resultados</th>
                                            <!--<th>&nbsp;</th>-->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${partidos}" var="partido">
                                        <tr>
                                            <td>${partido.idPartidoTorneo}</td>
                                            <td>${partido.fechaHoraF}</td>
                                            <td>${partido.idJugador1} - ${partido.idJugador2}</td>
                                            <td>${partido.resultado1} - ${partido.resultado2}</td>
<!--                                            <td>
                                                <a class="btn btn-info btn-xs" href="/TorneoTenisMesa/FormResultados?idPartido=${partido.idPartido}">Ingresar Resultados</a>
                                                <a class="btn btn-info btn-xs" href="/TorneoTenisMesa/ConsultarPartidoCtrl?idPartido=${partido.idPartido}">Modificar</a>
                                            </td>-->
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
    </body>
</html>