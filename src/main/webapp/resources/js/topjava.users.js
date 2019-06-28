userAjaxUrl = "ajax/admin/users/";

$(function () {
    makeEditable({
            ajaxUrl: userAjaxUrl,
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            }),
        updateTable: function () {
            $.get(userAjaxUrl, updateTableByData);
        }
        }
    );
});

function enable(checkBox, userId) {
    checkBoxState = checkBox.is(':checked');
    $.ajax({
        url: userAjaxUrl + userId,
        type: "POST",
        data: "enabled=" + checkBoxState
    }).done(function () {
        checkBox.closest("tr").attr("data-userEnabled", checkBoxState);
    }).fail(function () {
        $(chkbox).prop("checked", !checkBoxState);
    })
}