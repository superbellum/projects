
///////////////////////////////     GLOBAL    VARIABLES     ////////////////////////////////////////////////////////////

let intersectionArray = null;
let carArray = [];
let whiteboard = null;
let crossroad = null;
let indexIntersection = null;
let solutionTexts = null;

let circles = [];
let circlesClicked = 0;
let choiceArray = [];
let worker;

let sideBarOpened = false;

let solutionPattern = "";
let arrDivPedSem = [], arrDivCarSem =[];
let tempArray = [];
let iteration = 0;

let zoli=0;
let balazs=0;

let detectingCollisions = true;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function scaleTaskTable()
{
    let windowInnerWidth = window.innerWidth;
    let windowInnerHeight = window.innerHeight;

    let maxSize = Math.min(windowInnerWidth, windowInnerHeight);
    let table = document.getElementById("table-tasks");

    table.style.transform =  'translate(-50%, -50%)' + 'scale(' + (maxSize/1000) + ')';
}

function scaleCrossroad()
{
    let windowInnerWidth = window.innerWidth;
    let windowInnerHeight = window.innerHeight;
    let maxSize = Math.min(windowInnerWidth, windowInnerHeight);
    let scale = Math.min(maxSize / (1650 - windowInnerHeight / 2),(maxSize / (2100 - windowInnerWidth / 2)));

    crossroad.style.transform =  'translate(-50%, -50%)' + 'scale(' + scale + ')';
}


$(document).ready(function ()
{
    for (let styleSheet of document.styleSheets)
    {
        if (styleSheet.href.includes('index.css'))
        {
            // keyfrmaes animation
            styleSheet.insertRule('\
			@keyframes move {\
			    100% { offset-distance: 100%; }\
			}', 0);



            // print mode
            styleSheet.insertRule('\
            * {\
                color-adjust: exact !important;\
            }', 0);


        }
    }

    closeValidationResultDiv();
    closeCrashDiv();

    crossroad = document.querySelector(".crossroad");

    scaleCrossroad();
    scaleTaskTable();

    window.addEventListener('resize', function () {

        scaleCrossroad();
        scaleTaskTable();
    });

    whiteboard = document.getElementById("whiteboard");

    $.getJSON('data.json', function (data) {

        intersectionArray = data.intersections;
    });

    $.getJSON('solutions.json', function (data) {

        solutionTexts = data.solutions;
    });
});

function openWhiteboard(element)
{
    closeTopBar();
    whiteboard.style.display = "block";

    whiteboard.style.visibility = "hidden";
    if (typeof (Worker) !== "undefined")
    {
        worker = new Worker("loading.js");
        worker.onmessage = workerMessage;
        worker.postMessage("startWorker");
    }
    else {
        console.log( "Workers are not allowed in your browser!");
    }

    let idIntersection = element.id;
    let l = idIntersection.length;
    indexIntersection = parseInt(idIntersection[l - 2] + idIntersection[l - 1]) - 1;

    drawIntersection(indexIntersection);

    $('html, body').scrollTop(0);
    disableScroll();
}

function closeWhiteboard()
{
    clearCrossroad();

    whiteboard.style.display = "none";

    enableScroll();

    closeBottomBar();
}

function clearCrossroad()
{
    carArray = [];
    circles = [];
    circlesClicked = 0;
    choiceArray = [];
    solutionPattern = "";
    arrDivCarSem = [];
    arrDivPedSem = [];
    tempArray = [];
    iteration = 0;
    detectingCollisions = true;

    while (crossroad.firstChild)
    {
        crossroad.removeChild(crossroad.firstChild);
    }

    while (document.getElementById("div-choice-container").hasChildNodes())
    {
        document.getElementById("div-choice-container").firstChild.remove();
    }
}

