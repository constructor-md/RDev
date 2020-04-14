

var vm = new Vue({
    el: '#app',
    data: {
        user: {
            username: '',
            password: '',
            vCode: null,
            phoneNum:'',
            
        },
        usernameMsg:'',
        passwordMsg:'',
        vCodeMsg:'',


        psInput: false,
        vCodeInput:false,
        loginBtn:false,

    },
    methods:{

        usernameCheck:function(){
            
            let that = this;

            axios.post("http://localhost:8081/login/check","username="+this.user.username)
            .then(
                function(res){
                if(res.data.status == "ok"){
                    that.usernameMsg = '';
                    that.user.phoneNum = res.data.phoneNum;
                    that.psInput = true;
                    that.loginBtn = true;
                }else(
                    that.usernameMsg = res.data.status
                )
                if(res.data.IP == "no"){
                    that.vCodeInput = true;
                }
                //this.$forceUpdate();
                console.log(res);
               
                },
                function(err){

                console.log(err);

                });
 

        },

        doLogin:function(){

            let that = this;
            let location = window.location
            axios.post("http://localhost:8081/login","username="+this.user.username+"&password="+this.user.password+"&vCode="+this.user.vCode)
            .then(
                function(res){

                    if(res.data.status == "ok"){
                        location.replace("http://localhost:8081/page/home.html")
                    }
                    else if(res.data.status == "psNo"){
                        that.passwordMsg = res.data.desc
                    }
                    else if(res.data.status == "vNull"){
                        that.vCodeMsg = res.data.desc
                    }
                    if(res.data.status == "vNo"){
                        that.vCodeMsg = res.data.desc
                    }
         
                    console.log(res);
                },
                function(err){
                    console.log(err)
                }    
            )               
        },

        sendvCode:function(){

            let that = this;

            axios.get("http://localhost:8081/phoneCode")
            .then(function(res){
                if(res.data.status == "send"){
                    that.vCodeMsg = "已向您的手机："+this.user.phoneNum+"发送验证码";
                }
                if(res.data.status == "sendNo"){
                    that.vCodeMsg = "发送失败";
                }
                
            },
            function(err){
                console.log(err);
            })

        },

    },
})



