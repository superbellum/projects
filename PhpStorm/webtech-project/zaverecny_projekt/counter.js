
//code from
//https://jsfiddle.net/e2q8j1q4/55/?fbclid=IwAR2LqLTwAhVXmy2kXk_pjk5h9cf94RQxhaIyAgtX5aMqWGP2rVIZe1JP2wY

var n = localStorage.getItem('on_load_counter');
if (n === null) {
    n = 0;
}

n++;

localStorage.setItem("on_load_counter", n);
document.getElementById('CounterVisitor').innerHTML = "Počet osobných prístupov: " + n.toString();