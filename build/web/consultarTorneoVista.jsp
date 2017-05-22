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
            <c:if test="${tipo==1}">
                <jsp:include page="/jugador/menuJugador.jsp" />
            </c:if>
            <c:if test="${tipo==2}">
                <jsp:include page="/arbitro/menuArbitro.jsp" />
            </c:if>
            <c:if test="${tipo==3}">
                <jsp:include page="/admin/menuAdmin.jsp" />
            </c:if>
            <c:if test="${tipo==4}">
                <jsp:include page="/apostador/menuApostador.jsp" />
            </c:if>
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <c:choose>
                            <c:when test="${mensaje!=null}">
                                <div class="alert alert-success" id="mensaje">${mensaje}</div>
                            </c:when>
                            <c:when test="${mensajeError!=null}">
                                <div class="alert alert-danger" id="mensajeError">${mensajeError}</div>
                            </c:when>
                        </c:choose>
                                <h2 class="text-center">Torneo</h2>
                        <!--<p>Seleccione el tipo de usuario a crear</p>-->
                        <form class="form-horizontal" action="" method="post" name="addUser" id='consultarTorneoForm'>
<!--                            <div class="form-group">
                                <label class="col-sm-4 control-label">Tipo de usuario</label>
                                <div class="col-sm-8">
                                    <div class="btn-group" role="group">
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="jugador">Jugador</button>
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="arbitro">Arbitro</button>
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="admin">Administrador</button>
                                        <button type="button" class="btn btn-default user_types" name="user_type" value="apostador">Apostador</button>
                                    </div>
                                </div>
                            </div>-->
                            <div id="customer_fields" class="user_fields">
                            <div class="form-group">
                                <label for="nombreTorneo" class="col-sm-4 control-label">Nombre</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control edit" readonly id="nombreTorneo" name="nombre" placeholder="Nombre" value="${torneo.nombre}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customer_surname" class="col-sm-4 control-label">Estructura</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control edit" readonly id="customer_surname" name="apellido" placeholder="Apellidos" value="${torneo.estructura.nombre}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="telefono" class="col-sm-4 control-label">Cantidad jugadores</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control edit" readonly id="telefono" name="telefono" value="${torneo.cantidadJugadores}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tipo" class="col-sm-4 control-label">Cantidad mesas</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" readonly id="tipo" name="tipo" value="${torneo.cantidadMesas}">
                                </div>
                            </div>
                            </div>
                            <div class="form-group text-right">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <a id="modificarUsuario" href="/TorneoTenisMesa/ConsultarPartidosTorneoCtrl?idTorneo=${torneo.idTorneo}" class="btn btn-success btn-lg">Modificar</a>
                                </div>
                            </div>
                                <hr/>
                            <div class="form-group text-right">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <div class="btn-group" role="group">
                                        <a id="modificarUsuario" href="/TorneoTenisMesa/ConsultarResultadosPartidosTorneo?idTorneo=${torneo.idTorneo}" class="btn btn-info">Consultar resultados</a>
                                        <a id="modificarUsuario" href="/TorneoTenisMesa/ConsultarPartidosTorneoCtrl?idTorneo=${torneo.idTorneo}" class="btn btn-info">Ingresar resultados</a>
                                    </div>
                                </div>
                            </div>
                                
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript">
            $('#modificarUsuario').click(function (){
            var nombre = $("input[name='nombre']").val().trim();
            var apellido = $("input[name='apellido']").val().trim();
            var telefono = $("input[name='telefono']").val().trim();
            var nombreUsuario = $("input[name='nombreUsuario']").val().trim();
            var clave = $("input[name='clave']").val();
            var tieneError = false; 
               
            if(isNaN(telefono)){
                alert("El telefono no debe contener letras o caracteres especiales");
                tieneError = true
            } 
              
            if(apellido==''||telefono==''||nombreUsuario==''||clave==''||nombre==''){
                alert("Todos los campos deben estar llenos");
                tieneError = true
            }
            if(nombre.search(/[0-9]/g)!=-1&&(nombre.search(/[0-9]/g)!=nombre.length)){
                alert("El nombre no debe contener numeros");
                tieneError = true
            }
            if(apellido.search(/[0-9]/g)!=-1&&(apellido.search(/[0-9]/g)!=apellido.length)){
                alert("El apellido no debe contener numeros");
                tieneError = true
            }
            
            $('.edit').prop("readonly",false);
            if($(this).html() == "Modificar"){
                $(this).html("Guardar");
            }else if((!tieneError)){
                $('#modificarUsuarioForm').submit();
            }
        });
        
        
        
        $('#mPerfil').click(function() {
            
             var tipo = $("input[name='tipo']").val().trim();
            
            $('.user_types').click(function (){
                var selectedUser = $(this).val();
                $('.user_fields').addClass('hide');
                var tipoJugador = null;
                if(selectedUser == 'jugador'){
                    tipoJugador = 1;
                    $('#customer_fields').removeClass('hide'); 
                }else if(selectedUser == 'arbitro'){
                    tipoJugador = 2;
                    $('#customer_fields').removeClass('hide');
                }else if(selectedUser == 'admin'){
                    tipoJugador = 3;
                    $('#customer_fields').removeClass('hide');
                }else if(selectedUser == 'apostador'){
                    tipoJugador = 4;
                    $('#customer_fields').removeClass('hide');
                }
                $('#login_fields').removeClass('hide');
                $('#tipoJugador').val(tipoJugador);
            });    
        });
        
        
        
        $("#mensaje").fadeTo(2000, 500).slideUp(500, function(){
        $("#mensaje").alert('close');
        });
        
        $("#mensajeError").fadeTo(2000, 500).slideUp(500, function(){
        $("#mensajeError").alert('close');
        });
            
        </script>
    </body>
</html>