function drawIntersection(indexIntersection)
{
    detectingCollisions = true;
    crossroad.style.backgroundImage = "url('" + intersectionArray[indexIntersection].backgroundImageSource + "')";

    let choiceText = document.createElement('h2');
    choiceText.innerHTML = "Poradie:";
    document.getElementById("div-choice-container").appendChild(choiceText);


    for (let indexRoute = 0; indexRoute < intersectionArray[indexIntersection].routes.length; ++indexRoute)
    {
        let circle = document.createElement("div");
        circle.id = indexRoute;
        let idText = parseInt(circle.id) + 1;
        circle.innerHTML = idText + ".";
        circles.push(parseInt(circle.id));
        let circleContainer = document.getElementById("div-choice-container");
        circle.classList.add("div-circle");
        circleContainer.appendChild(circle);

        let divCar = document.createElement('div');
        divCar.style.offsetPath = "path('" + intersectionArray[indexIntersection].routes[indexRoute].path + "')";

        divCar.className = intersectionArray[indexIntersection].routes[indexRoute].className;

        let type = intersectionArray[indexIntersection].routes[indexRoute].type;

        if (type === "vehicle")
        {

            divCar.addEventListener('click', function click() {
                sideBarController(true);
                if (!divCar.className.includes(' animate'))
                {
                    divCar.className += ' animate';
                }

                choiceArray.push(intersectionArray[indexIntersection].routes[indexRoute].id);
                if (!hasDuplicates() && circlesClicked < intersectionArray[indexIntersection].routes.length) {
                    document.getElementById(circles[circlesClicked++]).style.backgroundColor = intersectionArray[indexIntersection].routes[indexRoute].car_color;
                }
                if (choiceArray.length === intersectionArray[indexIntersection].routes.length) {
                    choiceArray = [];
                }

                solutionPattern += intersectionArray[indexIntersection].routes[indexRoute].car_color + '-';

                // check if validation is on time (last vehicle)
                tempArray.push(divCar);
                if (tempArray.length === carArray.length) {
                    divCar.addEventListener('animationend', function () {

                        validateSolution();

                        detectingCollisions = false;
                    });
                }

                // first car starts detecting collisions
                if (tempArray.length === 1)
                {
                    detectCollisions();
                }
            });

            divCar.addEventListener('animationend', function () {

                crossroad.removeChild(divCar);
            });
        }
        else if (type === "pedestrian")
        {
            divCar.addEventListener('click', function () {

                if (!divCar.className.includes(' walk'))
                {
                    divCar.className += ' walk';
                }

                choiceArray.push(intersectionArray[indexIntersection].routes[indexRoute].id);
                if (!hasDuplicates() && circlesClicked <  intersectionArray[indexIntersection].routes.length)
                {
                    document.getElementById(circles[circlesClicked++]).style.backgroundColor = intersectionArray[indexIntersection].routes[indexRoute].car_color;
                }
                if (choiceArray.length === intersectionArray[indexIntersection].routes.length)
                {
                    choiceArray = [];
                }

                solutionPattern  += intersectionArray[indexIntersection].routes[indexRoute].car_color + '-';

                tempArray.push(divCar);
                if (tempArray.length === carArray.length)
                {
                    divCar.addEventListener('animationend', function () {

                        validateSolution();

                        detectingCollisions = false;
                    });
                }

                // first car starts detecting collisions
                if (tempArray.length === 1)
                {
                    detectCollisions();
                }
            });

            divCar.addEventListener('animationend', function () {

                divCar.style.animationPlayState = 'paused';
            });
        }





        // adding the four checkpoints
        let leftTop = document.createElement('div');
        let rightTop = document.createElement('div');
        let rightBottom = document.createElement('div');
        let leftBottom = document.createElement('div');

        leftTop.className = 'left-top';
        rightTop.className = 'right-top';
        rightBottom.className = 'right-bottom';
        leftBottom.className = 'left-bottom';

        divCar.appendChild(leftTop);
        divCar.appendChild(rightTop);
        divCar.appendChild(rightBottom);
        divCar.appendChild(leftBottom);
        ////////





        carArray.push(divCar);

        crossroad.appendChild(divCar);
    }

    // semafores
    if (intersectionArray[indexIntersection].isSemafore === "true")
    {
        let semafores = intersectionArray[indexIntersection].semafores;

        for (let i = 0; i < semafores.length; ++i)
        {
            let divSemafore = document.createElement('div');

            if (semafores[i].type === "pedestrian")
            {
                divSemafore.className = "div-semafore-pedestrian";

                divSemafore.style.backgroundImage = "url('images/road_signs/semafor_pedestrian_green.png')";

                arrDivPedSem.push(divSemafore);
            }
            else if (semafores[i].type === "vehicle")
            {
                divSemafore.className = "div-semafore-vehicle";

                divSemafore.style.backgroundImage = "url('images/road_signs/semafor_car_red.png')";

                arrDivCarSem.push(divSemafore);
            }

            divSemafore.style.top = semafores[i].top;
            divSemafore.style.left = semafores[i].left;
            divSemafore.style.transform = "rotate(" + semafores[i].rotate + ")";

            crossroad.appendChild(divSemafore);
        }

        carArray[0].addEventListener('animationend', function () {

            changeSemafores(arrDivPedSem, arrDivCarSem);
        })
    }

}

