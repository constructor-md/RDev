

window.onload = function(){

    var btn = document.getElementById("login");

    btn.onclick = function() {




    var dlform = document.createElement('form');
    

    dlform.method = 'post';
    dlform.action = '/login';


    var usernameinput = document.getElementById("username");
    var passwordinput = document.getElementById("password");
    var vCode = document.getElementById("phoneCode");


    document.body.appendChild(dlform);
    dlform.appendChild(usernameinput);
    dlform.appendChild(passwordinput);
    dlform.appendChild(vCode);

    dlform.submit();

    //document.body.removeChild(dlform);

}
}
