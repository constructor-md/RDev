var vm = new Vue({
    el: '#app',
    data: {
        register:{
            jobId:'',
            name:'',
            username:'',
            password:'',
            phoneNum:'',
            vCode:'',
        },

        psInput:false,
        vcInput:false,
        resBtn:false,
        checkBtn:true,

        userCheck:'',
        usernameCheck:'',
        vCodeMsg:'',



    },

    methods:{

        registerCheck:function(){

            let that = this;

            

            axios.post("http://localhost:8081/register/check","jobId="+this.register.jobId+"&name="+this.register.name+"&username="+this.register.username)
            .then(
                function(res){
                    console.log(res);
                    
                    if(res.data.status == "ok"){
                        that.userCheck='';
                        that.usernameCheck='';
                        that.vCodeMsg='';
                        that.psInput = true;
                        that.vcInput = true;
                        that.resBtn = true;
                        that.checkBtn = false;
                        that.register.phoneNum = res.data.phoneNum;
                    }
                    if(res.data.status == "userExist"){
                        that.userCheck='';
                        that.usernameCheck='';
                        that.vCodeMsg='';
                        that.userCheck = res.data.desc;
                    }
                    if(res.data.status == "null"){
                        that.userCheck='';
                        that.usernameCheck='';
                        that.vCodeMsg='';
                        that.userCheck = res.data.desc
                    }
                    if(res.data.status == "usernameExist"){
                        that.userCheck='';
                        that.usernameCheck='';
                        that.vCodeMsg='';
                        that.usernameCheck = res.data.desc;
                    }
                },
                function(err){
                    console.log(err);
                }
            )
        },

        doRegister:function(){

            let that = this;

            let location = window.location;

            axios.post("http://localhost:8081/register","username="+this.register.username+"&password="+this.register.password+"&vCode="+this.register.vCode)
            .then(
                function(res){
                    console.log(res);
                    console.log(that.register.vCode)
                    if(res.data.status == "ok"){
                        //注册成功
                        alert("注册成功");
                        location.replace("http://localhost:8081")
                    }
                    if(res.data.status == "err"){
                        alert(res.data.desc);
                    }
                    if(res.data.status == "vNo"){
                        that.vCodeMsg = res.data.desc;
                    }
                    if(res.data.status == "vNull"){
                        that.vCodeMsg = res.data.desc;
                    }

                },
                function(err){
                    console.log(err);
                },
            )

        },


        sendvCode:function(){

            let that = this;

            axios.get("http://localhost:8081/phoneCode")
            .then(function(res){
                if(res.data.status == "send"){
                    that.vCodeMsg = "已向您的手机："+that.register.phoneNum+"发送验证码";
                }
                if(res.data.status == "sendNo"){
                    that.vCodeMsg = "发送失败";
                }
                
            },
            function(err){
                console.log(err);
            })

        },


    }




})