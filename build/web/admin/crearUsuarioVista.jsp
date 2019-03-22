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
                    <div class="col-md-10 col-md-offset-1">
                        <c:if test="${idUsuario>0}">
                            <div class="alert alert-success" id="msgCreacion">
                                Usuario Creado
                            </div>
                        </c:if>                        
                        <p>Seleccione el tipo de usuario a crear</p>
                        <form class="form-horizontal" action="" method="post" name="addUser" id='crearUsuarioForm'>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Tipo de usuario</label>
                                <div class="col-sm-8">
                                    <div class="btn-group" role="group">
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="jugador">Jugador</button>
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="arbitro">Arbitro</button>
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="admin">Administrador</button>
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="apostador">Apostador</button>
                                    </div>
                                </div>
                            </div>
                            <div id="customer_fields" class="hide user_fields">
                            <div class="form-group">
                                <label for="customer_cc" class="col-sm-4 control-label">Cedula de Ciudadania</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="customer_cc" name="cc" placeholder="CC">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customer_name" class="col-sm-4 control-label">Nombres</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="customer_name" name="nombre" placeholder="Nombres">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customer_surname" class="col-sm-4 control-label">Apellidos</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="customer_surname" name="apellido" placeholder="Apellidos">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="telefono" class="col-sm-4 control-label">Telefono</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Telefono">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fechaNacimiento" class="col-sm-4 control-label">Fecha de Nacimiento</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="fechaNacimiento" name="fechaNacimiento" placeholder="yyyy-mm-dd">
                                </div>
                            </div>
                            </div>
                            <div id="login_fields" class="hide user_fields">
                            <div class="form-group">
                                <label for="username" class="col-sm-4 control-label">Nombre de usuario</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="username" name="nombreUsuario" placeholder="Nombre de usuario">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-4 control-label">Contraseña</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="password" name="clave" placeholder="Contraseña">
                                </div>
                            </div>
                            <div class="form-group text-right">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <input type="hidden" name="tipo" value="" id="tipoJugador"/>
                                    <button id="crearUsuario" type="button" class="btn btn-success btn-lg">Crear</button>
                                </div>
                                
                            </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="btn-group" role="group">
                    <a id="volver" href="" class="btn btn-info">Volver</a>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript" src="/TorneoTenisMesa/assets/js/crearUsuario.js"></script>
    </body>
</html>