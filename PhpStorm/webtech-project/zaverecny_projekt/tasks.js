let closed = 0;

function tableCreate()
{
    $.getJSON('tasks.json', function (data) {

        let ulohy = data.ulohy;

        let tbl = document.getElementById("table-tasks");

        //tbl.className = "table";

        let tbhd = document.createElement('thead');
        let tr = document.createElement('tr');
        let th = document.createElement('th');

        th.innerHTML = "Úlohy";
        tr.appendChild(th);
        th = document.createElement('th');
        th.innerHTML = "Balázs";
        tr.appendChild(th);
        th = document.createElement('th');
        th.innerHTML = "Patrik";
        tr.appendChild(th);
        th = document.createElement('th');
        th.innerHTML = "Zoltán";
        tr.appendChild(th);
        tbhd.appendChild(tr);
        tbl.appendChild(tbhd);

        let tbdy = document.createElement('tbody');

        for (let i = 0; i < ulohy.length; i++)
        {
            tr = document.createElement('tr');

            for (let j = 0; j < 4; j++)
            {
                let td = document.createElement('td');

                if (j == 0)
                {
                    td.innerHTML=ulohy[i].nazov;
                }

                if (ulohy[i].praca == j)
                {
                    td.innerHTML='&#10003;';
                }

                tr.appendChild(td);
            }

            tbdy.appendChild(tr);
        }

        tbl.appendChild(tbdy);

    });

    closed = 1;
}


function showTaskTable()
{
    let table = document.getElementById("table-tasks");
    table.style.visibility='visible';

    if (closed == 0)
    {
        table.style.visibility = 'hidden';
        closed = 1;
    }
    else {
        table.style.visibility = 'visible';
        closed = 0;
    }
}

tableCreate();


