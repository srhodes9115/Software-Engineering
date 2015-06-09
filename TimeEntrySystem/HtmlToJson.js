
function tableToJson(table) {
    var data = [];
    for (var i = 0; i < tables.rows[0].cells.length; i++)
    {
        headers[i] = table.rows[0].cells[i].innerHTML.toLowerCase().replace(/ /gi, '');
    }
    for (var i = 1; i < table.rows.length; i++) {
        var tablerow = table.rows[i];
        var rowData = {};
        for (var j = 0; j < tableRow.cells.length; j++) {
            rowData[headers[j]] = tableRow.cells[j].innerHTML;
        }
    }
    data.push(rowData);
}