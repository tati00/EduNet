$(document).ready(function () {
    $('#alertError').hide();
    $("#studentForm").submit(function (event) {
        $('#alertError').hide();
        event.preventDefault();
        var user = $('#userStudent').val();
        var password = $('#passwordStudent').val();
        if (validateLoginInputs(user, password)) {
            validateStudent(user, password);
        }
    });
    $("#teacherForm").submit(function (event) {
        $('#alertError').hide();
        event.preventDefault();
        var userTeacher = $('#userTeacher').val();
        var passwordTeacher = $('#passwordTeacher').val();
        if (validateLoginInputs(userTeacher, passwordTeacher)) {
            validateTeacher(userTeacher, passwordTeacher);
        }
    });
});

function validateStudent(user, password) {
    $('#alertError').hide();
    $.ajax({
        url: "http://127.0.0.1:9090/" + user + "/" + password + "/student",
        type: "GET",
        dataType: "json",
        success: function (data) {
            if (data && data.length > 0) {
                var found = false;
                data.forEach(item => {
                    var auxUser = item.username;
                    var auxPassword = item.password;
                    if (auxUser == user && auxPassword == password) {
                        alert("Bienvenido [" + auxUser + "]");
                        found = true;
                        window.location.href = "studentProfile.html";
                    }
                    $('#userStudent').val('');
                    $('#passwordStudent').val('');
                });
                if (!found) {
                    $('#alertError').show();
                    setTimeout(function () {
                        $('#alertError').hide();
                    }, 3000);
                }
            } else {
                $('#alertError').show();
                setTimeout(function () {
                    $('#alertError').hide();
                }, 3000);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error: " + status);
        }
    });
}

function validateTeacher(userTeacher, passwordTeacher) {
    $('#alertError').hide();
    $.ajax({
        url: "http://127.0.0.1:9090/" + userTeacher + "/" + passwordTeacher + "/teacher",
        type: "GET",
        dataType: "json",
        success: function (data) {
            if (data && data.length > 0) {
                var found = false;
                data.forEach(item => {
                    var auxUser = item.username;
                    var auxPassword = item.password;
                    if (auxUser == userTeacher && auxPassword == passwordTeacher) {
                        alert("Bienvenido [" + auxUser + "]");
                        found = true;
                        window.location.href = "teacherProfile.html";
                    }

                    $('#userTeacher').val('');
                    $('#passwordTeacher').val('');
                    
                });
                if (!found) {
                    $('#alertError').show();
                    setTimeout(function () {
                        $('#alertError').hide();
                    }, 3000);
                }
            } else {
                $('#alertError').show();
                setTimeout(function () {
                    $('#alertError').hide();
                }, 3000);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error: " + status);
        }
    });
}
function validateLoginInputs(userLogin, passwordLogin) {
    var isValid = true;

    // Validar usuario
    var user = userLogin;
    if (user.trim() === "") {
        isValid = false;
        alert("Por favor, ingrese un usuario.");
    }

    // Validar contraseña
    var password = passwordLogin;
    if (!/^[A-Za-z\d@$!%?&]{8,25}$/.test(password)) {
        isValid = false;
        alert("La contraseña debe tener entre 8 y 25 caracteres, y puede contener letras, números y algunos caracteres especiales.");
    }

    return isValid;
}