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
        <jsp:include page="_mainNav.jsp" />
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <c:if test="${errorLogin!=null}">
                            <div class="alert alert-danger"><c:out value="${errorLogin}" /></div>
                        </c:if>
                        <p class="text-center">Iniciar sesion</p>
                        <form class="form-horizontal" action="/TorneoTenisMesa/IniciarSesion" method="post" name="addUser">
                            <div id="login_fields" class="user_fields">
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
                                    <c:choose>
                                        <c:when test="${tipoUsr!= null}">
                                            <input type="hidden" name="tipoUsuario" value="${tipoUsr}" id="tipoUsuario"/>
                                        </c:when>
                                        <c:when test="${param.tipo!= null}">
                                            <input type="hidden" name="tipoUsuario" value="${param.tipo}" id="tipoUsuario"/>
                                        </c:when>
                                    </c:choose>
                                    <input type="hidden" name="tipo" value="" id="tipo"/>
                                    <button type="submit" class="btn btn-success btn-lg">Iniciar Sesi&oacute;n</button>
                                </div>
                            </div>
                            </div>
                        </form>
                    </div>
            </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript">
            $(document).ready(function (){
               var tipoUsuario = $('#tipoUsuario').val();
               var tipo = 0;
               switch(tipoUsuario){
                   case 'jugador':
                       tipo = 1;
                       break;
                    case 'arbitro':
                        tipo = 2;
                    break;
                    case 'admin':
                        tipo = 3;
                    break;
                    case 'apostador':
                        tipo = 4;
                    break;
               }
               if(tipo == 0){
                   alert('Un error a ocurrido, ir a inicio');
                   window.location = '/TorneoTenisMesa/';
               }
               $('#tipo').val(tipo);
            });
        </script>
    </body>
</html>