let selectedElement = null, offset, svg;

let startPoint, firstCheckPoint, secondCheckPoint, endPoint;

let path;


$(document).ready(function ()
{
    startPoint = document.getElementById('circle-start-point');
    firstCheckPoint = document.getElementById('circle-first-check-point');
    secondCheckPoint = document.getElementById('circle-second-check-point');
    endPoint = document.getElementById('circle-end-point');

    path = document.getElementById('path-motion');
});

function startDrag(evt)
{
    if (evt.target.classList.contains('draggable'))
    {
        selectedElement = evt.target;
        offset = getMousePosition(evt);
        offset.x -= parseFloat(selectedElement.getAttribute('cx'));
        offset.y -= parseFloat(selectedElement.getAttribute('cy'));
    }
}

function drag(evt)
{
    if (selectedElement)
    {
        let coord = getMousePosition(evt);
        selectedElement.setAttribute('cx', coord.x - offset.x);
        selectedElement.setAttribute('cy', coord.y - offset.y);

        let sx = startPoint.getAttribute('cx').toString();
        let sy = startPoint.getAttribute('cy').toString();

        let c1x = firstCheckPoint.getAttribute('cx').toString();
        let c1y = firstCheckPoint.getAttribute('cy').toString();

        let c2x = secondCheckPoint.getAttribute('cx').toString();
        let c2y = secondCheckPoint.getAttribute('cy').toString();

        let ex = endPoint.getAttribute('cx').toString();
        let ey = endPoint.getAttribute('cy').toString();

        let pathString = "M" + sx + "," + sy + " C" + c1x + "," + c1y + " " + c2x + "," + c2y + " " + ex + "," + ey;

        path.setAttribute('d', pathString);

        let div = document.getElementById('div-path-string');
        div.innerText = pathString;


        let circles = [];
        circles.push(startPoint);
        circles.push(firstCheckPoint);
        circles.push(secondCheckPoint);
        circles.push(endPoint);

        for (let i = 0; i < circles.length;  ++i)
        {
            for (let j = 0; j < circles.length; ++j)
            {
                if (overlaps(circles[i], circles[j]) && i != j)
                {
                    console.log('fuk');
                }
            }
        }

    }
}

function overlaps(a, b)
{
    const rect1 = a.getBoundingClientRect();
    const rect2 = b.getBoundingClientRect();
    const isInHoriztonalBounds =
        rect1.x < rect2.x + rect2.width && rect1.x + rect1.width > rect2.x;
    const isInVerticalBounds =
        rect1.y < rect2.y + rect2.height && rect1.y + rect1.height > rect2.y;
    const isOverlapping = isInHoriztonalBounds && isInVerticalBounds;
    return isOverlapping;
}

function endDrag()
{
    selectedElement = null;
}


function getMousePosition(evt)
{
    let svg = document.getElementById('svg');
    let CTM = svg.getScreenCTM();
    return {
        x: (evt.clientX - CTM.e) / CTM.a,
        y: (evt.clientY - CTM.f) / CTM.d
    };
}




