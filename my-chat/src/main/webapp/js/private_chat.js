function _applayDynamicSettings ()
{
        drawMenuTriangle ();
}

function drawMenuTriangle ()
{
        var triangle = document.getElementById('forTriangle');
        if (!triangle)
            return;

        var topBodyBar = document.getElementById('topBar');
        var style = window.getComputedStyle(topBodyBar);
        var ctx = triangle.getContext('2d');

        ctx.beginPath();
        ctx.moveTo(10, 0);
        ctx.lineTo(90, 0);
        ctx.lineTo(50, 50);
        ctx.lineTo(10, 0);
        ctx.closePath();

        ctx.shadowColor = "rgba(0, 0, 0, 0.5)";
        ctx.shadowBlur = 15;
        ctx.shadowOffsetX = 0;
        ctx.shadowOffsetY = 4;
        ctx.fillStyle = style.getPropertyValue("background-color");
        ctx.fill();
}
//******************************************************************************