function changeSemafores(arrDivPedSem, arrDivCarSem)
{
    for (let i = 0; i < arrDivPedSem.length; ++i)
    {
        arrDivPedSem[i].style.backgroundImage = "url('images/road_signs/semafor_pedestrian_red.png')";
    }

    for (let j = 0; j < arrDivCarSem.length; ++j)
    {
        arrDivCarSem[j].style.backgroundImage = "url('images/road_signs/semafor_car_yellow.png')";
    }

    setInterval(() => {

        for (let j = 0; j < arrDivCarSem.length; ++j)
        {
            arrDivCarSem[j].style.backgroundImage = "url('images/road_signs/semafor_car_green.png')";
        }
    }, 1000);
}

function resetSituation()
{
    clearCrossroad();

    closeBottomBar();

    closeValidationResultDiv();

    closeCrashDiv();

    drawIntersection(indexIntersection);
}

function openCrashDiv()
{
    for (let i = 0; i < crossroad.childNodes.length; ++i)
    {
        crossroad.childNodes[i].style.animationPlayState = 'paused';
    }

    $("#div-crash").show();
}

function closeCrashDiv()
{
    $("#div-crash").hide();
}

function closeValidationResultDiv()
{
    $("#div-validation-result").hide();
}

function validateSolution()
{
    solutionPattern = solutionPattern.substring(0, solutionPattern.length - 1);

    for (let i = 0; i < intersectionArray[indexIntersection].solutions.length; ++i)
    {
        if (solutionPattern === intersectionArray[indexIntersection].solutions[i].value)
        {
            let resultDiv = document.getElementById("div-validation-result");
            resultDiv.style.backgroundImage = "url('images/success_person.png')";
            let innerResultDiv = document.getElementById("inner-div-validation-result");
            innerResultDiv.style.backgroundImage = "linear-gradient(to right, white, palegreen)";
            document.getElementById("p-validation-result-text").innerText = "Križovatku ste vyriešili správne!";

            $("#div-validation-result").show();

            return;
        }
    }

    let resultDiv = document.getElementById("div-validation-result");
    resultDiv.style.backgroundImage = "url('images/failure_person.png')";
    let innerResultDiv = document.getElementById("inner-div-validation-result");
    innerResultDiv.style.backgroundImage = "linear-gradient(to right, white, indianred)";
    document.getElementById("p-validation-result-text").innerText = "Križovatku ste nevyriešili správne!";

    $("#div-validation-result").show();
}

function sleep(ms)
{
    return new Promise(resolve => setTimeout(resolve, ms));
}

function disableButtons(... buttons)
{
    buttons.forEach(button => {

        button.disabled = true;
    });
}

function enableButtons(... buttons)
{
    buttons.forEach(button => {

        button.disabled = false;
    });
}

