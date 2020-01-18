let county = "SKd";
let header_country="SK";
let podlamena = 1;
let podladatumu = 0;
let closedsearch = 0;

let dict = {"á": "a", "é": "e", "č": "c", "í": "i", "ľ": "l", "š": "s", "ť": "t", "ž": "z", "ý": "y", "ó": "o"}


$(document).ready(function () {
    // set a variable
    let today = moment().format('DD MMM YYYY');

    document.getElementById('div-date').innerHTML = today.toString();

});

$(document).ready(function () {

    //$("#dvContent").append("<ul></ul>");

    $.ajax({

        type: "GET",

        url: "meniny.xml",

        dataType: "xml",

        success: function (xml) {

            let dnes = moment().format('MMDD');

            $(xml).find('zaznam').each(function () {

                let den = $(this).find('den').text();

                if (den === dnes)
                {
                    let meno = $(this).find(header_country).text();

                    document.getElementById("div-name").innerHTML = meno;
                }

            });

        },
        error: function () {

            alert("An error occurred while processing XML file.");
        }
    });

    closedsearch = 1;
});

function showSearcher()
{
    let search = document.getElementById("div-namedays");

    search.style.visibility = 'visible';

    if (closedsearch === 0)
    {
        search.style.visibility = 'hidden';

        document.getElementById('inlineFormInputName1').value = '';
        document.getElementById('inlineFormInputName2').value = '';
        document.getElementById('div-result').innerText = '';

        closedsearch = 1;
    }
    else {
        search.style.visibility = 'visible';

        closedsearch = 0;
    }
}

$('#button-get-date').click(function () {

    $.ajax({

        type: "GET",

        url: "meniny.xml",

        dataType: "xml",

        success: function (xml) {

            document.getElementById('div-result').innerText='';
            if (podladatumu === 1 && validateDate() === true)
            {
                let dnes = document.getElementById('inlineFormInputName2').value;


                let splitted = dnes.split('.');
                let dd = splitted[0];

                if (dd.length === 1)
                {
                    dd = "0" + dd;
                }

                let mm = splitted[1];

                if (mm.length === 1)
                {
                    mm = "0" + mm;
                }

                dnes = mm + dd;

                $(xml).find('zaznam').each(function ()
                {
                    let den = $(this).find('den').text();

                    if (den === dnes)
                    {
                        let meniny = $(this).find(county).text();

                        document.getElementById('div-result').innerText = meniny.toString();
                    }
                });
            }

            if (podlamena === 1 && validateName())
            {
                let meno = document.getElementById('inlineFormInputName1').value;
                $(xml).find('zaznam').each(function () {

                    let den = $(this).find(county).text().replace(/ /g, "");
                    let mena = den.split(',');

                    for (let i = 0; i < mena.length; i++)
                    {
                        if (meno.toLowerCase().replace(/[^\w ]/g, function (char) {

                            return dict[char] || char;
                        }) === mena[i].toLowerCase().replace(/[^\w ]/g, function (char) {

                            return dict[char] || char;
                        }))
                        {
                            let datum = $(this).find('den').text();
                            let mm = datum.slice(0,2);
                            let dd = datum.slice(2,4);
                            datum = dd + "." + mm + ".";

                            document.getElementById('div-result').innerText += datum.toString() + "\n";
                        }
                    }

                });
                if(document.getElementById('div-result').innerText===''){
                    document.getElementById('div-result').innerText='Meno sa nenachádza v kalendári.'
                }
            }
        },
        error: function () {

            alert("An error occurred while processing XML file.");
        }
    });
});

function changeCountry() {

    county = document.getElementById('select').value;
    header_country=county;
    if(county==="SKd"){
        header_country="SK";
    }

    $.ajax({

        type: "GET",

        url: "meniny.xml",

        dataType: "xml",

        success: function (xml) {

            let dnes = moment().format('MMDD');

            $(xml).find('zaznam').each(function () {

                let den = $(this).find('den').text();

                if (den === dnes)
                {
                    let meno = $(this).find(header_country).text();


                    document.getElementById("div-name").innerHTML = meno;
                }

            });

        },
        error: function () {

            alert("An error occurred while processing XML file.");
        }
    });
}

function changeSearch()
{
    if ($('#exampleRadios1').is(':checked'))
    {
        podlamena = 1;
        podladatumu = 0;

        $('#inlineFormInputName2').hide();
        $('#inlineFormInputName1').show();

        document.getElementById('div-result').innerText = '';
        document.getElementById('inlineFormInputName2').value = '';
        document.getElementById("tooltip").style.display = 'none';

    }
    if ($('#exampleRadios2').is(':checked'))
    {
        podlamena = 0;
        podladatumu = 1;

        $('#inlineFormInputName1').hide();
        $('#inlineFormInputName2').show();

        document.getElementById('div-result').innerText = '';
        document.getElementById('inlineFormInputName1').value = '';
        document.getElementById("tooltip").style.display = 'none';
    }


}

function validateDate()
{
    let date = document.getElementById('inlineFormInputName2').value;

    if (date.length < 3)
    {
        return false;
    }

    let fh = date.includes(".");

    if (!fh)
    {
        return false;
    }

    let splitted = date.split('.');
    let dd = parseInt(splitted[0], 10);
    let mm = parseInt(splitted[1], 10);

    if (mm < 1 || mm > 12)
    {
        return false;
    }

    let ListofDays = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    if (dd < 1 )
    {
        return false;
    }

    if (mm === 1 || mm > 2)
    {
        if (dd > ListofDays[mm - 1])
        {
            return false;
        }
    }

    return true;
}

function checkDate()
{
    if (!validateDate())
    {
        document.getElementById("tooltip").style.display = 'block';
      document.getElementById("tooltip").style.opacity = '1';
      document.getElementById('div-result').innerText='';
    }
    else {
        document.getElementById("tooltip").style.display = 'none';
    }
}

function validateName() {
    let meno = document.getElementById('inlineFormInputName1').value;
    if(meno.length>0){
        return true;
    }
    return false;

}