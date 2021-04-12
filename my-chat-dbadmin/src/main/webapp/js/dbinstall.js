//**************************************************************************************************
function progressHandlingFunction(e)
{
    if(e.lengthComputable)
    {
        // someday I'll do it. may be
    }
}
function beforeSendHandler(e)
{
    $("#btn").css ("display", "none");
    $("#btn-waiting").css ("display", "inline-block");
}

function completeHandler(e)
{
        $("#btn-waiting").css ("display", "none");        
        $("#btn").css ("display", "none");        

        var el = document.getElementById("srv-msg-container");

        var emd = document.createElement('div');
        
        $(emd).addClass ("result");
        var ih = '<font style="color:#03AD47; font-weight:bold;"> Таблицы созданы.</font><br>' + JSON.stringify(e);
        
        emd.innerHTML = ih;
        el.insertBefore(emd, el.firstChild);
        
        $(el).show();
}

function errorHandler(e, textStatus, errMess)
{
    $("#btn-waiting").css ("display", "none");
    $("#btn").css ("display", "none");        
    
    var msg = "";
    
    if (e.status === 404)
    {
        console.log(e.responseText);
        msg = e.responseText;
        var start = msg.indexOf("<body") + "<body".length + 1;
        var stop = msg.indexOf("</body>");
        
        msg = e.responseText.substring(start, stop);
    }     
    else
    {
        msg = "<font color=red>ERROR: Ошибка<br>HTTP response status: " + 
                e.status +
              "</font><br><i>Сообщение сервера:</i><br><br><font color=brown> " + 
                e.responseText + 
              "</font>";            
    }
      
    var 
    $_msg_cont = $("#srv-msg-container").eq(0);        
    $_msg_cont. append (msg);
    $_msg_cont.show();
}
//**************************************************************************************************
function createTables ()
{
    var req = '{"cmd": "init-db"}';    
    $.ajax
    (
            
        {   url: 'dbinstall/ajax-create-tables',  
            type: 'POST',
            xhr: function()
            {
                var myXhr = $.ajaxSettings.xhr();
                if(myXhr.upload)
                {
                    myXhr.upload.addEventListener('progress',progressHandlingFunction, false);
                }
                return myXhr;
            },
            beforeSend: beforeSendHandler,
            success: completeHandler,
            error: errorHandler,            
            contentType:"application/json; charset=utf-8",
            data: req,
            cache: false,            
            processData: false
        }
    );
}
//**************************************************************************************************
