$(document).ready(function () {
    $("#FilterDataTable").on("keyup",function (){
        var value = $(this).val().toLowerCase();
        $("#dataBody tr").filter(function (){
            $(this).toggle($(this).text().toLowerCase().indexOf(value)>-1);
        })
    })
    $("#exportJson").on("click",function (){
           var val=document.getElementById("FilterDataTable").value;
            if(val=="")val="n";
            var url = 'Api/Route/user/exportAllFormationUser/'+val+'/json';
            window.location.assign(url);
    })
    $("#exportCSV").on("click",function (){
        var val=document.getElementById("FilterDataTable").value;
        if(val=="")val="n";
        var url = 'Api/Route/user/exportAllFormationUser/'+val+'/csv';
        window.location.assign(url);
    })



})