async function showSolution()
{
    let closeButton = document.getElementById("button-close");
    let resetButton = document.getElementById("button-reset");
    let solutionButton = document.getElementById("button-show-solution");
    disableButtons(closeButton, resetButton, solutionButton);

    resetSituation();

    openBottomBar();

    // remove event listeners
    for (let i = 0; i < carArray.length; ++i)
    {
        let cloned = carArray[i].cloneNode(true);
        carArray[i].parentNode.replaceChild(cloned, carArray[i]);
        carArray[i] = cloned;
    }

    carArray[0].addEventListener('animationend', function () {

        changeSemafores(arrDivPedSem, arrDivCarSem);
    });


    // first wave
    runCars(intersectionArray[indexIntersection].first);

    await sleep(intersectionArray[indexIntersection].sleep1);

    // second wave
    runCars(intersectionArray[indexIntersection].second);

    await sleep(intersectionArray[indexIntersection].sleep2);

    // third wave
    runCars(intersectionArray[indexIntersection].third);
}

function runCars(wave)
{
    for (let i = 0; i < wave.length; ++i)
    {
        ++iteration;

        let index = wave[i];

        let type = intersectionArray[indexIntersection].routes[index].type;

        if (type === "vehicle")
        {
            carArray[index].addEventListener('animationend', function () {

                crossroad.removeChild(carArray[index]);
            });


            if (!carArray[index].className.includes(' animate'))
            {
                carArray[index].className += ' animate';
            }
        }
        else if (type === "pedestrian")
        {
            if (!carArray[index].className.includes(' walk'))
            {
                carArray[index].className += ' walk';
            }

            carArray[index].addEventListener('animationend', function () {

                carArray[index].style.animationPlayState = 'paused';
            });
        }

        if (iteration === carArray.length && (i === wave.length - 1))
        {
            carArray[index].addEventListener('animationend', function () {

                let closeButton = document.getElementById("button-close");
                let resetButton = document.getElementById("button-reset");
                let solutionButton = document.getElementById("button-show-solution");
                enableButtons(closeButton, resetButton, solutionButton);
            });
        }
    }
}

/////////////////////////////////   SCROLL FUNCTIONS   /////////////////////////////////////////////////////////////////

let keys = {37: 1, 38: 1, 39: 1, 40: 1};

function preventDefault(e)
{
    e = e || window.event;
    if (e.preventDefault)
        e.preventDefault();
    e.returnValue = false;
}

function preventDefaultForScrollKeys(e)
{
    if (keys[e.keyCode])
    {
        preventDefault(e);
        return false;
    }
}

function disableScroll()
{
    if (window.addEventListener) // older FF
        window.addEventListener('DOMMouseScroll', preventDefault, false);

    document.addEventListener('wheel', preventDefault, {passive: false}); // Disable scrolling in Chrome
    window.onwheel = preventDefault; // modern standard
    window.onmousewheel = document.onmousewheel = preventDefault; // older browsers, IE
    window.ontouchmove  = preventDefault; // mobile
    document.onkeydown  = preventDefaultForScrollKeys;
}

function enableScroll()
{
    if (window.removeEventListener)
        window.removeEventListener('DOMMouseScroll', preventDefault, false);

    document.removeEventListener('wheel', preventDefault, {passive: false}); // Enable scrolling in Chrome
    window.onmousewheel = document.onmousewheel = null;
    window.onwheel = null;
}

/////////////////////////////////   OTHER FUNCTIONS   /////////////////////////////////////////////////////////////////

async function detectCollisions()
{
    let frequency = 10;

    while (detectingCollisions)
    {
        out: for (let i = 0; i < crossroad.childNodes.length; ++i)
        {
            for (let j = 0; j < crossroad.childNodes.length; ++j)
            {
                if (i !== j && !crossroad.childNodes[i].className.includes('semafor') && !crossroad.childNodes[j].className.includes('semafor'))
                {
                    if (detectCollisionBetween2elements(crossroad.childNodes[i], crossroad.childNodes[j]))
                    {
                        detectingCollisions = false;
                        openCrashDiv();
                        break out;
                    }
                }
            }
        }

        await sleep(frequency);
    }
}

function hasDuplicates()
{
    let choiceArraySet = new Set(choiceArray);

    if (choiceArraySet.size < choiceArray.length)
    {
        choiceArray.pop();
        return true;
    }

    return false;
}

