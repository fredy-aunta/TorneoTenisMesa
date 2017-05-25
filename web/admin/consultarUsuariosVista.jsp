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
        <jsp:include page="menuAdmin.jsp" />
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        
                        <c:if test="${errorLogin!=null}">
                            <div class="alert alert-danger"><c:out value="${errorLogin}" /></div>
                        </c:if>
                            <div class="row">
                                <div class="col-md-4">
                                    <h3 class="">Usuarios</h3>
                                </div>
                                <div class="col-md-offset-4 col-md-4 text-right">
                                    <a class="btn btn-success btn-xs" href="/TorneoTenisMesa/Admin/CrearUsuario">Crear Usuario</a>
                                </div>
                            </div>
                        
                        <c:choose>
                            <c:when test="${usuarios==null}">
                                No hay usuarios
                            </c:when>
                            <c:otherwise>
                                <table class="table table-condensed">
                                    <thead>
                                        <tr>
                                            <th>Id Usuario</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Cedula</th>
                                            <th>Tel&eacute;fono</th>
                                            <th>Tipo</th>
                                            <th>Estado</th>
                                            <th>&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${usuarios}" var="usuario">
                                        <tr>
                                            <td>${usuario.idUsuario}</td>
                                            <td>${usuario.nombre}</td>
                                            <td>${usuario.apellido}</td>
                                            <td>${usuario.cedula}</td>
                                            <td>${usuario.telefono}</td>
                                            <td>${usuario.tipo}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${usuario.estado==true}">
                                                        <div class="square square-green"></div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="square square-red"></div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <a class="btn btn-info btn-xs" href="/TorneoTenisMesa/ConsultarUsuario?idUsuario=${usuario.idUsuario}">Consultar</a>
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