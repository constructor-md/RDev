
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
            userAddInfoShow:false,

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

        userAddInfo:{
            jobId:null,
            name:null,
            phoneNum:null,
            permission:null,

        },
// null
        userData:[
            {
                jobId:'233',
                name:'暖树',
                username:'admin',
                visible:false,
                edit:false,
                loginTime:'2020-04-22 17:06:05'
            },
            {
                jobId:'233',
                name:'灵均',
                username:'normal',
                visible:false,
                edit:false,
                loginTime:'2020-04-22'

            }
        ] ,
        permissionData:[
            {
            permission:'管理员',
            userGet:true,
            userPost:false,
            userAdd:true,
            userDelete:false,
            deviceGet:true,
            devicePost:false,
            deviceAdd:true,
            deviceDelete:false,
            temGet:true,
            temPost:false,
            temAdd:true,
            temDelete:false,
            softGet:true,
            softPost:false,
            softAdd:true,
            softDelete:false,
            faultGet:true,
            faultPost:false,
            faultAdd:true,
            faultDelete:false,
            exam:true,
            visible:false,
            edit:false
            },
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
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false,
            this.permissionManage= true
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

        deleteUser:function(row,index){

            let that = this;
            row.visible = false;
            axios.post("http://localhost:8081/user/delete","userId="+row.userId)
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        //删除当前行
                        that.userData.splice(index,1);
                        that.$message({
                            message:'删除成功',
                            type:'success'
                        });

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


        updateUser:function(row){
            console.log(row)

            let that = this;
            let rowIn = false;

            axios.post("http://localhost:8081/user/post",{
                userId:row.userId,
                jobId:row.jobId,
                name:row.name,
                permission:row.permission,
                phoneNum:row.phoneNum,
                loginTime:row.loginTime
                
            }).then(
                function(res){

                    if(res.data.status == "ok"){
                        that.$message({
                            message:'修改成功',
                            type:'success',
                        });
                        rowIn = false;
                    }
                    if(res.data.status == "err"){
                        that.$message.error('修改失败');
                    }

                },
                function(err){
                    console.log(err);
                }
            )

            if(!rowIn){
                row.edit = false;
            }


        },

        addUser:function(){

            let that = this;

            axios.post("http://localhost:8081",{
                jobId:that.userAddInfo.jobId,
                name:that.userAddInfo.name,
                permission:that.userAddInfo.permission,
                phoneNum:that.userAddInfo.phoneNum,
            })
            .then(
                function(res){

                    if(res.data.status == "ok"){
                        that.$message({
                            message:'新增用户信息成功',
                            type:'success',
                        })
                        that.userAddInfoShow = false;
                    }
                    if(res.data.status == "jobIdNull"){
                        that.$message({
                            message:'工号不可为空',
                            type:'error',
                        })
                    }
                    if(res.data.status == "nameNull"){
                        that.$message({
                            message:'用户名不可为空',
                            type:'error',
                        })
                    }
                    if(res.data.status == "permissionNull"){
                        that.$message({
                            message:'权限角色不存在',
                            type:'error',
                        })
                    }
                    if(res.data.status == "permissionEmpty"){
                        that.$message({
                            message:'权限角色不可为空',
                            type:'error',
                        })
                    }
                    if(res.data.status == "phoneNumErr"){
                        that.$message({
                            message:'手机号格式错误',
                            type:'error',
                        })
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'用户信息新增失败',
                            type:'error',
                        })
                    }

                    
                },
                function(err){
                    console.log(err);
                }
            )

        },

        addUserClose:function(){

            this.userAddInfoShow = false;
            this.userAddInfo.jobId = null;
            this.userAddInfo.name = null;
            this.userAddInfo.permission = null;
            this.userAddInfo.phoneNum = null;

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
