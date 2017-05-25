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
        <jsp:include page="admin/menuAdmin.jsp" />
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        
                        <c:if test="${idUsuario>0}">
                            <div class="alert alert-success" id="msgCreacion">
                                Usuario Creado
                            </div>
                        </c:if>
                            <div class="row">
                                <div class="col-md-4">
                                    <h3 class="">Torneos</h3>
                                </div>
                                <div class="col-md-offset-4 col-md-4 text-right">
                                    <a class="btn btn-success btn-xs" href="/TorneoTenisMesa/admin/crearTorneoVista.jsp">Crear Torneo</a>
                                </div>
                            </div>
                        
                        <c:choose>
                            <c:when test="${torneos==null}">
                                No hay torneos
                            </c:when>
                            <c:otherwise>
                                <table class="table table-condensed">
                                    <thead>
                                        <tr>
                                            <th>Id Torneo</th>
                                            <th>Nombre</th>
                                            <th>Estructura</th>
                                            <th># Jugadores</th>
                                            <th># Mesas</th>
                                            <th>&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${torneos}" var="torneo">
                                        <tr>
                                            <td>${torneo.idTorneo}</td>
                                            <td>${torneo.nombre}</td>
                                            <td>${torneo.estructura.nombre}</td>
                                            <td>${torneo.cantidadJugadores}</td>
                                            <td>${torneo.cantidadMesas}</td>
                                            <td>
                                                <a class="btn btn-info btn-xs" href="/TorneoTenisMesa/ConsultarTorneo?idTorneo=${torneo.idTorneo}">Consultar</a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="btn-group" role="group">
                    <a id="volver" href="" class="btn btn-info">Volver</a>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
    </body>
</html>