function workerMessage(event)
{
    let message = event.data;
    let screen = document.getElementById("loading_screen");

    if (message.messageType === "end")
    {
        screen.style.zIndex = "-1";
        screen.style.display = "none";
        whiteboard.style.zIndex = "2";
        whiteboard.style.visibility = "visible";
        worker.terminate();
    }
    else if (message.messageType === "start")
    {
        screen.style.display = "block";
        screen.style.zIndex = "3";
        whiteboard.style.zIndex = "-1";
        whiteboard.style.visibility = "hidden";
    }
}

function sideBarController(abort)
{
    let sidebar = document.getElementById("div-sidebar");
    let sidebarOpenerDiv = document.getElementById("div-sidebar-opener");
    let sidebarTutorialContainer = document.getElementById("div-sidebar-tutorial-container");

    if (!sideBarOpened && !abort)
    {
        openSideBar(sidebar, sidebarOpenerDiv, sidebarTutorialContainer);
    }
    else
    {
        closeSideBar(sidebar, sidebarOpenerDiv, sidebarTutorialContainer);
    }
}

function openSideBar(sidebar, sidebarOpenerDiv, sidebarTutorialContainer)
{

    sidebar.style.zIndex = "10";
    sidebarOpenerDiv.style.left = "50vw";
    sidebar.style.width = "50vw";

    setTimeout(function () {
        sidebarTutorialContainer.style.visibility = "visible";
    }, 1100);

    sideBarOpened = true;
}

function closeSideBar(sidebar, sidebarOpenerDiv, sidebarTutorialContainer)
{
    sidebar.style.width = "0vw";
    sidebarOpenerDiv.style.left = "0vw";
    sidebarTutorialContainer.style.visibility = "hidden";
    sideBarOpened = false;
}

function openTopBar()
{
    let topBar = document.getElementById("div-topbar");
    let topBarCloser = document.getElementById("button-topbar-closer");
    let topBarContentContainer = document.getElementById("div-topbar-main-container");

    topBar.style.zIndex = "10";
    topBar.style.height = "55vh";

    setTimeout(function () {

        topBarCloser.style.visibility = "visible";
        topBarContentContainer.style.visibility = "visible";
    }, 1100);
}

function closeTopBar()
{
    let topBar = document.getElementById("div-topbar");
    let topBarCloser = document.getElementById("button-topbar-closer");
    let topBarContentContainer = document.getElementById("div-topbar-main-container");

    topBarContentContainer.style.visibility = "hidden";
    topBarCloser.style.visibility = "hidden";
    topBar.style.height = "0";
}

 function showInfo_zoli()
 {
    if (zoli === 0)
    {
        zoli = 1;
        $('#zoli-info').show();
    }
    else {
        zoli = 0;
        $('#zoli-info').hide();

    }
 }

function showInfo_balazs()
{
    if (balazs === 0)
    {
        balazs = 1;
        $('#balazs-info').show();
    }
    else {
        balazs = 0;
        $('#balazs-info').hide();
    }
}

function showInfo_patrik()
{
    if (patrik === 0)
    {
        patrik = 1;
        $('#patrik-info').show();
    }
    else {
        patrik = 0;
        $('#patrik-info').hide();
    }
}

function openBottomBar()
{
    let bottomBar = document.getElementById("div-bottom-bar");

    bottomBar.style.height = "35vh";

    let bottomBarContent = document.getElementById("div-bottom-bar-content");

    bottomBarContent.innerText = solutionTexts[indexIntersection].text;
}

function closeBottomBar()
{
    let bottomBar = document.getElementById("div-bottom-bar");

    bottomBar.style.height = "0";

    let bottomBarContent = document.getElementById("div-bottom-bar-content");
    bottomBarContent.innerHTML = "";
}

function showFirefoxSettingsGuide() {
    let isFirefox = typeof InstallTrigger !== 'undefined';
    if (isFirefox)
    {
        let guideDiv = document.getElementById("div-firefox-settings-guide");
        guideDiv.style.display = "block";
    }

}

function closeFirefoxSettingsGuide() {
    let guideDiv = document.getElementById("div-firefox-settings-guide");
    guideDiv.style.display = "none";
}

