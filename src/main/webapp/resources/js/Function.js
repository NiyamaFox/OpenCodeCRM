function deleteStudent() {
    var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');

    if (checkedBoxes.length == 0) {
        alert("Выберите хотя бы одного студента!");
        return;
    }

    var ids = "";
    for (var i = 0; i < checkedBoxes.length; i++) {
        ids = ids + checkedBoxes[i].getAttribute("value") + ".";
    }
    $('#deleteStudentHidden').val(ids);
    $('#deleteStudentForm').submit();
}

function modifyStudent() {
    var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');

    if (checkedBoxes.length == 0) {
        alert("Выберите хотя бы одного студента!");
        return;
    }
    if (checkedBoxes.length > 1) {
        alert("Выберите только одного студента!");
        return;
    }

    var id = checkedBoxes[0].getAttribute("value");

    $('#modifyStudentHidden').val(id);
    $('#modifyStudentForm').submit();
}