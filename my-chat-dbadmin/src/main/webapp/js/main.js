//**************************************************************************************************
var $synx = {in_process: false};
//********************************
function _isInProcessNow ()
{
    return $synx. in_process;
}
function _inProcessFinish ()
{
    $synx. in_process = false;
}
function _inProcessStart ()
{
    $synx. in_process = true;
}
//**************************************************************************************************
function _ShowError (msg)
{
    alert (msg);
}
//**************************************************************************************************************
function closeNotice (noticeDiv)
{
    $(noticeDiv). slideUp(600);
}
//**************************************************************************************************
function _formatServerNull()
{
    var msg =
            "<b>Возможно, произошла ошибка сервера или несанкционированный перехват запроса.</b><br><br>" +
            "<font color=red>Обратитесь к системному администратору.</font><br>";
    
    return msg;
}
//**************************************************************************************************
function _formatServerMsg(jObj)
{
    var msg = "<!--not inited -->";     
    if (jObj == null)
    {
        msg = 
        "<b>Обратитесь к системному администратору.</b><br><br>" +
        "<font color='#808080'>Сервер успешно обработал запрос</font><br>" +                                
        "<div class='msg-line-title'> Сообщение:</div><br>Возможна ошибка сервера или несанкционированный перехват запроса";            
    }
    else
    {    
        try
        {
            msg =
            "<b>" + jObj ["server_message_header"] +":</b><br><br>" +
            "<font color='#808080'>Сервер успешно обработал запрос</font><br>" +                                
            "<div class='msg-line-title'> Сообщение:       </div><br>" + jObj ["server_message_body"];
        }
        catch (e)
        {
            console.log ('Ошибка ' + e.name + ":" + e.message + "\n" + e.stack);
        }
    }   
    
    return msg;
}
//**************************************************************************************************
function _formatServerErr (data, textStatus, errMess)
{
    var msg = "<not inited>";
    
    try
    {
        var jObj = {};
        
        jObj ["server_message_header"] = "Ошибка";
        jObj ["server_message_body"] = "Обратитесь к системному администратору.";        
        
        if (data.status == "404") 
        {
                jObj ["server_message_header"] = "Страница не найдена";
        }
        else
        {
            
            if (data != null)
                if (data.responseText != null)
                    if (data.responseText.length != 0) 
                    {
                        var  j = {};
                        try {j = JSON.parse(data.responseText);} catch (e)
                        {
                            j ["server_message_header"] = "Ошибка";
                            j ["server_message_body"] = "Обратитесь к системному администратору.";                                
                        }     
                        jObj ["server_message_header"] = j ["server_message_header"];
                        jObj ["server_message_body"] = j ["server_message_body"];
                    }
        }

        msg =
        "<i><div style='color:red;font-size:15px;text-decoration:underline;'><font color=gray>Заголовок:</font> " + jObj ["server_message_header"] + "</div></i><br>"+
        "<div style='font-size:12px;'>" +
        " <font color='#808080'>Ошибка обмена данными с сервером:</font><br>" +
        "<div class='err-line-title'> server.response.status:              </div>" + data.status                + "<br>" +                
                "<div class='err-line-title'> js.errorDescroption:         </div>" + errMess                    + "<br>" +
                "<div class='err-line-title'> js.readyState:               </div>" + data.readyState            + "<br>" +
                "<div class='err-line-title'> js.status:                   </div>" + textStatus                 + "<br>" +                                
                "<div class='err-line-title'> Сообщение сервера:       </div><br>" + jObj ["server_message_body"] +
        "</div>";        
    }
    catch (e)
    {
            console.log ('Ошибка ' + e.name + ":" + e.message + "\n" + e.stack);
    }    
    return msg;
}
//**************************************************************************************************
function CRequest ()
{
	var self = this;
        //************************************************************
        this. _error = function (data, textStatus, errMess)
        {
            _inProcessFinish ();
            
            var msg = _formatServerErr (data, textStatus, errMess);
            self._onError (msg);
        };
        //************************************************************
        this. _success = function (data)
        {
            _inProcessFinish ();            
            
            if (data == null)
            {
                self._onError (_formatServerNull ());
                return;
            }   
            
            //var jRes = JSON.parse (data);
            var jRes = data;
            
            var msg = _formatServerMsg(jRes);
            self._onSuccess (msg, jRes);
            
            self._processJResponce (data == null ? "" : jRes["server_message_body"]);
        };
        //************************************************************        
}
//**************************************************************************************************
function Sender ()
{
        //************************************************************
        this._send = function (type, _request, _uri, _params)
        {
            if (_isInProcessNow ())
            {
                _ShowError ("Не могу выполнить запрошенную операцию, дождитесь завершения текущей.");
                return;
            }

            _request._onStart  ();

            $.ajax(
            {	
                type:     type,
                url:     _uri,
                data:    _params,
                success: _request._success,
                error:   _request._error,
                
                contentType:"application/json; charset=utf-8",
            });
        };
}
//**************************************************************************************************