
var vm = new Vue({
    el: '#app',
    data: {
        
            userManage: false,
            permissionManage: false,
            examManage: false,
            deviceManage: false,
            temManage: false,
            softManage: false,
            faultManage: false,
            deadlineManage: false,

        userInfo: {
                name: '',
                username: '',
                role: '',
            },
        userSearchInfo:{
            jobId:null,
            name:null,
            username:null,
        },

        userData: null,
        permissionData:[
            {
            permission:'管理员',
            userGet:'yes',
            userPost:'no',
            userAdd:'yes',
            userDelete:'no',


            }
        ],
        // deviceData:[
        //     {
        //         deviceId:'cd57x633',
        //         devName:''
        //     }
        // ],
        deadlineDeviceData:[
            {

            }
        ],
        faultData:[
            {

            }
        ],
        // softData:[
        //     {

        //     }
        // ],
        // temData:[
        //     {

        //     }
        // ],
        // deviceExamData:[
        //     {

        //     }
        // ],
        // faultExamData:[
        //     {

        //     }
        // ],
        // sotfExamData:[
        //     {

        //     }
        // ],
        // temExamData:[
        //     {

        //     }
        // ],
    },

    mounted:function(){
      this.getUserInfo();  
    },

    methods:{
        
        doUserManage:function(){
            this.userManage= true,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doPermissionManage:function(){
            this.userManage= false,
            this.permissionManage= true,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doDeviceManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= true,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doExamManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= true,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doFaultManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= true,
            this.deadlineManage= false
        },
        doDeadlineManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= true
        },

        getUserInfo:function(){

            let that = this;

            axios.get("http://localhost:8081/home/userInfo")
            .then(
                function(res){
                    console.log(res);
                    that.userInfo.name = res.data.name;
                    that.userInfo.username = res.data.username;
                    that.userInfo.role = res.data.role;

                },
                function(err){
                    console.log(err)
                }
            )

        },

        searchUser:function(){

            let that = this;

            axios.post("http://localhost:8081/user/get",{
                jobId : that.userSearchInfo.jobId,
                name: that.userSearchInfo.name,
                username:that.userSearchInfo.username
            })
            .then(
                function(res){
                    console.log(res);
                    that.userData = res.data;
   
                    that.userSearchInfo.jobId = null;
                    that.userSearchInfo.name = null;
                    that.userSearchInfo.username = null;
                },
                function(err){
                    console.log(err);
                },
            )

        },

        postUserInfo(){

        },

        deleteUser:function(row){

            let that = this;
            let rowq = row;

            axios.post("http://localhost:8081/user/delete","userId="+row.userId)
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        that.$message({
                            message:'删除成功',
                            type:'success'
                        });

                        rowq = null;

                    }
                    if(res.data.status == "err"){
                        that.$message.error('删除失败');
                    }
                },
                function(err){
                    console.log(res);
                    that.$message.error('操作失败，服务器错误');
                }
            )

        },



        test:function(row){
            console.log(row)
        },

        logout:function(){

            let location = window.location;

            axios.get("http://localhost:8081/logout")
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        location.replace("http://localhost:8081")
                    }
                },
                function(err){
                    console.log(err);
                }
            )

        }


    }
})
