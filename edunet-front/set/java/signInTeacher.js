$(document).ready(function () {
    $('#alertSuccess').hide();
    $('#alertError').hide();
    $("#signInFormTeacher").submit(function (event) {
        event.preventDefault();
        if (validateTeacherInputs()) {
            signInTeacher()
        }
    });

    function signInTeacher() {
        $('#alertSuccess').hide();
        $('#alertError').hide();
        var teacherUser = $('#teacherUser').val();
        var teacherPsw = $('#teacherPsw').val();
        var teacherName = $('#teacherName').val();
        var teacherLastName = $('#teacherLastName').val();
        var teacherEmail = $('#teacherEmail').val();
        var teacherBirth = $('#teacherBirth').val();
        var teacherSpeciality = $('#teacherSpeciality').val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://127.0.0.1:9090/teacher",
            data: JSON.stringify({ username: teacherUser, password: teacherPsw, firstName: teacherName, lastName: teacherLastName, email: teacherEmail, dateBirth: teacherBirth, speciality: teacherSpeciality }),
            dataType: 'json',
        }).done(function (data) {
            $('#teacherUser').val('');
            $('#teacherPsw').val('');
            $('#teacherName').val('');
            $('#teacherLastName').val('');
            $('#teacherEmail').val('');
            $('#teacherBirth').val('');
            $('#teacherSpeciality').val('');
            $('#alertSuccess').show();
            setTimeout(function () {
                $('#alertSuccess').hide();
            }, 3000);
        }).fail(function () {
            $('#alertError').show();
            console.log('ERROR');
            setTimeout(function () {
                $('#alertSuccess').hide();
            }, 3000);
        });
    }
    function validateTeacherInputs() {
        var isValid = true;

        // Validar usuario
        var user = $('#teacherUser').val();
        if (user.trim() === "") {
            isValid = false;
            alert("Por favor, ingrese un usuario.");
        }

        // Validar contraseña
        var password = $('#teacherPsw').val();
        if (!/^[A-Za-z\d@$!%?&]{8,25}$/.test(password)) {
            isValid = false;
            alert("La contraseña debe tener entre 8 y 25 caracteres, y puede contener letras, números y algunos caracteres especiales.");
        }

        // Validar nombre
        var name = $('#teacherName').val();
        if (!/^[A-Za-z]{1,60}$/.test(name)) {
            isValid = false;
            alert("Por favor, ingrese un nombre válido.");
        }

        // Validar apellido
        var lastName = $('#teacherLastName').val();
        if (!/^[A-Za-z]{1,60}$/.test(lastName)) {
            isValid = false;
            alert("Por favor, ingrese un apellido válido.");
        }

        // Validar correo electrónico
        var email = $('#teacherEmail').val();
        if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,3}$/.test(email)) {
            isValid = false;
            alert("Por favor, ingrese un correo electrónico válido.");
        }

        // Validar fecha de nacimiento
        var fechaNacimiento = $('#teacherBirth').val();
        // Expresión regular para el formato YYYY-MM-DD
        var formatoFecha = /^\d{4}-\d{2}-\d{2}$/;

        // Verificar el formato de la fecha
        if (!formatoFecha.test(fechaNacimiento)) {
            isValid = false;
            alert("Por favor, ingrese el formato de la fecha yyyy-mm-dd.");
        }

        // Obtener el año, mes y día
        var partesFecha = fechaNacimiento.split('-');
        var año = parseInt(partesFecha[0], 10);
        var mes = parseInt(partesFecha[1], 10);
        var dia = parseInt(partesFecha[2], 10);

        // Verificar el rango del año
        if (año < 1000 || año > 9999) {
            isValid = false;
            alert("Por favor, ingrese un año válido.");
        }

        // Verificar el rango del mes
        if (mes < 1 || mes > 12) {
            isValid = false;
            alert("Por favor, ingrese un mes válido.");
        }

        // Verificar el rango del día
        var diasEnMes = new Date(año, mes, 0).getDate();
        if (dia < 1 || dia > diasEnMes) {
            isValid = false;
            alert("Por favor, ingrese un día del mes válido.");
        }

        // Verificar si es año bisiesto (febrero tiene 29 días)
        if (mes === 2 && dia === 29 && !((año % 4 === 0 && año % 100 !== 0) || (año % 400 === 0))) {
            isValid = false;
            alert("Por favor, revise si es año bisiesto.");
        }

        // Validar especialidad
        var name = $('#teacherSpeciality').val();
        if (!/^[A-Za-z]{1,100}$/.test(name)) {
            isValid = false;
            alert("Por favor, ingrese el nombre de la especialidad correctamente.");
        }

        return isValid;
    }
});