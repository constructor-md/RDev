

var loginArea = new Vue({

    el: "#loginArea",
    data: {
        username: '',
        password: '',
        seen:true,
        vCode:''
    },
    methods: {
        loginCheck: function () {

            axios.post("http://localhost:8081/login/check", "username="+this.username)
                .then(function (res) {
                    alert(res.data);
                    console.log(res);
                }, function (err) {
                    alert(err);
                    console.log(res);
                })

        },
        loginSubmit: function () {
            axios.post("http://localhost:8081/login", "username="+this.username+"&password="+this.password)
                .then(function (res) {
                    alert(res.data);
                    console.log(res);
                }, function (err) {
                    alert(err);
                    console.log(res);
                })

        }
        
    }

})
